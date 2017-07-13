package cn.edu.dlut.career.controller.company;

import javax.servlet.http.HttpServletRequest;

import cn.edu.dlut.career.domain.common.OperateLog;
import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.domain.company.OfferTemplate;
import cn.edu.dlut.career.domain.company.RecOffer;
import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.domain.student.ViolateApplication;
import cn.edu.dlut.career.dto.company.RecOfferDTO;
import cn.edu.dlut.career.service.common.OperateLogService;
import cn.edu.dlut.career.service.company.CompanyInfoQueryService;
import cn.edu.dlut.career.service.company.OfferTemplateQueryService;
import cn.edu.dlut.career.service.company.RecOfferCommandService;
import cn.edu.dlut.career.service.company.RecOfferQueryService;


import cn.edu.dlut.career.service.student.GraduateDestinationCommandService;
import cn.edu.dlut.career.service.student.GraduateDestinationQueryService;
import cn.edu.dlut.career.service.student.StudentInfoCommandService;
import cn.edu.dlut.career.service.student.StudentInfoQueryService;
import cn.edu.dlut.career.service.student.ViolateApplicationCommandService;
import cn.edu.dlut.career.service.student.ViolateApplicationQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by HealerJean on 2017/4/14.
 */
@Controller
@Transactional
public class RecOfferController {

    @Autowired
    private StudentInfoCommandService studentInfoCommandService;
    @Autowired
    private StudentInfoQueryService studentInfoQueryService;
    @Autowired
    private CompanyInfoQueryService companyInfoQueryService;
    @Autowired
    private OfferTemplateQueryService offerTemplateQueryService;
    @Autowired
    private RecOfferCommandService recOfferCommandService;
    @Autowired
    private GraduateDestinationCommandService graduateDestinationCommandService;
    @Autowired
    private RecOfferQueryService recOfferQueryService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ViolateApplicationCommandService violateApplicationCommandService;
    @Autowired
    private ViolateApplicationQueryService violateApplicationQueryService;
    @Autowired
    private GraduateDestinationQueryService graduateDestinationQueryService;
    @Autowired
    private  OperateLogService operateLogService;

    /**
     * @Autor 史念念 2017/6/5
     * @Description 企业端取消已发送的offer
     * @param id offer id
     * @return
     */
    @GetMapping("/company/deleteOffer")
    public String deleteOffer(@RequestParam UUID id){

        String result = recOfferCommandService.deleteOffer(id);

        return "companyHTML/offer";
    }

    /**
     * 企业跳转到签约管理页面
     * @return
     */
    @GetMapping("/company/agreementHtml")
    public String toAgreement(){return "companyHTML/agreement";}

    /**
     * 企业端跳转到签约详情页
     * @return
     */
    @GetMapping("/company/signDetail")
    public String toAgreementDetails(@RequestParam UUID id,Model model){

        RecOffer recOffer = recOfferQueryService.findOne(id);
        model.addAttribute("recOffer",recOffer);

        return "companyHTML/agreementDetails";
    }

    /**
     * 企业跳转到offer管理页面
     * @return
     */
    @GetMapping("/company/offerHTML")
    public String toOffer(){return "companyHTML/offer";}

