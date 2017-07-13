package cn.edu.dlut.career.repository.student;

import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.company.CStudentDTO;
import cn.edu.dlut.career.dto.student.StuLoginDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 学生学籍信息 数据层
 * Created by HealerJean on 2017/4/12.
 */
@Repository
@Transactional
public interface StudentInfoRepository extends CrudRepository<StudentInfo,UUID>{

    StudentInfo findById(UUID id);

    List<StudentInfo> findAll();

    List<StudentInfo> save(List<StudentInfo> studentInfos);

    @Query(value = "select new cn.edu.dlut.career.dto.student.StuLoginDTO (s.id ,s.name,s.stuNo,s.pwd ,s.salt )from StudentInfo s where s.stuNo=?1")
    StuLoginDTO findLoginInfo(String stuNo);


    // 修改密码
    @Modifying
    @Query(value= "update StudentInfo s set s.pwd =?2 where s.id =?1")
    int updatePwd(UUID id,String newPwd);



    /**
     * 教师端
     *  查找本院系学生的学籍
     *  分页查询
     * @return
     */

    @Query("from StudentInfo as s where (" +
        "s.name like %?1% or ?1 =null) and " +
        "(s.stuNo =?2 or ?2 = null) and " +
        "(s.gender =?3 or ?3 = null)  and " +
        "(s.ethnic =?4 or ?4 = null) and" +
        " (s.departmentId =?5 or ?5 = null) and " +
        "(s.majorCode =?6 or ?6 = null) and " +
        "(s.eduYear =?7 or ?7 = null) and " +
        "(s.eduMode =?8 or ?8 = null) and " +
        "(s.endDate like %?9% or ?9 =null) and " +
        "(s.tutorName =?10 or ?10 = null) and " +
        "(s.status =?11 or ?11 = null) and " +
        "(s.eduDegree =?12 or ?12 = null) and "+
        "(s.haveReport =?13 or ?13 = null)")
    Page<StudentInfo> queryGetMyDepartStusPage(
        String name,
        String stuNo,
        String gender,
        String ethnic,
        String departmentId,
        String majorCode,
        String eduYear ,
        String eduMode,
        String endDate ,
        String tutorName,
        String status,
        String eduDegree,
        String haveReport,
        Pageable pageable);

    /**
     * 分页查询
     * 查找所有学生学籍
     */
    @Query("from StudentInfo as s where (" +
        "s.name like %?1% or ?1 =null) and " +
        "(s.stuNo =?2 or ?2 = null) and " +
        "(s.gender =?3 or ?3 = null)  and " +
        "(s.ethnic =?4 or ?4 = null) and" +
        " (s.departmentId =?5 or ?5 = null) and " +
        "(s.majorCode =?6 or ?6 = null) and " +
        "(s.eduYear =?7 or ?7 = null) and " +
        "(s.eduMode =?8 or ?8 = null) and " +
        "(s.endDate like %?9% or ?9 =null) and " +
        "(s.tutorName =?10 or ?10 = null) and " +
        "(s.status =?11 or ?11 = null) and " +
        "(s.eduDegree =?12 or ?12 = null)")
    Page<StudentInfo> queryGetDepartStusPage(
        String name,
        String stuNo,
        String gender,
        String ethnic,
        String departmentId,
        String majorCode,
        String eduYear ,
        String eduMode,
        String endDate ,
        String tutorName,
        String status,
        String eduDegree,
        Pageable pageable);






    //根据学生id进行查找
    StudentInfo findByStuNo(String stuNo);

    /**
     * 查询某一院系在某一年的毕业人数
     * @param departmentId 院系id
     * @param endDate 毕业时间
     * @return
     */
    @Query("select count(*) from StudentInfo s where s.departmentId=?1 and s.endDate like ?2%")
    int findStuTotalNum(String departmentId, String endDate);

    /**
     * 学生学籍待审核数量
     * @param departmentId
     * @param graduateDate
     * @return
     */
    @Query("select count(*) from StudentInfo s where s.departmentId=?1 and s.endDate like ?2% and s.status='00' and s.haveReport='1'")
    int findStuInfoAuditNum(String departmentId, String graduateDate);

    /**
     * @author wei  2017/5/27
     * @method findDynamic
     * @param [stuNo 学号, mobilephone 电话, name 姓名]
     * @return java.util.List<cn.edu.dlut.career.dto.company.CStudentDTO>
     * @description  企业端查询生源信息
     */
        @Query(value = "select new cn.edu.dlut.career.dto.company.CStudentDTO(s.id, s.stuNo, s.name, s.gender, s.eduDegree, s.majorCode, s.eduMode, s.homelandCode, s.homelandName, s.eduYear, s.endDate) from StudentInfo as s where (s.name = ?3 or ?3 ='') and " +
        "(s.stuNo =?1 or ?1 = '') and " +
        "(s.mobilephone =?2 or ?2 = '') ")
    List<CStudentDTO> findDynamic(String stuNo, String mobilephone, String name);

//    /**
//     * 动态查询学生就业去向
//     * @param stuNo
//     * @param name
//     * @param department
//     * @param eduDegree
//     * @param endDate
//     * @param stuStatus
//     * @param majorCode
//     * @param recName
//     * @param pageable
//     * @return
//     */
//    @Query(value = "From StudentInfo as s where " +
//        "(s.stuNo = ?1 or ?1 = '') and " +
//        "(s.name like %?2% or ?2 = '') and " +
//        "(s.department like %?3% or ?3 = '') and " +
//        "(s.eduDegree = ?4 or ?4 = '') and " +
//        "(s.endDate like ?5% or ?5= '') and " +
//        "(s.jobDestination.stuStatus = ?6 or ?6 = '') and " +
//        "(s.majorCode = ?7 or ?7 = '') and " +
//        "(s.jobDestination.recName = ?8 or ?8 = '')")
//    Page<StudentInfo> findList(
//        String stuNo,
//        String name,
//        String department,
//        String eduDegree,
//        String endDate,
//        String stuStatus,
//        String majorCode,
//        String recName,
//        Pageable pageable);
}
