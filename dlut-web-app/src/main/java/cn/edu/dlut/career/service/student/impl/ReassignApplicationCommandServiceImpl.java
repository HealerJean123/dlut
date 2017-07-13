package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.common.OperateLog;
import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.ReassignApplication;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.repository.student.ReassignApplicationRespository;
import cn.edu.dlut.career.service.common.OperateLogService;
import cn.edu.dlut.career.service.student.*;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *  改派申请 服务层
 * Created by HealerJean on 2017/4/13.
 */
@Service
@Transactional
public class ReassignApplicationCommandServiceImpl implements ReassignApplicationCommandService {
    @Autowired
   private ReassignApplicationRespository reassignApplicationRespository;
    @Autowired
    private StudentInfoCommandService studentInfoCommandService;
    @Autowired
    private StudentInfoQueryService studentInfoQueryService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GraduateDestinationCommandService graduateDestinationCommandService;
    @Autowired
    private GraduateDestinationQueryService graduateDestinationQueryService;
    @Autowired
    private OperateLogService operateLogService;
    Logger logger = LoggerFactory.getLogger(ReassignApplicationCommandServiceImpl.class);
    /**
     * 派遣申请 表的添加
     * @param reassignApplication
     * @return
     */
    @Override
    public ReassignApplication saveReassignApply(ReassignApplication reassignApplication) {
        UserPrincipal userPrincipal =(UserPrincipal) SecurityUtils.getSubject().getPrincipal();

        //看看有没有就业去向
        GraduateDestination graduateDestination = graduateDestinationQueryService.findByStatusAndStuNo("02",userPrincipal.getId());
       if(graduateDestination!=null) {
           ReassignApplication reassignApplicationStatus = reassignApplicationRespository.findByStuNo(userPrincipal.getUserName());
           if (reassignApplicationStatus == null) {
               logger.info(userPrincipal.getId() + "**********");
               StudentInfo studentInfo = studentInfoQueryService.findById(userPrincipal.getId());
               reassignApplication.setEduDegree(studentInfo.getEduDegree());
               reassignApplication.setHomelandName(studentInfo.getHomelandName());
               reassignApplication.setEndDate(studentInfo.getEndDate());

               reassignApplication.setMajorCode(studentInfo.getMajorCode());
               reassignApplication.setMajorName(studentInfo.getMajorName());
               reassignApplication.setDepartmentId(studentInfo.getDepartmentId());
               reassignApplication.setDepartment(studentInfo.getDepartment());

               GraduateDestination graduateDestination1 = graduateDestinationQueryService.findByStuId(userPrincipal.getId());
               reassignApplication.setPfileToAddress(graduateDestination1.getPfileToAddress());
               reassignApplication.setPfileToName(graduateDestination1.getPfileToName());
               //此处缺少档案接收人及其联系方式，因字段值为确认。后期加上。
               reassignApplication.setHukouToAddress(graduateDestination1.getHukouToAddress());
               reassignApplication.setOriginalRecCode(graduateDestination1.getRecCode());

               //审核状态设置为0
               reassignApplication.setAuditStatus("00");
               return reassignApplicationRespository.save(reassignApplication);
           }
       }
        return  null;
    }


    @Override
    public ReassignApplication updateReassignApply(ReassignApplication reassignApplication) {
        return reassignApplicationRespository.save(reassignApplication);
    }

    @Override
    public String deleteReassignApply(UUID id) {
        try {
            reassignApplicationRespository.delete(id);
            return "ok";
        }catch (Exception e){
         e.printStackTrace();
        }
        return null;
    }



    /**
     * 教师端
     * 更新 某个学生改派申请 审核状态
     * @param reassignApplication
     * @return
     */
    @Override
    public ReassignApplication updateReassAppAuditStatus(ReassignApplication reassignApplication) {

        Subject currentUser = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal)currentUser.getPrincipal();
        String principal = userPrincipal.getPrincipal();
        //只有校级的身份才可以修改派遣信息
        if (userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN")) {
            ReassignApplication reassignApplicationFinale = reassignApplicationRespository.findByStuNo(reassignApplication.getStuNo());
            reassignApplicationFinale.setAuditStatus(reassignApplication.getAuditStatus());
            reassignApplicationFinale.setNoPassReason(reassignApplication.getNoPassReason());
            reassignApplicationFinale.setAuditTime(LocalDateTime.now());
            reassignApplicationFinale.setAuditor(userPrincipal.getRealName());
            OperateLog operateLog = new OperateLog(reassignApplicationFinale.getId(),"改派审核");
            String operateString = null;
            if("01".equals(reassignApplicationFinale.getAuditStatus())){
                operateString ="改派审核通过";
            }else if("02".equals(reassignApplicationFinale.getAuditStatus())){
                operateString ="改派审核不通过";
            }
            operateLogService.addOptLog(operateLog,operateString);
            return reassignApplicationRespository.save(reassignApplicationFinale);
        }
        return  null;
    }

    /**
     * 教师端
     * 批量审核改派申请
     * @param id
     * @return
     */
    @Override
    public int stuReassAppBatchAudit(UUID[] id) {
        UserPrincipal userPrincipal = (UserPrincipal)SecurityUtils.getSubject().getPrincipal();
        //只有校级的身份才可以修改派遣信息
        if (userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN")) {
            List<ReassignApplication> reassignApplications = new ArrayList<ReassignApplication>();
            for (UUID sid : id) {
                ReassignApplication reassignApplication = reassignApplicationRespository.findOne(sid);
                reassignApplication.setAuditStatus("01");
                reassignApplication.setAuditTime(LocalDateTime.now());
                reassignApplication.setAuditor(userPrincipal.getRealName());
                reassignApplications.add(reassignApplication);
                //操作日志
                OperateLog operateLog  = new OperateLog(reassignApplication.getId(),"改派审核");
                operateLogService.addOptLog(operateLog,"改派审核通过");
            }
            if (reassignApplicationRespository.save(reassignApplications) != null) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 直接根据id 进行审核 改派信息
     * @param id
     * @return
     */
    @Override
    public ReassignApplication stuReassAppAudit(UUID id) {

         UserPrincipal userPrincipal = (UserPrincipal)SecurityUtils.getSubject().getPrincipal();
        //只有校级的身份才可以修改派遣信息
        if (userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN")) {
            //1 代表审核已经通过
            String auditStatus = "01";
            ReassignApplication reassignApplicationFinale = reassignApplicationRespository.findOne(id);
            reassignApplicationFinale.setAuditStatus(auditStatus);
            reassignApplicationFinale.setAuditTime(LocalDateTime.now());
            reassignApplicationFinale.setAuditor(userPrincipal.getRealName());
           //操作日志
            OperateLog operateLog  = new OperateLog(reassignApplicationFinale.getId(),"改派审核");
            operateLogService.addOptLog(operateLog,"改派审核通过");
            return reassignApplicationRespository.save(reassignApplicationFinale);
        }
        return  null;
    }



}
