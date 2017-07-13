package cn.edu.dlut.career.domain.student;


import cn.edu.dlut.career.util.PubCodeUtil;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by wei on 2017/4/5.
 * 空白协议申请表信息
 */
@Entity
@Table(name = "blank_protocol")
public class BlankProtocol {
    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    @Type(type = "pg-uuid")
    private UUID id;
    //学生id
    @Column(length = 32,nullable = false, updatable = false)
    private UUID stuId;
    //学号
    @Column(length = 32,nullable = false, updatable = false)
    private String stuNo;
    //姓名
    @Column(length=30,nullable = false, updatable = false)
    private String name;
    //学历层次（关联字典）
    @Column(length = 20,nullable = false, updatable = false)
    private String eduDegree;
    //所属院系id
    @Column(length=20,nullable = false, updatable = false)
    private String departmentId;
    //所属院系
    @Column(length=20,nullable = false, updatable = false)
    private String department;
    //所学专业
    @Column(length=20,nullable = false, updatable = false)
    private String major;
    //联系电话
    @Column(length=20,nullable = false, updatable = false)
    private String mobilephone;
    //电子邮件
    @Column(length=20,nullable = false, updatable = false)
    private String email;
    //毕业时间
    @Column(nullable = false,length = 10, updatable = false)
    private String endDate;
    //申请原因
    @Column(length = 10, nullable = false, updatable = false)
    private String applicationReason;

    //申请原因对应名称
    @Transient
    private String applicationReasonName;

    //申请原因备注
    @Column(length = 100)
    private String applicationReasonRemarks;
    //申请审核状态（0 待审核 ，1 院系同意，2 院系不同意，3 学校同意 ，4 学校不同意）
    @Column(length = 3, nullable = false)
    private String auditStatus;

    @Transient
    private String auditStatusName;

    //院系审核状态（0,待审核，1 审核 通过，2 不通过
    @Column(length = 3, nullable = false)
    private String departAuditStatus;
    //院系审核时间
    @Column(nullable = true)
    private LocalDateTime departAuditTime;
    //院系审核人
    @Column(length = 10, nullable = true)
    private String departAuditor;
    //院系审核不通过原因
    @Column(length = 50, nullable = true)
    private String departNoPassReason;
    //学校审核状态（0,待审核，1 审核 通过，2 不通过
    @Column(length = 3, nullable = false)
    private String schAuditStatus;
    //学校审核时间
    @Column(nullable = true)
    private LocalDateTime schAuditTime;
    //学校审核人
    @Column(length = 10, nullable = true)
    private String schAuditor;
    //学校审核不通过原因
    @Column(length = 50,nullable = true)
    private String schNoPassReason;
    //申请时间
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime applicationTime;
    // 生成协议书编号
    @Column(length = 40, nullable = true)
    private String agreementId;
    //空白协议书打印时间
    @Column(nullable = true)
    private LocalDateTime greementPrintingTtime;

    public BlankProtocol() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getStuId() {
        return stuId;
    }

    public void setStuId(UUID stuId) {
        this.stuId = stuId;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEduDegree() {
        return eduDegree;
    }

    public void setEduDegree(String eduDegree) {
        this.eduDegree = eduDegree;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getApplicationReason() {
        return applicationReason;
    }

    public void setApplicationReason(String applicationReason) {
        this.applicationReason = applicationReason;
    }

    public String getApplicationReasonName() {
        return PubCodeUtil.getName("blankProReason",this.getApplicationReason());
    }

    public String getApplicationReasonRemarks() {
        return applicationReasonRemarks;
    }

    public void setApplicationReasonRemarks(String applicationReasonRemarks) {
        this.applicationReasonRemarks = applicationReasonRemarks;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditStatusName() {
        return PubCodeUtil.getName("totalAuditStatus",this.getAuditStatus());
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

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public LocalDateTime getGreementPrintingTtime() {
        return greementPrintingTtime;
    }

    public void setGreementPrintingTtime(LocalDateTime greementPrintingTtime) {
        this.greementPrintingTtime = greementPrintingTtime;
    }
}
