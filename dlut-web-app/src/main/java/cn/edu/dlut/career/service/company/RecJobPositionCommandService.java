package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.dto.company.RecJobPositionDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/23.
 * <p>
 * 招聘职位 服务层接口
 */
public interface RecJobPositionCommandService {


    /**
     * 添加招聘职位信息
     *
     * @param recJobPositionDTOs
     * @return
     */
    String saveRecJobPosition(List<RecJobPositionDTO> recJobPositionDTOs);


    /**
     * 修改审核状态,审核人,审核时间,未通过原因
     *
     * @param id           主键
     * @param auditStatus  状态
     * @param auditor      审核人
     * @param auditTime    审核时间
     * @param nopassReason 审核失败原因
     * @return
     */
    String updateAudit(UUID id, String auditStatus,
                       String auditor, LocalDateTime auditTime,
                       String nopassReason);

    /**
     * 删除招聘职位信息
     *
     * @return
     */
    String deleteById(UUID id);

    /**
     * 企业端 修改职位信息
     * @param id 招聘职位id
     * @param name 招聘职位名称
     * @param description 描述
     * @param type 类型
     * @param category 类别
     * @param degree 学历
     * @param major 专业
     * @param recEmail 邮箱
     * @param receiveMode 接收简历方式
     * @param city 城市
     * @param address 详细地址
     * @param recruitmentNum 招聘人数
     * @param salary 薪资
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    RecJobPosition updateJob(String id, String name, String description, String type, String category, String degree, String major, String recEmail, String receiveMode, String city, String address, Integer recruitmentNum, String salary, String startTime, String endTime);
}
