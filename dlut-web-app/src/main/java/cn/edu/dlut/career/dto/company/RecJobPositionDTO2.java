package cn.edu.dlut.career.dto.company;

import cn.edu.dlut.career.util.PubCodeUtil;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/6/5.
 *
 * 招聘职位DTO 教师端查询时使用
 */
public class RecJobPositionDTO2 {

    //编号
    private UUID id;

    //公司编号(外键)
    private UUID recId;

    //公司名
    private String recName;

    //职位名称
    private String name;

    //职位描述
    private String description;

    //职位类型 （01 兼职，02 实习，03全职）
    private String type;

    //职位类型名
    private String typeName;

    //职位类别
    private String category;

    //职位类别名
    private String categoryName;

    //学历要求(0 不限，1 专科，2 本科，3 硕士，4 博士)
    private String degree;

    //学历名
    private String degreeName;

    //专业要求(企业手动输入，不关联字典)
    private String major;

    //接受简历方式（01：两者都可,02：只用邮箱接收简历，03：只在该系统接收简历）
    private String receiveMode;

    //接收简历邮箱
    private String recEmail;

    //工作城市
    private String city;

    //详细地址
    private String address;

    //招聘人数
    private Integer recruitmentNum;

    //薪资待遇 （关联字典 0:0-3000,1:3000-5000....）
    private String salary;


    //有效开始时间
    private LocalDate startTime;

    //有效结束时间
    private LocalDate endTime;

    //审核状态（0 等待审核，1 通过审核，2 未通过审核）
    private String auditStatus;

    //审核状态名
    @Transient
    private String auditStatusName;

    //审核时间
    private LocalDateTime auditTime;

    //审核人
    private String auditor;

    //审核失败原因
    private String nopassReason;

    //提交时间
    private LocalDateTime publishTime;

    //上下线状态
    private String onlineStatus;

    //标签（匹配学生群体特征 专业，性别，学历）
    private String tags;

    public RecJobPositionDTO2() {}

    public RecJobPositionDTO2(UUID id, String recName, String name, String type, String degree, String major, String city, Integer recruitmentNum, String auditStatus, LocalDate startTime, LocalDate endTime, LocalDateTime publishTime, String onlineStatus) {
        this.id = id;
        this.recName = recName;
        this.name = name;
        this.type = type;
        this.degree = degree;
        this.major = major;
        this.city = city;
        this.recruitmentNum = recruitmentNum;
        this.auditStatus = auditStatus;
        this.startTime = startTime;
        this.endTime = endTime;
        this.publishTime = publishTime;
        this.onlineStatus = onlineStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRecId() {
        return recId;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getReceiveMode() {
        return receiveMode;
    }

    public void setReceiveMode(String receiveMode) {
        this.receiveMode = receiveMode;
    }

    public String getRecEmail() {
        return recEmail;
    }

    public void setRecEmail(String recEmail) {
        this.recEmail = recEmail;
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

    public Integer getRecruitmentNum() {
        return recruitmentNum;
    }

    public void setRecruitmentNum(Integer recruitmentNum) {
        this.recruitmentNum = recruitmentNum;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
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

    public String getNopassReason() {
        return nopassReason;
    }

    public void setNopassReason(String nopassReason) {
        this.nopassReason = nopassReason;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


    public String getTypeName() {
        return PubCodeUtil.getName("positionType",this.getType());
    }

    public String getCategoryName() {
        return PubCodeUtil.getName("jobType",this.getCategory());
    }

    public String getDegreeName() {
        return PubCodeUtil.getName("eduDegree",this.getDegree());
    }

    public String getAuditStatusName() {
        return PubCodeUtil.getName("auditStatus",this.getAuditStatus());
    }
}
