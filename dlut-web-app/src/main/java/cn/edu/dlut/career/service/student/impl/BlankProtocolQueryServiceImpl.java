package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.student.BlankProtocol;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.repository.student.BlankProtocolRepository;
import cn.edu.dlut.career.service.student.BlankProtocolCommandService;
import cn.edu.dlut.career.service.student.BlankProtocolQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/25.
 *
 * 空白协议书 服务层实现类
 */
@Service
@Transactional(readOnly = true)
public class BlankProtocolQueryServiceImpl implements BlankProtocolQueryService {
    @Autowired
    private BlankProtocolRepository blankProtocolRepository;



    /**
     * 根据学生id查找空白协议书
     * @param stuId
     * @return
     */
    @Override
    public List<BlankProtocol> findByStuId(UUID stuId) {
        return blankProtocolRepository.findByStuId(stuId);
    }

    /**
     * 根据id查找空白协议书
     * @param id
     * @return
     */
    @Override
    public BlankProtocol findById(UUID id) {

        return blankProtocolRepository.findOne(id);
    }

    /**
     *该方法用于教师端 动态查询空白协议书
     * @param stuNo 学生学号 要求精确
     * @param name 学生姓名 可模糊
     * @param auditStatus 审核状态
     * @param departmentId 院系
     * @param pageable
     * @return
     */
    @Override
    public Page<BlankProtocol> findBlankProtocol(String stuNo, String name, String auditStatus, String departmentId, Pageable pageable) {

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
        Page<BlankProtocol> bps = blankProtocolRepository.findBlankProtocol(stuNo,name,departAuditStatus,schAuditStatus,departmentId,pageable);
        return bps;
    }

}