    /**
     * 企业动态查询offer信息
     * @param stuNo 学号
     * @param stuName 姓名
     * @param sTime 签约时间/offer发放时间
     * @param eduDegree 学历
     * @param academy 院系
     * @param major 专业
     * @param graduationTime 毕业时间
     * @return
     */
    @GetMapping("/company/findOffer")
    @ResponseBody
    public Page<RecOffer> findOffer(String stuNo,
                                   String stuName,
                                   String sTime,
                                   String eduDegree,
                                   String academy,
                                   String major,
                                   String graduationTime,
                                   String offerStatus,
                                   Pageable pageable){
        //准备数据
        LocalDate signTime = null;
        if(sTime!=null && sTime!="") {
            signTime = LocalDate.parse(sTime);
        }else{
            signTime = null;
        }
        if(stuNo==""){stuNo=null;}
        if(stuName==""){stuName=null;}
        if(eduDegree==""){eduDegree=null;}
        if(academy==""){academy=null;}
        if(major==""){major=null;}
        if(graduationTime==""){graduationTime=null;}
        if(offerStatus==""){offerStatus=null;}

        //调用查找方法
        Page<RecOffer> recOffers = recOfferQueryService.findOffer(stuNo,offerStatus,stuName,signTime,eduDegree,academy,major,graduationTime,pageable);

        return recOffers;
    }
    /**
     * 批量审核学生offer同意
     * @param
     * @return
     */
    @GetMapping("teacher/auditBatch")
    public String auditBatch(String[] box){
        UserPrincipal user = (UserPrincipal)  SecurityUtils.getSubject().getPrincipal();
        String auditStatus = "";
        String stuReceivingStatus = "";
        if(user.getPrincipal().equals("SCHOOL") || user.getPrincipal().equals("SCHADMIN")){
            auditStatus = "03";//校级审核通过
            stuReceivingStatus = "04";//签约
        }else if(user.getPrincipal().equals("ACADEMY") || user.getPrincipal().equals("ACADMIN")){
            auditStatus = "01";//院级审核通过
            stuReceivingStatus = "01";//offer接受
        }
        for (String uid:box
             ) {
            UUID uuid = UUID.fromString(uid);
            recOfferCommandService.update(uuid,auditStatus,stuReceivingStatus);
            RecOffer recOffer = recOfferQueryService.findOne(uuid);
            GraduateDestination graduateDestination = graduateDestinationQueryService.findByStuId(recOffer.getStuId());
            recOfferCommandService.update(uuid,auditStatus,stuReceivingStatus);
            graduateDestination.setDepartmentBelong(recOffer.getDepartmentBelong());
            graduateDestination.setDestinationType(recOffer.getDestinationType());
            graduateDestination.setHukouToAddress(recOffer.getHukouToAddress());
            graduateDestination.setJobType(recOffer.getCategory());
            graduateDestination.setRecName(recOffer.getRecName());
            graduateDestination.setRecCode(recOffer.getOrgCode());
            graduateDestination.setRecNature(recOffer.getNature());
            graduateDestination.setRecIndustry(recOffer.getIndustry());
            graduateDestination.setRecCity(recOffer.getRecCity());
            graduateDestination.setRecLinker(recOffer.getRecLinker());
            graduateDestination.setRecTelphone(recOffer.getRecTelphone());
            graduateDestination.setRecMobile(recOffer.getRecMobile());
            graduateDestination.setRecAddress(recOffer.getRecAddress());
            graduateDestination.setRecZipcode(recOffer.getRecZipcode());
            graduateDestination.setPfileToName(recOffer.getPfileToName());
            graduateDestination.setPfileToAddress(recOffer.getPfileToAddress());
            //如果企业接收档案，设置档案信息
            if("1".equals(recOffer.getIsPfile())){
                graduateDestination.setPfileToName(recOffer.getPfileToName());
                graduateDestination.setPfileToAddress(recOffer.getPfileToAddress());
                graduateDestination.setPfileLocal(recOffer.getPfileToLocal());
                graduateDestination.setPfileLinker(recOffer.getPfileToRecipient());
                graduateDestination.setPfileMobile(recOffer.getPfileToPhone());
            }
            graduateDestination.setHukouIsSchool(recOffer.getIsSchHuKou());
            graduateDestination.setId(recOffer.getStuId());
            graduateDestinationCommandService.save(graduateDestination);
            //操作日志
            OperateLog operateLog  = new OperateLog(recOffer.getId(),"签约审核");

            operateLogService.addOptLog(operateLog,"签约审核通过");
            if("01".equals(auditStatus)){
                operateLogService.addOptLog(operateLog,"校级签约审核通过");
            }else {
                operateLogService.addOptLog(operateLog,"校级签约审核通过");

            }
        }
        return "teacherHTML/tea_agreementReview";
    }

