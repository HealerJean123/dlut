package cn.edu.dlut.career.repository.student;

import cn.edu.dlut.career.domain.student.ViolateApplication;
import cn.edu.dlut.career.dto.student.ViolateApplicationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 *  违约申请 数据层
 * Created by HealerJean on 2017/4/13.
 */
public interface ViolateApplicationRepository extends CrudRepository<ViolateApplication,UUID>{
    List<ViolateApplication> findAll();

    /**
     * 动态查询 违约申请
     * 该方法用于教师端 对违约申请的查询
     * @param stuNo 学号
     * @param stuName 姓名
     * @param department 院系
     * @param majorName 专业
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param departAuditStatus 院审核状态
     * @param schAuditStatus 校审核状态
     * @return
     */
    @Query("select new cn.edu.dlut.career.dto.student.ViolateApplicationDTO( " +
        "va.id,va.stuNo,va.stuName,va.departmentId,va.department,va.majorCode,va.majorName,ro.recName,va.recOfferId, " +
        "va.reason,va.applyReason,va.cost,va.auditStatus,va.departAuditStatus,va.departAuditTime, " +
        "va.departAuditor,va.departNoPassReason,va.schAuditStatus,va.schAuditTime, " +
        "va.schAuditor,va.schNoPassReason,va.applicationTime) " +
        "from ViolateApplication va left join RecOffer ro on ro.id=va.recOfferId " +
        "where (va.stuNo=?1 or ?1=null) and " +
        "(va.stuName like %?2% or ?2=null) and " +
        "(va.departmentId=?3 or ?3=null) and " +
        "(va.majorName=?4 or ?4=null) and " +
        "(va.applicationTime>=?5 or cast(?5 as timestamp)=null) and " +
        "(va.applicationTime<=?6 or cast(?6 as timestamp)=null) and " +
        "(va.departAuditStatus=?7 or ?7=null) and " +
        "(va.schAuditStatus=?8 or ?8=null)")
    Page<ViolateApplicationDTO> findByKey(String stuNo,
                                          String stuName,
                                          String departmentId,
                                          String majorName,
                                          LocalDateTime startTime,
                                          LocalDateTime endTime,
                                          String departAuditStatus,
                                          String schAuditStatus,
                                          Pageable pageable);

    /**
     * 根据违约申请id查找违约申请信息
     * @param id
     * @return
     */
    @Query("select new cn.edu.dlut.career.dto.student.ViolateApplicationDTO( " +
        "va.id,va.stuNo,va.stuName,va.departmentId,va.department,va.majorCode,va.majorName,ro.recName,va.recOfferId, " +
        "va.reason,va.applyReason,va.cost,va.auditStatus,va.departAuditStatus,va.departAuditTime, " +
        "va.departAuditor,va.departNoPassReason,va.schAuditStatus,va.schAuditTime, " +
        "va.schAuditor,va.schNoPassReason,va.applicationTime) " +
        "from ViolateApplication va left join RecOffer ro on ro.id=va.recOfferId " +
        "where va.id=?1")
    ViolateApplicationDTO findById(UUID id);

    @Query("from ViolateApplication  v where v.stuNo = ?1")
    ViolateApplication findByStuNo(String stuNo);

    /**
     * 修改院系审核信息
     * @param id
     * @param auditStatus 审核状态
     * @param departAuditStatus 院系审核状态
     * @param departAuditor 审核人
     * @param departAuditTime 审核时间
     * @param departNoPassReason 审核不通过原因
     * @return
     */
    @Modifying
    @Query("update ViolateApplication set " +
           "auditStatus=:auditStatus," +
           "departAuditStatus=:departAuditStatus," +
           "departAuditor=:departAuditor," +
           "departAuditTime=:departAuditTime," +
           "departNoPassReason=:departNoPassReason " +
           "where id=:id")
    int updateDepartAudit(@Param("id") UUID id,
                          @Param("auditStatus") String auditStatus,
                          @Param("departAuditStatus") String departAuditStatus,
                          @Param("departAuditor") String departAuditor,
                          @Param("departAuditTime") LocalDateTime departAuditTime,
                          @Param("departNoPassReason") String departNoPassReason);

    /**
     * 修改校级审核信息
     * @param id
     * @param auditStatus 审核状态
     * @param schAuditStatus 校级审核状态
     * @param schAuditor 校级审核人
     * @param schAuditTime 校级审核时间
     * @param schNoPassReason 校级审核不通过原因
     * @return
     */
    @Modifying
    @Query("update ViolateApplication set " +
        "auditStatus=:auditStatus," +
        "schAuditStatus=:schAuditStatus," +
        "schAuditor=:schAuditor," +
        "schAuditTime=:schAuditTime," +
        "schNoPassReason=:schNoPassReason " +
        "where id=:id")
    int updateSchAudit(@Param("id") UUID id,
                       @Param("auditStatus") String auditStatus,
                       @Param("schAuditStatus") String schAuditStatus,
                       @Param("schAuditor") String schAuditor,
                       @Param("schAuditTime") LocalDateTime schAuditTime,
                       @Param("schNoPassReason") String schNoPassReason);


    @Query("select count(*) from ViolateApplication v where v.stuNo=?1")
    int countStuInfo(String stuNo);

    /**
     * @Description 根据毕业时间获取违约总数.
     * @Author  wangyj
     * @CreateDate 2017/4/28 15:59
     * @Param
     * @Return
     */
    @Query(value = "select count(v.id) from ViolateApplication v left join StudentInfo s on v.stuNo = s.stuNo where  v.schAuditStatus='00' and s.endDate like ?1%")
    int countUnsign(String graduateDate);


    /**
     * 违约申请 待审核数量
     * @param departmentId
     * @param graduateDate
     * @return
     */
    @Query("select count(*) from ViolateApplication v " +
        "left join StudentInfo s on s.stuNo=v.stuNo " +
        "where v.departmentId=?1 and s.endDate like ?2% and v.departAuditStatus='00'")
    int findViolateAuditNum(String departmentId, String graduateDate);
}
