package cn.edu.dlut.career.dto.company;


import cn.edu.dlut.career.util.PubCodeUtil;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by wei on 2017/5/27.
 */
public class FieldDTOJ {

    private UUID id;

    //场地用途
    private String fieldUsing;

    //使用开始时间
    private String startDate;

    //场地地址
    private String fieldAddress;

    //场地要求/类型(多媒体教室、机房...)
    private String fieldRequire;

    //场地规模(0-50人，50-100人，....)
    private String fieldSize;

    //审核状态（0待处理，1借用中，2已落实，3已回复，4未落实 X废弃不用）（0待审核，1审核不通过，2审核通过提醒缴费，3未交费，4已缴费）
    private String auditStatus;

    private String startTime;

    private String endTime;

    private String recName;

    private String auditSuggest;

    private String auditTime;


    //接待人
    private String receiver;

    //接待人联系方式
    private String receiverTel;





    //企业ID
    @Column(length = 32)
    private UUID recId;


    //企业联系人
    @Column(length = 30)
    private String contacts;

    //企业联系人电话
    @Column(length = 30)
    private String contactsPhone;

    public FieldDTOJ() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getFieldAddress() {
        return fieldAddress;
    }

    public void setFieldAddress(String fieldAddress) {
        this.fieldAddress = fieldAddress;
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

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
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

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getAuditSuggest() {
        return auditSuggest;
    }

    public void setAuditSuggest(String auditSuggest) {
        this.auditSuggest = auditSuggest;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
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

    public UUID getRecId() {
        return recId;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
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
}
