package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/4/12.
 */
public interface GraduateDestinationQueryService {



    GraduateDestination findOne(UUID id);

    List<GraduateDestination> findAll();

    /**
     * 根据学生ID查看学生就业意向
     * @param stuId
     * @return
     */
    GraduateDestination findByStuId(UUID stuId);

    GraduateDestination findByStuIdAndStatus(UUID stuId, String status);

    Page<GraduateDestination> findList(String stuNo, String name, String departmentId, String eduDegree, String endDate, String stuStatus, String majorCode, String recName, Pageable pageable);


    GraduateDestination findByStatusAndStuNo(String status, UUID id);



}
