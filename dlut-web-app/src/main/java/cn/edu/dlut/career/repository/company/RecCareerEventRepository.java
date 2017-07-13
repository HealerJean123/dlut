package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.RecCareerEvent;
import cn.edu.dlut.career.dto.company.RecCareerEventDTO2;
import cn.edu.dlut.career.dto.company.SpecialDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/24.
 *
 * 专场招聘会预约 持久层
 */
@Repository
public interface RecCareerEventRepository extends CrudRepository<RecCareerEvent,UUID> {

  /**
   * 查询所有专场招聘会预约信息
   * @return
   */
 List<RecCareerEvent> findAll();

  /**
   * 根据id查找专场招聘会信息
   * @param id
   * @return
   */
 RecCareerEvent findById(UUID id);

  /**
   * 根据公司Id查找专场招聘会预约信息
   * @param recId
   * @return
   */
 List<RecCareerEvent> findByRecId(UUID recId);

    @Query("select new cn.edu.dlut.career.dto.company.SpecialDTO(r.id, r.fairName, r.applicationDate, r.startDate, r.auditsStatus, f.auditStatus) from RecCareerEvent r,Field f where" +
        "(r.fairName like %?1% or ?1 = '') AND (r.applicationDate = ?2 or ?2 = '') AND (r.startDate = ?3 or ?3 = '') AND (r.auditsStatus = ?4 or ?4 = '')AND r.recId=?5 AND r.fieldId = f.id")
    Page<SpecialDTO> findList(String fairName, String applicationDate, String startDate, String auditsStatus,UUID recId, Pageable pageable);


    @Query("select new cn.edu.dlut.career.dto.company.SpecialDTO(r.id, r.fairName, r.applicationDate, r.startDate, r.auditsStatus, f.auditStatus) from RecCareerEvent r,Field f where" +
        "(r.fairName like %?1% or ?1 = '') AND (r.applicationDate = ?2 or ?2 = '') AND (r.startDate = ?3 or ?3 = '') AND (r.auditsStatus = ?4 or ?4 = '') AND r.fieldId = f.id")
    Page<SpecialDTO> findListBySchool(String fairName, String applicationDate, String startDate, String auditsStatus, Pageable pageable);

    /**
   * 修改审核及回执信息
   * @param auditStatus
   * @return
   */
//  @Modifying
//  @Query("update RecCareerEvent set auditStatus=:auditStatus, " +
//         "auditSuggest=:auditSuggest, areaAddress=:areaAddress, " +
//         "areaCost=:areaCost, receiver=:receiver, " +
//         "receiverTel=:receiverTel, auditTime=:auditTime, " +
//         "auditor=:auditor," +
//         "fairStartTime=:fairStartTime, fairEndTime=:fairEndTime where id=:id")
//  int updateAudit(@Param("id") UUID id,
//                  @Param("auditStatus") String auditStatus,
//                  @Param("auditSuggest") String auditSuggest,
//                  @Param("areaAddress") String areaAddress,
//                  @Param("areaCost") Float areaCost,
//                  @Param("receiver") String receiver,
//                  @Param("receiverTel") String receiverTel,
//                  @Param("auditTime") LocalDateTime auditTime,
//                  @Param("auditor") String auditor,
//                  @Param("fairStartTime") LocalDateTime fairStartTime,
//                  @Param("fairEndTime") LocalDateTime fairEndTime);



    /**
     *  张宇晋
     *  动态查询 ，通过 申请日期，开始日期，审核状态，以及 招聘会名称
     *  查询专场招聘预约 申请表
     * @return
     */

//    @Query("FROM  RecCareerEvent as r where (r.applicationTime >=?1 or ?1 = null)AND (r.applicationTime <=?2 or ?2 = null) AND (r.fairStartTime >=?3 or ?3 = null) AND (r.fairEndTime <=?4 or ?4 = null) AND (r.auditStatus=?5) AND (r.fairName like %?6% or ?6 = null)")
//    List<RecCareerEvent> queryRecByCondition(LocalDateTime applicationTime,LocalDateTime applicationEndTime, LocalDateTime fairStartTime,LocalDateTime fairEndTime, String auditStatus,String fairName);

    /**
     * 通过招聘会结束时间查找专场招聘会信息
     */
//    @Query("select new cn.edu.dlut.career.dto.company.RecCareerEventDTO2 (" +
//        "rce.id,rce.bulletinId,count(rbj.bulletinId),rce.fairName," +
//        "rce.fairStartTime,rce.fairEndTime,rce.recId,rce.recName," +
//        "rce.recAddress,rce.contacts,rce.conTel,rce.conEmail," +
//        "rce.areaRequire,rce.areaSize,rce.areaNum,rce.areaUsing," +
//        "rce.auditStatus,rce.auditSuggest,rce.areaAddress," +
//        "rce.areaCost,rce.receiver,rce.receiverTel,rce.auditTime," +
//        "rce.auditor,rce.payType,rce.payTime,rce.applicationTime," +
//        "rce.updateTime,rce.remarks ) " +
//        "from RecCareerEvent rce left join RecBulletin rb on rb.id=rce.bulletinId " +
//        "left join RecBulletinJob rbj on rbj.bulletinId=rb.id " +
//        "where (rce.fairStartTime>=?1 or cast(?1 as timestamp)=null) and " +
//        "(rce.fairEndTime<=?2 or cast(?2 as timestamp)=null) and " +
//        "rce.auditStatus='4' " +
//        "group by rce.id")
//    Page<RecCareerEventDTO2> findByFairEndTime(LocalDateTime fairStartTime, LocalDateTime fairEndTime, Pageable pageable);
}
