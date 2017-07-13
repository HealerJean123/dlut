package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.RecBulletin;
import cn.edu.dlut.career.dto.company.RecBulletinApplicationDTO;
import cn.edu.dlut.career.dto.company.RecBulletinDTO;
import cn.edu.dlut.career.dto.company.RecBulletinDTO2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/7.
 *
 * 招聘简章 服务层接口
 */
public interface RecBulletinQueryService {

    /**
     * 查询所有 招聘简章
     * @return
     */
    List<RecBulletin> findALL();

    /**
     * 根据id查找招聘简章
     * @param id
     * @return
     */
    RecBulletin findById(UUID id);

    /**
     * 根据公司ID查找 招聘简章
     * @return
     */
    List<RecBulletin> findByRecId(UUID recId);

    /**
     * 根据标题关键字查找招聘简章
     * @param keywords
     * @return
     */
    List<RecBulletin> findByKeywords(String keywords);



    /**
     * 按条件查询招聘简章
     * @param title
     * @param auditStatus
     * @param startTime
     * @param endTime
     * @return
     */
    List<RecBulletin> findByCondition(String title, String auditStatus, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 企业端 动态查询 招聘简章
     * @param pTime 发布时间
     * @param auditStatus 审核状态
     * @param title 招聘简章标题
     * @param pageable
     * @return
     */
    Page<RecBulletinDTO2> findBulletin(LocalDateTime pTime, String auditStatus, String title, Pageable pageable);

    /**
     * @Autor 史念念
     * @Date 2017/6/2
     * @Description 教师端 动态查询招聘简章
     * @param pTime 发布时间
     * @param auditStatus 审核状态
     * @param title 标题
     * @param recName 公司名
     * @return
     */
    Page<RecBulletinDTO2> teaFindBulletin(LocalDateTime pTime, String auditStatus, String title, String recName, Pageable pageable);

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
    Page<RecBulletinDTO2> stuFindBulletin(LocalDateTime pTime, String title, String recName, String nature, Pageable pageable);
}
