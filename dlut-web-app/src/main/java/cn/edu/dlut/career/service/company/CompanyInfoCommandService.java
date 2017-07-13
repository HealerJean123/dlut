package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.dto.company.RecLoginDTO;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/23.
 */
public interface CompanyInfoCommandService {
  /**
   * 注册公司
   * @param companyInfo
   */
  void saveCompany(CompanyInfo companyInfo);


  CompanyInfo update(CompanyInfo companyInfo);

  void delete(UUID id);


    /**
     * @author wei  2017/6/5
     * @method update
     * @param id, auditStatus, reason
     * @return java.lang.String
     * @description   教师审核企业信息
     */
    CompanyInfo auditing(UUID id, String auditStatus, String reason);

    void updateList(UUID[] ids);
}
