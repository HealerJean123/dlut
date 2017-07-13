package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.RecCareerEvent;
import cn.edu.dlut.career.dto.company.RecCareerEventDTO;
import cn.edu.dlut.career.dto.company.SpecialDTO;
import cn.edu.dlut.career.repository.company.RecCareerEventRepository;
import cn.edu.dlut.career.service.company.RecCareerEventQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/24.
 * <p>
 * 专场招聘会预约服务层实现类
 */
@Service
@Transactional(readOnly = true)
public class RecCareerEventQueryServiceImpl implements RecCareerEventQueryService {
    @Autowired
    private RecCareerEventRepository recCareerEventRepository;



    /**
     * 查询所有 专场招聘会预约信息
     *
     * @return
     */
    @Override
    public List<RecCareerEvent> findAll() {
        List<RecCareerEvent> recCareerEvents = recCareerEventRepository.findAll();

        return recCareerEvents;
    }

    /**
     * 根据Id查找 专场招聘会预约信息
     *
     * @param id
     * @return
     */
    @Override
    public RecCareerEvent findById(UUID id) {
        RecCareerEvent recCareerEvent = recCareerEventRepository.findById(id);
        return recCareerEvent;
    }

    /**
     * 根据企业id 查找专场招聘会预约信息
     *
     * @param recId
     * @return
     */
    @Override
    public List<RecCareerEvent> findByRecId(UUID recId) {
        List<RecCareerEvent> recCareerEvents = recCareerEventRepository.findByRecId(recId);

        return recCareerEvents;
    }

    @Override
    /**
     * 动态查询专场招聘
     * @param fairName 名称
     * @param applicationDate 申请日期
     * @param startDate  召开日期
     * @param auditsStatus 审核状态
     */
    public Page<SpecialDTO> findList(String fairName, String applicationDate, String startDate, String auditsStatus, Pageable pageable) {
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        Page<SpecialDTO> specialDTOS = null;
        if(user.getRole()=="COMPANY") {
            specialDTOS = recCareerEventRepository.findList(fairName, applicationDate, startDate, auditsStatus,user.getId(), pageable);
        }else{
            specialDTOS = recCareerEventRepository.findListBySchool(fairName, applicationDate, startDate, auditsStatus, pageable);
        }
        return specialDTOS;
    }


 /*   *//**
     *  通过id查找审核状态
     *//*
    @Override
    public String auditStatusFindByid(UUID id) {
        try {
           return recCareerEventRepository.auditStatusFindByid(id);
        }catch (Exception e){
        }
        return null;
    }*/

//    @Override
//    public List<RecCareerEvent> queryRecByCondition(LocalDateTime applicationTime, LocalDateTime applicationEndTime, LocalDateTime fairStartTime, LocalDateTime fairEndTime, String auditStatus, String fairName) {
//        try {
//            return recCareerEventRepository.queryRecByCondition(applicationTime,applicationEndTime,fairStartTime,fairEndTime,auditStatus,fairName);
//        }catch (Exception e){
//        }
//        return null;
//    }
}
