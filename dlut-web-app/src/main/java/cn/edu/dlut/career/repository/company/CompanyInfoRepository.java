package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.dto.company.RecLoginDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by wei on 2017/3/23.
 */
public interface CompanyInfoRepository extends CrudRepository<CompanyInfo, UUID> {


    @Override
    List<CompanyInfo> findAll();

    /**
     * 根据email进行查询
     *
     * @param email
     * @return
     */
    @Query("from CompanyInfo c where c.email=?1")
    CompanyInfo findByEmail(String email);


    /**
     * @Description 根据邮箱获取登录信息.
     * @Author wangyj
     * @CreateDate 2017/4/6 11:03
     * @Param
     * @Return
     */
    @Query(value = "select new cn.edu.dlut.career.dto.company.RecLoginDTO (c.id ,c.name,c.email,c.pwd ,c.salt,c.auditStatus )from CompanyInfo c where c.email=?1")
    RecLoginDTO findLoginInfo(String email);


    /**
     * @author wei  2017/6/5
     * @method companyList
     * @param name, nature, industry, registerTime, auditStatus
     * @return java.util.List<cn.edu.dlut.career.domain.company.CompanyInfo>
     * @description  教师查询企业信息列表
     */
    @Query(value = "select new cn.edu.dlut.career.dto.company.CompanyListDTO (c.id, c.name, c.orgCode, c.nature, c.industry,c.registerTime, c.auditStatus) from CompanyInfo c where" +
        "(name like %?1% or ?1 = '') AND (nature = ?2 or ?2 = '') AND (industry = ?3 or ?3 = '') AND (registerTime = ?4 or ?4 = '') AND (auditStatus = ?5 or ?5 = '')")
    Page<CompanyInfo> findList(String name, String nature, String industry, String registerTime, String auditStatus, Pageable pageable);
}
