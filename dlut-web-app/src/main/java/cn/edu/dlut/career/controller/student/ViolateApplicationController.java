package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.company.RecOffer;
import cn.edu.dlut.career.domain.student.ReassignApplication;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.domain.student.ViolateApplication;
import cn.edu.dlut.career.dto.ResponseInfo;
import cn.edu.dlut.career.dto.student.ViolateApplicationDTO;
import cn.edu.dlut.career.service.company.RecOfferQueryService;
import cn.edu.dlut.career.service.student.*;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * 违约申请
 * Created by HealerJean on 2017/4/14.
 */
@Controller
public class ViolateApplicationController {
    @Autowired
    private StudentInfoCommandService studentInfoCommandService;
    @Autowired
    private StudentInfoQueryService studentInfoQueryService;
    @Autowired
    private ViolateApplicationCommandService violateApplicationCommandService;
    @Autowired
    private ViolateApplicationQueryService violateApplicationQueryService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RecOfferQueryService recOfferQueryService;
    @Autowired
    private ReassignApplicationCommandService reassignApplicationCommandService;
    @Autowired
    private ReassignApplicationQueryService reassignApplicationQueryService;
    @ModelAttribute
    public void getMap(Map<String, Object> map){
        map.put("totalAuditStatus", PubCodeUtil.getDictMap("totalAuditStatus"));
        map.put("allViolateReason", PubCodeUtil.getDictMap("violateReason"));
        map.put("academy", PubCodeUtil.getDictMap("academy"));
        map.put("auditStatus", PubCodeUtil.getDictMap("auditStatus"));

    }



    //违约申请页面
    @GetMapping("/student/agreementApply.html")
    public ModelAndView violateApplication(){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        ViolateApplication violateApplicationStatus = violateApplicationQueryService.findByStuNo(userPrincipal.getUserName());
        StudentInfo studentInfo = studentInfoQueryService.findById(userPrincipal.getId());
        ModelAndView mav=null;
        RecOffer recOffer = recOfferQueryService.findRecOfferByStuNo(userPrincipal.getUserName());
        ReassignApplication reassignApplication =reassignApplicationQueryService.findByStuNo(userPrincipal.getUserName());
     //改派通過才能違約
      /*  if(reassignApplication!=null&&"02".equals(reassignApplication.getAuditStatus())){
*/
            if (recOffer!=null) {
                if (violateApplicationStatus != null) {
                    mav = new ModelAndView("studentHTML/stu_agreement_apply");
                    mav.addObject("studentInfo", studentInfo);
                    mav.addObject("errorMsg", "您已经申请过了,不能重复申请");
                } else {
                    mav = new ModelAndView("studentHTML/stu_agreement_apply");
                    mav.addObject("studentInfo", studentInfo);
                }
            }else {
                mav = new ModelAndView("studentHTML/stu_offer");
                mav.addObject("errorMsg", "请先接受一个Offer");
            }
        /*}else{
            mav = new ModelAndView("studentHTML/stu_agreement_apply");
            mav.addObject("studentInfo", studentInfo);
            mav.addObject("errorMsg", "请先申请改派并通过之后再申请违约");
        }*/

        return mav;
    }

    //违约申请添加
    /**
     * @param violateApplication
     * @return
     */
    @PostMapping("/student/addviolateApplication")
    public ModelAndView saveviolateApplication(ViolateApplication violateApplication){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        RecOffer recOffer = recOfferQueryService.findRecOfferByStuNo(userPrincipal.getUserName());
        StudentInfo studentInfo = studentInfoQueryService.findById(userPrincipal.getId());

        ModelAndView mav=null;
        ReassignApplication reassignApplication = reassignApplicationQueryService.findByStuNo(userPrincipal.getUserName());

            if(recOffer!=null) {
                 ViolateApplication violateApplicationStatus = violateApplicationCommandService.saveViolateApply(violateApplication);
                 if (violateApplicationStatus != null) {
                     mav = new ModelAndView("studentHTML/stu_agreement_look");
                     mav.addObject("violateApplication", violateApplication);
                 } else {
                     mav = new ModelAndView("studentHTML/stu_agreement_apply");
                     mav.addObject("studentInfo", studentInfo);
                     mav.addObject("errorMsg", "您已经申请过了,不能重复申请");
                 }
             }else {
                 mav = new ModelAndView("studentHTML/stu_offer");
                 mav.addObject("errorMsg", "请先接受一个Offer");
             }


        return mav;
    }

