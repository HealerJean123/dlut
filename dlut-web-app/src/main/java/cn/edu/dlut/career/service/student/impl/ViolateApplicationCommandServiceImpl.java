package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.common.OperateLog;
import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.domain.student.ViolateApplication;
import cn.edu.dlut.career.dto.student.ViolateApplicationDTO;
import cn.edu.dlut.career.repository.company.RecOfferRepository;
import cn.edu.dlut.career.repository.student.GraduateDestinationRepostiory;
import cn.edu.dlut.career.repository.student.ViolateApplicationRepository;
import cn.edu.dlut.career.service.common.OperateLogService;
import cn.edu.dlut.career.service.company.RecOfferQueryService;
import cn.edu.dlut.career.service.student.StudentInfoCommandService;
import cn.edu.dlut.career.service.student.StudentInfoQueryService;
import cn.edu.dlut.career.service.student.ViolateApplicationCommandService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 违约申请 服务层
 * Created by HealerJean on 2017/4/13.
 */
@Service
@Transactional
public class ViolateApplicationCommandServiceImpl implements ViolateApplicationCommandService {
    @Autowired
    private ViolateApplicationRepository violateApplicationRepository;
    @Autowired
    private RecOfferRepository recOfferRepository;

    @Autowired
    private RecOfferQueryService recOfferQueryService;


    @Autowired
    private GraduateDestinationRepostiory graduateDestinationRepostiory;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StudentInfoQueryService studentInfoQueryService;
    @Autowired
    private OperateLogService operateLogService;
    @Override
    public ViolateApplication saveViolateApply(ViolateApplication violateApplication) {

        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        //查看这个学生表中是否已经存在违约申请了
        ViolateApplication violateApplicationStatus = violateApplicationRepository.findByStuNo(userPrincipal.getUserName());
        if(violateApplicationStatus==null) {
            StudentInfo studentInfo = studentInfoQueryService.findById(userPrincipal.getId());
            violateApplication.setMajorCode(studentInfo.getMajorCode());
            violateApplication.setMajorName(studentInfo.getMajorName());
            violateApplication.setDepartment(studentInfo.getDepartment());
            violateApplication.setDepartmentId(studentInfo.getDepartmentId());

            violateApplication.setAuditStatus("00");
            violateApplication.setDepartAuditStatus("00");
            violateApplication.setSchAuditStatus("00");
            UUID recOfferId = recOfferQueryService.findRecOfferByStuNo(violateApplication.getStuNo()).getId();
            violateApplication.setRecOfferId(recOfferId);
            return violateApplicationRepository.save(violateApplication);
        }

            return  null;


    }


    @Override
    public ViolateApplication updateViolateApply(ViolateApplication violateApplication) {
        return violateApplicationRepository.save(violateApplication);
    }

    @Override
    public String deleteViolateApply(UUID id) {
        try {
            violateApplicationRepository.delete(id);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 修改审核信息
     * @param id 违约申请id
     * @param departAuditStatus 院级审核状态
     * @param departNoPassReason 院级审核不通过原因
     * @param departAuditor 院级审核人
     * @param schAuditStatus 校级审核状态
     * @param schNoPassReason 校级审核不通过原因
     * @param schAuditor 校级审核人
     * @return
     */
    @Override
    public String updateAudit(UUID id, String departAuditStatus, String departNoPassReason, String departAuditor, String schAuditStatus, String schNoPassReason, String schAuditor) {

        LocalDateTime departAuditTime = null;
        LocalDateTime schAuditTime = null;
        int result = 0;
        //根据id查找违约申请信息
        ViolateApplication va = violateApplicationRepository.findOne(id);
        int status = Integer.parseInt(va.getAuditStatus());
        //申请审核状态
        String auditStatus="00";
        OperateLog operateLog  = new OperateLog(va.getId(),"违约审核");

        //院系老师进行的修改操作
        if(departAuditor!=null){
            //若校级已审核院系不可对总审核状态进行修改
            if(status>2){
                auditStatus = va.getAuditStatus();
                if ("01".equals(departAuditStatus)) {
                    departNoPassReason = null;
                    operateLogService.addOptLog(operateLog,"院级违约审核通过");
                }
            }else {
                if ("01".equals(departAuditStatus)) {
                    auditStatus = "01";
                    departNoPassReason = null;
                    operateLogService.addOptLog(operateLog,"院级违约审核通过");
                } else if ("02".equals(departAuditStatus)) {
                    auditStatus = "02";
                    operateLogService.addOptLog(operateLog,"院级违约审核不通过");
                }
            }

            departAuditTime = LocalDateTime.now();
            result = violateApplicationRepository.updateDepartAudit(id,auditStatus,departAuditStatus,departAuditor,departAuditTime,departNoPassReason);
        }
        //校级老师进行的修改操作
        if(schAuditor!=null){
            if("01".equals(schAuditStatus)){
                auditStatus = "03";
                schNoPassReason = null;
                operateLogService.addOptLog(operateLog,"校级违约审核通过");
            }else if("02".equals(schAuditStatus)){
                auditStatus = "04";
                operateLogService.addOptLog(operateLog,"校级违约审核不通过");
            }
            schAuditTime = LocalDateTime.now();
            result = violateApplicationRepository.updateSchAudit(id,auditStatus,schAuditStatus,schAuditor,schAuditTime,schNoPassReason);

            if(result>0 && "03".equals(auditStatus)){
                //如果校级审核通过，则学生签约表中的学生接收状态改成 05毁约
                result = recOfferRepository.updateStuReceivingStatus(va.getRecOfferId(),"05");
                //校级审核通过，删除旧就业去向，并添加一个新的
                GraduateDestination graduateDestination = graduateDestinationRepostiory.findByStuNo(va.getStuNo());
                graduateDestination.setStuStatus("00");
                graduateDestination.setDestinationType("70");
                graduateDestination.setRecName(null);
                graduateDestination.setRecCode(null);
                graduateDestination.setRecNature(null);
                graduateDestination.setRecIndustry(null);
                graduateDestination.setRecProvince(null);
                graduateDestination.setRecCity(null);
                graduateDestination.setJobType(null);
                graduateDestination.setRecLinker(null);
                graduateDestination.setRecMobile(null);
                graduateDestination.setRecTelphone(null);
                graduateDestination.setRecAddress(null);
                graduateDestination.setRecZipcode(null);
                graduateDestination.setDepartmentBelong(null);
                graduateDestination.setPfileToName(null);
                graduateDestination.setPfileToAddress(null);
                graduateDestination.setPfileLinker(null);
                graduateDestination.setPfileMobile(null);
                graduateDestination.setHukouToAddress(null);
                graduateDestination.setHukouIsSchool(null);
                graduateDestination.setReportCardType(null);
                graduateDestination.setReportCardAddress(null);
                graduateDestination.setReportCardDate(null);
                graduateDestination.setReportCardRec(null);

                graduateDestinationRepostiory.save(graduateDestination);
            }else if(result>0 && "04".equals(auditStatus)){
                //如果校级审核不通过，则学生签约表中的学生接收状态改成 04已签约
                result = recOfferRepository.updateStuReceivingStatus(va.getRecOfferId(),"04");
            }
        }
        return result>0?"ok":"fail";
    }
}
