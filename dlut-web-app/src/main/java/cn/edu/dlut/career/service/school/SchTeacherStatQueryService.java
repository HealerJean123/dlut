package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.dto.school.SchTeacherStatDTO;

import java.util.List;
import java.util.Map;

/**
 * @Author wangyj.
 * @Date 2017/4/28  9:36.
 */
public interface SchTeacherStatQueryService {
    SchTeacherStatDTO getStat(String graduateDate);
}
