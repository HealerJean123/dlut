package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.dto.company.RecJobPositionDTO;
import cn.edu.dlut.career.dto.company.RecJobPositionDTO2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/23.
 * <p>
 * 招聘职位 服务层接口
 */
public interface RecJobPositionQueryService {

    /**
     * 查询全部招聘职位信息
     *
     * @return
     */
    List<RecJobPosition> findAll();


    /**
     * 根据编号查找公司的招聘信息
     *
     * @param id
     * @return
     */
    RecJobPosition findById(UUID id);

    /**
     * 根据公司编号查找招聘职位信息
     *
     * @param recId
     * @return
     */
    List<RecJobPosition> findByRecId(UUID recId);

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
    Page<RecJobPosition> findByCondition(String name, String auditStatus, String positionType, String eduDegree, LocalDateTime publishTime, Pageable pageable);

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
    Page<RecJobPositionDTO2> teaQueryByCondition(String name, String recName, String auditStatus, String positionType, String eduDegree, String major, LocalDateTime pTime, Pageable pageable);

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
    Page<RecJobPositionDTO2> stuQueryByCondition(String name, String recName, String positionType, String eduDegree, String major, String nature, LocalDateTime publishTime, Pageable pageable);
}
