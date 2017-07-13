package cn.edu.dlut.career.dto.company;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by wei on 2017/5/31.
 */
public class OfferTemplateDTO {
    private UUID id;
    //模板名称
    private String name;
    //职位类别
    private String jobType;
    //适用学生类型 01,本科生  02硕士  03博士  04双学位
    private String stuType;
    //截止日期
    private String closingDate;
    //offer说明
    private String remarks;

    //是否接受档案
    private String isPfile;

    // 档案转寄单位名称pfileToName
    private String pfileToName;
    // 档案转寄部门pfileToDepart
    private String pfileToDepart;
    // 档案转寄单位地址pfileToAddress
    private String pfileToAddress;
    // 档案转寄单位所在省市
    private String pfileToLocal;
    // 档案转寄单位接收人
    private String pfileToRecipient;

    // 档案转寄接受人电话
    private String pfileToPhone;

    //是否解决户口(1解决0不解决)
    private String isSolveHukou;
    //是否允许不迁入(1允许不迁入0不允许不迁入)
    private String isNotMoveHuKou;


    // 户口迁转地址hukouToAddress
    private String hukouToAddress;

    public OfferTemplateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getStuType() {
        return stuType;
    }

    public void setStuType(String stuType) {
        this.stuType = stuType;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIsPfile() {
        return isPfile;
    }

    public void setIsPfile(String isPfile) {
        this.isPfile = isPfile;
    }

    public String getPfileToName() {
        return pfileToName;
    }

    public void setPfileToName(String pfileToName) {
        this.pfileToName = pfileToName;
    }

    public String getPfileToDepart() {
        return pfileToDepart;
    }

    public void setPfileToDepart(String pfileToDepart) {
        this.pfileToDepart = pfileToDepart;
    }

    public String getPfileToAddress() {
        return pfileToAddress;
    }

    public void setPfileToAddress(String pfileToAddress) {
        this.pfileToAddress = pfileToAddress;
    }

    public String getPfileToLocal() {
        return pfileToLocal;
    }

    public void setPfileToLocal(String pfileToLocal) {
        this.pfileToLocal = pfileToLocal;
    }

    public String getPfileToRecipient() {
        return pfileToRecipient;
    }

    public void setPfileToRecipient(String pfileToRecipient) {
        this.pfileToRecipient = pfileToRecipient;
    }

    public String getPfileToPhone() {
        return pfileToPhone;
    }

    public void setPfileToPhone(String pfileToPhone) {
        this.pfileToPhone = pfileToPhone;
    }

    public String getIsSolveHukou() {
        return isSolveHukou;
    }

    public void setIsSolveHukou(String isSolveHukou) {
        this.isSolveHukou = isSolveHukou;
    }

    public String getIsNotMoveHuKou() {
        return isNotMoveHuKou;
    }

    public void setIsNotMoveHuKou(String isNotMoveHuKou) {
        this.isNotMoveHuKou = isNotMoveHuKou;
    }

    public String getHukouToAddress() {
        return hukouToAddress;
    }

    public void setHukouToAddress(String hukouToAddress) {
        this.hukouToAddress = hukouToAddress;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
