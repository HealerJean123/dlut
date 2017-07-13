package cn.edu.dlut.career.domain.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 违约申请表
 * Created by HealerJean on 2017/4/12.
 */
@Entity
@Table(name="stu_violate_application")
public class ViolateApplication {

    //主键id
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //学号（外键）
    @Column(length = 32,nullable = false)
    private  String stuNo;

    //姓名
    @Column(length = 20, nullable = false)
    private String stuName;

    //所在院系
    @Column(length = 32, nullable = true)
    private String department;

    //所在院系id
    @Column(length = 32, nullable = true)
    private String departmentId;

    //专业代码
    @Column(length = 6, nullable = true)
    private String majorCode;

    //专业名称（标准的专业名称）
    @Column(length = 32, nullable = true)
    private String majorName;

    //关联的协议书(三方协议)
    @Column(length = 32,nullable = true)
    private  UUID recOfferId;

    //违约原因（关联字典 ）
    @Column(length = 3,nullable = false)
    private  String reason;

    //申请理由及原因
    @Column(length = 100,nullable = false)
    private  String applyReason;

    //违约金金额
    @Column(nullable = true)
    private  float cost;

    //申请审核状态（0 待审核 ，1 院系同意，2 院系不同意，3 学校同意 ，4 学校不同意）
    @Column(length = 3, nullable = false)
    private String auditStatus;

    //院系审核状态（0,待审核，1 审核 通过，2 不通过
    @Column(length = 3, nullable = false)
    private String departAuditStatus;

    //院系审核时间
    @Column(nullable = true)
    private LocalDateTime departAuditTime;

    //院系审核人
    @Column(length = 10,nullable = true)
    private String departAuditor;

    //院系审核不通过原因
    @Column(length = 50,nullable = true)
    private String departNoPassReason;


    //学校审核状态（0,待审核，1 审核 通过，2 不通过
    @Column(length = 3, nullable = false)
    private String schAuditStatus;

    //学校审核时间
    @Column(nullable = true)
    private LocalDateTime schAuditTime;

    //学校审核人
    @Column(length = 10,nullable = true)
    private String schAuditor;

    //学校审核不通过原因
    @Column(length = 50,nullable = true)
    private String schNoPassReason;


    //申请时间
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime applicationTime;



    public ViolateApplication() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public UUID getRecOfferId() {
        return recOfferId;
    }

    public void setRecOfferId(UUID recOfferId) {
        this.recOfferId = recOfferId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getDepartAuditStatus() {
        return departAuditStatus;
    }

    public void setDepartAuditStatus(String departAuditStatus) {
        this.departAuditStatus = departAuditStatus;
    }

    public LocalDateTime getDepartAuditTime() {
        return departAuditTime;
    }

    public void setDepartAuditTime(LocalDateTime departAuditTime) {
        this.departAuditTime = departAuditTime;
    }

    public String getDepartAuditor() {
        return departAuditor;
    }

    public void setDepartAuditor(String departAuditor) {
        this.departAuditor = departAuditor;
    }

    public String getDepartNoPassReason() {
        return departNoPassReason;
    }

    public void setDepartNoPassReason(String departNoPassReason) {
        this.departNoPassReason = departNoPassReason;
    }

    public String getSchAuditStatus() {
        return schAuditStatus;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
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

    public void setSchAuditStatus(String schAuditStatus) {
        this.schAuditStatus = schAuditStatus;
    }

    public LocalDateTime getSchAuditTime() {
        return schAuditTime;
    }

    public void setSchAuditTime(LocalDateTime schAuditTime) {
        this.schAuditTime = schAuditTime;
    }

    public String getSchAuditor() {
        return schAuditor;
    }

    public void setSchAuditor(String schAuditor) {
        this.schAuditor = schAuditor;
    }

    public String getSchNoPassReason() {
        return schNoPassReason;
    }

    public void setSchNoPassReason(String schNoPassReason) {
        this.schNoPassReason = schNoPassReason;
    }

    public LocalDateTime getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(LocalDateTime applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}




