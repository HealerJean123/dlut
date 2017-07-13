package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.dto.company.RecJobPositionDTO2;
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
 * Created by 史念念 on 2017/3/23.
 * <p>
 * 招聘职位持久层
 */
@Transactional
public interface RecJobPositionRepository extends CrudRepository<RecJobPosition, UUID> {

    /**
     * 查询全部招聘职位信息
     *
     * @return
     */
    List<RecJobPosition> findAll();

    /**
     * 批量保存职位
     *
     * @return
     */
    List<RecJobPosition> save(List<RecJobPosition> recJobPositions);

    /**
     * 根据编号查找公司招聘信息
     *
     * @param id
     * @return
     */
    RecJobPosition findById(UUID id);

    /**
     * 根据公司编号查找招聘信息
     *
     * @param recId
     * @return
     */
    List<RecJobPosition> findByRecId(UUID recId);

    /**
     * 修改审核状态,审核人,审核时间,未通过原因
     *
     * @param auditStatus
     * @return
     */
    @Modifying
    @Query("update RecJobPosition  set auditStatus=?2 ,auditor=?3 ," +
        "auditTime=?4,nopassReason=?5 where id = ?1")
    int updateAudit(UUID id,
                    String auditStatus,
                    String auditor,
                    LocalDateTime auditTime,
                    String nopassReason);


    /**
     * 根据招聘简章id查找招聘职位数量
     *
     * @param bulletinId
     * @return
     */
    @Query(value = "select count(*) from rec_job_position jp left join rec_bulletin_job bj on bj.job_id=jp.id where bj.bulletin_id=?1", nativeQuery = true)
    int findByBulId(UUID bulletinId);

    /**
     * 企业端 根据条件动态查询招聘职位信息
     * @param recId 公司id
     * @param name 职位名
     * @param auditStatus 审核状态
     * @param positionType 职位类型
     * @param eduDegree 学历
     * @param publishTime 发布时间
     * @param pageable
     * @return
     */
    @Query("from RecJobPosition r where recId=?1 and " +
        "(name=?2 or ?2=null) and " +
        "(auditStatus=?3 or ?3=null) and " +
        "(type=?4 or ?4=null) and " +
        "(degree=?5 or ?5=null) and " +
        "(publishTime>?6 or cast(?6 as timestamp)=null)")
    Page<RecJobPosition> findByCondition(UUID recId,
                                         String name,
                                         String auditStatus,
                                         String positionType,
                                         String eduDegree,
                                         LocalDateTime publishTime,
                                         Pageable pageable);

    /**
     * @Autor 史念念 2017/6/5
     * @Description 教师端 动态查询招聘职位信息
     * @param name 职位名
     * @param recName 公司名
     * @param auditStatus 审核状态
     * @param positionType 职位类型
     * @param eduDegree 学历
     * @param major 专业
     * @param pTime 发布时间
     * @param pageable
     * @return
     */
    @Query("select new cn.edu.dlut.career.dto.company.RecJobPositionDTO2(" +
        "jp.id,c.name,jp.name,jp.type,jp.degree,jp.major," +
        "jp.city,jp.recruitmentNum,jp.auditStatus,jp.startTime,jp.endTime," +
        "jp.publishTime,jp.onlineStatus ) " +
        "from RecJobPosition jp left join CompanyInfo c on c.id=jp.recId " +
        "where (jp.name like %?1% or ?1=null) " +
        "and (c.name like %?2% or ?2=null) " +
        "and (jp.auditStatus =?3 or ?3=null) " +
        "and (jp.type=?4 or ?4=null) " +
        "and (jp.degree=?5 or ?5=null) " +
        "and (jp.major=?6 or ?6=null) " +
        "and (jp.publishTime>=?7 or cast(?7 as timestamp)=null)")
    Page<RecJobPositionDTO2> teaQueryByCondition(String name,
                                                 String recName,
                                                 String auditStatus,
                                                 String positionType,
                                                 String eduDegree,
                                                 String major,
                                                 LocalDateTime pTime,
                                                 Pageable pageable);

    /**
     * @Autor 史念念 2017/6/6
     * @Description 学生端 动态查询招聘职位信息
     * @param name 职位名
     * @param recName 公司名
     * @param positionType 职位类型
     * @param eduDegree 学历
     * @param major 专业
     * @param nature 单位性质
     * @param publishTime 发布时间
     * @param pageable
     * @return
     */
    @Query("select new cn.edu.dlut.career.dto.company.RecJobPositionDTO2(" +
        "jp.id,c.name,jp.name,jp.type,jp.degree,jp.major," +
        "jp.city,jp.recruitmentNum,jp.auditStatus,jp.startTime,jp.endTime," +
        "jp.publishTime,jp.onlineStatus ) " +
        "from RecJobPosition jp left join CompanyInfo c on c.id=jp.recId " +
        "where (jp.name like %?1% or ?1=null) " +
        "and (c.name like %?2% or ?2=null) " +
        "and (jp.type=?3 or ?3=null) " +
        "and (jp.degree=?4 or ?4=null) " +
        "and (jp.major=?5 or ?5=null) " +
        "and (c.nature=?6 or ?6=null) " +
        "and (jp.publishTime>=?7 or cast(?7 as timestamp)=null) " +
        "and jp.auditStatus='01'")
    Page<RecJobPositionDTO2> stuQueryByCondition(String name, String recName, String positionType, String eduDegree, String major, String nature, LocalDateTime publishTime,Pageable pageable);
}