    /**
     * 老师拒绝学生Offer
     * @param id
     * @param auditStatus
     * @param noPassReason
     * @return
     */
    @GetMapping("teacher/noPassOffer")
    public String recOffer(@RequestParam UUID id, String auditStatus, String noPassReason){
        UserPrincipal user = (UserPrincipal)  SecurityUtils.getSubject().getPrincipal();
        RecOffer recOffer = recOfferQueryService.findOne(id);
        OperateLog operateLog  = new OperateLog(recOffer.getId(),"签约审核");
        if(user.getPrincipal().equals("SCHOOL") || user.getPrincipal().equals("SCHADMIN")){
            //学生接受状态变为已作废
            recOffer.setStuReceivingStatus("06");
            operateLogService.addOptLog(operateLog,"校级签约审核不通过");
        }else {
            operateLogService.addOptLog(operateLog,"院级级签约审核不通过");
        }
        recOffer.setNoPassReason(noPassReason);
        recOffer.setAuditStatus(auditStatus);
        recOfferCommandService.save(recOffer);
        //操作日志

        return "teacherHTML/tea_agreementReview";
    }


    /**
     * 老师单个审核学生offer，只有同意
     * @param id
     * @return
     */
    @GetMapping("teacher/agreerecOffer")
    public String agreeRecoffer(@RequestParam UUID id){
        UserPrincipal user = (UserPrincipal)  SecurityUtils.getSubject().getPrincipal();
        String auditStatus = "";
        String stuReceivingStatus = "";
        //操作日志
        OperateLog operateLog  = new OperateLog(id,"签约审核");
        if(user.getPrincipal().equals("SCHOOL") || user.getPrincipal().equals("SCHADMIN")){
            auditStatus = "03";//校级审核通过
            stuReceivingStatus = "04";//签约
            RecOffer recOffer = recOfferQueryService.findOne(id);
            GraduateDestination graduateDestination = graduateDestinationQueryService.findByStuId(recOffer.getStuId());
            graduateDestination.setDepartmentBelong(recOffer.getDepartmentBelong());
            graduateDestination.setDestinationType(recOffer.getDestinationType());
            graduateDestination.setHukouToAddress(recOffer.getHukouToAddress());
            graduateDestination.setJobType(recOffer.getCategory());
            graduateDestination.setRecName(recOffer.getRecName());
            graduateDestination.setRecCode(recOffer.getOrgCode());
            graduateDestination.setRecNature(recOffer.getNature());
            graduateDestination.setRecIndustry(recOffer.getIndustry());
            graduateDestination.setRecCity(recOffer.getRecCity());
            graduateDestination.setRecLinker(recOffer.getRecLinker());
            graduateDestination.setRecTelphone(recOffer.getRecTelphone());
            graduateDestination.setRecMobile(recOffer.getRecMobile());
            graduateDestination.setRecAddress(recOffer.getRecAddress());
            graduateDestination.setRecZipcode(recOffer.getRecZipcode());
            graduateDestination.setPfileToName(recOffer.getPfileToName());
            //如果企业接收档案，设置档案信息
            if("1".equals(recOffer.getIsPfile())){
                graduateDestination.setPfileToName(recOffer.getPfileToName());
                graduateDestination.setPfileToAddress(recOffer.getPfileToAddress());
                graduateDestination.setPfileLocal(recOffer.getPfileToLocal());
                graduateDestination.setPfileLinker(recOffer.getPfileToRecipient());
                graduateDestination.setPfileMobile(recOffer.getPfileToPhone());
            }
            graduateDestination.setHukouIsSchool(recOffer.getIsSchHuKou());
//            graduateDestination.setRecProvince(recOf);
            graduateDestination.setId(recOffer.getStuId());
            graduateDestinationCommandService.save(graduateDestination);
            operateLogService.addOptLog(operateLog,"校级签约审核通过");
        }else if(user.getPrincipal().equals("ACADEMY") || user.getPrincipal().equals("ACADMIN")){
            auditStatus = "01";//院级审核通过
            stuReceivingStatus = "01";//offer接受
            operateLogService.addOptLog(operateLog,"院级签约审核通过");
        }
            recOfferCommandService.update(id,auditStatus,stuReceivingStatus);
        return "teacherHTML/tea_agreementReview";
    }
    /**
     * 学生同意offer
     * @param id
     */
    @GetMapping("student/updateOffer1")
    public ModelAndView agree(@RequestParam UUID id){
        ModelAndView mv = new ModelAndView("studentHTML/stu_offer");
        RecOffer recOffer = recOfferQueryService.findOne(id);
        RecOfferDTO recOfferDTOS = null;
        recOfferDTOS = recOfferQueryService.findByStuId(recOffer.getStuId());
        if(recOfferDTOS!=null) {
            mv.addObject("errorMessage","你已经有一份接受的Offer了！");
        }else{
            recOffer.setStuReceivingStatus("01");
            recOfferCommandService.save(recOffer);
        }
        return mv;
    }

