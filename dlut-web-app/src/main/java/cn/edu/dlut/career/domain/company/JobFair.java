package cn.edu.dlut.career.domain.company;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * 实体类  双选会，招聘会信息表
 * Created by HealerJean on 2017/4/6.
 */
@Entity
@Table(name="rec_job_fair")
public class JobFair {
    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //招聘会名称
    @Column(length = 50,nullable = true)
    private String name;

    //招聘会简介
    @Column(length = 1000,nullable = true)
    private String description;

    //类型：1 大型招聘,2 组团招聘
    @Column(length = 3,nullable = true)
    private String type;

    //招聘会开始时间
    @Column(name="fair_start_time",nullable = false)
    private LocalDateTime fairStartTime;

    //招聘会结束时间
    @Column(name="fair_end_time",nullable = false)
    private LocalDateTime fairEndTime;

    //报名开始时间
    @Column(nullable = false)
    private LocalDateTime startTime;

    //报名结束时间
    @Column(nullable = false)
    private LocalDateTime endTime;

    //召开地点
    @Column(length = 50,nullable = false)
    private String location;


    //是否收取服务费：1收取，2，不收取
    @Column(length = 3,nullable = false)
    private String needCost;

    //	邀请函
    @Column(length = 1000,nullable = true)
    private String invitation;

    //	校验码
    @Column(length = 16,nullable = true)
    private String checkCode;

    //	公司报名数 ,每次报名一个需要加1
    @Column(nullable=false,columnDefinition="INT default 0")
    private int recNum;

    //	创建人
    @Column(length = 10,nullable = false)
    private String creator;

    //创建时间
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createTime;

    //最后修改时间
    @Column(nullable = false)
    private LocalDateTime updateTime;

    //	最后修改人
    @Column(length = 10,nullable = false)
    private String updatePerson;

    public JobFair() {
    }

    public int getRecNum() {
        return recNum;
    }

    public void setRecNum(int recNum) {
        this.recNum = recNum;
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

