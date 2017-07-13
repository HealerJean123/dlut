package cn.edu.dlut.career.dto.student;

import cn.edu.dlut.career.util.PubCodeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/18.
 *
 * 违约申请 DTO
 */
public class ViolateApplicationDTO {
    //主键id
    private UUID id;

    //学号（外键）
    private  String stuNo;

    //姓名
    private String stuName;

    //所在院系id
    private String departmentId;

    //所在院系
    private String department;

    //专业代码
    private String majorCode;

    //专业名称（标准的专业名称）
    private String majorName;

    //公司名
    private String recName;

    //关联的协议书(三方协议)
    private  UUID recOfferId;

    //违约原因（关联字典 ）
    private  String reason;
    //违约原因 对应名称
    private String reasonName;

    //申请理由及原因
    private  String applyReason;

    //违约金金额
    private  float cost;

    @JsonIgnore
    //申请审核状态（1 待审核 ，2 院系同意，3 院系不同意，4 学校同意 ，5 学校不同意）
    private String auditStatus;
    //申请审核状态对应名称
    private String auditStatusName;

    //院系审核状态（0,待审核，1 审核 通过，2 不通过
    private String departAuditStatus;

    //院系审核时间
    private LocalDateTime departAuditTime;

    //院系审核人
    private String departAuditor;

    //院系审核不通过原因
    private String departNoPassReason;


    //学校审核状态（0,待审核，1 审核 通过，2 不通过
    private String schAuditStatus;

    //学校审核时间
    private LocalDateTime schAuditTime;

    //学校审核人
    private String schAuditor;

    //学校审核不通过原因
    private String schNoPassReason;


    //申请时间
    private LocalDateTime applicationTime;

    public ViolateApplicationDTO(){}

    public ViolateApplicationDTO(UUID id, String stuNo, String stuName, String departmentId, String department, String majorCode, String majorName, String recName, UUID recOfferId, String reason, String applyReason, float cost, String auditStatus, String departAuditStatus, LocalDateTime departAuditTime, String departAuditor, String departNoPassReason, String schAuditStatus, LocalDateTime schAuditTime, String schAuditor, String schNoPassReason, LocalDateTime applicationTime) {
        this.id = id;
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.departmentId = departmentId;
        this.department = department;
        this.majorCode = majorCode;
        this.majorName = majorName;
        this.recName = recName;
        this.recOfferId = recOfferId;
        this.reason = reason;
        this.applyReason = applyReason;
        this.cost = cost;
        this.auditStatus = auditStatus;
        this.departAuditStatus = departAuditStatus;
        this.departAuditTime = departAuditTime;
        this.departAuditor = departAuditor;
        this.departNoPassReason = departNoPassReason;
        this.schAuditStatus = schAuditStatus;
        this.schAuditTime = schAuditTime;
        this.schAuditor = schAuditor;
        this.schNoPassReason = schNoPassReason;
        this.applicationTime = applicationTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRecOfferId(UUID recOfferId) {
        this.recOfferId = recOfferId;
    }

    public UUID getRecOfferId() {
        return recOfferId;
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

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReasonName() {
        return PubCodeUtil.getName("violateReason",this.getReason());
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
}
