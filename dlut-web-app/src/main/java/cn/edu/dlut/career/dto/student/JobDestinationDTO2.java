package cn.edu.dlut.career.dto.student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by wei on 2017/4/13.
 */
public class JobDestinationDTO2 {
    private UUID id;

    //就业去向代码
    private String destinationType;

    //单位名称
    private String recName;

    //组织机构代码
    private String recCode;

    //单位性质
    private String recNature;

    //单位行业
    private String recIndustry;

    //单位所在地recCity
    private String recCity;
    // 工作职位类别jobType
    private String jobType;
    // 单位联系人recLinker
    private String recLinker;
    // 联系人电话recTelphone
    private String recTelphone;
    // 联系人手机recMobile
    private String recMobile;
//    // 联系人电子邮件recEmail
//    private String recEmail;
    // 单位详细地址recAddress
    private String recAddress;
    // 单位邮编recZipcode
    private String recZipcode;
    // 档案转寄单位名称pfileToName
    private String pfileToName;
    // 档案转寄部门pfileToDepart
    private String pfileToDepart;
    // 档案转寄单位地址pfileToAddress
    private String pfileToAddress;
    // 档案转寄单位邮编pfileToZipcode
    private String pfileToZipcode;
    // 户口迁转地址hukouToAddress
    private String hukouToAddress;
    private String hukouIsSchool;

    //报到证信息前发信息类别
    private String reportCardType;
    // 迁往单位所在地
    private String reportCardAddress;
    //报到证迁往单位
    private String reportCardRec;
    //报到证签发时间
    private LocalDate reportCardDate;

    // 创建时间createTime
    private LocalDateTime createTime;
    // 最后修改时间updateTime
    private LocalDateTime updateTime;



    //学号
    private String stuNo;
    //姓名
    private String name;


    //身份证号
    private String idCard;


    //民族代码
    private String ethnic;


    //学历代码
    private String eduDegree;

    //专业代码
    private String majorCode;


    //培养方式代码(1 非定向 ,2 定向 ,3 在职 ,4 委培 ,5自筹)
    private String eduMode;


    //生源地代码／名称
    private String homeland;


    //入学时间
    private String startDate;


    //所在院系
    private String department;

    public JobDestinationDTO2() {
    }


    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getEduMode() {
        return eduMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(String destinationType) {
        this.destinationType = destinationType;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecCode() {
        return recCode;
    }

    public void setRecCode(String recCode) {
        this.recCode = recCode;
    }

    public String getRecNature() {
        return recNature;
    }

    public void setRecNature(String recNature) {
        this.recNature = recNature;
    }

    public String getRecIndustry() {
        return recIndustry;
    }

    public void setRecIndustry(String recIndustry) {
        this.recIndustry = recIndustry;
    }

    public String getRecCity() {
        return recCity;
    }

    public void setRecCity(String recCity) {
        this.recCity = recCity;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getRecLinker() {
        return recLinker;
    }

    public void setRecLinker(String recLinker) {
        this.recLinker = recLinker;
    }

    public String getRecTelphone() {
        return recTelphone;
    }

    public void setRecTelphone(String recTelphone) {
        this.recTelphone = recTelphone;
    }

    public String getRecMobile() {
        return recMobile;
    }

    public void setRecMobile(String recMobile) {
        this.recMobile = recMobile;
    }

//    public String getRecEmail() {
//        return recEmail;
//    }
//
//    public void setRecEmail(String recEmail) {
//        this.recEmail = recEmail;
//    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getRecZipcode() {
        return recZipcode;
    }

    public void setRecZipcode(String recZipcode) {
        this.recZipcode = recZipcode;
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

    public String getPfileToZipcode() {
        return pfileToZipcode;
    }

    public void setPfileToZipcode(String pfileToZipcode) {
        this.pfileToZipcode = pfileToZipcode;
    }

    public String getHukouToAddress() {
        return hukouToAddress;
    }

    public void setHukouToAddress(String hukouToAddress) {
        this.hukouToAddress = hukouToAddress;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
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

    public String getHukouIsSchool() {
        return hukouIsSchool;
    }

    public void setHukouIsSchool(String hukouIsSchool) {
        this.hukouIsSchool = hukouIsSchool;
    }

    public String getReportCardType() {
        return reportCardType;
    }

    public void setReportCardType(String reportCardType) {
        this.reportCardType = reportCardType;
    }

    public String getReportCardAddress() {
        return reportCardAddress;
    }

    public void setReportCardAddress(String reportCardAddress) {
        this.reportCardAddress = reportCardAddress;
    }

    public String getReportCardRec() {
        return reportCardRec;
    }

    public void setReportCardRec(String reportCardRec) {
        this.reportCardRec = reportCardRec;
    }

    public LocalDate getReportCardDate() {
        return reportCardDate;
    }

    public void setReportCardDate(LocalDate reportCardDate) {
        this.reportCardDate = reportCardDate;
    }

    public void setEduMode(String eduMode) {
        this.eduMode = eduMode;
    }

    public String getHomeland() {
        return homeland;
    }

    public void setHomeland(String homeland) {
        this.homeland = homeland;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
