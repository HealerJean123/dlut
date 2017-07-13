package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.RecBulletin;
import cn.edu.dlut.career.dto.company.RecBulletinDTO2;
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
 * Created by 史念念 on 2017/4/7.
 *
 * 招聘简章 持久层
 */
@Transactional
public interface RecBulletinRepository extends CrudRepository<RecBulletin,UUID> {

    /**
     * 查询所有招聘简章
     * @return
     */
    List<RecBulletin> findAll();

    /**
     * 根据id查找招聘简章
     * @return
     */
    RecBulletin findById(UUID id);

    /**
     * 根据公司id查找招聘简章
     * @param recId
     * @return
     */
    List<RecBulletin> findByRecId(UUID recId);

    /**
     * 根据标题关键字查找招聘简章
     * @param keywords
     * @return
     */
    @Query("from RecBulletin where title like %?1%")
    List<RecBulletin> findByKeywords(String keywords);

    /**
     * 修改审核信息
     * @param id 招聘简章id
     * @param auditStatus 审核状态
     * @param auditTime 审核时间
     * @param auditor 审核人
     * @param nopassReason 审核不通过原因
     * @return
     */
    @Modifying
    @Query("update RecBulletin set auditStatus=?2,auditTime=?3,auditor=?4,nopassReason=?5 where id=?1")
    int updateAudit(UUID id,
                    String auditStatus,
                    LocalDateTime auditTime,
                    String auditor,
                    String nopassReason);


    @Query("FROM  RecBulletin as r where (r.title like %?1% or ?1 = null) AND (r.auditStatus=?2) AND (r.startTime<=?3) AND (r.endTime>=?4)")
    List<RecBulletin> findByCondition(String title, String auditStatus, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 企业端 动态查询招聘简章
     * @param recId 企业id
     * @param pTime 发布时间
     * @param auditStatus 审核状态
     * @param title 标题
     * @param pageable
     * @return
     */
    @Query("select new cn.edu.dlut.career.dto.company.RecBulletinDTO2(" +
        "r.id,r.title,r.startTime,r.endTime," +
        "r.auditStatus,r.publishTime,r.onlineStatus) " +
        "from RecBulletin r where (r.recId=?1 or ?1=null) " +
        "and (r.publishTime>=?2 or cast(?2 as timestamp)=null) " +
        "and (r.auditStatus=?3 or ?3=null) " +
        "and (r.title like %?4% or ?4=null)")
    Page<RecBulletinDTO2> findBulletin(UUID recId, LocalDateTime pTime, String auditStatus, String title, Pageable pageable);

    /**
     * @Autor 史念念 2017/6/2
     * @Description 教师端 动态查询 招聘简章信息
     * @param pTime
     * @param auditStatus
     * @param title
     * @param recName
     * @param pageable
     * @return
     */
    @Query("select new cn.edu.dlut.career.dto.company.RecBulletinDTO2(" +
        "rb.id,rb.title,rb.recId,c.name,rb.startTime,rb.endTime," +
        "rb.auditStatus,rb.publishTime,rb.onlineStatus) " +
        "from RecBulletin rb left join CompanyInfo c " +
        "on rb.recId=c.id " +
        "where (rb.publishTime>=?1 or cast(?1 as timestamp)=null) " +
        "and (rb.auditStatus=?2 or ?2=null) " +
        "and (rb.title like %?3% or ?3=null) " +
        "and (c.name like %?4% or ?4=null)")
    Page<RecBulletinDTO2> teaFindBulletin(LocalDateTime pTime, String auditStatus, String title, String recName, Pageable pageable);

    /**
     * @Autor 史念念 2017/6/5
     * @Description 学生端动态查询招聘简章
     * @param pTime 发布日期
     * @param title 标题
     * @param recName 公司名
     * @param nature 公司性质
     * @param pageable
     * @return
     */
    @Query("select new cn.edu.dlut.career.dto.company.RecBulletinDTO2(" +
        "rb.id,rb.title,rb.recId,c.name,rb.startTime,rb.endTime," +
        "rb.auditStatus,rb.publishTime,rb.onlineStatus) " +
        "from RecBulletin rb left join CompanyInfo c " +
        "on rb.recId=c.id " +
        "where (rb.publishTime>=?1 or cast(?1 as timestamp)=null) " +
        "and (rb.title like %?2% or ?2=null) " +
        "and (c.name like %?3% or ?3=null) " +
        "and (c.nature=?4 or ?4=null) " +
        "and rb.auditStatus='01'")
    Page<RecBulletinDTO2> stuFindBulletin(LocalDateTime pTime, String title, String recName, String nature, Pageable pageable);
}
