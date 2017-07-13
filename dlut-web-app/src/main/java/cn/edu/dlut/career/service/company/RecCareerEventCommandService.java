package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.RecCareerEvent;
import cn.edu.dlut.career.dto.company.RecCareerEventDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by HealerJean on 2017/3/24.
 *
 * 专场招聘会预约 服务层接口
 */
public interface RecCareerEventCommandService {

  /**
  * @Description 企业端 申请专场预约
  * @Author HealerJean
  * @CreateDate 2017/5/26 18:16
  */
  RecCareerEvent saveRecCareerEvent(RecCareerEventDTO recCareerEventDTO);

    /**
     * 审核专场招聘
     * @param id
     * @param auditsStatus
     * @return
     */
    RecCareerEvent update(UUID id, String auditsStatus);


    /**
     * 修改审核及回执信息
     * @param id
     * @param auditStatus 审核状态
     * @param auditSuggest 审核意见
     * @param areaAddress 场地地址
     * @param areaCost 场地费用
     * @param receiver 接待人
     * @param receiverTel 接待人联系方式
     * @param auditTime 审核时间
     * @param auditor 审核人
     * @param fairStartTime 开始时间
     * @param fairEndTime 结束时间
     * @return
     */
//  String updateAudit(UUID id, String auditStatus, String auditSuggest, String areaAddress,
//                     Float areaCost, String receiver, String receiverTel, LocalDateTime auditTime,
//                     String auditor, LocalDateTime fairStartTime,
//                     LocalDateTime fairEndTime);



}
