package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.BlankProtocol;
import cn.edu.dlut.career.domain.student.StudentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/25.
 *
 * 空白协议书 服务层接口
 */
public interface BlankProtocolQueryService {


    /**
     * 根据学生id查找空白协议书
     * @param stuId
     * @return
     */
    List<BlankProtocol> findByStuId(UUID stuId);

    /**
     * 根据id查找空白协议书
     * @param id
     * @return
     */
    BlankProtocol findById(UUID id);

    /**
     *该方法用于教师端 动态查询空白协议书
     * @param stuNo 学生学号 要求精确
     * @param name 学生姓名 可模糊
     * @param auditStatus 审核状态
     * @param departmentId 院系
     * @param pageable
     * @return
     */
    Page<BlankProtocol> findBlankProtocol(String stuNo, String name, String auditStatus, String departmentId, Pageable pageable);

    }
