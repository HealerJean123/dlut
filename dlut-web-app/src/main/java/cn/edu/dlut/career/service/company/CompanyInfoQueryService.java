package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.dto.company.RecLoginDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wei on 2017/3/23.
 */
public interface CompanyInfoQueryService {




  List<CompanyInfo> findAll();

  CompanyInfo findOne(UUID id);


  CompanyInfo findByEmail(String email);

  /**
   * @Description 根据邮箱获取登录信息.
   * @Author  wangyj
   * @CreateDate 2017/4/6 11:14
   * @Param
   * @Return
   */
  RecLoginDTO findLoginInfo(String email);


    /**
     * @author wei  2017/6/5
     * @method companyList
     * @param name, nature, industry, registerTime, auditStatus
     * @return java.util.List<cn.edu.dlut.career.domain.company.CompanyInfo>
     * @description  教师查询企业信息列表
     */
    Page<CompanyInfo> findList(String name, String nature, String industry, String registerTime, String auditStatus, Pageable pageable);
}
