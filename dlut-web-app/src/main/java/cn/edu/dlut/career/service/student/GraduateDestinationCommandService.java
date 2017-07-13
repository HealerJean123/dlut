package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.GraduateDestination;

import java.util.UUID;

/**
 * Created by wei on 2017/4/12.
 */
public interface GraduateDestinationCommandService {

    void save(GraduateDestination jobDestination);

    void delete(UUID id);

    void update(UUID id);

    /**
     * @Description  一键派遣回原籍.
     * @Author  wangyj
     * @CreateDate 2017/5/11 14:31
     * @Param
     * @Return
     */
    String backToHomeland(String graduateDate);

    /**
     * 教师端 老师修改学生的核对状态
     * @return
     */
    String upStuStatus();

}
