package cn.edu.dlut.career.dto.company;

import cn.edu.dlut.career.util.PubCodeUtil;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by wei on 2017/4/13.
 */
public class RecOfferDTO {
    private UUID id;

    private String recName;

    private String closingDate;

    private String category;

    private String categoryName;

    private String hasReportCard;

    private String isPfile;

    private String isSolveHukou;

    private String StuReceivingStatus;
    private String StuReceivingStatusName;

    private String auditStatusName;
    private String auditStatus;

    public RecOfferDTO() {
    }

    public RecOfferDTO(UUID id, String recName, String closingDate, String category, String hasReportCard, String isPfile, String isSolveHukou, String stuReceivingStatus, String auditStatus) {
        this.id = id;
        this.recName = recName;
        this.closingDate = closingDate;
        this.category = category;
        this.hasReportCard = hasReportCard;
        this.isPfile = isPfile;
        this.isSolveHukou = isSolveHukou;
        StuReceivingStatus = stuReceivingStatus;
        this.auditStatus = auditStatus;
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

    public String getCategoryName() {
        return PubCodeUtil.getName("jobType",this.getCategory());
    }

    public String getStuReceivingStatusName() {
        return PubCodeUtil.getName("offerStatus",this.getStuReceivingStatus());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHasReportCard() {
        return hasReportCard;
    }

    public void setHasReportCard(String hasReportCard) {
        this.hasReportCard = hasReportCard;
    }

    public String getIsPfile() {
        return isPfile;
    }

    public void setIsPfile(String isPfile) {
        this.isPfile = isPfile;
    }

    public String getIsSolveHukou() {
        return isSolveHukou;
    }

    public void setIsSolveHukou(String isSolveHukou) {
        this.isSolveHukou = isSolveHukou;
    }

    public String getStuReceivingStatus() {
        return StuReceivingStatus;
    }

    public void setStuReceivingStatus(String stuReceivingStatus) {
        StuReceivingStatus = stuReceivingStatus;
    }
}
