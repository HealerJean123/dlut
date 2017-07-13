package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.repository.student.GraduateDestinationRepostiory;
import cn.edu.dlut.career.service.student.GraduateDestinationCommandService;
import cn.edu.dlut.career.service.student.GraduateDestinationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/4/12.
 */
@Service
@Transactional(readOnly = true)
public class GraduateDestinationQueryServiceImpl implements GraduateDestinationQueryService {
    @Autowired
    private GraduateDestinationRepostiory graduateDestinationRepostiory;


    @Override
    public GraduateDestination findOne(UUID id) {
        return graduateDestinationRepostiory.findOne(id);
    }

    @Override
    public List<GraduateDestination> findAll() {
        return graduateDestinationRepostiory.findAll();
    }

    /**
     * 根据学生ID查看学生就业意向
     * @param stuId
     * @return
     */
    @Override
    public GraduateDestination findByStuId(UUID stuId) {
        return graduateDestinationRepostiory.findByStuId(stuId);
    }

    @Override
    public GraduateDestination findByStuIdAndStatus(UUID stuId, String status) {
        return graduateDestinationRepostiory.findByStuIdAndStatus(stuId,status);
    }

    @Override
    public Page<GraduateDestination> findList(String stuNo, String name, String departmentId, String eduDegree, String endDate, String stuStatus, String majorCode, String recName, Pageable pageable) {
        return graduateDestinationRepostiory.findList(stuNo,name,departmentId,eduDegree,endDate,stuStatus,majorCode,recName,pageable);
    }




    @Override
    public GraduateDestination findByStatusAndStuNo(String status, UUID id) {
        return graduateDestinationRepostiory.findByStatusAndStuNo(status, id);
    }


}
