package cn.edu.dlut.career.dto.company;

import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/6/1.
 *
 * 企业端、教师端查询招聘简章时使用此DTO  提高查询效率
 */
public class RecBulletinDTO2 {

    //主键ID
    private UUID id;

    //招聘简章标题
    private String title;

    //企业ID
    private UUID recId;

    //企业名
    private String recName;

    //招聘简章内容
    private String content;

    //有效开始时间
    private LocalDate startTime;

    //有效结束时间
    private LocalDate endTime;

    //审核状态（待审核，审核通过，审核不通过）
    private String auditStatus;

    //审核状态名
    private String auditStatusName;

    //审核时间
    private LocalDateTime auditTime;

    //审核人
    private String auditor;

    //审核不通过原因
    private String nopassReason;

    //申请/发布时间
    private LocalDateTime publishTime;

    //最后修改时间
    private LocalDateTime updateTime;

    //上下线状态
    private String onlineStatus;

    //备注信息
    private String remarks;

    //关联招聘职位 创建中间表rec_bulletin_job
    private List<RecJobPosition> recJobPositions = new ArrayList<RecJobPosition>();

    public RecBulletinDTO2(){}

    public RecBulletinDTO2(UUID id, String title, UUID recId, String recName, LocalDate startTime, LocalDate endTime, String auditStatus, LocalDateTime publishTime, String onlineStatus) {
        this.id = id;
        this.title = title;
        this.recId = recId;
        this.recName = recName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.auditStatus = auditStatus;
        this.publishTime = publishTime;
        this.onlineStatus = onlineStatus;
    }

    public RecBulletinDTO2(UUID id, String title, LocalDate startTime, LocalDate endTime, String auditStatus, LocalDateTime publishTime, String onlineStatus) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.auditStatus = auditStatus;
        this.publishTime = publishTime;
        this.onlineStatus = onlineStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<RecJobPosition> getRecJobPositions() {
        return recJobPositions;
    }

    public void setRecJobPositions(List<RecJobPosition> recJobPositions) {
        this.recJobPositions = recJobPositions;
    }

    public String getAuditStatusName() {
        return PubCodeUtil.getName("auditStatus",this.getAuditStatus());
    }
}
