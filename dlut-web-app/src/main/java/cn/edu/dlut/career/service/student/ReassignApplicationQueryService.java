package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.ReassignApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * 改派申请 服务层接口
 * Created by HealerJean on 2017/4/13.
 */
public interface ReassignApplicationQueryService {

    //根据id查询
    ReassignApplication findById(UUID id);


    //查询所有的数据
    List<ReassignApplication> findAllReassignApply();

    /**
     * 根据学号查看改派信息
     * @param StuNo
     * @return
     */
    ReassignApplication findByStuNo(String StuNo);

    /**
     * 教师端查询改派信息
     */
    Page<ReassignApplication> listRessignApplication(String stuNo, String stuName, String majorCode, String eduDegree, String departmentId, String auditStatus, Pageable pageable);

    }
