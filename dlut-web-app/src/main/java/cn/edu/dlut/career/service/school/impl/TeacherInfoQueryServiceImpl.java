package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.domain.school.TeacherInfo;
import cn.edu.dlut.career.dto.school.TeacherLoginDTO;
import cn.edu.dlut.career.repository.school.TeacherInfoRepository;
import cn.edu.dlut.career.service.school.TeacherInfoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 教师信息服务层
 * Created by HealerJean on 2017/4/13.
 */
@Service
@Transactional(readOnly = true)
public class TeacherInfoQueryServiceImpl implements TeacherInfoQueryService {


    @Autowired
    private TeacherInfoRepository teacherInfoRepository;

    @Override
    public List<TeacherInfo> findAll() {
        return teacherInfoRepository.findAll();
    }

    @Override
    public TeacherInfo findById(UUID id) {
        return teacherInfoRepository.findOne(id);
    }

    @Override
    public TeacherLoginDTO findLoginInfo(String username) {
        return teacherInfoRepository.findLoginInfo(username);
    }

}