    /**
     * 学生拒绝offer
     * @param id
     */
    @GetMapping("student/updateOffer2")
    public String refuse(@RequestParam UUID id){
        RecOffer recOffer = recOfferQueryService.findOne(id);
        recOffer.setStuReceivingStatus("02");
        recOfferCommandService.save(recOffer);
        return "studentHTML/stu_offer";
    }




    @GetMapping("teacher/tea_agreementReview.html")
    public String tea_agreementReview(){
        return "teacherHTML/tea_agreementReview";
    }



    /*
        三方协议页面
     */

    @GetMapping("student/agreement.html")
    public ModelAndView recOffer() {
        ModelAndView modelAndView = null;
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        RecOffer recOffer = recOfferQueryService.findRecOfferByStuNo(userPrincipal.getUserName());
        if (recOffer!=null) {
            ViolateApplication violateApplication = violateApplicationQueryService.findByStuNo(userPrincipal.getUserName());
            modelAndView = new ModelAndView("studentHTML/stu_agreement");
            modelAndView.addObject("violateApplication", violateApplication);
            modelAndView.addObject("recOffer", recOffer);
        }else {
            modelAndView = new ModelAndView("studentHTML/stu_agreementNull");
        }
        return modelAndView;


    }

    @GetMapping("student/stu_offer.html")
    public String goToOffer(){
        return "studentHTML/stu_offer";
    }

    /**
     * 学生查询自己的offer
     * @param stuNo
     * @return
     */
    @GetMapping("student/RecOffer")
    @ResponseBody
    public List<RecOfferDTO> RecOffer(String stuNo, String stuReceivingStatus){
        List<RecOfferDTO> recOfferDTOS = null;
        if(stuReceivingStatus.equals("07")){
            recOfferDTOS = recOfferQueryService.findByStuNo(stuNo);
        }else {
            recOfferDTOS = recOfferQueryService.findByStuNos(stuNo, stuReceivingStatus);
        }
        return recOfferDTOS;
    }

    /**
     * 查看某条offer的详情
     * @param id
     * @return
     */
    @GetMapping("student/myoffer")
    public ModelAndView findOne(@RequestParam UUID id){
        ModelAndView mv = new ModelAndView("studentHTML/stu_offerDetails");
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        RecOffer recOffer = recOfferQueryService.findOne(id);
        RecOfferDTO recOffers = recOfferQueryService.findByStuId(userPrincipal.getId());
        mv.addObject("recOffer",recOffer);
        mv.addObject("recOffers",recOffers);
        return mv;
    }

