package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.repository.student.GraduateDestinationRepostiory;
import cn.edu.dlut.career.repository.student.StudentInfoRepository;
import cn.edu.dlut.career.service.student.GraduateDestinationCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by wei on 2017/4/12.
 */
@Service
@Transactional
public class GraduateDestinationCommandServiceImpl implements GraduateDestinationCommandService {
    @Autowired
    private GraduateDestinationRepostiory graduateDestinationRepostiory;

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    /**
     * @Description 教师添加、修改学生的就业去向.
     * @Author  wangyj
     * @CreateDate 2017/5/12 11:45
     * @Param
     * @Return
     */
    @Override
    public void save(GraduateDestination jobDestination) {
        UUID stuId = jobDestination.getId();
        //修改毕业去向时没有修改学生信息
        StudentInfo studentInfo= studentInfoRepository.findById(stuId);
        if (studentInfo!=null) {
            jobDestination.setStudentInfo(studentInfo);
        }
        graduateDestinationRepostiory.save(jobDestination);
    }

    @Override
    public void delete(UUID id) {
        graduateDestinationRepostiory.delete(id);
    }


    @Override
    public void update(UUID id) {
        graduateDestinationRepostiory.upStatus(id);
    }


    /**
     * @Description 一键派遣回原籍.
     * @Author wangyj
     * @CreateDate 2017/5/11 14:31
     * @Param
     * @Return
     */
    @Override
    public String backToHomeland(String graduateDate) {
        String result = "";
        try {
            int i=graduateDestinationRepostiory.updateNotUnderemployed(graduateDate);
            int j =graduateDestinationRepostiory.updateUderemployed(graduateDate);
            result = "ok";
        } catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 教师端 老师修改学生的核对状态
     *
     * @return
     */
    @Override
    public String upStuStatus() {
        int graduateDate = LocalDate.now().getYear();
        int result = graduateDestinationRepostiory.upStuStatus("01", graduateDate);
        return result > 0 ? "ok" : "fail";
    }


}
