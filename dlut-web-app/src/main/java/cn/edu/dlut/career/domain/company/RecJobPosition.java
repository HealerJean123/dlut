package cn.edu.dlut.career.domain.company;

import cn.edu.dlut.career.util.PubCodeUtil;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/23.
 * 招聘职位  实体类
 */
@Entity
@Table(name = "rec_job_position")
public class RecJobPosition{

    //编号
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //公司编号(外键)
    @Column(name = "rec_id", length = 32, nullable = false)
    private UUID recId;

    //职位名称
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    //职位描述
    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    //职位类型 （01 兼职，02 实习，03全职）
    @Column(name = "type", length = 3, nullable = false)
    private String type;

    //职位类型名
    @Transient
    private String typeName;

    //职位类别
    @Column(name = "category", length = 3, nullable = false)
    private String category;

    //职位类别名
    @Transient
    private String categoryName;

    //学历要求(0 不限，1 专科，2 本科，3 硕士，4 博士)
    @Column(length = 3, nullable = false)
    private String degree;

    //学历名
    @Transient
    private String degreeName;

    //专业要求(企业手动输入，不关联字典)
    @Column(length = 100, nullable = false)
    private String major;

    //接受简历方式（01：两者都可,02：只用邮箱接收简历，03：只在该系统接收简历）
    @Column(name = "receive_mode", length = 3, nullable = false)
    private String receiveMode;

    //接收简历方式名
    @Transient
    private String receiveModeName;

    //接收简历邮箱
    @Column(name = "rec_email", length = 100, nullable = true)
    private String recEmail;

    //工作城市
    @Column(length = 100, nullable = false)
    private String city;

    //详细地址
    @Column(length = 1000, nullable = false)
    private String address;

    //招聘人数
    @Column(name = "recruitment_num", nullable = false)
    private Integer recruitmentNum;

    //薪资待遇 （关联字典 0:0-3000,1:3000-5000....）
    @Column(length = 3, nullable = false)
    private String salary;

    //有效开始时间
    @Column(name = "start_time", nullable = false)
    private LocalDate startTime;

    //有效结束时间
    @Column(name = "end_time", nullable = false)
    private LocalDate endTime;

    //审核状态（0 等待审核，1 通过审核，2 未通过审核）
    @Column(length = 3, nullable = false)
    private String auditStatus;

    //审核状态名
    @Transient
    private String auditStatusName;

    //审核时间
    @Column(nullable = true)
    private LocalDateTime auditTime;

    //审核人
    @Column(length = 32, nullable = true)
    private String auditor;

    //审核失败原因
    @Column(length = 100, nullable = true)
    private String nopassReason;

    //提交时间
    @Column(name = "publish_time", nullable = false)
    @CreationTimestamp
    private LocalDateTime publishTime;

    //上下线状态
    @Column(name = "online_status", length = 3, nullable = false)
    private String onlineStatus;

    //标签（匹配学生群体特征 专业，性别，学历）
    @Column(length = 1000, nullable = true)
    private String tags;

    public RecJobPosition() {
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public String getDegree() {
        return degree;
    }

    public String getMajor() {
        return major;
    }

    public String getReceiveMode() {
        return receiveMode;
    }

    public String getRecEmail() {
        return recEmail;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Integer getRecruitmentNum() {
        return recruitmentNum;
    }

    public String getSalary() {
        return salary;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public String getNopassReason() {
        return nopassReason;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public String getTags() {
        return tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setReceiveMode(String receiveMode) {
        this.receiveMode = receiveMode;
    }

    public void setRecEmail(String recEmail) {
        this.recEmail = recEmail;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRecruitmentNum(Integer recruitmentNum) {
        this.recruitmentNum = recruitmentNum;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public void setNopassReason(String nopassReason) {
        this.nopassReason = nopassReason;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
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

    public String getReceiveModeName() {
        return PubCodeUtil.getName("receiveMode",this.getReceiveMode());
    }
}
