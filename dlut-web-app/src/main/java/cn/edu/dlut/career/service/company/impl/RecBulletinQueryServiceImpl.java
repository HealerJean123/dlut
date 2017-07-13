package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.RecBulletin;
import cn.edu.dlut.career.dto.company.RecBulletinDTO;
import cn.edu.dlut.career.dto.company.RecBulletinDTO2;
import cn.edu.dlut.career.repository.company.RecBulletinRepository;
import cn.edu.dlut.career.service.company.RecBulletinQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@Transactional(readOnly = true)
public class RecBulletinQueryServiceImpl implements RecBulletinQueryService {

    @Autowired
    private RecBulletinRepository recBulletinRepository;

    /**
     * 查找所有招聘简章
     * @return
     */
    @Override
    public List<RecBulletin> findALL() {
        List<RecBulletin> recBulletins = recBulletinRepository.findAll();
        return recBulletins;
    }

    /**
     * 根据id查找招聘简章
     * @param id
     * @return
     */
    @Override
    public RecBulletin findById(UUID id) {
        RecBulletin recBulletin = recBulletinRepository.findById(id);
        return recBulletin;
    }

    /**
     * 根据公司id查找招聘简章
     * @param recId
     * @return
     */
    @Override
    public List<RecBulletin> findByRecId(UUID recId) {
        List<RecBulletin> recBulletins = recBulletinRepository.findByRecId(recId);

        return recBulletins;
    }

    /**
     * 根据标题关键字查找招聘简章
     * @param keywords
     * @return
     */
    @Override
    public List<RecBulletin> findByKeywords(String keywords) {
        List<RecBulletin> recBulletins = recBulletinRepository.findByKeywords(keywords);
        return recBulletins;
    }

    /**
     * 按条件查询招聘简章
     * @param title
     * @param auditStatus
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<RecBulletin> findByCondition(String title, String auditStatus, LocalDateTime startTime, LocalDateTime endTime) {
        return recBulletinRepository.findByCondition(title,auditStatus,startTime,endTime);
    }

    /**
     * 企业端 动态查询 招聘简章
     * @param pTime 发布时间
     * @param auditStatus 审核状态
     * @param title 招聘简章标题
     * @param pageable
     * @return
     */
    @Override
    public Page<RecBulletinDTO2> findBulletin(LocalDateTime pTime, String auditStatus, String title, Pageable pageable) {
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        UUID recId = user.getId();

        Page<RecBulletinDTO2> recBulletins = recBulletinRepository.findBulletin(recId,pTime,auditStatus,title,pageable);
        return recBulletins;
    }

    /**
     * @Autor 史念念 2017/6/2
     * @Description 教师端动态查询招聘简章信息
     * @param pTime
     * @param auditStatus
     * @param title
     * @param recName
     * @param pageable
     * @return
     */
    @Override
    public Page<RecBulletinDTO2> teaFindBulletin(LocalDateTime pTime,
                                                 String auditStatus,
                                                 String title,
                                                 String recName,
                                                 Pageable pageable) {
        Page<RecBulletinDTO2> recBulletinDTO2s = recBulletinRepository.teaFindBulletin(pTime,auditStatus,title,recName,pageable);

        return recBulletinDTO2s;
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 学生端动态查询招聘简章
     * @param pTime 发布日期
     * @param title 标题
     * @param recName 公司名
     * @param nature 公司性质
     * @param pageable
     * @return
     */
    @Override
    public Page<RecBulletinDTO2> stuFindBulletin(LocalDateTime pTime, String title, String recName, String nature, Pageable pageable) {

        Page<RecBulletinDTO2> recBulletinDTO2s = recBulletinRepository.stuFindBulletin(pTime,title,recName,nature,pageable);

        return recBulletinDTO2s;
    }
}
