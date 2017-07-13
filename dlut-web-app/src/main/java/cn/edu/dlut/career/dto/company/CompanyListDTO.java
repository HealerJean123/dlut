package cn.edu.dlut.career.dto.company;

import cn.edu.dlut.career.util.PubCodeUtil;

import java.util.UUID;

/**
 * Created by wei on 2017/6/5.
 */
public class CompanyListDTO {

    private UUID id;

    private String name;

    private String orgCode;

    private String nature;
    private String natureName;

    private String industry;
    private String industryName;

    private String registerTime;

    private String auditStatus;
    private String auditStatusName;

    public CompanyListDTO(UUID id, String name, String orgCode, String nature, String industry, String registerTime, String auditStatus) {
        this.id = id;
        this.name = name;
        this.orgCode = orgCode;
        this.nature = nature;
        this.industry = industry;
        this.registerTime = registerTime;
        this.auditStatus = auditStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getNatureName() {
        return PubCodeUtil.getName("nature",this.getNature());
    }

    public String getIndustryName() {
        return PubCodeUtil.getName("industry",this.getIndustry());
    }

    public String getAuditStatusName() {
        return PubCodeUtil.getName("auditStatus",this.getAuditStatus());
    }
}
