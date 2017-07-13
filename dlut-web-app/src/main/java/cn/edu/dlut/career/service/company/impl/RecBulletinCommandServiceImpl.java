package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.RecBulletin;
import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.dto.company.RecBulletinDTO;
import cn.edu.dlut.career.repository.company.RecBulletinRepository;
import cn.edu.dlut.career.repository.company.RecJobPositionRepository;
import cn.edu.dlut.career.service.company.RecBulletinCommandService;
import cn.edu.dlut.career.service.company.RecBulletinQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/7.
 *
 * 招聘简章 服务层实现类
 */
@Service
@Transactional
public class RecBulletinCommandServiceImpl implements RecBulletinCommandService {

    @Autowired
    private RecBulletinRepository recBulletinRepository;

    @Autowired
    private RecJobPositionRepository recJobPositionRepository;

    /**
     * 添加招聘简章
     * @param recBulletinDTO
     * @return
     */
    @Override
    public RecBulletin saveRecBulletin(RecBulletinDTO recBulletinDTO) {

        RecBulletin recBulletin = new RecBulletin();
        RecBulletin rec = new RecBulletin();
        LocalDate startTime = LocalDate.parse(recBulletinDTO.getStartTime());
        LocalDate endTime = LocalDate.parse(recBulletinDTO.getEndTime());
        recBulletin.setRecId(recBulletinDTO.getRecId());
        recBulletin.setTitle(recBulletinDTO.getTitle());
        recBulletin.setContent(recBulletinDTO.getContent());
        recBulletin.setStartTime(startTime);
        recBulletin.setEndTime(endTime);
        recBulletin.setRemarks(recBulletinDTO.getRemarks());
        recBulletin.setAuditStatus("00");//审核状态默认为00 待审核
        recBulletin.setOnlineStatus("0");//上下线状态默认0为下线

        rec = recBulletinRepository.save(recBulletin);
        return rec;

    }

    /**
     * 删除 招聘简章
     * @param id
     * @return
     */
    @Override
    public String deleteRecBulletin(UUID id) {
        try {
            recBulletinRepository.delete(id);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 企业端添加与招聘简章相关联的职位信息
     * @param bulletinId 招聘简章id
     * @param recJobPositions 招聘职位集合
     * @return
     */
    @Override
    public String saveJob(UUID bulletinId, List<RecJobPosition> recJobPositions) {
        RecBulletin recBulletin = recBulletinRepository.findById(bulletinId);

        recBulletin.setRecJobPositions(recJobPositions);

        try {
            recBulletinRepository.save(recBulletin);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

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
    @Override
    public RecBulletin updateRecBulletin(UUID id,
                                    String title,
                                    String content,
                                    String startTime,
                                    String endTime,
                                    String remarks) {
        RecBulletin recBulletin = recBulletinRepository.findById(id);
        recBulletin.setTitle(title);
        recBulletin.setContent(content);
        recBulletin.setStartTime(LocalDate.parse(startTime));
        recBulletin.setEndTime(LocalDate.parse(endTime));
        recBulletin.setRemarks(remarks);


         RecBulletin recBulletin1 = recBulletinRepository.save(recBulletin);
       return recBulletin1;
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 教师端 审核招聘简章
     * @param id 招聘简章id
     * @param auditor 审核人
     * @param auditStatus 审核状态
     * @param noPassReason 审核不通过原因
     * @return
     */
    @Override
    public String teaBulletinAudit(UUID id, String auditor, String auditStatus, String noPassReason) {
        LocalDateTime auditTime = LocalDateTime.now();

        int result = recBulletinRepository.updateAudit(id,auditStatus,auditTime,auditor,noPassReason);

        RecBulletin recBulletin = recBulletinRepository.findById(id);

        List<RecJobPosition> jobPositions = recBulletin.getRecJobPositions();

        //招聘简章审核状态改变时与之相关的招聘职位审核状态改变
        for(RecJobPosition recJobPosition :jobPositions){
            int result2 = recJobPositionRepository.updateAudit(recJobPosition.getId(),auditStatus,auditor,auditTime,noPassReason);
        }

        return result>0?"success":"error";
    }
}
