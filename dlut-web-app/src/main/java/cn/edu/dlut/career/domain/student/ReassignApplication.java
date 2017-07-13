package cn.edu.dlut.career.domain.student;

import cn.edu.dlut.career.util.PubCodeUtil;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 改派申请表
 * Created by HealerJean on 2017/4/12.
 */
@Entity
@Table(name="stu_reassign_application")
public class ReassignApplication {

    //主键id
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //学号
    @Column(length = 32, nullable = false)
    private String stuNo;

    //姓名
    @Column(length = 40, nullable = false)
    private String stuName;

    //所在院系
    @Column(length = 32, nullable = false)
    private String department;

    //所在院系 id
    @Column(length = 32, nullable = true)
    private String departmentId;


    //学历名称（代码）
    @Column(length = 2, nullable = true)
    private String eduDegree;

    //专业代码
    @Column(length = 6, nullable = false)
    private String majorCode;

    //专业名称（标准的专业名称）
    @Column(length = 32, nullable = true)
    private String majorName;

    //生源地代码／名称
    @Column(length = 20, nullable = true)
    private String homelandName;

    //毕业时间
    @Column(length = 10,nullable = true)
    private String endDate;


    //违约原因（关联字典 ）
    @Column(length = 3,nullable = false)
    private  String reason;

    //违约原因 对应名称
    @Transient
    private String reasonName;

    //申请理由及原因
    @Column(length = 100,nullable = false)
    private  String applyReason;

    //原派遣单位代码
    @Column(length = 20, nullable = true)
    private String  originalRecCode;

    //原派遣单位地址
    @Column(length = 50, nullable = false)
    private String  originalAddress;

    //原派遣单位名称
    @Column(length = 50, nullable = false)
    private String  originalRecName;


    //单位代码
    @Column(length = 9, nullable = true)
    private String  recCode;

    //单位名称
    @Column(length = 50, nullable = true)
    private String  recName;

    //档案转寄单位名称
    @Column(length = 50, nullable = true)
    private String  pfileToName;

    //档案转寄部门
    @Column(length = 20, nullable = true)
    private String  pfileToDepart;

    //档案转寄单位地址
    @Column(length = 50, nullable = true)
    private String  pfileToAddress;

    //档案转寄单位邮编
    @Column(length = 6, nullable = true)
    private String  pfileToZipcode;

    //户口迁转地址
    @Column(length = 50, nullable = true)
    private String  hukouToAddress;

    //审核时间
    @Column( nullable = true)
    private LocalDateTime auditTime;

    //审核人
    @Column(length = 20,nullable = true)
    private String auditor;

    //审核状态（0,待审核，1 审核通过 2 审核不通过）
    @Column(length = 3, nullable = false)
    private String auditStatus;

    //审核状态 对应名称
    @Transient
    private String auditStatusName;

    //申请时间
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime applicationTime;

    //审核不通过原因
    @Column(length = 50,nullable = true)
    private String noPassReason;

    public String getNoPassReason() {
        return noPassReason;
    }

    public void setNoPassReason(String noPassReason) {
        this.noPassReason = noPassReason;
    }

    public ReassignApplication() {
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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
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

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getHomelandName() {
        return homelandName;
    }

    public void setHomelandName(String homelandName) {
        this.homelandName = homelandName;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReasonName() {
        return PubCodeUtil.getName("reassignReason",this.getReason());
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getOriginalRecCode() {
        return originalRecCode;
    }

    public void setOriginalRecCode(String originalRecCode) {
        this.originalRecCode = originalRecCode;
    }

    public String getOriginalAddress() {
        return originalAddress;
    }

    public void setOriginalAddress(String originalAddress) {
        this.originalAddress = originalAddress;
    }

    public String getOriginalRecName() {
        return originalRecName;
    }

    public void setOriginalRecName(String originalRecName) {
        this.originalRecName = originalRecName;
    }

    public String getRecCode() {
        return recCode;
    }

    public void setRecCode(String recCode) {
        this.recCode = recCode;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
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

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditStatusName() {
        return PubCodeUtil.getName("auditStatus",this.getAuditStatus());
    }

    public LocalDateTime getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(LocalDateTime applicationTime) {
        this.applicationTime = applicationTime;
    }
}






