package cn.edu.dlut.career.controller.company;

import cn.edu.dlut.career.domain.company.RecBulletin;
import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.dto.company.RecBulletinDTO;
import cn.edu.dlut.career.dto.company.RecBulletinDTO2;
import cn.edu.dlut.career.dto.company.RecJobPositionDTO;
import cn.edu.dlut.career.service.company.RecBulletinCommandService;
import cn.edu.dlut.career.service.company.RecBulletinQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/7.
 *
 * 招聘简章 增删改
 */
@Controller
@RequestMapping
@Transactional
public class RecBulletinController {

    @Autowired
    private RecBulletinQueryService recBulletinQueryService;
    @Autowired
    private RecBulletinCommandService  recBulletinCommandService;

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

    /**
     * @Autor 史念念 2017/6/6
     * @Description 学生端查看招聘简章详情
     * @param id
     * @return
     */
    @GetMapping("/student/stuBulletinDetail")
    public String stuBulletinDetail(@RequestParam UUID id,Model model){
        RecBulletin recBulletin = recBulletinQueryService.findById(id);

        model.addAttribute("recBulletin",recBulletin);

        return "studentHTML/stu_recruitmentTxt";
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 学生端 跳转到招聘简章列表页
     */
    @GetMapping("/student/stuRecruitment")
    public String stuRecruitment(){return "studentHTML/stu_recruitment";}

    /**
     * @Autor 史念念 2017/6/5
     * @Description 学生端动态查询招聘简章
     * @param publishTime 发布日期
     * @param title 标题
     * @param recName 公司名
     * @param nature 公司性质
     * @param pageable
     * @return
     */
    @GetMapping("/student/stuFindBulletin")
    @ResponseBody
    public Page<RecBulletinDTO2> stuFindBulletin(String publishTime,
                                                 String title,
                                                 String recName,
                                                 String nature,
                                                 Pageable pageable){
        if("".equals(title)){
            title=null;
        }
        if("".equals(recName)){
            recName=null;
        }
        if("".equals(nature)){
            nature=null;
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

        Page<RecBulletinDTO2> recBulletinDTO2s = recBulletinQueryService.stuFindBulletin(pTime,title,recName,nature,pageable);

        return recBulletinDTO2s;
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 教师端 详情页 审核招聘简章
     * @param id 招聘简章id
     * @param auditStatus 审核状态 01 通过 02 不通过
     * @param noPassReason 不通过原因
     * @return
     */
    @GetMapping("/teacher/teaBulletinAudit")
    public String teaBulletinAudit1(@RequestParam UUID id,String auditStatus,String noPassReason,Model model){
        String result = teaBulletinAudit(id,auditStatus,noPassReason);

        if("success".equals(result)){
            model.addAttribute("msg","审核状态修改成功");
        }else{
            model.addAttribute("msg","审核状态修改失败");
        }
        RecBulletin recBulletin = recBulletinQueryService.findById(id);
        model.addAttribute("recBulletin",recBulletin);
        return "teacherHTML/tea_recruitmentTxt";
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 教师端 列表页 单个审核通过
     * @param id 招聘简章id
     * @param model
     * @return
     */
    @PostMapping("/teacher/teaBulletinPass")
    @ResponseBody
    public String teaBulletinAudit2(@RequestParam UUID id,Model model){
        String result = teaBulletinAudit(id,"01",null);
        if("success".equals(result)){
            return "审核状态修改成功";
        }else{
            return "审核状态修改失败";
        }

    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 教师端 批量审核通过招聘简章
     * @param ids
     * @param model
     * @return
     */
    @PostMapping("/teacher/teaMoreBulletinAudit")
    @ResponseBody
    public String teaMoreBulletinAudit(String[] ids,Model model){
        String result ="";
        for(String id :ids){
            result = teaBulletinAudit(UUID.fromString(id),"01",null);
        }
        if("success".equals(result)){
            return "批量审核状态修改成功";
        }else{
            return "批量审核状态修改失败";
        }

    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 招聘简章审核公共方法
     * @param id 招聘简章id
     * @param auditStatus 审核状态
     * @param noPassReason 不通过原因
     * @return
     */
    public String teaBulletinAudit(UUID id,String auditStatus,String noPassReason){
        UserPrincipal user = (UserPrincipal)SecurityUtils.getSubject().getPrincipal();
        String auditor = user.getRealName();

        String result = recBulletinCommandService.teaBulletinAudit(id,auditor,auditStatus,noPassReason);

        return result;
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 教师端 跳转到招聘简章详情页
     * @param id 招聘简章id
     * @return
     */
    @GetMapping("/teacher/teaBulletinDetail")
    public String teaRecruitmentTxt(@RequestParam UUID id,Model model){

        RecBulletin recBulletin = recBulletinQueryService.findById(id);
        model.addAttribute("recBulletin",recBulletin);

        return "teacherHTML/tea_recruitmentTxt";
    }

    /**
     * @Autor 史念念
     * @Date 2017/6/2
     * @Description 教师端跳转到招聘简章列表页
     * @return
     */
    @GetMapping("/teacher/teaRecruitment")
    public String teaRecruitment(){return "teacherHTML/tea_recruitment";}

    /**
     * @Autor 史念念
     * @Date 2017/6/2
     * @Description 教师端 动态查询招聘简章
     * @param publishTime 发布时间
     * @param auditStatus 审核状态
     * @param title 标题
     * @param recName 公司名
     * @return
     */
    @GetMapping("/teacher/teaFindBulletin")
    @ResponseBody
    public Page<RecBulletinDTO2> teaFindBulletin(String publishTime,
                                                 String auditStatus,
                                                 String title,
                                                 String recName,
                                                 Pageable pageable){
        //准备数据
        if("".equals(title)){
            title=null;
        }
        if("".equals(auditStatus)){
            auditStatus=null;
        }
        if("".equals(recName)){
            recName=null;
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

        Page<RecBulletinDTO2> recBulletins = recBulletinQueryService.teaFindBulletin(pTime,auditStatus,title,recName,pageable);

        return recBulletins;
    }

    /**
     * 企业端 招聘简章查询页面的跳转
     * @return
     */
    @GetMapping("/company/recruitment.html")
    public String recruitment(){
        return "companyHTML/recruitment";
    }

    /**
     * 企业端 跳转到 简章添加成功与否提示页面
     * @return
     */
    @GetMapping("/company/recruitment-step3.html")
    public String toStep3(){return "companyHTML/recruitment-step3";}

    /**
     * 企业端 动态查询 招聘简章
     * @param publishTime
     * @param auditStatus
     * @param title
     * @return
     */
    @GetMapping("/company/findBulletin")
    @ResponseBody
    public Page<RecBulletinDTO2> findBulletin(String publishTime, String auditStatus, String title, Pageable pageable){
        //准备数据
        if("".equals(auditStatus)){
            auditStatus= null;
        }
        if("".equals(title)){
            title = null;
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

        //调用查询方法
        Page<RecBulletinDTO2> recBulletins = recBulletinQueryService.findBulletin(pTime,auditStatus,title,pageable);
        return recBulletins;
    }
    /**
     * 企业端 招聘简章添加页面的跳转
     * @return
     */
    @GetMapping("/company/recruitment-step1.html")
    public String recruitmentStep1(){
        return "companyHTML/recruitment-step1";
    }

    /**
     * 企业端 修改招聘简章
     * @param id 简章id
     * @param title 标题
     * @param content 内容
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param remarks 备注
     * @return
     */
    @PostMapping("/company/updateRecBulletin")
    public String updateRecBulletin(String id,
                                    String title,
                                    String content,
                                    String startTime,
                                    String endTime,
                                    String remarks,
                                    Model model){

        RecBulletin recBulletin = recBulletinCommandService.updateRecBulletin(UUID.fromString(id),title,content,startTime,endTime,remarks);

        model.addAttribute("recBulletin",recBulletin);
        return "companyHTML/recruitmentDetails1";
    }

    /**
     * 企业端 添加招聘简章
     * @param recBulletinDTO
     * @return
     */
    @PostMapping("/company/RecBulletin")
    public String saveRecBulletin(RecBulletinDTO recBulletinDTO,Model model){
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        UUID recId = user.getId();
        recBulletinDTO.setRecId(recId);
        RecBulletin recBulletin = recBulletinCommandService.saveRecBulletin(recBulletinDTO);
        if(recBulletin!=null){
            model.addAttribute("bulletinId",recBulletin.getId());
            //添加成功后跳转到添加职位页面
            return "companyHTML/recruitment-step2";
        }else{
            //添加失败后跳转到提示页面
            model.addAttribute("msg","哎啊，招聘简章发布失败了");
            return "companyHTML/recruitment-step3";
        }

    }

    /**
     * 企业端添加与招聘简章相关联的职位信息
     * @param recJobPositionDTOS 招聘职位集合
     * @return
     */
    @PostMapping("/company/saveJob")
    @ResponseBody
    public String saveJob(@RequestBody RecJobPositionDTO[] recJobPositionDTOS){
        List<RecJobPosition> rjp = new ArrayList<RecJobPosition>();
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        UUID recId = user.getId();
        String bulletinId = "";

        for(RecJobPositionDTO recJobPositionDTO:recJobPositionDTOS){
            RecJobPosition recJobPosition = new RecJobPosition();
            LocalDate startTime = LocalDate.parse(recJobPositionDTO.getStartTime());
            LocalDate endTime = LocalDate.parse(recJobPositionDTO.getEndTime());
            recJobPosition.setRecId(recId);
            recJobPosition.setName(recJobPositionDTO.getName());
            recJobPosition.setDescription(recJobPositionDTO.getDescription());
            recJobPosition.setType(recJobPositionDTO.getType());
            recJobPosition.setCategory(recJobPositionDTO.getCategory());
            recJobPosition.setDegree(recJobPositionDTO.getDegree());
            recJobPosition.setMajor(recJobPositionDTO.getMajor());
            recJobPosition.setReceiveMode(recJobPositionDTO.getReceiveMode());
            recJobPosition.setRecEmail(recJobPositionDTO.getRecEmail());
            recJobPosition.setCity(recJobPositionDTO.getCity());
            recJobPosition.setAddress(recJobPositionDTO.getAddress());
            recJobPosition.setRecruitmentNum(recJobPositionDTO.getRecruitmentNum());
            recJobPosition.setSalary(recJobPositionDTO.getSalary());
            recJobPosition.setStartTime(startTime);
            recJobPosition.setEndTime(endTime);
            //审核状态默认0待审核
            recJobPosition.setAuditStatus("00");
            //上线下线默认0下线
            recJobPosition.setOnlineStatus("0");
            rjp.add(recJobPosition);

            bulletinId = recJobPositionDTO.getBulletinId();
        }

        UUID bid = UUID.fromString(bulletinId);
        String result = recBulletinCommandService.saveJob(bid,rjp);

        String msg = "";
        if("success".equals(result)){
            msg = "恭喜招聘简章发布成功！";
        }else{
            msg = "哎呀，简章发布成功了，但是职位发布失败了呢。";
        }

        return msg;
    }

    /**
     * 企业端 根据id查找招聘简章信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/company/bulletinDetail")
    public String findById(@RequestParam UUID id, Model model){

        RecBulletin recBulletin = recBulletinQueryService.findById(id);
        model.addAttribute("recBulletin",recBulletin);

        return "companyHTML/recruitmentDetails1";
    }

}