    /**
     * 老师在详情页修改违约申请审核状态
     * 教师类型 SCHOOL普校 SCHADMIN校管 ACADEMY普院 ACADMIN院管
     * @param id 违约申请id
     * @param auditStatus 审核状态
     * @param noPassReason 审核不通过原因
     * @return
     */
    @GetMapping("/teacher/updateVioAudit1")
    public ModelAndView updateAudit1(String id,
                                    String auditStatus,
                                    String noPassReason){
        ModelAndView mv = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        //教师身份
        String principal = userPrincipal.getPrincipal();

        //若是院系老师返回院系老师页面
        if ("ACADEMY".equals(principal) || "ACADMIN".equals(principal)) {
            mv.setViewName("teacherHTML/tea_absent");
        }
        //若是校级老师返回校级老师页面
        else if("SCHOOL".equals(principal) || "SCHADMIN".equals(principal)){
            mv.setViewName("teacherHTML/tea_absent2");
        }

        UUID vid = UUID.fromString(id);
        String result = updateVioAudit(vid,auditStatus,noPassReason);

        if("ok".equals(result)){
            mv.addObject("msg","审核状态修改成功");
        }else{
            mv.addObject("msg","审核状态修改失败");
        }
        ViolateApplicationDTO vad = violateApplicationQueryService.findByVioId(vid);
        mv.addObject("vad", vad);
        return mv;
    }

    /**
     * 老师在列表页修改违约申请审核状态
     * @param id
     * @return
     */
    @PostMapping("/teacher/updateVioAudit2")
    @ResponseBody
    public String updateAudit2(String id){
        UUID vid = UUID.fromString(id);
        String result = updateVioAudit(vid, "01", null);
        if ("ok".equals(result)) {
                return "审核状态修改成功";
        } else {
                return "审核状态修改失败";
        }

    }

    /**
     * 违约审核公共方法
     * 将该方法单独摘出来原因是：
     * 在详情页面中请求审核后返回的是ModelAndView，在列表页面中为ajax请求返回的是json
     * @param id
     * @param auditStatus
     * @param noPassReason
     * @return
     */
    public String updateVioAudit(UUID id,
                                 String auditStatus,
                                 String noPassReason){

        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        //教师身份
        String principal = userPrincipal.getPrincipal();
        //教师姓名
        String auditor = userPrincipal.getRealName();

        //院校审核人、审核状态、审核不通过原因
        String departAuditor = null;
        String departAuditStatus=null;
        String departNoPassReason=null;
        String schAuditor = null;
        String schAuditStatus=null;
        String schNoPassReason=null;
        //根据教师身份类型 赋值
        if("ACADEMY".equals(principal) || "ACADMIN".equals(principal)){
            departAuditor = auditor;
            departAuditStatus = auditStatus;
            departNoPassReason = noPassReason;
        }else if("SCHOOL".equals(principal) || "SCHADMIN".equals(principal)){
            schAuditor = auditor;
            schAuditStatus = auditStatus;
            schNoPassReason = noPassReason;
        }
        String result = violateApplicationCommandService.updateAudit(id,departAuditStatus,departNoPassReason,departAuditor,schAuditStatus,schNoPassReason,schAuditor);

        return result;
    }

    /**
     * 教师端 批量审核通过 违约申请
     * @param ids
     * @return
     */
    @Transactional
    @PostMapping("/teacher/updateMoreVioAudit")
    @ResponseBody
    public String updateMoreVioAudit(String[] ids){

        String result="";
        //循环 修改审核信息
        for(int i=0;i<ids.length;i++){
            UUID vid = UUID.fromString(ids[i]);

            result = updateVioAudit(vid,"01",null);
        }
        if("ok".equals(result)){
            return "审核状态修改成功";
        }else{
            return "审核状态修改失败";
        }
    }

    /**
     * query
     */

    /**
     * 页面的跳转
     *
     * @return
     */
    @GetMapping("/teacher/tea_absentReview.html")
    public String toTea_absentReview() {
        return "teacherHTML/tea_absentReview";
    }

