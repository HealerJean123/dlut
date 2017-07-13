package cn.edu.dlut.career.domain.school;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * 实体类 问卷信息表
 *
 * Created by HealerJean on 2017/4/6.
 */
@Entity
@Table(name="questionnaire_info")
public class QuestionnaireInfo {
    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    @Type(type = "pg-uuid")
    private UUID id;
    //问卷标题(名称)
    @Column(length = 50, nullable = false)
    private  String title;
    //问卷调查描述
    @Column(length = 300, nullable = false)
    private  String description;
    //适用范围: 1学校，2院系，3专业
    @Column(length = 3, nullable = false)
    private String applScope;
    //有效期开始时间
    @Column(nullable = false)
    private LocalDateTime startTime;
    //有效期结束时间
    @Column(nullable = false)
    private LocalDateTime endTime;
    //调查问卷创建时间
    @Column(nullable = false)
    private LocalDateTime createTime;
    //回收问卷总数
    private int totalQuesNum;
    //有效问卷总数
    private int valQuesNum;
    //发布时间
    @Column(nullable = true)
    private LocalDateTime publishTime;
    //发布人
    @Column(length = 10,nullable = true)
    private  String publishPerson;
    //最后修改时间
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateTime;
    //发最后修改人
    @Column(length = 10,nullable = false)
    private  String updatePerson;

    public QuestionnaireInfo() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplScope() {
        return applScope;
    }

    public void setApplScope(String applScope) {
        this.applScope = applScope;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getTotalQuesNum() {
        return totalQuesNum;
    }

    public void setTotalQuesNum(int totalQuesNum) {
        this.totalQuesNum = totalQuesNum;
    }

    public int getValQuesNum() {
        return valQuesNum;
    }

    public void setValQuesNum(int valQuesNum) {
        this.valQuesNum = valQuesNum;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishPerson() {
        return publishPerson;
    }

    public void setPublishPerson(String publishPerson) {
        this.publishPerson = publishPerson;
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


