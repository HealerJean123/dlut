package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.dto.company.RecLoginDTO;
import cn.edu.dlut.career.repository.company.CompanyInfoRepository;
import cn.edu.dlut.career.service.company.CompanyInfoQueryService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/23.
 */
@Service
@Transactional(readOnly = true)
public class CompanyInfoQueryServiceImpl implements CompanyInfoQueryService {
  @Autowired
  private CompanyInfoRepository companyInfoRepository;


  @Override
  public List<CompanyInfo> findAll() {
    return companyInfoRepository.findAll();
  }

  @Override
  public CompanyInfo findOne(UUID id) {
    return companyInfoRepository.findOne(id);
  }

  @Override
  public CompanyInfo findByEmail(String email) {
    CompanyInfo companyInfo = companyInfoRepository.findByEmail(email);
    return companyInfo;
  }

  /**
   * @Description 根据邮箱获取登录信息.
   * @Author  wangyj
   * @CreateDate 2017/4/6 11:18
   * @Param
   * @Return
   */
    @Override
    public RecLoginDTO findLoginInfo(String email) {
        return companyInfoRepository.findLoginInfo(email);
    }

    /**
     * @author wei  2017/6/5
     * @method companyList
     * @param name, nature, industry, registerTime, auditStatus
     * @return java.util.List<cn.edu.dlut.career.domain.company.CompanyInfo>
     * @description  教师查询企业信息列表
     */
    @Override
    public Page<CompanyInfo> findList(String name, String nature, String industry, String registerTime, String auditStatus, Pageable pageable) {
        return companyInfoRepository.findList(name,nature,industry,registerTime,auditStatus,pageable);
    }
}
