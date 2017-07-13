package cn.edu.dlut.career.domain.company;

import cn.edu.dlut.career.util.PubCodeUtil;
import org.hibernate.annotations.GenericGenerator;

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
 * Created by wei on 2017/4/13.
 * offer表
 */
@Entity
@Table(name = "rec_offer")
public class RecOffer {
    //编号
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //学号
    @Column(length = 32, nullable = false)
    private String stuNo;
    //学生id
    @Column(length = 32)
    private UUID stuId;


    //姓名
    @Column(length = 40, nullable = false)
    private String realName;

    //身份证号
    @Column(length = 18, nullable = false)
    private String idCard;

    //学历代码
    @Column(length = 2, nullable = false)
    private String eduDegree;

    //学历名字
    @Transient
    private String eduName;

    //专业代码
    @Column(length = 6, nullable = false)
    private String majorCode;

    //专业名称
    @Column(length = 30, nullable = true)
    private String majorName;

    //民族代码
    @Column(length = 2, nullable = false)
    private String ethnic;

    //所在院系id
    @Column(length = 32, nullable = true)
    private String departmentId;

    //所在院系
    @Column(length = 32, nullable = true)
    private String department;



    //培养方式代码(1 非定向 ,2 定向 ,3 在职 ,4 委培 ,5自筹)
    @Column(length = 3, nullable = false)
    private String eduMode;


    //生源地代码／名称
    @Column(length = 6, nullable = true)
    private String homelandName;

    //学制
    @Column(length = 3, nullable = false)
    private String eduYear;

    //入学时间
    @Column(nullable = false)
    private String startDate;

    //毕业时间
    @Column(nullable = true)
    private String endDate;

    @Column(length = 32, nullable = false)
    private UUID recId;

    //公司名称
    @Column(length = 32, nullable = false)
    private String recName;

    //组织机构代码
    @Column(length = 20, nullable = false)
    private String orgCode;

    //公司性质
    @Column(length = 10, nullable = false)
    private String nature;

    //公司行业
    @Column(length = 10, nullable = false)
    private String industry;

    //隶属部门
    @Column(length = 20, nullable = true)
    private String departmentBelong;

    //公司详细地址
    @Column(length = 50, nullable = false)
    private String recAddress;

    //公司所在省份
    @Column(length = 3,nullable =false )
    private String recProvince;
    //公司所在地区
    @Column(length = 50, nullable = false)
    private String recCity;

    //联系部门
    @Column(length = 50, nullable = false)
    private String recDepartment;


    //联系人
    @Column(length = 20, nullable = false)
    private String recLinker;

    //联系电话
    @Column(length = 20, nullable = false)
    private String recMobile;

    //联系电话
    @Column(length = 20, nullable = false)
    private String recTelphone;

    //职位类别
    @Column(length = 3)
    private String category;

    @Transient
    private String categoryName;

    //是否需要报到证
    @Column(length = 1)
    private String hasReportCard;

    //是否接受档案
    @Column(length = 1, nullable = false)
    private String isPfile;

    // 档案转寄单位名称pfileToName
    @Column(length = 50)
    private String pfileToName;
    // 档案转寄部门pfileToDepart
    @Column(length = 50)
    private String pfileToDepart;
    // 档案转寄单位地址pfileToAddress
    @Column(length = 100)
    private String pfileToAddress;
    // 档案转寄单位所在省市
    @Column(length = 20)
    private String pfileToLocal;
    // 档案转寄单位接收人
    @Column(length = 30)
    private String pfileToRecipient;

    // 档案转寄接受人电话
    @Column(length = 30)
    private String pfileToPhone;

    //是否解决户口(1解决0不解决)
    @Column(length = 1)
    private String isSolveHukou;
    //是否允许不迁入(1允许不迁入0不允许不迁入)
    @Column(length = 1)
    private String isNotMoveHuKou;
    //确认是否迁入户口
    @Column(length = 1)
    private String isConfirmHuKou;
    //户口是否在学校
    @Column(length = 1)
    private String isSchHuKou;
    // 户口迁转地址hukouToAddress
    @Column(length = 100)
    private String hukouToAddress;

    //学生接收状态00待接收01已接受02拒绝03已过期04签约05毁约
    @Column(length = 3, nullable = false)
    private String stuReceivingStatus;

    @Transient
    private String stuReceivingStatusName;

    //当前审核状态(待审核/院系审核通过/院系审核不通过/学校审核通过/学校审核不通过)
    @Column(length = 3, nullable = false)
    private String auditStatus;

    @Transient
    private String auditStatusName;
    //审核人
    @Column
    private String auditor;
    //审核时间
    @Column
    private LocalDateTime auditTime;
    //审核不通过原因
    @Column(length = 500)
    private String noPassReason;
    //发放时间
    @Column
    private LocalDate createOn;
    //截止日期
    @Column
    private String closingDate;
    //获取信息途径
    @Column(length = 100)
    private String accessToInfo;
    //协议书是否已交
    @Column(length = 1)
    private String hasAgreement;


    // 单位邮编recZipcode
    @Column(length = 20, nullable = false)
    private String recZipcode;

    //就业去向代码
    @Column(length = 20, nullable = false)
    private String destinationType;

    //签约日期
    @Column(nullable = true)
    private LocalDate signTime;

    @Column(length = 500)
    private String remarks;



    public RecOffer() {
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }


    public String getAuditStatusName() {
        return PubCodeUtil.getName("totalAuditStatus",this.getAuditStatus());
    }

