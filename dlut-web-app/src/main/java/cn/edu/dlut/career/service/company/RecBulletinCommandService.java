package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.RecBulletin;
import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.dto.company.RecBulletinDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/7.
 *
 * 招聘简章 服务层接口
 */
public interface RecBulletinCommandService {

    /**
     * 添加 招聘简章
     * @param recBulletinDTO
     * @return
     */
    RecBulletin saveRecBulletin(RecBulletinDTO recBulletinDTO);

    /**
     * 删除招聘简章
     * @param id
     * @return
     */
    String deleteRecBulletin(UUID id);

    /**
     * 企业端添加与招聘简章相关联的职位信息
     * @param bulletinId 招聘简章id
     * @param recJobPositions 招聘职位集合
     * @return
     */
    String saveJob(UUID bulletinId, List<RecJobPosition> recJobPositions);

    /**
     * 企业端 修改招聘简章
     * @param id 招聘简章id
     * @param title 标题
     * @param content 内容
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param remarks 备注
     * @return
     */
    RecBulletin updateRecBulletin(UUID id,
                             String title,
                             String content,
                             String startTime,
                             String endTime,
                             String remarks);

    /**
     * @Autor 史念念 2017/6/5
     * @Description 教师端 审核招聘简章
     * @param id 招聘简章id
     * @param auditor 审核人
     * @param auditStatus 审核状态
     * @param noPassReason 审核不通过原因
     * @return
     */
    String teaBulletinAudit(UUID id, String auditor, String auditStatus, String noPassReason);
}
