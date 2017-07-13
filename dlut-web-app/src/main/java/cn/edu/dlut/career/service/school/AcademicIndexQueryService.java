package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.dto.school.AcademicIndexDTO;

/**
 * Created by 史念念 on 2017/4/28.
 *
 * 院系老师首页信息服务层
 */
public interface AcademicIndexQueryService {

    /**
     * 查询首页所需信息
     * @return
     */
    AcademicIndexDTO findAll(String departmentId,String endDate);
}
