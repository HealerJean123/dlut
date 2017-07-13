package cn.edu.dlut.career.domain.company;

import cn.edu.dlut.career.util.PubCodeUtil;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by wei on 2017/5/26.
 * 场地预约表
 */
@Entity
@Table(name = "field")
public class Field {
    //主键
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;
    //场地要求/类型(多媒体教室、机房...)
    @Column(length = 3, nullable = false)
    private String fieldRequire;
    @Transient
    private String fieldRequireName;

    //场地规模(0-50人，50-100人，....)
    @Column(length = 3, nullable = false)
    private String fieldSize;
    @Transient
    private String fieldSizeName;

    //场地数量
    @Column(nullable = true)
    private Integer fieldNum;

    //场地用途
    @Column(length = 100, nullable = false)
    private String fieldUsing;

    @Transient
    private String fieldUsingName;

    //召开日期
    @Column(length = 20)
    private String startDate;

    //使用开始时间
    @Column(length = 20,nullable = true)
    private String startTime;
    //使用结束时间
    @Column(length = 20,nullable = true)
    private String endTime;

    //审核状态（0待处理，1借用中，2已落实，3已回复，4未落实 X废弃不用）（0待审核，1审核不通过，2审核通过提醒缴费，3未交费，4已缴费）
    @Column( length = 3, nullable = false)
    private String auditStatus;
    @Transient
    private String auditStatusName;

    //审批意见
    @Column(length = 1000, nullable = true)
    private String auditSuggest;

    //场地地址
    @Column(length = 100, nullable = true)
    private String fieldAddress;


    //接待人
    @Column(length = 50, nullable = true)
    private String receiver;

    //接待人联系方式
    @Column(length = 20, nullable = true)
    private String receiverTel;

    //审核时间
    @Column(nullable = true)
    private String auditTime;

    //审核人
    @Column(length = 50, nullable = true)
    private String auditor;

    // TODO: 2017/6/2 场地费用待定
    //场地费用
    @Column(nullable = true)
    private Float fieldCost;

    //缴费方式(线上，线下)
    @Column(length = 3, nullable = true)
    private String payType;

    //缴费时间
    @Column(nullable = true)
    private LocalDateTime payTime;

    //申请日期
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime applicationTime;

    //企业ID
    @Column(length = 32)
    private UUID recId;
    //企业名称
    @Column(length = 30)
    private String recName;

    //企业联系人
    @Column(length = 30)
    private String contacts;

    //企业联系人电话
    @Column(length = 30)
    private String contactsPhone;

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFieldRequire() {
        return fieldRequire;
    }

    public void setFieldRequire(String fieldRequire) {
        this.fieldRequire = fieldRequire;
    }

    public String getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(String fieldSize) {
        this.fieldSize = fieldSize;
    }

    public Integer getFieldNum() {
        return fieldNum;
    }

    public void setFieldNum(Integer fieldNum) {
        this.fieldNum = fieldNum;
    }

    public String getFieldUsing() {
        return fieldUsing;
    }

    public void setFieldUsing(String fieldUsing) {
        this.fieldUsing = fieldUsing;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFieldRequireName() {
        return PubCodeUtil.getName("areaRequire",this.getFieldRequire());
    }

    public String getFieldSizeName() {
        return PubCodeUtil.getName("areaSize",this.getFieldSize());
    }

    public String getFieldUsingName() {
        return PubCodeUtil.getName("areaUsing",this.getFieldUsing());
    }

    public String getAuditStatusName() {
        return PubCodeUtil.getName("areaAuditStatus",this.getAuditStatus());

    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditSuggest() {
        return auditSuggest;
    }

    public void setAuditSuggest(String auditSuggest) {
        this.auditSuggest = auditSuggest;
    }

    public String getFieldAddress() {
        return fieldAddress;
    }

    public void setFieldAddress(String fieldAddress) {
        this.fieldAddress = fieldAddress;
    }

    public Float getFieldCost() {
        return fieldCost;
    }

    public void setFieldCost(Float fieldCost) {
        this.fieldCost = fieldCost;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public LocalDateTime getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(LocalDateTime applicationTime) {
        this.applicationTime = applicationTime;
    }

    public UUID getRecId() {
        return recId;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
    }
}
