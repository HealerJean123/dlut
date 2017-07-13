package cn.edu.dlut.career.dto.student;

import java.util.UUID;

/**
 * 连接学生学籍信息表stuId  和就业去向表 jobDestinationId
 * Created by HealerJean on 2017/4/14.
 */

public class StuRecOfferDTO {

    //学号（外键）
    private UUID stuId;

    //毕业去向（外键）
    private UUID jobDestinationId;

    //姓名
    private String realName;

    //学历代码
    private String eduDegree;

    //身份证号
    private String idCard;

    //民族代码
    private String ethnic;

    //所在院系
    private String department;

    //专业代码
    private String majorCode;

    //专业名称（标准的专业名称）
    private String majorName;

    //生源地代码／名称
    private String homeland;

    //入学时间
    private String startDate;

    //培养方式代码(1 非定向 ,2 定向 ,3 在职 ,4 委培 ,5自筹)
    private String eduMode;

    //就业去向代码
    private String destinationType;

    // 工作职位类别jobType
    private String jobType;

    //单位名称
    private String recName;

    //组织机构代码
    private String recCode;

    //单位行业
    private String recIndustry;

    //单位性质
    private String recNature;

    //单位所在地recCity
    private String recCity;

    // 单位邮编recZipcode
    private String recZipcode;

    // 单位详细地址recAddress
    private String recAddress;


    public StuRecOfferDTO() {
    }

    public UUID getStuId() {
        return stuId;
    }

    public void setStuId(UUID stuId) {
        this.stuId = stuId;
    }

    public UUID getJobDestinationId() {
        return jobDestinationId;
    }

    public void setJobDestinationId(UUID jobDestinationId) {
        this.jobDestinationId = jobDestinationId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEduDegree() {
        return eduDegree;
    }

    public void setEduDegree(String eduDegree) {
        this.eduDegree = eduDegree;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getHomeland() {
        return homeland;
    }

    public void setHomeland(String homeland) {
        this.homeland = homeland;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEduMode() {
        return eduMode;
    }

    public void setEduMode(String eduMode) {
        this.eduMode = eduMode;
    }

    public String getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(String destinationType) {
        this.destinationType = destinationType;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
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

    public String getRecIndustry() {
        return recIndustry;
    }

    public void setRecIndustry(String recIndustry) {
        this.recIndustry = recIndustry;
    }

    public String getRecNature() {
        return recNature;
    }

    public void setRecNature(String recNature) {
        this.recNature = recNature;
    }

    public String getRecCity() {
        return recCity;
    }

    public void setRecCity(String recCity) {
        this.recCity = recCity;
    }

    public String getRecZipcode() {
        return recZipcode;
    }

    public void setRecZipcode(String recZipcode) {
        this.recZipcode = recZipcode;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }
}
