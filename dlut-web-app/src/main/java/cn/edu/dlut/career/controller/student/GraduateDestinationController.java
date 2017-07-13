package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.common.OperateLog;
import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.ReassignApplication;
import cn.edu.dlut.career.service.common.OperateLogService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wei on 2017/4/14.
 */
@Controller
@Transactional
public class GraduateDestinationController {

    @Autowired
    private StudentInfoCommandService studentInfoCommandService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GraduateDestinationCommandService graduateDestinationCommandService;
    @Autowired
    private GraduateDestinationQueryService graduateDestinationQueryService;
    @Autowired
    private ReassignApplicationCommandService reassignApplicationCommandService;
    @Autowired
    private ReassignApplicationQueryService reassignApplicationQueryService;
    @Autowired
    private OperateLogService operateLogService;

    /**
     * 教师添加、修改学生的就业去向
     *
     * @param graduateDestination
     * @return
     */
    @PostMapping("teacher/jobDestinationSave")
    public String addJob(GraduateDestination graduateDestination) {
        graduateDestinationCommandService.save(graduateDestination);
        return "redirect:/teacher/jobAdd?id=" + graduateDestination.getId();
    }

    /**
     * 教师一键派遣所有应届毕业生学生
     *
     * @return
     */
    @PutMapping("teacher/dispatchAll")
    @ResponseBody
    public String dispatchAll(@RequestParam String graduateDate) {
        String result = graduateDestinationCommandService.backToHomeland(graduateDate);
        if("ok".equals(result)){
           //操作日志 一键派遣，这里讲被操作id 设置为 操作人id
            UserPrincipal userPrincipal = (UserPrincipal)SecurityUtils.getSubject().getPrincipal();
            OperateLog operateLog = new OperateLog(userPrincipal.getId(), "派遣相关");
            operateLogService.addOptLog(operateLog, "一键派遣");
            return "已成功派遣所有应届生";
        }else{
            return "一键派遣操作失败";
        }
    }
   /**
     * 学生修改、添加自己的就业去向
     *
     * @param graduateDestination
     * @return
     */
    @PostMapping("student/graDesUpdate")
    public String upJob(GraduateDestination graduateDestination) {
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        GraduateDestination gd = graduateDestinationQueryService.findByStuId(userPrincipal.getId());
        if(gd==null){
            graduateDestinationCommandService.save(graduateDestination);
        }else{
            gd.setRecAddress(graduateDestination.getRecAddress());
            gd.setRecIndustry(graduateDestination.getRecIndustry());
            gd.setRecNature(graduateDestination.getRecNature());
            gd.setRecCode(graduateDestination.getRecCode());
            gd.setJobType(graduateDestination.getJobType());
            gd.setRecLinker(graduateDestination.getRecLinker());
            gd.setRecMobile(graduateDestination.getRecMobile());
            gd.setRecTelphone(graduateDestination.getRecTelphone());
            gd.setPfileToAddress(graduateDestination.getPfileToAddress());
            gd.setPfileLocal(graduateDestination.getPfileLocal());
            gd.setPfileLinker(graduateDestination.getPfileLinker());
            gd.setPfileMobile(graduateDestination.getPfileMobile());
            gd.setHukouToAddress(graduateDestination.getHukouToAddress());
            graduateDestinationCommandService.save(gd);
        }

        return "redirect:/student/JobDestination?stuId=" + userPrincipal.getId();
    }

    @ModelAttribute("graduateTo")
    public Map<String, String> offerStatus() {
        return PubCodeUtil.getDictMap("graduateTo");
    }

    /**
     * 审核状态
     *
     * @return
     */
    @ModelAttribute("auditStatus")
    public Map<String, String> auditStatus() {
        return PubCodeUtil.getDictMap("auditStatus");
    }

    /**
     * 院系
     *
     * @return
     */
    @ModelAttribute("academy")
    public Map<String, String> academy() {
        return PubCodeUtil.getDictMap("academy");
    }

    /**
     * 专业
     *
     * @return
     */
    @ModelAttribute("major")
    public Map<String, String> major() {
        return PubCodeUtil.getDictMap("major");
    }

    /**
     * 学历代码表映射
     *
     * @return
     */
    @ModelAttribute("eduDegree")
    public Map<String, String> eduDegree() {
        return PubCodeUtil.getDictMap("eduDegree");
    }

    /**
     * 工作类型表映射
     *
     * @return
     */
    @ModelAttribute("jobType")
    public Map<String, String> jobType() {
        return PubCodeUtil.getDictMap("jobType");
    }

