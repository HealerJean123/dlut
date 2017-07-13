package cn.edu.dlut.career.domain.consultation;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 咨询安排表
 */
@Entity
@Table(name = "consultation_arrangement")
public class ConsultationArrangement {
    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    @Type(type = "pg-uuid")
    private UUID id;
    //标题
    @Column(length=30,nullable = false)
    private String title;
    //描述
    @Column(length=100)
    private String describe;
    //咨询老师id
    @Column(length = 32,nullable = false)
    private UUID teacherId;
    //咨询老师姓名
    @Column(length = 30,nullable = false)
    private String teacherName;
    //咨询老师简介
    @Column(length = 50)
    private String teacher_describe;
    //开始时间
    @Column(nullable = false)
    private LocalDateTime startTime;
    //结束时间
    @Column(nullable = false)
    private LocalDateTime endTime;
    //活动地点
    @Column(length = 50,nullable = false)
    private String address;
    //拟接待人数
    @Column(nullable = false)
    private int intendedReceptionNum;
    //预约人数
    @Column(nullable = false)
    private int orderNum = 0;
    //实际接待人数
    @Column(nullable = false)
    private int actualReceptionNum = 0;
    //创建人
    @Column(length=30, nullable = false, updatable = false)
    private String founder;
    //创建时间
    @Column(nullable = false, updatable = false)
    private LocalDateTime createOn;
    //最后修改人
    @Column(length = 30)
    private String lastRevision;
    //最后修改时间
    @Column
    private LocalDateTime updateTime;

    public ConsultationArrangement() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacher_describe() {
        return teacher_describe;
    }

    public void setTeacher_describe(String teacher_describe) {
        this.teacher_describe = teacher_describe;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIntendedReceptionNum() {
        return intendedReceptionNum;
    }

    public void setIntendedReceptionNum(int intendedReceptionNum) {
        this.intendedReceptionNum = intendedReceptionNum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getActualReceptionNum() {
        return actualReceptionNum;
    }

    public void setActualReceptionNum(int actualReceptionNum) {
        this.actualReceptionNum = actualReceptionNum;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public LocalDateTime getCreateOn() {
        return createOn;
    }

    public void setCreateOn(LocalDateTime createOn) {
        this.createOn = createOn;
    }

    public String getLastRevision() {
        return lastRevision;
    }

    public void setLastRevision(String lastRevision) {
        this.lastRevision = lastRevision;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
