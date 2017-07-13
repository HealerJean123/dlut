package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.dto.company.RecLoginDTO;
import cn.edu.dlut.career.repository.company.CompanyInfoRepository;
import cn.edu.dlut.career.service.company.CompanyInfoCommandService;
import cn.edu.dlut.career.service.company.CompanyInfoQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/23.
 */
@Service
@Transactional
public class CompanyInfoCommandServiceImpl implements CompanyInfoCommandService {
  @Autowired
  private CompanyInfoRepository companyInfoRepository;

  /**
   * 注册公司
   * @param companyInfo
   */
  @Override
  public void saveCompany(CompanyInfo companyInfo) {

      //获得随机盐
      SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
      String salt = secureRandomNumberGenerator.nextBytes().toHex();
      //对密码加密后,将加密后的密码和盐存入对象
      String password = companyInfo.getPwd();
      password = new Md5Hash(password, salt).toString();
      companyInfo.setPwd(password);
      companyInfo.setSalt(salt);
      //公司需要审核，这里设置为待审核状态 审核通过才可以对其他功能进行操作
      companyInfo.setAuditStatus("00");
      //注册时间
      SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
      String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
      companyInfo.setRegisterTime(date);
      companyInfoRepository.save(companyInfo);

  }


  @Override
  public CompanyInfo update(CompanyInfo companyInfo) {
      return companyInfoRepository.save(companyInfo);
  }

  @Override
  public void delete(UUID id) {
    companyInfoRepository.delete(id);
  }

    @Override
    /**
     * @author wei  2017/6/5
     * @method update
     * @param [id, auditStatus, reason]
     * @return java.lang.String
     * @description   教师审核企业信息
     */
    public CompanyInfo auditing(UUID id, String auditStatus, String reason) {
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        CompanyInfo companyInfo  = companyInfoRepository.findOne(id);
        companyInfo.setAuditor(user.getUserName());
        companyInfo.setUpdateTime(LocalDateTime.now());
        companyInfo.setReason(reason);
        companyInfo.setAuditStatus(auditStatus);
        companyInfo = companyInfoRepository.save(companyInfo);
        return companyInfo;
    }

    @Override
    /**
    * @author wei  2017/6/5
    * @method updateList
    * @param [ids]
    * @return void
    * @description  批量通过企业信息
    */
    public void updateList(UUID[] ids) {
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        for (UUID id:ids) {
            CompanyInfo companyInfo  = companyInfoRepository.findOne(id);
            companyInfo.setAuditor(user.getUserName());
            companyInfo.setUpdateTime(LocalDateTime.now());
            companyInfo.setAuditStatus("01");
            companyInfoRepository.save(companyInfo);
        }
    }


}