    /**
     * 单位性质
     *
     * @return
     */
    @ModelAttribute("nature")
    public Map<String, String> nature() {
        return PubCodeUtil.getDictMap("nature");
    }

    /**
     * 单位行业
     *
     * @return
     */
    @ModelAttribute("industry")
    public Map<String, String> industry() {
        return PubCodeUtil.getDictMap("industry");
    }

    //学生确认自己的就业去向信息
    @PostMapping("student/stuStatus")
    public String stuStatus() {
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        graduateDestinationCommandService.update(userPrincipal.getId());
        return "redirect:/student/JobDestination?stuId=" + userPrincipal.getId();
    }


    @GetMapping("student/stu_employment.html")
    public String goEmployment() {
        return "studentHTML/stu_employment";
    }

    @GetMapping("/teacher/job.html")
    public String goJob() {
        return "teacherHTML/tea_job";
    }

    /**
     * 根据学生id查询学生的就业意向
     *
     * @param
     * @return
     */
    @GetMapping("student/JobDestination")
    public ModelAndView findOne(@RequestParam UUID stuId) {
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        String stuNo = userPrincipal.getUserName();
        ModelAndView mv = new ModelAndView();
        GraduateDestination graduateDestination = graduateDestinationQueryService.findByStuId(stuId);

        if(graduateDestination!=null){
            mv.addObject("graduateDestination", graduateDestination);
            if("00".equals(graduateDestination.getStuStatus())){
                mv.setViewName("studentHTML/stu_employment");
            }else {
                mv.setViewName("studentHTML/stu_employment2");
            }
        }else {
            mv.addObject("errorMsg", "派遣信息有误，请联系就业办老师！");
            mv.setViewName("studentHTML/stu_employment");
        }
        return mv;
    }

//    /**
//     * 根据学生id查询学生的派遣信息
//     *
//     * @param
//     * @return
//     */
//    @GetMapping("student/stuDispatch")
//    public ModelAndView findDispatch(@RequestParam UUID stuId) {
//        Subject subject = SecurityUtils.getSubject();
//        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
//        String stuNo = userPrincipal.getUserName();
//        ModelAndView mv = new ModelAndView("studentHTML/stu_dispatch");
//        GraduateDestination graduateDestination = graduateDestinationQueryService.findStuDispatch(stuId);
//
////        ReassignApplication reassignApplication = null;
////        //根据学号查看改派信息
////        reassignApplication = reassignApplicationQueryService.findByStuNo(stuNo);
////        mv.addObject("reassignApplication", reassignApplication);
//        mv.addObject("graduateDestination", graduateDestination);
//        return mv;
//    }

    /**
     * 修改学生的核对状态
     * <p>
     * 当老师结束 学生核对 时 将学生的核对状态改为01 已修改
     *
     * @return
     */
    @PostMapping("teacher/upStuStatus")
    public String upStuStatus() {
        String result = graduateDestinationCommandService.upStuStatus();
        return "";
    }

    /**
     * 教师通过条件筛选学生就业意向
     *
     * @param stuNo
     * @param name
     * @param departmentId
     * @param eduDegree
     * @param endDate
     * @param stuStatus
     * @param majorCode
     * @param recName
     * @param pageable
     * @return
     */
    @GetMapping("teacher/jobDestinationList")
    @ResponseBody
    public Page<GraduateDestination> jobList(String stuNo, String name, String departmentId, String eduDegree, String endDate, String stuStatus, String majorCode, String recName, Pageable pageable) {
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        departmentId = "";
        if (!"00".equals(user.getDepId())) {
            departmentId = user.getDepId();
        }
        Page<GraduateDestination> graduateDestinations = graduateDestinationQueryService.findList(stuNo, name, departmentId, eduDegree, endDate, stuStatus, majorCode, recName, pageable);
        return graduateDestinations;
    }

//    @GetMapping("teacher/jobDestinationList")
//    public ModelAndView jobList(String stuNo, String name, String department, String eduDegree, String endDate, String stuStatus, String majorCode, String recName, Pageable pageable){
//        ModelAndView mv = new ModelAndView();
//        Page<StudentInfo> StudentInfos = studentInfoCommandService.findList(stuNo,name,department,eduDegree,endDate,stuStatus,majorCode,recName,pageable);
//        mv.addObject("StudentInfos",StudentInfos);
//        return mv;
//    }

    @GetMapping("/teacher/jobAdd")
    public ModelAndView job(@RequestParam UUID id) {
        ModelAndView mv = new ModelAndView("teacherHTML/tea_jobAdd");
        GraduateDestination graduateDestination = graduateDestinationQueryService.findByStuId(id);
        mv.addObject("graduateDestination", graduateDestination);
        return mv;
    }







}