    /**
     * 院系
     * @return
     */
    @ModelAttribute("academy")
    public Map<String, String> academy() {
        return PubCodeUtil.getDictMap("academy");
    }

    /**
     * 审核状态
     * @return
     */
    @ModelAttribute("totalAuditStatus")
    public Map<String, String> totalAuditStatus() {
        return PubCodeUtil.getDictMap("totalAuditStatus");
    }

    /**
     * offer状态
     * @return
     */
    @ModelAttribute("offerStatus")
    public Map<String, String> offerStatus() {
        return PubCodeUtil.getDictMap("offerStatus");
    }

    /**
     * 专业
     * @return
     */
    @ModelAttribute("major")
    public Map<String, String> major() {
        return PubCodeUtil.getDictMap("major");
    }

    /**
     * 学历代码表映射
     * @return
     */
    @ModelAttribute("eduDegree")
    public Map<String, String> eduDegree() {
        return PubCodeUtil.getDictMap("eduDegree");
    }


    /**
     * 老师通过条件筛选符合的offer,
     * @param stuNo
     * @param realName
     * @param department
     * @param eduDegree
     * @param endDate
     * @param stuReceivingStatus
     * @param majorCode
     * @param recName
     * @param pageable
     * @return
     */
    @GetMapping("teacher/RecOffers")
    @ResponseBody
    public Page<RecOffer> recOffres(String stuNo, String realName, String department, String eduDegree, String endDate, String stuReceivingStatus, String majorCode, String recName, Pageable pageable){
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        Page<RecOffer> recOffers = null;
        String departmentId = "";
        if (!"00".equals(user.getDepId())){
            departmentId = user.getDepId();
        }
        recOffers = recOfferQueryService.findRecOfferDynamic(stuNo,realName,departmentId,eduDegree,endDate,stuReceivingStatus,majorCode,recName,pageable);


        if(recOffers!=null) {
            return recOffers;
        }else {
            return null;
        }
    }

    /**
     * 根据ID查询简历详情
     * @param id
     * @return
     */
    @GetMapping("teacher/RecOfferById")
    public ModelAndView findById(@RequestParam UUID id){
        ModelAndView mv = new ModelAndView("/teacherHTML/tea_agreementReview");
        RecOffer recOffer = recOfferQueryService.findOne(id);
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        if(user.getPrincipal().equals("SCHOOL") || user.getPrincipal().equals("SCHADMIN")){
            mv.setViewName("teacherHTML/tea_agreement2");
        }else if(user.getPrincipal().equals("ACADEMY") || user.getPrincipal().equals("ACADMIN")){
            mv.setViewName("teacherHTML/tea_agreement");
        }
        mv.addObject("recOffer",recOffer);
        return mv;
    }

    @PostMapping("/company/sendOffer")
    @ResponseBody
    /**
    * @author wei  2017/6/6
    * @method sendOffer
    * @param [stuId, templateId]
    * @return java.util.Map<java.lang.String,java.lang.String>
    * @description  企业发送offer
    */
    public Map<String,String> sendOffer(@RequestParam UUID stuId,@RequestParam UUID templateId){
        Map<String,String> map = new HashMap<>();
        RecOffer recOffer = recOfferCommandService.sendOffer(stuId, templateId);
        if(recOffer!=null) {
            map.put("message","ok");
        }else{
            map.put("message","error");
        }
        return map;
    }

    /**
     * @Autor 史念念 2017/6/6
     * @Description 企业端 再次发送offer
     * @param stuId 学生id
     * @param templateId offer模板id
     * @return
     */
    @PostMapping("/company/sendOfferAgain")
    @ResponseBody
    public String sendOfferAgain(@RequestParam UUID stuId,@RequestParam UUID templateId){

        RecOffer recOffer = recOfferCommandService.sendOfferAgain(stuId,templateId);

        if(recOffer!=null){
            return "ok";
        }else {
            return "error";
        }

    }
}
