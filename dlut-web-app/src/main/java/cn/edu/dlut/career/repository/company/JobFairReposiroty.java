package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.JobFair;
import cn.edu.dlut.career.dto.company.JobFairDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会信息表 数据操作层
 * Created by HealerJean on 2017/4/6.
 */
@Repository
public interface JobFairReposiroty extends CrudRepository<JobFair, UUID> {
    //查找全部信息
    List<JobFair> findAll();

    //根据id进行查找
    JobFair findById(UUID id);

//    /**
//     * @Description 招聘会信息分页查询
//     * @Author HealerJean
//     * @CreateDate 2017/5/27 16:44
//     */
//    @Query(value = "select new cn.edu.dlut.career.dto.company.JobFairDTO " +
//        "(j.id,j.recNum,j.name,j.fairStartTime,j.location,j.needCost,c.auditStatus) " +
//        "from  JobFair j left join  CarrerFairEntry c on j.id = c.recJobFairId  " +
//        "where (j.name like %?1% or ?1 = null or ?1 ='') and " +
//        "(j.location like %?2% or ?2 = null or ?2 ='') and " +
//        "(j.fairStartTime >= ?3 or cast(?3 as timestamp) =null) and " +
//        "(j.fairStartTime <= ?4 or cast(?4 as timestamp) =null) and " +
//        "(c.recId <= ?6 or ?6 =null) and " +
//        "(c.auditStatus <= ?5 or cast(?5 as timestamp) =null)")
//    Page<JobFairDTO> getComJobFairDTOPage(String name, String location, LocalDateTime fairStartFrom, LocalDateTime fairStartTo, String auditStatus, UUID id, Pageable pageable);

    /**
     * @Autor 史念念 2017/6/6
     * @Description 学生端动态查询组团/大招招聘会信息
     * @param fairType 招聘会类型
     * @param fairStartTime 开始时间
     * @param fairEndTime 结束时间
     * @return
     */
    @Query("select new cn.edu.dlut.career.dto.company.JobFairDTO (" +
        "jf.id,count(fe.id),count(rb.recJobPositions),jf.name," +
        "jf.type,jf.fairStartTime,jf.location) " +
        "from JobFair jf left join CarrerFairEntry fe on fe.recJobFairId=jf.id " +
        "left join RecBulletin rb on rb.id=fe.recBulletinId " +
        "where (jf.type=?1 or ?1=null) " +
        "and (jf.fairStartTime>=?2 or cast(?2 as timestamp)=null) " +
        "and (jf.fairEndTime<=?3 or cast(?3 as timestamp)=null) " +
        "and fe.auditStatus='01' " +
        "and rb.auditStatus='01' ")
    Page<JobFairDTO> stuFindJobFair(String fairType, LocalDateTime fairStartTime, LocalDateTime fairEndTime,Pageable pageable);
}
