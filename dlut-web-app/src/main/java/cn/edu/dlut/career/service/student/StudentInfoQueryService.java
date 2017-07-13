package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.company.CStudentDTO;
import cn.edu.dlut.career.dto.student.StuLoginDTO;
import cn.edu.dlut.career.dto.student.StudentInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * 学生学籍信息表 服务层接口
 * Created by HealerJean on 2017/4/12.
 */
public interface StudentInfoQueryService {


    //根据id查询
    StudentInfo findById(UUID id);

    //查询所有的数据
    List<StudentInfo> findAllStudentInfo();

    //查询登录初始信息
    StuLoginDTO findLoginInfo(String stuNo);


    //判断是不是有这个密码
    String findPwd(UUID id, String pwd, String originalPwd, String salt);


    //分页查询所有学生的学籍
    Page<StudentInfo> listDepartStuPage(String name, String stuNo, String gender, String ethnic, String departmentId, String majorCode, String eduYear, String eduMode, String endDate, String tutorName, String status, String eduDegree, Pageable pageable);

    //分页查询 本学院 所有学生的学籍
    Page<StudentInfo> listMyDepartStuPage(String name, String stuNo, String gender, String ethnic, String departmentId, String majorCode, String eduYear, String eduMode, String endDate, String tutorName, String status, String eduDegree, String haveReport, Pageable pageable);


    //通过学号查找学生
    StudentInfo findByStuNo(String stuNo);

    /**
     * @author wei  2017/5/27
     * @method findDynamic
     * @param stuNo 学号, mobilephone 电话, name 姓名
     * @return java.util.List<cn.edu.dlut.career.dto.company.CStudentDTO>
     * @description  企业端查询生源信息
     */
    List<CStudentDTO> findDynamic(String stuNo, String mobilephone, String name);
}
