package cn.edu.dlut.career.domain.company;

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
 * Created by 史念念 on 2017/4/7.
 *
 * 招聘简章 实体类
 */
@Entity
@Table(name="rec_bulletin")
public class RecBulletin {

    //主键ID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //招聘简章标题
    @Column(length = 100,nullable = false)
    private String title;

    //企业ID
    @Column(name = "rec_id",nullable = false)
    private UUID recId;

    //招聘简章内容
    @Column(length = 2000,nullable = false)
    private String content;

    //有效开始时间
    @Column(name = "start_time",nullable = false)
    private LocalDate startTime;

    //有效结束时间
    @Column(name="end_time",nullable = false)
    private LocalDate endTime;

    //审核状态（待审核，审核通过，审核不通过）
    @Column(name="audit_status",length = 3, nullable = false)
    private String auditStatus;

    //审核状态名
    @Transient
    private String auditStatusName;

    //审核时间
    @Column(name = "audit_time",nullable = true)
    private LocalDateTime auditTime;

    //审核人
    @Column(length = 50,nullable = true)
    private String auditor;

    //审核不通过原因
    @Column(name = "nopass_reason",length = 100,nullable = true)
    private String nopassReason;

    //申请/发布时间
    @Column(name="publish_time",nullable = false)
    @CreationTimestamp
    private LocalDateTime publishTime;

    //最后修改时间
    @Column(name="update_time",nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateTime;

    //上下线状态
    @Column(name="online_status",length = 3,nullable = false)
    private String onlineStatus;

    //备注信息
    @Column(length = 1000,nullable = true)
    private String remarks;

    //关联招聘职位 创建中间表rec_bulletin_job
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="rec_bulletin_job")
    private List<RecJobPosition> recJobPositions = new ArrayList<RecJobPosition>();

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
