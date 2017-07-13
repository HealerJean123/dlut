package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.StudentInfo;
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
public interface StudentInfoCommandService {
    //保存，添加
    StudentInfo saveStudentInfo(StudentInfo studentInfo) ;

    // 学生更新
    StudentInfo updateStudentInfo(StudentInfoDTO studentInfoDTO);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteStudentInfo(UUID id);


    //保存学生信息 list集合形式
    void saveStudentInfos(List<StudentInfo> studentInfos);


    //修改密码
    int updatePwd(UUID id,String newPwd,String salt);



    //教师更新学生信息
    StudentInfo teaUpdateStudentInfo(StudentInfoDTO studentInfoDTO, String teaDepartId) ;

    //教师直接 审核学生单个审核信息
    StudentInfo teaUpdateStuInfoAuditStatus(UUID id) ;

    //教师学籍批量审核
    List<StudentInfo> stuInfoBatchAudit(UUID []id);









}
