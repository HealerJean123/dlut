package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.dto.company.RecJobPositionDTO;
import cn.edu.dlut.career.dto.company.RecJobPositionDTO2;
import cn.edu.dlut.career.repository.company.RecJobPositionRepository;
import cn.edu.dlut.career.service.company.RecJobPositionQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@Transactional(readOnly = true)
public class RecJobPositionQueryServiceImpl implements RecJobPositionQueryService {
    @Autowired
    private RecJobPositionRepository recJobPositionRepository;

    /**
     * 查询全部招聘职位信息
     *
     * @return
     */
    @Override
    public List<RecJobPosition> findAll() {
        return recJobPositionRepository.findAll();
    }



    /**
     * 根据编号查找招聘职位信息
     *
     * @param id
     * @return
     */
    @Override
    public RecJobPosition findById(UUID id) {
        RecJobPosition recJobPosition = recJobPositionRepository.findById(id);
        return recJobPosition;
    }

    /**
     * 根据公司编号查找招聘职位信息
     *
     * @param recId
     * @return
     */
    @Override
    public List<RecJobPosition> findByRecId(UUID recId) {
        List<RecJobPosition> ls = recJobPositionRepository.findByRecId(recId);
        return ls;
    }

    /**
     * 企业端 根据条件动态查询职位信息
     * @param name 职位名
     * @param auditStatus 审核状态
     * @param positionType 职位类型
     * @param eduDegree 学历
     * @param publishTime 发布时间
     * @param pageable
     * @return
     */
    @Override
    public Page<RecJobPosition> findByCondition(String name, String auditStatus, String positionType, String eduDegree, LocalDateTime publishTime, Pageable pageable) {
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        UUID recId = user.getId();

        Page<RecJobPosition> recJobPositions = recJobPositionRepository.findByCondition(recId,name,auditStatus,positionType,eduDegree,publishTime,pageable);
        return recJobPositions;
    }

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
    @Override
    public Page<RecJobPositionDTO2> teaQueryByCondition(String name, String recName, String auditStatus, String positionType, String eduDegree, String major, LocalDateTime pTime, Pageable pageable) {
        Page<RecJobPositionDTO2> recJobPositions = recJobPositionRepository.teaQueryByCondition(name,recName,auditStatus,positionType,eduDegree,major,pTime,pageable);

        return recJobPositions;
    }

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
    @Override
    public Page<RecJobPositionDTO2> stuQueryByCondition(String name, String recName, String positionType, String eduDegree, String major, String nature, LocalDateTime publishTime, Pageable pageable) {
        Page<RecJobPositionDTO2> recJobPositionDTO2s = recJobPositionRepository.stuQueryByCondition(name,recName,positionType,eduDegree,major,nature,publishTime,pageable);

        return recJobPositionDTO2s;
    }
}
