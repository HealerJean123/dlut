package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.student.BlankProtocol;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.service.student.BlankProtocolCommandService;
import cn.edu.dlut.career.service.student.BlankProtocolQueryService;
import cn.edu.dlut.career.service.student.StudentInfoCommandService;
import cn.edu.dlut.career.service.student.StudentInfoQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by 史念念 on 2017/4/25.
 * <p>
 * 空白协议书 增删改
 */
@Controller
public class BlankProtocolController {


    @Autowired
    private StudentInfoCommandService studentInfoCommandService;
    @Autowired
    private StudentInfoQueryService studentInfoQueryService;
    @Autowired
    private BlankProtocolCommandService blankProtocolCommandService;
    @Autowired
    private BlankProtocolQueryService blankProtocolQueryService;

    @ModelAttribute("academy")
    public Map<String, String> academy() {
        return PubCodeUtil.getDictMap("academy");
    }
    @ModelAttribute("blankProReason")
    public Map<String, String> blankProReason() {
        return PubCodeUtil.getDictMap("blankProReason");
    }
    @ModelAttribute("totalAuditStatus")
    public Map<String, String> totalAuditStatus() {
        return PubCodeUtil.getDictMap("totalAuditStatus");
    }

    /**
     * 教师端详情页 空白协议书申请审核状态修改
     *
     * @param id
     * @param auditStatus
     * @param noPassReason
     * @return
     */
    @GetMapping("teacher/updateBlankAudit1")
    public ModelAndView updateAudit1(String id, String auditStatus, String noPassReason) {
        ModelAndView mv = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        //教师身份
        String principal = userPrincipal.getPrincipal();

        UUID bid = UUID.fromString(id);

        String result = updateBlankAudit(bid, auditStatus, noPassReason);
        BlankProtocol b = blankProtocolQueryService.findById(bid);
        if ("ok".equals(result)) {
            if ("ACADEMY".equals(principal) || "ACADMIN".equals(principal)) {
                mv.setViewName("teacherHTML/tea_blankBookDetails");
                b.setDepartAuditStatus(auditStatus);
                b.setDepartNoPassReason(noPassReason);
            }else if("SCHOOL".equals(principal) || "SCHADMIN".equals(principal)){
                mv.setViewName("teacherHTML/tea_blankBookDetails2");
                b.setSchAuditStatus(auditStatus);
                b.setSchNoPassReason(noPassReason);
            }
            mv.addObject("msg", "审核状态修改成功");

        } else if ("fail".equals(result)) {
            mv.addObject("msg", "审核状态修改失败");
        }


        mv.addObject("bp", b);

        return mv;
    }

    /**
     * 教师端列表页 空白协议书申请审核状态修改
     *
     * @param id
     * @return
     */
    @PostMapping("teacher/updateBlankAudit2")
    @ResponseBody
    public String updateAudit2(@RequestParam UUID id) {
        BlankProtocol bp = blankProtocolQueryService.findById(id);
        int status = Integer.parseInt(bp.getAuditStatus());
        String result = updateBlankAudit(id, "01", null);
        if ("ok".equals(result)) {
            return "审核状态修改成功";
        } else {
            return "审核状态修改失败";
        }

    }

    /**
     * 批量审核通过 空白协议书申请
     *
     * @param ids
     * @return
     */
    @PostMapping("teacher/updateMoreBlankAudit")
    @ResponseBody
    public String updateMoreBlankAudit(@RequestParam UUID[] ids) {
        String result = "";
        for (int i = 0; i < ids.length; i++) {
            UUID id = ids[i];
            result = updateBlankAudit(id, "01", null);
        }

        if ("ok".equals(result)) {
            return "审核状态修改成功";
        } else {
            return "审核状态修改失败";
        }
    }

    /**
     * 单个空白协议申请审核公共方法
     * 将该方法单独摘出来原因是：
     * 在详情页面中请求审核后返回的是ModelAndView，在列表页面中为ajax请求返回的是json
     *
     * @param id
     * @param auditStatus
     * @param noPassReason
     * @return
     */
    public String updateBlankAudit(UUID id, String auditStatus, String noPassReason) {
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        //教师身份
        String principal = userPrincipal.getPrincipal();
        //教师姓名
        String auditor = userPrincipal.getRealName();

        //院校审核人、审核状态、审核不通过原因
        String departAuditor = null;
        String departAuditStatus = null;
        String departNoPassReason = null;
        String schAuditor = null;
        String schAuditStatus = null;
        String schNoPassReason = null;
        //根据教师身份类型 赋值
        if ("ACADEMY".equals(principal) || "ACADMIN".equals(principal)) {
            departAuditor = auditor;
            departAuditStatus = auditStatus;
            departNoPassReason = noPassReason;
        } else if ("SCHOOL".equals(principal) || "SCHADMIN".equals(principal)) {
            schAuditor = auditor;
            schAuditStatus = auditStatus;
            schNoPassReason = noPassReason;
        }

        String result = blankProtocolCommandService.updateAudit(id, departAuditStatus, departAuditor, departNoPassReason, schAuditStatus, schAuditor, schNoPassReason);

        return result;
    }

