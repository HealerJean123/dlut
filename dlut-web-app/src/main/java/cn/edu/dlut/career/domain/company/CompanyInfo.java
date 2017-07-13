package cn.edu.dlut.career.domain.company;

import cn.edu.dlut.career.util.PubCodeUtil;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by wei on 2017/3/23.
 * 公司信息表
 */
@Entity
@Table(name = "rec_info")
public class CompanyInfo {
    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //公司名称
    @Column(length = 32,nullable = false)
    private String name;

    //组织机构代码
    @Column(length = 20,nullable = false)
    private String orgCode;

    //公司性质
    @Column(length = 10,nullable = false)
    private String nature;

    @Transient
    private String natureName;

    //公司行业
    @Column(length = 10,nullable = false)
    private String industry;
    @Transient
    private String industryName;

    //主管部门名称（关联字典）
    @Column(length = 100,nullable = false)
    private String department;

    @Transient
    private String departmentName;

    //企业简介
    @Column(length = 500,nullable = false)
    private String describe;

    //注册资本(关联字典)
    @Column(length = 10,nullable = false)
    private String capital;
    @Transient
    private String capitalName;

    //公司规模(关联字典)
    @Column(length = 10,nullable = false)
    private String size;
    @Transient
    private String sizeName;

    //公司所在省
    @Column(length = 20,nullable = false)
    private String province;

    //公司所在市
    @Column(length = 20,nullable = false)
    private String city;


    //公司详细地址
    @Column(length = 50,nullable = false)
    private String address;

    //公司邮编
    @Column(length = 10,nullable = false)
    private String zipCode;

    //企业网址
    @Column(length = 100)
    private String website;

    //联系人
    @Column(length = 20,nullable = false)
    private String contacts;

    //联系电话
    @Column(length = 20,nullable = false)
    private String  telphone;

    //固定电话
    @Column(length = 20,nullable = false)
    private String  fixedTelphone;

    //联系邮箱 用于登录
    @Column(length = 50,nullable = false)
    private String email;

    //登录密码
    @Column(length = 32,nullable = false)
    private String pwd;

    //随机盐
    @Column(length = 50,nullable = false)
    private String salt;

    //审核状态(00待审核 01审核通过  02审核不通过)
    @Column(length = 2)
    private String auditStatus;

    @Transient
    private String auditStatusName;

    //审核时间
    @Column
    private LocalDateTime auditTime;
    //审核人
    @Column(length = 20)
    private String auditor;

    //营业执照/代码证图片
    @Column(length = 100,nullable = false)
    private String license;

    //过期时间
    @Column
    private LocalDate expiryDate;

    //审核不通过原因
    @Column(length = 100)
    private String reason;

    //注册时间
    @Column(length = 50)
    private String registerTime;

    //最后登陆时间
    @Column
    private LocalDateTime lastLoginTime;

    //最后修改时间
    @Column
    @UpdateTimestamp
    private LocalDateTime updateTime;
    //标签
    @Column(length = 100)
    private String labels;

    //备注
    @Column(length = 100)
    private String remarks;

    public CompanyInfo() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getFixedTelphone() {
        return fixedTelphone;
    }

    public void setFixedTelphone(String fixedTelphone) {
        this.fixedTelphone = fixedTelphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getNatureName() {
        return PubCodeUtil.getName("nature",this.getNature());
    }

    public String getIndustryName() {
        return PubCodeUtil.getName("industry",this.getIndustry());
    }

    public String getCapitalName() {
        return PubCodeUtil.getName("capital",this.getCapital());
    }

    public String getSizeName() {
        return PubCodeUtil.getName("companySize",this.getSize());
    }

    public String getAuditStatusName() {
        return PubCodeUtil.getName("auditStatus",this.getAuditStatus());
    }

    public String getDepartmentName() {
        return PubCodeUtil.getName("department",this.getDepartment());
    }
}

