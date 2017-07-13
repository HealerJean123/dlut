package cn.edu.dlut.career.controller.company;


import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.dto.company.RecJobPositionDTO;
import cn.edu.dlut.career.dto.company.RecJobPositionDTO2;
import cn.edu.dlut.career.service.company.RecJobPositionCommandService;
import cn.edu.dlut.career.service.company.RecJobPositionQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/27.
 * <p>
 * 招聘职位 增删改
 */
@Controller
@RequestMapping
@Transactional
public class RecJobPositionController {
    @Autowired
    private RecJobPositionQueryService recJobPositionQueryService;
    @Autowired
    private RecJobPositionCommandService recJobPositionCommandService;

    //审核状态
    @ModelAttribute("auditStatus")
    public Map<String,String> auditStatus(){return PubCodeUtil.getDictMap("auditStatus");}

    //学历
    @ModelAttribute("eduDegree")
    public Map<String,String> eduDegree(){return PubCodeUtil.getDictMap("eduDegree");}

    //职位类型
    @ModelAttribute("positionType")
    public Map<String,String> positionType(){return PubCodeUtil.getDictMap("positionType");}

    //职位类别
    @ModelAttribute("jobType")
    public Map<String,String> jobType(){return PubCodeUtil.getDictMap("jobType");}

    //简历接收方式
    @ModelAttribute("receiveMode")
    public Map<String,String> receiveMode(){return PubCodeUtil.getDictMap("receiveMode");}

    //薪资待遇
    @ModelAttribute("salary")
    public Map<String,String> salary(){return PubCodeUtil.getDictMap("salary");}

    //单位性质
    @ModelAttribute("nature")
    public Map<String,String> nature(){return PubCodeUtil.getDictMap("nature");}


    private Logger logger = LoggerFactory.getLogger(RecJobPositionController.class);

