package cn.edu.dlut.career.domain.school;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * 实体类 问卷问题内容
 * Created by HealerJean on 2017/4/6.
 */
@Entity
@Table(name="questionnaire_content")
public class QuestionnaireContent {
    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    @Type(type = "pg-uuid")
    private UUID id;
    //问卷信息id(外键)
    @Column(length = 30,nullable = false)
    private UUID quesInfoId;
    //题干(问题内容)
    @Column(length = 50,nullable = false)
    private String title;
    //类型：1 选择题，2 问答题，3 判断题
    @Column(length = 3,nullable = true)
    private String type;
    //是否必填:（1必填，2不必填）
    @Column(length = 3,nullable = true)
    private String isRequired;
    //是选项A
    @Column(length = 50 ,nullable = true)
    private String optionA;
    //选项B
    @Column(length = 50 ,nullable = true)
    private String optionB;
    //选项C
    @Column(length = 50 ,nullable = true)
    private String optionC;
    //选项D
    @Column(length = 50 ,nullable = true)
    private String optionD;
    //选项E
    @Column(length = 50 ,nullable = true)
    private String optionE;
    //选项F
    @Column(length = 50 ,nullable = true)
    private String optionF;

    public QuestionnaireContent() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getQuesInfoId() {
        return quesInfoId;
    }

    public void setQuesInfoId(UUID quesInfoId) {
        this.quesInfoId = quesInfoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getOptionE() {
        return optionE;
    }

    public void setOptionE(String optionE) {
        this.optionE = optionE;
    }

    public String getOptionF() {
        return optionF;
    }

    public void setOptionF(String optionF) {
        this.optionF = optionF;
    }
}


