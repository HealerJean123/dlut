package cn.edu.dlut.career.dto.company;

import cn.edu.dlut.career.util.PubCodeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/17.
 *
 * 大型/组团招聘会DTO 此DTO用于学生端招聘会查询
 */
public class JobFairDTO {
    //主键
    private UUID id;

    //参会公司数量
    private Long recNum;

    //招聘职位数量
    private Long jobNum;

    //招聘会名称
    private String name;

    //招聘会简介
    private String description;

    //类型：1 大型招聘,2 组团招聘
    private String type;

    //招聘会开始时间从
    private  String fairStartFrom;
    // 招聘会开始时间到
    private  String FairStartTo;

    //招聘会开始时间
    private LocalDateTime fairStartTime;

    //招聘会结束时间
    private LocalDateTime fairEndTime;

    //报名开始时间
    private LocalDateTime startTime;

    //报名结束时间
    private LocalDateTime endTime;

    //召开地点
    private String location;

    //是否收取服务费：1收取，2，不收取
    private String needCost;

    //邀请函
    private String invitation;

    //	校验码
    private String checkCode;

    //	创建人
    private String creator;

    //创建时间
    private LocalDateTime createTime;

    //最后修改时间
    private LocalDateTime updateTime;

    //最后修改人
    private String updatePerson;

    //本字段是针对某个企业来说的，审核状态
    @JsonIgnore
    private  String auditStatus;
    private  String getAuditStatusName;

    //本字段也是对单个企业来说，表示企业的id
    private UUID recId;


    public UUID getRecId() {
        return recId;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
    }

    public UUID getId() {
        return id;
    }

    public JobFairDTO(){}

    public JobFairDTO(UUID id, Long recNum, Long jobNum, String name, String type, LocalDateTime fairStartTime, String location) {
        this.id = id;
        this.recNum = recNum;
        this.jobNum = jobNum;
        this.name = name;
        this.type = type;
        this.fairStartTime = fairStartTime;
        this.location = location;
    }

    public JobFairDTO(UUID id, Long recNum, Long jobNum, String name, String description, String type, LocalDateTime fairStartTime, LocalDateTime fairEndTime, LocalDateTime startTime, LocalDateTime endTime, String location, String needCost, String invitation, String checkCode, String creator, LocalDateTime createTime, LocalDateTime updateTime, String updatePerson) {
        this.id = id;
        this.recNum = recNum;
        this.jobNum = jobNum;
        this.name = name;
        this.description = description;
        this.type = type;
        this.fairStartTime = fairStartTime;
        this.fairEndTime = fairEndTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.needCost = needCost;
        this.invitation = invitation;
        this.checkCode = checkCode;
        this.creator = creator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.updatePerson = updatePerson;
    }

    public String getFairStartFrom() {
        return fairStartFrom;
    }

    public void setFairStartFrom(String fairStartFrom) {
        this.fairStartFrom = fairStartFrom;
    }

    public String getFairStartTo() {
        return FairStartTo;
    }

    public void setFairStartTo(String fairStartTo) {
        FairStartTo = fairStartTo;
    }

    /**
    * @Description 企业查看招聘會詳情在使用下面的构造器
    * @Author HealerJean
    * @CreateDate 2017/5/27 10:10
    */
    public JobFairDTO(UUID id, Long recNum, String name, String description, String type, LocalDateTime fairStartTime, LocalDateTime fairEndTime, LocalDateTime startTime, LocalDateTime endTime, String location, String needCost, String invitation, String checkCode, String creator, LocalDateTime createTime, LocalDateTime updateTime, String updatePerson, String auditStatus, String getAuditStatusName) {
        this.id = id;
        this.recNum = recNum;
        this.name = name;
        this.description = description;
        this.type = type;
        this.fairStartTime = fairStartTime;
        this.fairEndTime = fairEndTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.needCost = needCost;
        this.invitation = invitation;
        this.checkCode = checkCode;
        this.creator = creator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.updatePerson = updatePerson;
        this.auditStatus = auditStatus;
        this.getAuditStatusName = getAuditStatusName;
    }

    public JobFairDTO(UUID id, Long recNum, String name, LocalDateTime fairStartTime, String location, String needCost,String auditStatus) {
        this.id = id;
        this.recNum = recNum;
        this.name = name;
        this.fairStartTime = fairStartTime;
        this.location = location;
        this.needCost = needCost;
        this.auditStatus =auditStatus;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getGetAuditStatusName() {
        return PubCodeUtil.getName("auditStatus",this.getAuditStatus());
    }

    public void setGetAuditStatusName(String getAuditStatusName) {
        this.getAuditStatusName = getAuditStatusName;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getRecNum() {
        return recNum;
    }

    public void setRecNum(Long recNum) {
        this.recNum = recNum;
    }

    public Long getJobNum() {
        return jobNum;
    }

    public void setJobNum(Long jobNum) {
        this.jobNum = jobNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNeedCost() {
        return needCost;
    }

    public void setNeedCost(String needCost) {
        this.needCost = needCost;
    }

    public String getInvitation() {
        return invitation;
    }

    public void setInvitation(String invitation) {
        this.invitation = invitation;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }
}
