package cn.edu.dlut.career.dto.company;

import cn.edu.dlut.career.domain.company.InvoiceInfo;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 双选会 招聘会 DTO
 * Created by HealerJean on 2017/4/7.
 */
public class CarreFairEntryDTO {

    private UUID id;

    private UUID recJobFairId;


    private UUID recBulletinId;
    //公司id
    private  UUID recId;

    private String recName;


    private String recAddress;



    private String unitFinaPhone;

    private char isSign;

    private String accoHotel;


    private LocalDateTime applyTime;

    private String ehxPlace;


    private char payType;


    private LocalDateTime payTime;


    private char receStatus;


    private char auditStatus;


    private String auditor;

    private LocalDateTime auditTime;


    private String nopassReason;

    //json 对象
    private InvoiceInfo invoiceInfo;

    public CarreFairEntryDTO() {
    }

    public UUID getRecId() {
        return recId;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRecJobFairId() {
        return recJobFairId;
    }

    public void setRecJobFairId(UUID recJobFairId) {
        this.recJobFairId = recJobFairId;
    }

    public UUID getRecBulletinId() {
        return recBulletinId;
    }

    public void setRecBulletinId(UUID recBulletinId) {
        this.recBulletinId = recBulletinId;
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

    public String getUnitFinaPhone() {
        return unitFinaPhone;
    }

    public void setUnitFinaPhone(String unitFinaPhone) {
        this.unitFinaPhone = unitFinaPhone;
    }

    public char getIsSign() {
        return isSign;
    }

    public void setIsSign(char isSign) {
        this.isSign = isSign;
    }

    public String getAccoHotel() {
        return accoHotel;
    }

    public void setAccoHotel(String accoHotel) {
        this.accoHotel = accoHotel;
    }

    public LocalDateTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalDateTime applyTime) {
        this.applyTime = applyTime;
    }

    public String getEhxPlace() {
        return ehxPlace;
    }

    public void setEhxPlace(String ehxPlace) {
        this.ehxPlace = ehxPlace;
    }

    public char getPayType() {
        return payType;
    }

    public void setPayType(char payType) {
        this.payType = payType;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public char getReceStatus() {
        return receStatus;
    }

    public void setReceStatus(char receStatus) {
        this.receStatus = receStatus;
    }

    public char getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(char auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public String getNopassReason() {
        return nopassReason;
    }

    public void setNopassReason(String nopassReason) {
        this.nopassReason = nopassReason;
    }

    public InvoiceInfo getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }
}