    public String getCategoryName() {
        return PubCodeUtil.getName("jobType",this.getCategory());
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getMajorName() {
        return PubCodeUtil.getName("major",this.getMajorCode());
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getRecDepartment() {
        return recDepartment;
    }

    public String getRecProvince() {
        return recProvince;
    }

    public void setRecProvince(String recProvince) {
        this.recProvince = recProvince;
    }

    public void setRecDepartment(String recDepartment) {
        this.recDepartment = recDepartment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEduName() {
        return PubCodeUtil.getName("eduDegree",this.getEduDegree());
    }


    public String getDepartment() {
        return PubCodeUtil.getName("academy",this.getDepartmentId());
    }



    public String getStuReceivingStatusName() {
        return PubCodeUtil.getName("offerStatus",this.getStuReceivingStatus());
    }

    public UUID getRecId() {
        return recId;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
    }

    public UUID getStuId() {
        return stuId;
    }

    public void setStuId(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEduDegree() {
        return eduDegree;
    }

    public void setEduDegree(String eduDegree) {
        this.eduDegree = eduDegree;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setStuId(UUID stuId) {
        this.stuId = stuId;
    }


    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }



    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEduMode() {
        return eduMode;
    }

    public void setEduMode(String eduMode) {
        this.eduMode = eduMode;
    }

    public String getHomelandName() {
        return homelandName;
    }

    public void setHomelandName(String homelandName) {
        this.homelandName = homelandName;
    }

    public String getEduYear() {
        return eduYear;
    }

    public void setEduYear(String eduYear) {
        this.eduYear = eduYear;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
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

    public String getDepartmentBelong() {
        return departmentBelong;
    }

    public void setDepartmentBelong(String departmentBelong) {
        this.departmentBelong = departmentBelong;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getRecCity() {
        return recCity;
    }

    public void setRecCity(String recCity) {
        this.recCity = recCity;
    }

    public String getRecLinker() {
        return recLinker;
    }

    public void setRecLinker(String recLinker) {
        this.recLinker = recLinker;
    }

    public String getRecMobile() {
        return recMobile;
    }

    public void setRecMobile(String recMobile) {
        this.recMobile = recMobile;
    }

    public String getRecTelphone() {
        return recTelphone;
    }

    public void setRecTelphone(String recTelphone) {
        this.recTelphone = recTelphone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHasReportCard() {
        return hasReportCard;
    }

    public void setHasReportCard(String hasReportCard) {
        this.hasReportCard = hasReportCard;
    }

    public String getIsPfile() {
        return isPfile;
    }

    public void setIsPfile(String isPfile) {
        this.isPfile = isPfile;
    }

    public String getPfileToName() {
        return pfileToName;
    }

    public void setPfileToName(String pfileToName) {
        this.pfileToName = pfileToName;
    }

    public String getPfileToDepart() {
        return pfileToDepart;
    }

    public void setPfileToDepart(String pfileToDepart) {
        this.pfileToDepart = pfileToDepart;
    }

    public String getPfileToAddress() {
        return pfileToAddress;
    }

    public void setPfileToAddress(String pfileToAddress) {
        this.pfileToAddress = pfileToAddress;
    }

    public String getPfileToLocal() {
        return pfileToLocal;
    }

    public void setPfileToLocal(String pfileToLocal) {
        this.pfileToLocal = pfileToLocal;
    }

    public String getPfileToRecipient() {
        return pfileToRecipient;
    }

    public void setPfileToRecipient(String pfileToRecipient) {
        this.pfileToRecipient = pfileToRecipient;
    }

    public String getPfileToPhone() {
        return pfileToPhone;
    }

    public void setPfileToPhone(String pfileToPhone) {
        this.pfileToPhone = pfileToPhone;
    }

    public String getIsSolveHukou() {
        return isSolveHukou;
    }

    public void setIsSolveHukou(String isSolveHukou) {
        this.isSolveHukou = isSolveHukou;
    }

    public String getIsNotMoveHuKou() {
        return isNotMoveHuKou;
    }

    public void setIsNotMoveHuKou(String isNotMoveHuKou) {
        this.isNotMoveHuKou = isNotMoveHuKou;
    }

    public String getIsConfirmHuKou() {
        return isConfirmHuKou;
    }

    public void setIsConfirmHuKou(String isConfirmHuKou) {
        this.isConfirmHuKou = isConfirmHuKou;
    }

    public String getIsSchHuKou() {
        return isSchHuKou;
    }

    public void setIsSchHuKou(String isSchHuKou) {
        this.isSchHuKou = isSchHuKou;
    }

    public String getHukouToAddress() {
        return hukouToAddress;
    }

    public void setHukouToAddress(String hukouToAddress) {
        this.hukouToAddress = hukouToAddress;
    }

    public String getStuReceivingStatus() {
        return stuReceivingStatus;
    }

    public void setStuReceivingStatus(String stuReceivingStatus) {
        this.stuReceivingStatus = stuReceivingStatus;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public String getNoPassReason() {
        return noPassReason;
    }

    public void setNoPassReason(String noPassReason) {
        this.noPassReason = noPassReason;
    }

    public LocalDate getCreateOn() {
        return createOn;
    }

    public void setCreateOn(LocalDate createOn) {
        this.createOn = createOn;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getAccessToInfo() {
        return accessToInfo;
    }

    public void setAccessToInfo(String accessToInfo) {
        this.accessToInfo = accessToInfo;
    }

    public String getHasAgreement() {
        return hasAgreement;
    }

    public void setHasAgreement(String hasAgreement) {
        this.hasAgreement = hasAgreement;
    }

    public String getRecZipcode() {
        return recZipcode;
    }

    public void setRecZipcode(String recZipcode) {
        this.recZipcode = recZipcode;
    }

    public String getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(String destinationType) {
        this.destinationType = destinationType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getSignTime() {
        return signTime;
    }

    public void setSignTime(LocalDate signTime) {
        this.signTime = signTime;
    }
}
