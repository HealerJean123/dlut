package cn.edu.dlut.career.dto.company;

import cn.edu.dlut.career.util.PubCodeUtil;

import java.util.UUID;

/**
 * Created by wei on 2017/6/6.
 * 专场招聘列表展示DTO
 */
public class SpecialDTO {

    private UUID id;

    private String fairName;

    private String applicationDate;

    private String startDate;

    private String auditsStatus;

    private String auditsStatusName;

    private String fieldAuditsStatus;

    private String  fieldAuditsStatusName;

    public SpecialDTO(UUID id, String fairName, String applicationDate, String startDate, String auditsStatus, String fieldAuditsStatus) {
        this.id = id;
        this.fairName = fairName;
        this.applicationDate = applicationDate;
        this.startDate = startDate;
        this.auditsStatus = auditsStatus;
        this.fieldAuditsStatus = fieldAuditsStatus;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFairName(String fairName) {
        this.fairName = fairName;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setAuditsStatus(String auditsStatus) {
        this.auditsStatus = auditsStatus;
    }

    public void setFieldAuditsStatus(String fieldAuditsStatus) {
        this.fieldAuditsStatus = fieldAuditsStatus;
    }

    public UUID getId() {

        return id;
    }

    public String getFairName() {
        return fairName;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getAuditsStatus() {
        return auditsStatus;
    }

    public String getAuditsStatusName() {
        return PubCodeUtil.getName("auditStatus",this.getAuditsStatus());
    }

    public String getFieldAuditsStatus() {
        return fieldAuditsStatus;
    }

    public String getFieldAuditsStatusName() {
        return PubCodeUtil.getName("auditStatus",this.getFieldAuditsStatus());
    }
}
