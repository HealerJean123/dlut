package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.dto.company.RecJobPositionDTO;
import cn.edu.dlut.career.repository.company.RecJobPositionRepository;
import cn.edu.dlut.career.service.company.RecJobPositionCommandService;
import cn.edu.dlut.career.service.company.RecJobPositionQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/23.
 * <p>
 * 招聘职位  服务层实现类
 */
@Service
@Transactional
public class RecJobPositionCommandServiceImpl implements RecJobPositionCommandService {
    @Autowired
    private RecJobPositionRepository recJobPositionRepository;


    /**
     * 添加招聘职位信息
     *
     * @param recJobPositionDTOs
     * @return
     */
    @Override
    public String saveRecJobPosition(List<RecJobPositionDTO> recJobPositionDTOs) {

        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        UUID recId = user.getId();
            List<RecJobPosition> recJobPositions = new ArrayList<RecJobPosition>();
            for (RecJobPositionDTO recJobPositionDTO:recJobPositionDTOs) {
                RecJobPosition recJobPosition = new RecJobPosition();
                LocalDate startTime = LocalDate.parse(recJobPositionDTO.getStartTime());
                LocalDate endTime = LocalDate.parse(recJobPositionDTO.getEndTime());
                recJobPosition.setRecId(recId);
                recJobPosition.setName(recJobPositionDTO.getName());
                recJobPosition.setDescription(recJobPositionDTO.getDescription());
                recJobPosition.setType(recJobPositionDTO.getType());
                recJobPosition.setCategory(recJobPositionDTO.getCategory());
                recJobPosition.setDegree(recJobPositionDTO.getDegree());
                recJobPosition.setMajor(recJobPositionDTO.getMajor());
                recJobPosition.setReceiveMode(recJobPositionDTO.getReceiveMode());
                recJobPosition.setRecEmail(recJobPositionDTO.getRecEmail());
                recJobPosition.setCity(recJobPositionDTO.getCity());
                recJobPosition.setAddress(recJobPositionDTO.getAddress());
                recJobPosition.setRecruitmentNum(recJobPositionDTO.getRecruitmentNum());
                recJobPosition.setSalary(recJobPositionDTO.getSalary());
                recJobPosition.setStartTime(startTime);
                recJobPosition.setEndTime(endTime);
                //审核状态默认0待审核
                recJobPosition.setAuditStatus("00");
                //上线下线默认0下线
                recJobPosition.setOnlineStatus("0");

                recJobPositionRepository.save(recJobPosition);

               // recJobPositions.add(recJobPosition);
            }
           return "success";


    }



    /**
     * 修改审核状态,审核人,审核时间,未通过原因
     *
     * @param id
     * @param auditStatus
     * @param auditor
     * @param auditTime
     * @param nopassReason
     * @return
     */
    @Override
    public String updateAudit(UUID id, String auditStatus, String auditor, LocalDateTime auditTime, String nopassReason) {
        int result = recJobPositionRepository.updateAudit(id, auditStatus, auditor, auditTime, nopassReason);

        return result > 0 ? "success" : "error";
    }

    /**
     * 删除招聘职位信息
     *
     * @param id
     * @return
     */
    @Override
    public String deleteById(UUID id) {
        try {
            recJobPositionRepository.delete(id);
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

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
    @Override
    public RecJobPosition updateJob(String id,
                                    String name,
                                    String description,
                                    String type,
                                    String category,
                                    String degree,
                                    String major,
                                    String recEmail,
                                    String receiveMode,
                                    String city,
                                    String address,
                                    Integer recruitmentNum,
                                    String salary,
                                    String startTime,
                                    String endTime) {
        RecJobPosition recJobPosition = recJobPositionRepository.findById(UUID.fromString(id));

        recJobPosition.setName(name);
        recJobPosition.setDescription(description);
        recJobPosition.setType(type);
        recJobPosition.setCategory(category);
        recJobPosition.setDegree(degree);
        recJobPosition.setMajor(major);
        recJobPosition.setRecEmail(recEmail);
        recJobPosition.setReceiveMode(receiveMode);
        recJobPosition.setCity(city);
        recJobPosition.setAddress(address);
        recJobPosition.setRecruitmentNum(recruitmentNum);
        recJobPosition.setSalary(salary);
        recJobPosition.setStartTime(LocalDate.parse(startTime));
        recJobPosition.setEndTime(LocalDate.parse(endTime));

        RecJobPosition recJobPosition1 = recJobPositionRepository.save(recJobPosition);
        return recJobPosition1;
    }
}
