package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.ReassignApplication;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.repository.student.ReassignApplicationRespository;
import cn.edu.dlut.career.service.student.*;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@Transactional(readOnly = true)
public class ReassignApplicationQueryServiceImpl implements ReassignApplicationQueryService {
    @Autowired
   private ReassignApplicationRespository reassignApplicationRespository;

    Logger logger = LoggerFactory.getLogger(ReassignApplicationQueryServiceImpl.class);

    @Override
    public ReassignApplication findById(UUID id) {
        return reassignApplicationRespository.findOne(id);
    }


    @Override
    public List<ReassignApplication> findAllReassignApply() {
        return reassignApplicationRespository.findAll();
    }

    /**
     * 根据学号查看改派信息
     * @param stuNo
     * @return
     */
    @Override
    public ReassignApplication findByStuNo(String stuNo) {
        return reassignApplicationRespository.findByStuNo(stuNo);
    }



    /**
     * 教师端
     * 分页展示改派申请信息
     * @param stuNo
     * @param stuName
     * @param majorCode
     * @param eduDegree
     * @param departmentId
     * @param auditStatus
     * @param pageable
     * @return
     */
   @Override
    public Page<ReassignApplication> listRessignApplication(
        String stuNo,
        String stuName,
        String majorCode,
        String eduDegree,
        String departmentId,
        String auditStatus,
        Pageable pageable) {
       if(stuNo.equals("")){
           stuNo = null;
       }if(stuName.equals("")){
           stuName = null;
       }if(majorCode.equals("")){
           majorCode = null;
       }if(eduDegree.equals("")){
           eduDegree = null;
       }if(departmentId.equals("")){
           departmentId = null;
       }if(auditStatus.equals("")){
           auditStatus = null;
       }
        UserPrincipal userPrincipal = (UserPrincipal)SecurityUtils.getSubject().getPrincipal();
       if (userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN")) {
           return reassignApplicationRespository.queryGetReassignApplicationPage(stuNo, stuName, majorCode, eduDegree, departmentId, auditStatus, pageable);
       }
       return  null;
    }


}