    /**
     * 添加 空白协议书
     *
     * @param applicationReason        申请理由
     * @param applicationReasonRemarks 申请理由备注
     * @return
     */
    @PostMapping("/student/saveBPro")
    @ResponseBody
    public Map<String, String> savePro(String applicationReason, String applicationReasonRemarks) {

        Map<String, String> map = new HashMap();
        Subject subject = SecurityUtils.getSubject();

        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        UUID stuId = userPrincipal.getId();

        StudentInfo student = studentInfoQueryService.findById(stuId);
        String result = blankProtocolCommandService.saveBPro(student, applicationReason, applicationReasonRemarks);
        if ("success".equals(result)) {
            map.put("msg", "申请成功，请耐心等待学校审核");
            map.put("url", "/app/student/stuBlankBook");
        } else if ("fail".equals(result)) {
            map.put("msg", "申请失败，请重新操作");
            map.put("url", "");
        }

        return map;
    }


    /**
     *  query
     */



    /**
     *该方法用于教师端 动态查询空白协议书
     * @param stuNo 学生学号 要求精确
     * @param name 学生姓名 可模糊
     * @param auditStatus 审核状态
     * @param departmentId 院系
     * @param pageable
     * @return
     */
    @GetMapping("/teacher/findBlankProtocol")
    @ResponseBody
    public Page<BlankProtocol> findBlankProtocol(String stuNo, String name, String auditStatus, String departmentId, Pageable pageable){

        if("".equals(stuNo)){
            stuNo = null;
        }
        if("".equals(name)){
            name = null;
        }
        if("".equals(auditStatus)){
            auditStatus = null;
        }
        if("".equals(departmentId)){
            departmentId = null;
        }

        Page<BlankProtocol> bps = blankProtocolQueryService.findBlankProtocol(stuNo,name,auditStatus,departmentId,pageable);
        return bps;
    }

    /**
     * 该方法用于教师端 老师进入空白协议书页面
     * @return
     */
    @GetMapping("/teacher/teaBlankBook")
    public String teaBlankBook(){ return "teacherHTML/tea_blankBook";}

    /**
     * 教师端 根据id查找空白协议书 进入教师空白协议书详情页面
     * @return
     */
    @GetMapping("/teacher/blankBookLook")
    public ModelAndView findBlankPro(@RequestParam UUID bid) {
        ModelAndView mv = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        //教师身份
        String principal = userPrincipal.getPrincipal();

        //若是院系老师返回院系老师页面
        if ("ACADEMY".equals(principal) || "ACADMIN".equals(principal)) {
            mv.setViewName("teacherHTML/tea_blankBookDetails");
        }
        //若是校级老师返回校级老师页面
        else if("SCHOOL".equals(principal) || "SCHADMIN".equals(principal)){
            mv.setViewName("teacherHTML/tea_blankBookDetails2");
        }

        BlankProtocol b = blankProtocolQueryService.findById(bid);

        mv.addObject("bp",b);
        return mv;
    }

    /**
     * 跳转到申请空白协议书页面
     * @return
     */
    @GetMapping("/student/stuBlankBookNew")
    public String goBlankBookNew() {
        return "studentHTML/stu_blankBookNew";
    }


    /**
     * 学生端根据id查找空白协议书 跳转到学生端空白协议书详情页
     * @return
     */
    @GetMapping("/student/blankBookLook")
    public ModelAndView findBPro(@RequestParam UUID bid) {
        ModelAndView mv = new ModelAndView("studentHTML/stu_blankBookLook");

        BlankProtocol b = blankProtocolQueryService.findById(bid);

        mv.addObject("bp",b);
        return mv;
    }

    /**
     * 页面跳转到 空白协议书页面
     * @return
     */
    @GetMapping("/student/stuBlankBook")
    public ModelAndView stuBlankBook(){
        ModelAndView mv = new ModelAndView("studentHTML/stu_blankBook");
        Subject subject = SecurityUtils.getSubject();

        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        UUID stuId = userPrincipal.getId();
        List<BlankProtocol> bps = blankProtocolQueryService.findByStuId(stuId);

        mv.addObject("bps",bps);
        return mv;
    }

}
