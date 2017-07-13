package cn.edu.dlut.career.dto.company;


import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/17.
 *
 * 专场招聘会DTO   此DTO用于学生端招聘会查询
 */
public class RecCareerEventDTO2 {
    //主键
    private UUID id;

    //招聘简章id
    private UUID bulletinId;

    //招聘职位数量
    private long jobNum;

    //招聘会名称
    private String fairName;

    //招聘会开始时间
    private LocalDateTime fairStartTime;

    //招聘会结束时间
    private LocalDateTime fairEndTime;

    //企业id
    private UUID recId;

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

    //场地要求/类型(多媒体教室、机房...)
    private String areaRequire;

    //场地规模(0-50人，50-100人，....)
    private String areaSize;

    //场地数量
    private Integer areaNum;

    //场地用途
    private String areaUsing;

    //审核状态（0待处理，1借用中，2已落实，3已回复，4未落实 X废弃不用）（0待审核，1审核不通过，2审核通过提醒缴费，3未交费，4已缴费）
    private String auditStatus;

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
    private LocalDateTime auditTime;

    //审核人
    private String auditor;

    //缴费方式(线上，线下)
    private String payType;

    //缴费时间
    private LocalDateTime payTime;

    //申请日期
    private LocalDateTime applicationTime;

    //更新日期
    private LocalDateTime updateTime;

    //备注
    private String remarks;



    public RecCareerEventDTO2(){}

    public RecCareerEventDTO2(UUID id, UUID bulletinId, long jobNum, String fairName, LocalDateTime fairStartTime, LocalDateTime fairEndTime, UUID recId, String recName, String recAddress, String contacts, String conTel, String conEmail, String areaRequire, String areaSize, Integer areaNum, String areaUsing, String auditStatus, String auditSuggest, String areaAddress, Float areaCost, String receiver, String receiverTel, LocalDateTime auditTime, String auditor, String payType, LocalDateTime payTime, LocalDateTime applicationTime, LocalDateTime updateTime, String remarks) {
        this.id = id;
        this.bulletinId = bulletinId;
        this.jobNum = jobNum;
        this.fairName = fairName;
        this.fairStartTime = fairStartTime;
        this.fairEndTime = fairEndTime;
        this.recId = recId;
        this.recName = recName;
        this.recAddress = recAddress;
        this.contacts = contacts;
        this.conTel = conTel;
        this.conEmail = conEmail;
        this.areaRequire = areaRequire;
        this.areaSize = areaSize;
        this.areaNum = areaNum;
        this.areaUsing = areaUsing;
        this.auditStatus = auditStatus;
        this.auditSuggest = auditSuggest;
        this.areaAddress = areaAddress;
        this.areaCost = areaCost;
        this.receiver = receiver;
        this.receiverTel = receiverTel;
        this.auditTime = auditTime;
        this.auditor = auditor;
        this.payType = payType;
        this.payTime = payTime;
        this.applicationTime = applicationTime;
        this.updateTime = updateTime;
        this.remarks = remarks;
    }


    public UUID getId() {
        return id;
    }

    public UUID getRecId() {
        return recId;
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

    public long getJobNum() {
        return jobNum;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
    }

    public void setJobNum(long jobNum) {
        this.jobNum = jobNum;
    }

    public String getFairName() {
        return fairName;
    }

    public void setFairName(String fairName) {
        this.fairName = fairName;
    }

    public LocalDateTime getFairStartTime() {
        return fairStartTime;
    }

    public void setFairStartTime(LocalDateTime fairStartTime) {
        this.fairStartTime = fairStartTime;
    }

    public LocalDateTime getFairEndTime() {
        return fairEndTime;
    }

    public void setFairEndTime(LocalDateTime fairEndTime) {
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

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
