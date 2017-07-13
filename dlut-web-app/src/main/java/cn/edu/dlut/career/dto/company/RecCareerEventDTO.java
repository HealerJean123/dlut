package cn.edu.dlut.career.dto.company;

import cn.edu.dlut.career.domain.common.PubCode;
import cn.edu.dlut.career.util.PubCodeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.net.ServerSocket;
import java.util.UUID;

/**
 * 专场预约申请 表对应DTO 对应于企业端
 * Created by HealerJean on 2017/4/10.
 */
public class RecCareerEventDTO implements Serializable {

    //主键
    private UUID id;

    //招聘简章id
    private UUID bulletinId;

    //场地关联外键
    private UUID fieldId;

    //企业id
    private UUID recId;

    //招聘会名称
    private String fairName;

    private String startDate;

    //招聘会开始时间
    private String fairStartTime;

    //招聘会结束时间
    private String fairEndTime;


    //企业名称
    private String recName;

    //企业所在地
    private String recAddress;

    //联系人
    private String contacts;

    //联系人联系电话
    private String conTel;

    //联系人邮箱
    private String conEmail;

    //备注
    private String remarks;
    //审核时间
    private String fairAuditTime;

    //审核人
    private String fairAuditor;

    //审核状态 00待审核 01审核通过 02审核不通过
    @JsonIgnore
    private String fairAuditsStatus;

    //教师审核状态 对应名称
    private String fairAuditsStatusName;


    //场地要求/类型(多媒体教室、机房...)
    private String areaRequire;

    //场地规模(0-50人，50-100人，....)
    private String areaSize;

    //场地数量
    private Integer areaNum;

    //场地用途
    private String areaUsing;


    //审批意见
    private String auditSuggest;

    //场地地址
    private String areaAddress;

    //场地费用
    private Float areaCost;

    //接待人
    private String receiver;

    //接待人联系方式
    private String receiverTel;

    //审核时间
    private String fieldAuditTime;

    //审核人
    private String uditor;

    //审核状态（0待处理，1借用中，2已落实，3已回复，4未落实 X废弃不用）（0待审核，1审核不通过，2审核通过提醒缴费，3未交费，4已缴费）
   @JsonIgnore
    private String fieldAuditStatus;

    //审核状态（0待处理，1借用中，2已落实，3已回复，4未落实 X废弃不用）（0待审核，1审核不通过，2审核通过提醒缴费，3未交费，4已缴费）
    private String fieldAuditStatusName;

    //缴费方式(线上，线下)
    private String payType;

    //缴费时间
    private String payTime;

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

    public UUID getFieldId() {
        return fieldId;
    }

    public void setFieldId(UUID fieldId) {
        this.fieldId = fieldId;
    }

    public UUID getRecId() {
        return recId;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFairAuditTime() {
        return fairAuditTime;
    }

    public void setFairAuditTime(String fairAuditTime) {
        this.fairAuditTime = fairAuditTime;
    }

    public String getFairAuditor() {
        return fairAuditor;
    }

    public void setFairAuditor(String fairAuditor) {
        this.fairAuditor = fairAuditor;
    }

    public String getFairAuditsStatus() {
        return fairAuditsStatus;
    }

    public void setFairAuditsStatus(String fairAuditsStatus) {
        this.fairAuditsStatus = fairAuditsStatus;
    }

    public String getFairAuditsStatusName() {
        return PubCodeUtil.getName("auditStatus",this.getFairAuditsStatus());
    }

    public void setFairAuditsStatusName(String fairAuditsStatusName) {
        this.fairAuditsStatusName = fairAuditsStatusName;
    }

    public String getAreaRequire() {
        return areaRequire;
    }

    public void setAreaRequire(String areaRequire) {
        this.areaRequire = areaRequire;
    }

    public String getAreaSize() {
        return areaSize;
    }

    public void setAreaSize(String areaSize) {
        this.areaSize = areaSize;
    }

    public Integer getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(Integer areaNum) {
        this.areaNum = areaNum;
    }

    public String getAreaUsing() {
        return areaUsing;
    }

    public void setAreaUsing(String areaUsing) {
        this.areaUsing = areaUsing;
    }

    public String getAuditSuggest() {
        return auditSuggest;
    }

    public void setAuditSuggest(String auditSuggest) {
        this.auditSuggest = auditSuggest;
    }

    public String getAreaAddress() {
        return areaAddress;
    }

    public void setAreaAddress(String areaAddress) {
        this.areaAddress = areaAddress;
    }

    public Float getAreaCost() {
        return areaCost;
    }

    public void setAreaCost(Float areaCost) {
        this.areaCost = areaCost;
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

    public String getFieldAuditTime() {
        return fieldAuditTime;
    }

    public void setFieldAuditTime(String fieldAuditTime) {
        this.fieldAuditTime = fieldAuditTime;
    }

    public String getUditor() {
        return uditor;
    }

    public void setUditor(String uditor) {
        this.uditor = uditor;
    }

    public String getFieldAuditStatus() {
        return fieldAuditStatus;
    }

    public void setFieldAuditStatus(String fieldAuditStatus) {
        this.fieldAuditStatus = fieldAuditStatus;
    }

    public String getFieldAuditStatusName() {
        return PubCodeUtil.getName("auditStatus",this.getFieldAuditStatus());
    }



    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