    @GetMapping("/student/stuJobDetail")
    public String stuJobDetail(@RequestParam UUID id,Model model){

        RecJobPosition recJobPosition = recJobPositionQueryService.findById(id);

        model.addAttribute("recJobPosition",recJobPosition);

        return "studentHTML/stu_recruitmentJob";
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 学生端 跳转到招聘职位列表页
     */
    @GetMapping("/student/stuRecruitment2")
    public String stuRecruitment2(){return "studentHTML/stu_recruitment2";}

    /**
     * @Autor 史念念 2017/6/6
     * @Description 学生端 动态查询招聘职位信息
     * @param name 职位名
     * @param recName 公司名
     * @param positionType 职位类型
     * @param eduDegree 学历
     * @param major 专业
     * @param nature 单位性质
     * @param publishTime 发布时间
     * @param pageable
     * @return
     */
    @GetMapping("/student/stuQueryByCondition")
    @ResponseBody
    public Page<RecJobPositionDTO2> stuQueryByCondition(String name,
                                                        String recName,
                                                        String positionType,
                                                        String eduDegree,
                                                        String major,
                                                        String nature,
                                                        String publishTime,
                                                        Pageable pageable){
        //准备数据
        if("".equals(name)){
            name = null;
        }
        if("".equals(recName)){
            recName = null;
        }
        if("".equals(positionType)){
            positionType = null;
        }
        if("".equals(eduDegree)){
            eduDegree = null;
        }
        if("".equals(major)){
            major = null;
        }if("".equals(nature)){
            nature = null;
        }
        LocalDateTime pTime = null;
        if(publishTime.equals("01")){
            pTime = LocalDateTime.parse(LocalDate.now().toString()+"T00:00:00");
        }else if(publishTime.equals("02")){
            pTime = LocalDateTime.parse(LocalDate.now().toString()+"T00:00:00").plusWeeks(-1);
        }else if(publishTime.equals("03")){
            pTime = LocalDateTime.parse(LocalDate.now().toString()+"T00:00:00").plusMonths(-1);
        }else{
            pTime = null;
        }

        //调用方法
        Page<RecJobPositionDTO2> recJobPositionDTO2s = recJobPositionQueryService.stuQueryByCondition(name, recName, positionType, eduDegree, major, nature, pTime, pageable);
        return recJobPositionDTO2s;
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 教师端 在详情页面进行招聘职位审核
     * @param id 职位id
     * @param auditStatus 审核状态
     * @param noPassReason 审核不通过原因
     * @return
     */
    @GetMapping("/teacher/teaJobAudit1")
    public String teaJobAudit1(@RequestParam UUID id, String auditStatus, String noPassReason,Model model){

        String result = teaJobAudit(id,auditStatus,noPassReason);

        if("success".equals(result)){
            model.addAttribute("msg","审核状态修改成功");
        }else{
            model.addAttribute("msg","审核状态修改失败");
        }
        RecJobPosition recJobPosition = recJobPositionQueryService.findById(id);
        model.addAttribute("recJobPosition",recJobPosition);

        return "teacherHTML/tea_recruitmentJob";
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 教师端 在职位列表页进行审核通过操作
     * @param
     * @return
     */
    @PostMapping("/teacher/teaJobAudit2")
    @ResponseBody
    public String teaJobAudit2(@RequestParam UUID id ){
        String result = teaJobAudit(id,"01",null);

        if("success".equals(result)){
            return "审核状态修改成功";
        }else{
            return "审核状态修改失败";
        }
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 教师端 批量审核通过招聘职位
     * @param ids
     * @return
     */
    @PostMapping("/teacher/teaMoreJobAudit")
    @ResponseBody
    public String teaMoreJobAudit(String [] ids){
        String result = "";
        for(String id :ids){
            result = teaJobAudit(UUID.fromString(id),"01",null);
        }

        if("success".equals(result)){
            return "批量审核状态修改成功";
        }else{
            return "批量审核状态修改失败";
        }
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 教师端审核招聘职位公共方法
     * @param id
     * @param auditStatus
     * @param noPassReason
     * @return
     */
    public String teaJobAudit(UUID id,String auditStatus,String noPassReason){
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        String auditor = user.getRealName();

        String result = recJobPositionCommandService.updateAudit(id,auditStatus,auditor,LocalDateTime.now(),noPassReason);

        return result;
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 教师端 跳转到职位详情页
     * @param id 招聘职位id
     * @return
     */
    @GetMapping("/teacher/teaJobDetail")
    public String teaRecruitmentJob(@RequestParam UUID id,Model model){
        RecJobPosition recJobPosition = recJobPositionQueryService.findById(id);
        model.addAttribute("recJobPosition",recJobPosition);

        return "teacherHTML/tea_recruitmentJob";
    }

    /**
    * @Autor 史念念
    * @Date 2017/6/2
    * @Description 教师端 跳转到招聘职位列表页
    * @return
    */
    @GetMapping("/teacher/teaRecruitment2")
    public String teaRecruitment(){
        return "teacherHTML/tea_recruitment2";
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 教师端 动态查询招聘职位信息
     * @param name 职位名
     * @param recName 公司名
     * @param auditStatus 审核状态
     * @param positionType 职位类型
     * @param eduDegree 学历
     * @param major 专业
     * @param publishTime 发布时间
     * @param pageable
     * @return
     */
    @GetMapping("/teacher/teaQueryByCondition")
    @ResponseBody
    public Page<RecJobPositionDTO2> teaQueryByCondition(String name,
                                                        String recName,
                                                        String auditStatus,
                                                        String positionType,
                                                        String eduDegree,
                                                        String major,
                                                        String publishTime,
                                                        Pageable pageable){

        //准备数据
        if("".equals(name)){name=null;}
        if("".equals(recName)){recName=null;}
        if("".equals(auditStatus)){auditStatus=null;}
        if("".equals(positionType)){positionType=null;}
        if("".equals(eduDegree)){eduDegree=null;}
        if("".equals(major)){major=null;}
        LocalDateTime pTime = null;
        if(publishTime.equals("01")){
            pTime = LocalDateTime.parse(LocalDate.now().toString()+"T00:00:00");
        }else if(publishTime.equals("02")){
            pTime = LocalDateTime.parse(LocalDate.now().toString()+"T00:00:00").plusWeeks(-1);
        }else if(publishTime.equals("03")){
            pTime = LocalDateTime.parse(LocalDate.now().toString()+"T00:00:00").plusMonths(-1);
        }else{
            pTime = null;
        }

        Page<RecJobPositionDTO2> recJobPositions = recJobPositionQueryService.teaQueryByCondition(name,recName,auditStatus, positionType, eduDegree, major, pTime, pageable);

        return recJobPositions;
    }

    /**
     * 企业端 招聘职位查询页面的跳转
     * @return
     */
    @GetMapping("/company/recruitment2.html")
    public String recruitment(){
        return "companyHTML/recruitment2";
    }

    /**
     * 企业端 跳转到职位添加页面
     * @return
     */
    @GetMapping("/company/recruitmentJob")
    public String recruitmentStep(){
        return "companyHTML/recruitmentJob";
    }

    /**
     * 企业端 修改职位信息
     * @param id 招聘职位id
     * @param name 招聘职位名称
     * @param description 描述
     * @param type 类型
     * @param category 类别
     * @param degree 学历
     * @param major 专业
     * @param recEmail 邮箱
     * @param receiveMode 接收简历方式
     * @param city 城市
     * @param address 详细地址
     * @param recruitmentNum 招聘人数
     * @param salary 薪资
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @PostMapping("/company/updateJob")
    public String updateJob(String id,
                            String name,
                            String description,
                            String type,
                            String category,
                            String degree,
                            String major,
                            String recEmail,
                            String receiveMode,
                            String city,
                            String address,
                            Integer recruitmentNum,
                            String salary,
                            String startTime,
                            String endTime,
                            Model model){

        RecJobPosition recJobPosition = recJobPositionCommandService.updateJob(id,name,description,type,category,degree,major,recEmail,receiveMode,city,address,recruitmentNum,salary,startTime,endTime);

        model.addAttribute("recJobPosition",recJobPosition);
        return "companyHTML/recruitmentDetails2";
    }


    /**
     * 企业端 添加招聘职位
     * @param
     * @return
     */
    @PostMapping("company/RecJob")
    @ResponseBody
    public String saveRecJob(@RequestBody RecJobPositionDTO[] recJobPositionDTOS) {
        List<RecJobPositionDTO> rjd = new ArrayList<RecJobPositionDTO>();
        for(int i=0;i<recJobPositionDTOS.length;i++){
            rjd.add(recJobPositionDTOS[i]);
        }
        String result = recJobPositionCommandService.saveRecJobPosition(rjd);
        String msg = "";
        if("success".equals(result)){
            msg = "职位添加成功";
        }else{
           msg = "职位添加失败.";
        }

        return msg;
    }

    /**
     * 企业端 根据id查找职位信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/company/jobDetail")
    public String findById(@RequestParam UUID id , Model model){

        RecJobPosition recJobPosition = recJobPositionQueryService.findById(id);
        model.addAttribute("recJobPosition",recJobPosition);
        return "companyHTML/recruitmentDetails2";

    }

    /**
     * 企业端根据条件动态查询职位信息
     * @param name
     * @param auditStatus
     * @param positionType
     * @param eduDegree
     * @param publishTime
     * @param pageable
     * @return
     */
    @GetMapping("/company/queryByCondition")
    @ResponseBody
    public Page<RecJobPosition> queryByCondition(String name,
                                                 String auditStatus,
                                                 String positionType,
                                                 String eduDegree,
                                                 String publishTime,
                                                 Pageable pageable){
        //准备数据
        if("".equals(name)){name=null;}
        if("".equals(auditStatus)){auditStatus=null;}
        if("".equals(positionType)){positionType=null;}
        if("".equals(eduDegree)){eduDegree=null;}
        LocalDateTime pTime = null;
        if(publishTime.equals("01")){
            pTime = LocalDateTime.parse(LocalDate.now().toString()+"T00:00:00");
        }else if(publishTime.equals("02")){
            pTime = LocalDateTime.parse(LocalDate.now().toString()+"T00:00:00").plusWeeks(-1);
        }else if(publishTime.equals("03")){
            pTime = LocalDateTime.parse(LocalDate.now().toString()+"T00:00:00").plusMonths(-1);
        }else{
            pTime = null;
        }

        //调用查询方法
        Page<RecJobPosition> recJobPositions = recJobPositionQueryService.findByCondition(name, auditStatus, positionType, eduDegree, pTime, pageable);
        return recJobPositions;
    }
}
