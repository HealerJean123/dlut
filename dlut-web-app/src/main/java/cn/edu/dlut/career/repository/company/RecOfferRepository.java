package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.RecOffer;
import cn.edu.dlut.career.dto.company.RecOfferDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/4/13.
 */
public interface RecOfferRepository extends CrudRepository<RecOffer,UUID> {

    /**
     * 根据学号查询offer
     * @return
     */
    @Query(value = "select new cn.edu.dlut.career.dto.company.RecOfferDTO(r.id, r.recName, r.closingDate, r.category, r.hasReportCard, r.isPfile, r.isSolveHukou, r.stuReceivingStatus,r.auditStatus) from RecOffer r where r.stuNo=?1")
    List<RecOfferDTO> findByStuNo(String stuNo);

    @Query(value = "from RecOffer r where r.stuNo=?1 and r.stuReceivingStatus='04'")
    RecOffer findRecOfferByStuNo(String stuNo);

    @Query(value = "from RecOffer r where r.stuNo=?1 and r.stuReceivingStatus =?2")
    RecOffer findRecOfferByStuNoAndStatus(String stuNo,String stuReceivingStatus );

    /**
     * 根据学号带状态的查询offer
     * @param stuNo
     * @param stuReceivingStatus
     * @return
     */
    @Query(value = "select new cn.edu.dlut.career.dto.company.RecOfferDTO(r.id, r.recName, r.closingDate, r.category, r.hasReportCard, r.isPfile, r.isSolveHukou, r.stuReceivingStatus,r.auditStatus) from RecOffer r where r.stuNo=?1 and r.stuReceivingStatus=?2")
    List<RecOfferDTO> findByStuNos(String stuNo,String stuReceivingStatus);


    @Query(value = "From RecOffer r WHERE (stuNo = ?1 or ?1 = null) AND (departmentId = ?3 or ?3 = null) AND (realName like %?2% or ?2 = null)" +
        " AND (eduDegree = ?4 or ?4 = null) AND (endDate like ?5% or ?5 = null) AND (stuReceivingStatus = ?6 or ?6 = null) AND (majorCode = ?7 or ?7 = null)" +
        " AND (recName like %?8% or ?8 = null)")
    Page<RecOffer> findRecOfferDynamic(String stuNo, String realName, String departmentId, String eduDegree, String endDate, String stuReceivingStatus, String majorCode, String recName, Pageable pageable);


    /**
     * 根据offer id 查找公司名
     * @param id
     * @return
     */
    @Query("select recName from RecOffer where id=?1")
    String findById(UUID id);

    /**
     * 修改学生offer 接收状态
     * @param stuReceivingStatus
     * @return
     */
    @Modifying
    @Query("update RecOffer set stuReceivingStatus=?2 where id=?1")
    int updateStuReceivingStatus(UUID id,String stuReceivingStatus);

    @Query(value = "From RecOffer r WHERE (stuNo = ?1 or ?1 = null) AND (realName like %?2% or ?2 = null) AND (department = ?3 or ?3 = null)" +
        " AND (eduDegree = ?4 or ?4 = null) AND (endDate = ?5 or ?5 = '') AND (stuReceivingStatus = ?6 or ?6 = null) AND (majorCode = ?7 or ?7 = null)" +
        " AND (recName like %?8% or ?8 = null) AND (auditStatus in ('0','1')) AND (stuReceivingStatus = '01')")
    Page<RecOffer> findRecOfferDynamicByAcademy(String stuNo, String realName, String department, String eduDegree, String endDate, String stuReceivingStatus, String majorCode, String recName, Pageable pageable);

    @Query(value = "From RecOffer r WHERE (stuNo = ?1 or ?1 = null) AND (realName like %?2% or ?2 = null) AND (department = ?3 or ?3 = null)" +
        " AND (eduDegree = ?4 or ?4 = null) AND (endDate = ?5 or ?5 = '') AND (stuReceivingStatus = ?6 or ?6 = null) AND (majorCode = ?7 or ?7 = null)" +
        " AND (recName like %?8% or ?8 = null) AND (auditStatus in ('01','03','04')) AND (stuReceivingStatus = '01')")
    Page<RecOffer> findRecOfferDynamicBySchool(String stuNo, String realName, String department, String eduDegree, String endDate, String stuReceivingStatus, String majorCode, String recName, Pageable pageable);

    @Modifying
    @Query(value = "update rec_offer SET audit_status = ?2,stu_receiving_status=?3 where id=?1",nativeQuery = true)
    void update(UUID uuid,String auditStatus,String stuReceivingStatus);

    /**
     * @Description 根据毕业时间获取签约总数.
     * @Author  wangyj
     * @CreateDate 2017/4/28 15:54
     * @Param
     * @Return
     */
    @Query(value = "select count(*) from RecOffer r where r.endDate like ?1% and r.stuReceivingStatus ='04'")
    int countSign(String graduateDate);

    /**
     * 签约待审核数量
     * @param departmentId
     * @return
     */
    @Query("select count(*) from RecOffer r where r.departmentId=?1 and r.endDate like ?2% and r.auditStatus='00'")
    int findSignAuditNum(String departmentId,String endDate);

    /**
     * 学生查看有无已经签约或者接收的offer
     * @param stuNo
     * @return
     */
    @Query("select new cn.edu.dlut.career.dto.company.RecOfferDTO(r.id, r.recName, r.closingDate, r.category, r.hasReportCard, r.isPfile, r.isSolveHukou, r.stuReceivingStatus,r.auditStatus) from RecOffer r where r.stuNo=?1 and r.stuReceivingStatus in ('01','04')")
    List<RecOfferDTO> findByStuNos(String stuNo);

    @Query("select new cn.edu.dlut.career.dto.company.RecOfferDTO(r.id, r.recName, r.closingDate, r.category, r.hasReportCard, r.isPfile, r.isSolveHukou, r.stuReceivingStatus,r.auditStatus) from RecOffer r where r.stuId=?1 and r.stuReceivingStatus in ('01','04')")
    RecOfferDTO findByStuId(UUID id);

    /**
     * 企业动态查询offer信息
     * @param stuNo 学号
     * @param stuName 姓名
     * @param signTime 签约时间
     * @param eduDegree 学历
     * @param academy 院系
     * @param major 专业
     * @param graduationTime 毕业时间
     * @param offerStatus 签约状态
     * @param recName 公司名
     * @param pageable
     * @return
     */
    @Query("from RecOffer r where (stuNo=?1 or ?1=null) " +
        "and (realName=?2 or ?2=null) " +
        "and (signTime=?3 or cast(?3 as date)=null) " +
        "and (eduDegree=?4 or ?4=null) " +
        "and (departmentId=?5 or ?5=null) " +
        "and (majorCode=?6 or ?6=null) " +
        "and (endDate=?7 or ?7=null) " +
        "and (stuReceivingStatus=?8 or ?8=null)" +
        "and recName=?9")
    Page<RecOffer> findOffer(String stuNo,
                            String stuName,
                            LocalDate signTime,
                            String eduDegree,
                            String academy,
                            String major,
                            String graduationTime,
                            String offerStatus,
                            String recName,
                            Pageable pageable);


    List<RecOffer> findByRecIdAndStuId(UUID id, UUID stuId);

    /**
     * @Autor 史念念 2017/6/5
     * @Description 企业端 取消已发送的offer
     * @param id offer id
     * @return
     */
    @Modifying
    @Query("delete from RecOffer r where r.id=?1 and r.stuReceivingStatus='00'")
    int deleteOffer(UUID id);
}