    /**
     * 教师端详情页面的跳转
     *
     * @return
     */
    @GetMapping("/teacher/teaAbsent.html")
    public ModelAndView toTea_absent(String vid) {
        UUID id = UUID.fromString(vid);
        ModelAndView mv = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        //教师身份
        String principal = userPrincipal.getPrincipal();

        //若是院系老师返回院系老师页面
        if ("ACADEMY".equals(principal) || "ACADMIN".equals(principal)) {
            mv.setViewName("teacherHTML/tea_absent");
        }
        //若是校级老师返回校级老师页面
        else if("SCHOOL".equals(principal) || "SCHADMIN".equals(principal)){
            mv.setViewName("teacherHTML/tea_absent2");
        }

        ViolateApplicationDTO vad = violateApplicationQueryService.findByVioId(id);

        mv.addObject("vad", vad);
        return mv;
    }

    /**
     * 动态查询 违约申请
     * 该方法用于教师端 对违约申请的查询
     * 教师类型 SCHOOL普校 SCHADMIN校管 ACADEMY普院 ACADMIN院管
     * @param stuNo           学号
     * @param stuName         姓名
     * @param departmentId      院系id
     * @param majorName       专业
     * @param applicationTime 申请时间
     * @param auditStatus     审核状态
     * @return
     */
    @GetMapping("/teacher/findVio")
    @ResponseBody
    public Page<ViolateApplicationDTO> findByKey(String stuNo,
                                                 String stuName,
                                                 String departmentId,
                                                 String majorName,
                                                 String applicationTime,
                                                 String auditStatus,
                                                 Pageable pageable) {

        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        //如果学号为空 ，stuNo值设为null 下同
        if ("".equals(stuNo)) {
            stuNo = null;
        }
        if ("".equals(stuName)) {
            stuName = null;
        }
        if ("".equals(majorName)) {
            majorName = null;
        }
        if("".equals(departmentId)){
            departmentId = null;
        }
        if("".equals(auditStatus)){
            auditStatus = null;
        }

        if ("3".equals(applicationTime) || "0".equals(applicationTime)) {
            //查询所有
            startTime = null;
            endTime = null;
        } else if ("1".equals(applicationTime)) {
            //查询一周以内的违约申请
            startTime = LocalDateTime.now().plusWeeks(-1);
            endTime = LocalDateTime.now();
        } else if ("2".equals(applicationTime)) {
            //查询一月以内的违约申请
            startTime = LocalDateTime.now().plusMonths(-1);
            endTime = LocalDateTime.now();
        }

        Page<ViolateApplicationDTO> vads = violateApplicationQueryService.findByKey(stuNo, stuName, departmentId, majorName, startTime, endTime, auditStatus, pageable);

        return vads;
    }

    /**
     * 违约详情
     *
     * @return
     */
    @GetMapping("/student/violateApplicationInfo")
    public ModelAndView violateApplicationInfo() {
        UserPrincipal userPrincipal = (UserPrincipal)  SecurityUtils.getSubject().getPrincipal();
        ViolateApplication violateApplication = violateApplicationQueryService.findByStuNo(userPrincipal.getUserName());
        ModelAndView mav = new ModelAndView("studentHTML/stu_agreement_look");
        mav.addObject("violateApplication", violateApplication);
        return mav;
    }

    /**
     * 张宇晋
     * 查看 学生是不是已经具有违约申请表了
     *
     * @return
     */
    @GetMapping("/student/haveViolateApplication")
    @ResponseBody
    public ResponseInfo haveViolateApplication() {
        UserPrincipal userPrincipal = (UserPrincipal)  SecurityUtils.getSubject().getPrincipal();
        ViolateApplication violateApplication = violateApplicationQueryService.findByStuNo(userPrincipal.getUserName());
        ResponseInfo responseInfo = new ResponseInfo();
        if (violateApplication != null) {
            responseInfo.setStatus(1);
            responseInfo.setMessage("已经存在违约申请位，不可以再次申请");
        } else {
            responseInfo.setStatus(0);
            responseInfo.setMessage("违约申请不存在，可以申请");
        }
        return responseInfo;
    }

}
