package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.ResponseInfo;
import cn.edu.dlut.career.dto.company.CStudentDTO;
import cn.edu.dlut.career.dto.student.StuLoginDTO;
import cn.edu.dlut.career.dto.student.StudentInfoDTO;
import cn.edu.dlut.career.service.student.StudentInfoCommandService;
import cn.edu.dlut.career.service.student.StudentInfoQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * HealerJean
 */
@Controller
@Transactional
public class StudentInfoController {


    Logger logger = LoggerFactory.getLogger(StudentInfoController.class);
    @Autowired
    private StudentInfoCommandService studentInfoCommandService;
    @Autowired
    private StudentInfoQueryService studentInfoQueryService;
    @Autowired
    private HttpServletRequest request;

    @ModelAttribute
    public void getMap(Map<String, Object> map){
        map.put("allGender",  PubCodeUtil.getDictMap("gender"));
        map.put("allMajor",   PubCodeUtil.getDictMap("major"));
        map.put("allEthnics", PubCodeUtil.getDictMap("ethnic"));
        map.put("allEduMode", PubCodeUtil.getDictMap("eduMode"));
        map.put("allEduYear", PubCodeUtil.getDictMap("eduYear"));
        map.put("allAcademy", PubCodeUtil.getDictMap("academy"));
        map.put("allAuditStatus", PubCodeUtil.getDictMap("auditStatus"));
        map.put("allEduDegree", PubCodeUtil.getDictMap("eduDegree"));
        map.put("allPolitical", PubCodeUtil.getDictMap("political"));
        map.put("allLanguage", PubCodeUtil.getDictMap("language"));
        map.put("allEthnics", PubCodeUtil.getDictMap("ethnic"));

    }


    /**
     * 学生修改学生学籍 ，进行确认
     *
     * @return
     */

    @PostMapping("/student/updateInformation")

    public ModelAndView stuSaveInformation(StudentInfoDTO studentInfoDTO) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        studentInfoDTO.setId(userPrincipal.getId());
        ModelAndView mav = new ModelAndView();
        StudentInfo studentInfo = studentInfoCommandService.updateStudentInfo(studentInfoDTO);

