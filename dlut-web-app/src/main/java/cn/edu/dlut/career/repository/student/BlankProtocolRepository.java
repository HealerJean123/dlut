package cn.edu.dlut.career.repository.student;

import cn.edu.dlut.career.domain.student.BlankProtocol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/25.
 *
 * 空白协议书 数据处理层
 */
@Transactional
public interface BlankProtocolRepository extends CrudRepository<BlankProtocol,UUID>{

    /**
     * 根据学生id查找空白协议书
     * @param stuId
     * @return
     */
    List<BlankProtocol> findByStuId(UUID stuId);

    /**
     *该方法用于教师端 动态查询空白协议书
     * @param stuNo 学生学号 要求精确
     * @param name 学生姓名 可模糊
     * @param departAuditStatus 院审核状态
     * @param schAuditStatus 校审核状态
     * @param departmentId 院系
     * @param pageable
     * @return
     */
    @Query("from BlankProtocol b where (b.stuNo=?1 or ?1=null) and (b.name like %?2% or ?2=null) and (b.departAuditStatus=?3 or ?3=null) and (b.schAuditStatus=?4 or ?4=null) and (b.departmentId=?5 or ?5=null)")
    Page<BlankProtocol> findBlankProtocol(String stuNo, String name, String departAuditStatus,String schAuditStatus, String departmentId, Pageable pageable);

    /**
     * 修改院审核信息
     * @param id 空白协议书申请表id
     * @param auditStatus 审核状态
     * @param departAuditStatus 院审核状态
     * @param departAuditor 院审核人
     * @param departAuditTime 院审核时间
     * @param departNoPassReason 院审核不通过原因
     * @return
     */
    @Modifying
    @Query("update BlankProtocol set auditStatus=?2," +
        "departAuditStatus=?3," +
        "departAuditor=?4," +
        "departAuditTime=?5," +
        "departNoPassReason=?6 where id=?1")
    int updateDepartAudit(UUID id, String auditStatus, String departAuditStatus, String departAuditor, LocalDateTime departAuditTime, String departNoPassReason);

    /**
     * 修改校审核信息
     * @param id 空白协议书申请表id
     * @param auditStatus 审核状态
     * @param schAuditStatus 校审核状态
     * @param schAuditor 校审核人
     * @param schAuditTime 校审核时间
     * @param schNoPassReason 校审核不通过原因
     * @return
     */
    @Modifying
    @Query("update BlankProtocol set auditStatus=?2," +
        "schAuditStatus=?3," +
        "schAuditor=?4," +
        "schAuditTime=?5," +
        "schNoPassReason=?6 where id=?1")
    int updateSchAudit(UUID id, String auditStatus, String schAuditStatus, String schAuditor, LocalDateTime schAuditTime, String schNoPassReason);

    /**
     * @Description 根据毕业时间获取该届申请空白协议书尚未处理总数.
     * @Author  wangyj
     * @CreateDate 2017/4/28 16:20
     * @Param
     * @Return
     */
    @Query(value = "select count(*) from BlankProtocol b where b.endDate like ?1% and b.schAuditStatus='00'")
    int coutApplay(String graduateDate);

    /**
     * 空白协议书申请 待审核数量
     * @param departmentId
     * @param graduateDate
     * @return
     */
    @Query("select count(*) from BlankProtocol b " +
        "left join StudentInfo s on s.id=b.stuId " +
        "where s.departmentId=?1 and s.endDate like ?2% and b.departAuditStatus='00'")
    int findBlankAuditNum(String departmentId, String graduateDate);

}
