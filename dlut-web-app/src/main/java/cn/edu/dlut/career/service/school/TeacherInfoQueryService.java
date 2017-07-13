package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.domain.school.TeacherInfo;
import cn.edu.dlut.career.dto.school.TeacherLoginDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 教师信息服务层 接口
 * Created by HealerJean on 2017/4/13.
 */
public interface TeacherInfoQueryService {
    List<TeacherInfo> findAll();

    TeacherInfo findById(UUID id);

    TeacherLoginDTO findLoginInfo(String username);

}
