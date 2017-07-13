package cn.edu.dlut.career.domain.school;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * 实体类 问卷统计信息表
 * Created by HealerJean on 2017/4/6.
 */
@Entity
@Table(name="questionnaire_stat_info")
public class QuestionnaireStatInfo {
    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    @Type(type = "pg-uuid")
    private UUID id;

    //问卷信息id(外键)
    @Column(length = 30,nullable = false)
    private UUID quesInfoId;

    //院系名称
    @Column(length = 50,nullable = false)
    private String depaName;

    //学历层次
    @Column(length = 50,nullable = false)
    private String educLevel;

    //目标调查人数
    private int targetNum;

    //实际完成人数
    private int actualNum;

    public QuestionnaireStatInfo() {
    }

    public UUID getQuesInfoId() {
        return quesInfoId;
    }

    public void setQuesInfoId(UUID quesInfoId) {
        this.quesInfoId = quesInfoId;
    }

    public UUID getId() {

        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDepaName() {
        return depaName;
    }

    public void setDepaName(String depaName) {
        this.depaName = depaName;
    }

    public String getEducLevel() {
        return educLevel;
    }

    public void setEducLevel(String educLevel) {
        this.educLevel = educLevel;
    }

    public int getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(int targetNum) {
        this.targetNum = targetNum;
    }

    public int getActualNum() {
        return actualNum;
    }

    public void setActualNum(int actualNum) {
        this.actualNum = actualNum;
    }
}



