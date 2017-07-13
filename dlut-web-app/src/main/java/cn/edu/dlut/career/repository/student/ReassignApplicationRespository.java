package cn.edu.dlut.career.repository.student;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.ReassignApplication;
import cn.edu.dlut.career.domain.student.StudentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created by HealerJean on 2017/4/13.
 */
@Repository
public interface ReassignApplicationRespository extends CrudRepository<ReassignApplication,UUID> {


    List<ReassignApplication>  findAll();

    @Query(value = "From ReassignApplication as r where r.stuNo = ?1")
    ReassignApplication findByStuNo(String stuNo);



    /**
     *     教师端改派模糊查询
     */

    @Query(value = "From ReassignApplication as r where" +
        " (r.stuNo = ?1 or ?1 =null) and " +
        "(r.stuName like %?2% or ?2 =null) and  " +
        "(r.majorCode = ?3 or ?3 =null) and " +
        " (r.eduDegree = ?4 or ?4 =null) and " +
        "(r.departmentId = ?5 or ?5 =null) and " +
        "(r.auditStatus = ?6 or ?6 =null)")
    Page<ReassignApplication> queryGetReassignApplicationPage(String stuNo, String  stuName, String majorCode, String eduDegree, String departmentId, String auditStatus, Pageable pageable);



    /**
     * @Description 根据毕业时间获取该届违约申请尚未处理总数.
     * @Author  wangyj
     * @CreateDate 2017/4/28 16:26
     * @Param
     * @Return
     */
    @Query(value = " select count(*) From ReassignApplication as r where r.endDate like ?1% and r.auditStatus='00'")
    int ressignCount(String graduateDate);
}
