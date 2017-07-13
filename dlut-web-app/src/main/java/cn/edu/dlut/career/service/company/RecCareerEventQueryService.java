package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.RecCareerEvent;
import cn.edu.dlut.career.dto.company.RecCareerEventDTO;
import cn.edu.dlut.career.dto.company.SpecialDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/24.
 *
 * 专场招聘会预约 服务层接口
 */
public interface RecCareerEventQueryService {



    /**
   * 查询所有 专场招聘会预约信息
   * @return
   */
  List<RecCareerEvent> findAll();

  /**
   * 根据id查找 专场招聘会预约信息
   * @param id
   * @return
   */
  RecCareerEvent findById(UUID id);

  /**
   * 根据公司id查找 专场招聘会预约信息
   * @param recId
   * @return
   */
  List<RecCareerEvent> findByRecId(UUID recId);

    /**
     * 动态查询专场招聘
     * @param fairName 名称
     * @param applicationDate 申请日期
     * @param startDate  召开日期
     * @param auditsStatus 审核状态
     */
  Page<SpecialDTO> findList(String fairName, String applicationDate, String startDate, String auditsStatus,Pageable pageable);




/*

    */
/*
     通过id查找审核后状态
     *//*

    String auditStatusFindByid(UUID id);
*/

    /**
     *  张宇晋
     *  动态查询 ，通过 申请日期，开始日期，审核状态，以及 招聘会名称
     *  查询专场招聘预约 申请表
     * @return
     */
//    List<RecCareerEvent> queryRecByCondition(LocalDateTime applicationTime,LocalDateTime applicationEndTime, LocalDateTime fairStartTime,LocalDateTime fairEndTime, String auditStatus,String fairName);

}
