package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.domain.company.Field;
import cn.edu.dlut.career.domain.company.RecCareerEvent;
import cn.edu.dlut.career.dto.company.RecCareerEventDTO;
import cn.edu.dlut.career.repository.company.RecCareerEventRepository;
import cn.edu.dlut.career.service.company.*;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by HealerJean on 2017/3/24.
 * <p>
 * 专场招聘会预约服务层实现类
 */
@Service
@Transactional
public class RecCareerEventCommandServiceImpl implements RecCareerEventCommandService {
    @Autowired
    private RecCareerEventRepository recCareerEventRepository;
    @Autowired
    private CompanyInfoQueryService companyInfoQueryService;
    @Autowired
    private  RecCareerEventQueryService recCareerEventQueryService;
    @Autowired
    private FieldCommandService fieldCommandService;
    /**
    * @Description 添加专场预约申请表,以及关联的场地 向学校提出申请
    * @Author HealerJean
    * @CreateDate 2017/5/25 14:25
    */
    @Override
    public RecCareerEvent saveRecCareerEvent(RecCareerEventDTO recCareerEventDTO) {
        //数据库中得到
        UserPrincipal userPrincipal = (UserPrincipal)SecurityUtils.getSubject().getPrincipal();
        CompanyInfo companyInfo = companyInfoQueryService.findOne(userPrincipal.getId());
        RecCareerEvent recCareerEvent = new RecCareerEvent();
        recCareerEvent.setRecId(companyInfo.getId());
        recCareerEvent.setRecAddress(companyInfo.getAddress());
        recCareerEvent.setRecName(companyInfo.getName());
        recCareerEvent.setAuditsStatus("00");


        //前台传来的值
        recCareerEvent.setFairName(recCareerEventDTO.getFairName());
        recCareerEvent.setFairStartTime(recCareerEventDTO.getFairStartTime());
        recCareerEvent.setFairEndTime(recCareerEventDTO.getFairEndTime());
        recCareerEvent.setContacts(recCareerEventDTO.getContacts());
        recCareerEvent.setConTel(recCareerEventDTO.getConTel());
        recCareerEvent.setConEmail(recCareerEventDTO.getConEmail());
        recCareerEvent.setRemarks(recCareerEventDTO.getRemarks());
        recCareerEvent.setStartDate(recCareerEventDTO.getStartDate());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        recCareerEvent.setApplicationDate(date);

        Field field = new Field();
        field.setAuditStatus("00");
        field.setFieldRequire(recCareerEventDTO.getAreaRequire());
        field.setFieldSize(recCareerEventDTO.getAreaSize());
        field.setFieldNum(recCareerEventDTO.getAreaNum());
        field.setFieldUsing("05"); //专场招聘
        field.setRecName(recCareerEventDTO.getRecName());
        field.setContacts(recCareerEventDTO.getContacts());
        field.setContactsPhone(recCareerEventDTO.getConTel());
        field.setStartDate(recCareerEventDTO.getStartDate());
        field.setEndTime(recCareerEventDTO.getFairEndTime());
        field.setStartTime(recCareerEventDTO.getFairStartTime());
        field.setRecId(userPrincipal.getId());

        Field fieldNow = fieldCommandService.addField(field);
        recCareerEvent.setFieldId(fieldNow.getId());
        RecCareerEvent recCareerEventNow = recCareerEventRepository.save(recCareerEvent);
        return recCareerEventNow;
    }

    @Override
    /**
     * 审核专场招聘
     * @param id
     * @param auditsStatus
     * @return
     */
    public RecCareerEvent update(UUID id, String auditsStatus) {
        RecCareerEvent recCareerEvent = recCareerEventRepository.findOne(id);
        UserPrincipal userPrincipal = (UserPrincipal)SecurityUtils.getSubject().getPrincipal();
        recCareerEvent.setAuditor(userPrincipal.getUserName());
        recCareerEvent.setAuditsStatus(auditsStatus);
        recCareerEvent.setAuditTime(LocalDateTime.now());
        recCareerEvent = recCareerEventRepository.save(recCareerEvent);
        return recCareerEvent;
    }

    /**
     * 修改审核及回执信息
     *
     * @param id
     * @param auditStatus  审核状态
     * @param auditSuggest 审核意见
     * @param areaAddress  场地地址
     * @param areaCost     场地费用
     * @param receiver     接待人
     * @param receiverTel  接待人联系方式
     * @param auditTime    审核时间
     * @param auditor      审核人
     * @param fairStartTime    开始时间
     * @param fairEndTime      结束时间
     * @return
     */
//    @Override
//    public String updateAudit(UUID id, String auditStatus, String auditSuggest, String areaAddress, Float areaCost, String receiver, String receiverTel, LocalDateTime auditTime, String auditor, LocalDateTime fairStartTime, LocalDateTime fairEndTime) {
//        try {
//            int i = recCareerEventRepository.updateAudit(id, auditStatus, auditSuggest, areaAddress, areaCost, receiver, receiverTel, auditTime, auditor, fairStartTime, fairEndTime);
//            return i > 0 ? "ok" : "fail";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "fail";
//        }
//    }



}
