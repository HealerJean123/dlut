package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.domain.student.ViolateApplication;
import cn.edu.dlut.career.dto.student.ViolateApplicationDTO;
import cn.edu.dlut.career.repository.company.RecOfferRepository;
import cn.edu.dlut.career.repository.student.GraduateDestinationRepostiory;
import cn.edu.dlut.career.repository.student.ViolateApplicationRepository;
import cn.edu.dlut.career.service.company.RecOfferQueryService;


import cn.edu.dlut.career.service.student.StudentInfoCommandService;
import cn.edu.dlut.career.service.student.StudentInfoQueryService;
import cn.edu.dlut.career.service.student.ViolateApplicationCommandService;
import cn.edu.dlut.career.service.student.ViolateApplicationQueryService;
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
@Transactional(readOnly = true)
public class ViolateApplicationQueryServiceImpl implements ViolateApplicationQueryService {
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

    @Override
    public ViolateApplication findById(UUID id) {
        return violateApplicationRepository.findOne(id);
    }



    @Override
    public List<ViolateApplication> findAllViolateApply() {
        return violateApplicationRepository.findAll();
    }

    /**
     * 动态查询 违约申请
     * 该方法用于教师端 对违约申请的查询
     *
     * @param stuNo       学号
     * @param stuName     姓名
     * @param departmentId  院系id
     * @param majorName   专业
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param auditStatus 审核状态
     * @return
     */
    @Override
    public Page<ViolateApplicationDTO> findByKey(String stuNo,
                                                 String stuName,
                                                 String departmentId,
                                                 String majorName,
                                                 LocalDateTime startTime,
                                                 LocalDateTime endTime,
                                                 String auditStatus,
                                                 Pageable pageable) {

        String departAuditStatus = null;
        String schAuditStatus = null;
        if("00".equals(auditStatus)){
            departAuditStatus = "00";
            schAuditStatus = "00";
        }else  if("01".equals(auditStatus)){
            departAuditStatus ="01";
        }else  if("02".equals(auditStatus)){
            departAuditStatus ="02";
        }else if("03".equals(auditStatus)){
            schAuditStatus = "01";
        }else if("04".equals(auditStatus)){
            schAuditStatus="02";
        }
        Page<ViolateApplicationDTO> vads = violateApplicationRepository.findByKey(stuNo,stuName,departmentId,majorName,startTime,endTime,departAuditStatus,schAuditStatus,pageable);

        return vads;
    }

    @Override
    public ViolateApplication findByStuNo (String stuNo){
        return violateApplicationRepository.findByStuNo(stuNo);
    }

    /**
     * 根据违约申请id查找违约申请信息
     * @param id
     * @return
     */
    @Override
    public ViolateApplicationDTO findByVioId(UUID id) {
        ViolateApplicationDTO vad = violateApplicationRepository.findById(id);

        return vad;
    }


}
