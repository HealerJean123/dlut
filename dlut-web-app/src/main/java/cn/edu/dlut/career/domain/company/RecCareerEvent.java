package cn.edu.dlut.career.domain.company;

import cn.edu.dlut.career.util.PubCodeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/24.
 *
 * 专场招聘会预约 实体类
 */
@Entity
@Table(name = "rec_career_event")
public class RecCareerEvent {

    //主键
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //招聘简章id
    @Column(name = "bulletin_id", nullable = true)
    private UUID bulletinId;

    //招聘会名称
    @Column(name = "fair_name", length = 100, nullable = false)
    private String fairName;

    //申请日期
    @Column(length = 30)
    private String applicationDate;

    //召开日期
    @Column(length = 30)
    private String startDate;

    //招聘会开始时间
    @Column(name = "fair_start_time", nullable = false)
    private String fairStartTime;

    //招聘会结束时间
    @Column(name = "fair_end_time", nullable = false)
    private String fairEndTime;

    //企业id
    @Column(name = "rec_id", nullable = false)
    private UUID recId;

    //企业名称
    @Column(name = "rec_name", length = 100, nullable = false)
    private String recName;

    //企业所在地
    @Column(name = "rec_address", length = 100, nullable = false)
    private String recAddress;

    //联系人
    @Column(name = "contacts", length = 50, nullable = false)
    private String contacts;

    //联系人联系电话
    @Column(name = "con_tel", length = 20, nullable = false)
    private String conTel;

    //联系人邮箱
    @Column(name = "con_email", length = 100, nullable = false)
    private String conEmail;

    //场地关联外键
    @Column(length = 32)
    private UUID fieldId;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateTime;

    //备注
    @Column(name = "remarks", length = 1000, nullable = true)
    private String remarks;


    //审核时间
    @Column
    private LocalDateTime auditTime;

    //审核人
    @Column(length = 10, nullable = true)
    private String auditor;

    //审核状态 00待审核 01审核通过 02审核不通过
    @Column(length = 3, nullable = true)
    private String auditsStatus;

    //教师审核状态 对应名称
    @Transient
    private String auditsStatusName;

    public RecCareerEvent() {
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(UUID bulletinId) {
        this.bulletinId = bulletinId;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
    }

    public String getFairName() {
        return fairName;
    }

    public void setFairName(String fairName) {
        this.fairName = fairName;
    }

    public String getFairStartTime() {
        return fairStartTime;
    }

    public void setFairStartTime(String fairStartTime) {
        this.fairStartTime = fairStartTime;
    }

    public String getFairEndTime() {
        return fairEndTime;
    }

    public void setFairEndTime(String fairEndTime) {
        this.fairEndTime = fairEndTime;
    }

    public void setAuditsStatusName(String auditsStatusName) {
        this.auditsStatusName = auditsStatusName;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getConTel() {
        return conTel;
    }

    public void setConTel(String conTel) {
        this.conTel = conTel;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }

    public UUID getRecId() {
        return recId;
    }

    public UUID getFieldId() {
        return fieldId;
    }

    public void setFieldId(UUID fieldId) {
        this.fieldId = fieldId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getAuditsStatus() {
        return auditsStatus;
    }

    public void setAuditsStatus(String auditsStatus) {
        this.auditsStatus = auditsStatus;
    }

    public String getAuditsStatusName() {
        return PubCodeUtil.getName("auditStatus",this.getAuditsStatus());
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }
}
