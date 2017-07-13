package cn.edu.dlut.career.domain.company;

import cn.edu.dlut.career.util.StringJsonUserType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 实体类 双选会，招聘会预约申请表
 * Created by HealerJean on 2017/4/6.
 */
@Entity
@Table(name = "rec_career_fair_entry")
@TypeDefs({@TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class)})
public class CarrerFairEntry implements Serializable {

    private static final long SERIVALVERSIONUID = 876280513342148585L;
    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //双选会，招聘会信息表ID(外键)
    @Column(length = 30,nullable = false)
    private UUID recJobFairId;

    //招聘简章id(外键)
    @Column(length = 30,nullable = false)
    private UUID recBulletinId;

    //企业名称
    @Column(length = 50,nullable = true)
    private UUID recId;

    //企业名称
    @Column(length = 50,nullable = false)
    private String recName;

    //所在地区
    @Column(length = 50,nullable = false)
    private String recAddress;

    //单位财务电话
    @Column(length = 15,nullable = false)
    private String unitFinaPhone;

    //是否已报到:1 是，2 否
    @Column(length = 3,nullable = true)
    private String isSign;

    //住宿酒店
    @Column(length = 30,nullable = true)
    private String accoHotel;

    //申请时间
    @Column(nullable = false)
    private LocalDateTime applyTime;

    //分配的展位 ?
    @Column(length = 30,nullable = true)
    private String ehxPlace;

    //缴费方式：1 线上，2 线下
    @Column(length = 3,nullable = true)
    private String payType;

    //缴费时间
    @Column(nullable = true)
    private LocalDateTime payTime;

    //发票开具状态: 1 已经开具，2,未开具
    @Column(length = 3,nullable = true)
    private String receStatus;

    //审核状态（00 等待审核，01 通过审核，02 未通过审核）
    @Column(length = 3,nullable = false)
    private String auditStatus;

    //审核人
    @Column(length = 10,nullable = true)
    private String auditor;

    //审核时间
    @Column(nullable = true)
    private LocalDateTime auditTime;

    //审核不通过原因
    @Column(length = 50,nullable = true)
    private String nopassReason;

    //发票信息json
    @Type(type = "StringJsonObject")
    @Column(nullable = true)
    private String  invoiceInfo;

    public CarrerFairEntry() {
    }

    public UUID getRecId() {
        return recId;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
    }

    public static long getSERIVALVERSIONUID() {
        return SERIVALVERSIONUID;
    }

    public String getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(String invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
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

    public String getIsSign() {
        return isSign;
    }

    public void setIsSign(String isSign) {
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

    public String getReceStatus() {
        return receStatus;
    }

    public void setReceStatus(String receStatus) {
        this.receStatus = receStatus;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
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
}