        if (studentInfo != null) {
            //判断是修改还是全部提交
            if (studentInfoDTO.getAllupdateStatus()!=null){ //全部提交
                mav.setViewName("studentHTML/stu_information2");
                mav.addObject("studentInfo", studentInfo);
            }else{ //修改  还是返回刚刚的页面
                mav.setViewName("studentHTML/stu_information");
                mav.addObject("studentInfo", studentInfo);
            }
        } else {
            mav.setViewName("studentHTML/stu_information");
            StudentInfo studentInfoMsg = studentInfoQueryService.findById(userPrincipal.getId());
            mav.addObject("studentInfo", studentInfoMsg);
            mav.addObject("errorMsg", "您已经审核过了，不能再进行修改");
        }
        return mav;
    }

    /**
     * 更新密码
     *
     * @return
     */
    @PostMapping("/student/updatePwd")
    public ModelAndView updatePwd(String pwd, String newPwd) {
        ModelAndView mav = null;
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        StudentInfo studentInfo = studentInfoQueryService.findById(userPrincipal.getId());
        mav = new ModelAndView("studentHTML/stu_index");
        mav.addObject("studentInfo", studentInfo);
        if (newPwd.length()>=6){
            StuLoginDTO stuLoginDTO = studentInfoQueryService.findLoginInfo(userPrincipal.getUserName());
            String pwdStatus = studentInfoQueryService.findPwd(userPrincipal.getId(), pwd.trim(), stuLoginDTO.getPwd(), stuLoginDTO.getSalt());
            if (pwdStatus != null) {
                int falseNo = studentInfoCommandService.updatePwd(userPrincipal.getId(), newPwd, stuLoginDTO.getSalt());
                if (falseNo != 0) {
                    SecurityUtils.getSubject().logout();
                    mav = new ModelAndView("redirect:/login.html");
                    return mav;
                }
            } else {
                mav.addObject("errorMsg", "您输入的密码错误，请重新输入密码");
            }
        }else {
            mav.addObject("errorMsg", "您输入的新密码太短，请重新输入密码");
        }
        return mav;
    }

    /**
     * 教师端
     * 修改学生学籍 ，进行确认
     *
     * @return
     */
    @PostMapping("/teacher/teaUpdateStuInfo")
    public ModelAndView teaUpdateStudentInfo(StudentInfoDTO studentInfoDTO) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        ModelAndView mav = new ModelAndView();

        StudentInfo studentInfo = studentInfoCommandService.teaUpdateStudentInfo(studentInfoDTO, userPrincipal.getDepId());
        if (studentInfo != null) {
            mav.setViewName("teacherHTML/tea_informationDetails");
            mav.addObject("studentInfo", studentInfo);
            mav.addObject("principal",userPrincipal.getPrincipal());
        } else {
            mav.setViewName("teacherHTML/tea_informationDetails");
            mav.addObject("studentInfo", studentInfoDTO);
            mav.addObject("principal",userPrincipal.getPrincipal());
            mav.addObject("errorMsg", "学生未上报或身份错误");
        }
        return mav;
    }


    /**
     * 教师端
     * 审核通过单个学生学籍
     */
    @GetMapping("/teacher/stuInfoAuditPass")
    @ResponseBody
    public String stuInfoAuditPass(@RequestParam UUID id) {
        StudentInfo studentInfo = studentInfoCommandService.teaUpdateStuInfoAuditStatus(id);
        if (studentInfo != null) { //审核通过进入查看页面
            return "审核成功";
        } else {
            return "学生未上报或身份错误";
        }
    }

    /**
     * 教师端
     * 批量审核通过单个学生学籍
     */
    @GetMapping("/teacher/stuInfoBatchAudit")
    @ResponseBody
    public String stuInfoBatchAudit(@RequestParam UUID[] id) {
        List<StudentInfo> studentInfos = studentInfoCommandService.stuInfoBatchAudit(id);
        if (studentInfos!= null) { //审核通过进入查看页面*/
            return "批量审核成功";
        } else {
            return "学生未上报或身份错误";
        }

    }


    /**
     * query
     */



    /**
     * 学生学籍信息查看 页面
     * @return
     */
    @GetMapping("/student/stuInformation.html")
    public ModelAndView stuInformation(){
        Subject currentUser = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal)currentUser.getPrincipal();;
        StudentInfo studentInfo = studentInfoQueryService.findById(userPrincipal.getId());
        ModelAndView mav =new ModelAndView();
        if (studentInfo.getHaveReport().equals("0")){
            mav.setViewName("studentHTML/stu_information");
        }else {
            mav.setViewName("studentHTML/stu_information2");
        }
        mav.addObject("studentInfo", studentInfo);
        return mav;
    }


    /**
     * 查看表中是否已经存在用户输入的密码
     * @return
     */
    @GetMapping("/student/findPwd")
    @ResponseBody
    public ResponseInfo findByPwd(String pwd){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        StuLoginDTO stuLoginDTO = studentInfoQueryService.findLoginInfo(userPrincipal.getUserName());
        String pwdStatus = studentInfoQueryService.findPwd( userPrincipal.getId(),  pwd,  stuLoginDTO.getPwd(), stuLoginDTO.getSalt());
        ResponseInfo responseInfo = new ResponseInfo();

        if(pwdStatus != null){
            responseInfo.setStatus(200);
            responseInfo.setMessage("密码存在");
        }else {
            responseInfo.setStatus(403);
            responseInfo.setMessage("密码不存在");
        }
        return responseInfo;
    }


    /**
     * 张宇晋
     * 教师端，院系生源显示页面
     * @return
     */
    @GetMapping("/teacher/teaEnrollment.html")
    public String departStusHtml(){
        return "teacherHTML/tea_enrollment";
    }

    /**
     * 张宇晋
     * 教师端，本院系生源信息审核页面
     * @return
     */
    @GetMapping("/teacher/teaDepartStusAudit.html")
    public String departStusAudit(){
        return "teacherHTML/tea_enrollment2";
    }





    /**
     * 教师端
     * 分页查询 学生学籍信息
     */
    @GetMapping("/teacher/listDepartStuPage")
    @ResponseBody
    public Page<StudentInfo> listDepartStuPage(String name, String stuNo, String gender, String ethnic, String departmentId, String majorCode, String eduYear, String eduMode, String endDate, String tutorName, String status, String eduDegree , Pageable pageable) {
        Page<StudentInfo> StudentInfos = studentInfoQueryService.listDepartStuPage(name, stuNo, gender, ethnic, departmentId, majorCode, eduYear, eduMode, endDate, tutorName, status, eduDegree,pageable);
        if (StudentInfos!=null) {
            return  StudentInfos;
        }else {
            return  null;
        }
    }

    /**
     * 教师端 (校级可以随意查看，修改)
     * 分页查询 某个学院的学生学籍 将来用于修改学生学籍
     */
    @GetMapping("/teacher/listMyDepartStuPage")
    @ResponseBody
    public   Page<StudentInfo> listMyDepartStuPage(String name, String stuNo, String gender, String ethnic, String majorCode, String  departmentId ,String eduYear, String eduMode, String endDate, String tutorName, String status, String eduDegree , String haveReport, Pageable pageable) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        logger.info("院系id"+departmentId);
        if (!(userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN"))) {
            departmentId = userPrincipal.getDepId();
        }
        Page<StudentInfo> StudentInfos = studentInfoQueryService.listMyDepartStuPage(name, stuNo, gender, ethnic, majorCode,  departmentId,eduYear, eduMode, endDate, tutorName, status, eduDegree,haveReport, pageable);
        if (StudentInfos!=null) {
            return  StudentInfos;
        }else {
            return  null;
        }
    }
    /**
     * 教师端
     * 查看某个学生的学籍信息 ，仅仅是查看
     */
    @GetMapping("/teacher/stuInformation")
    public   ModelAndView stuInfos(@RequestParam UUID id) {
        StudentInfo studentInfo = studentInfoQueryService.findById(id);
        ModelAndView mav = new ModelAndView("teacherHTML/tea_informationDetails2");
        mav.addObject("studentInfo", studentInfo);
        return  mav;
    }


    /**
     * 教师端
     * 查看某个学生的学籍信息 ，并将来对其进行修改
     */
    @GetMapping("/teacher/editStuInformation")
    public   ModelAndView editStuInformation( String id) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        UUID sid = UUID.fromString(id);
        StudentInfo studentInfo = studentInfoQueryService.findById(sid);
        ModelAndView mav = new ModelAndView("teacherHTML/tea_informationDetails");
        mav.addObject("principal",userPrincipal.getPrincipal());
        if (userPrincipal.getDepId().equals(studentInfo.getDepartmentId())||userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN")) {
            mav.addObject("studentInfo", studentInfo);
            return mav;
        }else {
            mav.addObject("studentInfo", studentInfo);
            mav.addObject("errorMsg","您只能修改本院系的学生学籍");
            return mav;
        }
    }



    /**
    * @author wei  2017/5/27
    * @method findDynamic
    * @param   stuNo 学号, mobilephone 电话, name 姓名
    * @return java.util.List<cn.edu.dlut.career.dto.company.CStudentDTO>
    * @description  企业端  查询生源信息
    */
    @GetMapping("/company/findDynamic")
    @ResponseBody
    public List<CStudentDTO> findDynamic(String stuNo, String mobilephone, String name){
        List<CStudentDTO> students = studentInfoQueryService.findDynamic(stuNo,mobilephone,name);
        return students;
    }

    /**
     * @author wei  2017/5/27
     * @method findOne
     * @param id
     * @return org.springframework.web.servlet.ModelAndView
     * @description 公司查看学生个人信息
     */
    @GetMapping("/company/findStuOne")
    public ModelAndView findStuOne(@RequestParam UUID id){
        ModelAndView mv = new ModelAndView("/companyHTML/informationDetails");
        StudentInfo studentInfo = studentInfoQueryService.findById(id);
        mv.addObject("studentInfo",studentInfo);
        return mv;
    }

    /**
     * 页面跳转到生源查询页面
     */
    @GetMapping("/company/enrollment.html")
    public String toEnrollment(){
        return "/companyHTML/enrollment";
    }
}

