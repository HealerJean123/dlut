package cn.edu.dlut.career.domain.consultation;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 咨询预约表
 */
@Entity
@Table(name = "consultation_appointment")
public class ConsultationAppointment {
    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;
    //咨询安排编号
    @Column(length=32,nullable = false)
    private UUID arrangementId;
    //咨询安排标题
    @Column(length=30,nullable = false)
    private String title;
    //咨询安排描述
    @Column(length=100)
    private String describe;
    //学生id
    @Column(length = 32,nullable = false)
    private UUID stuId;
    //姓名
    @Column(length=30,nullable = false)
    private String name;
    //学历层次（关联字典）
    @Column(length = 20,nullable = false)
    private String education;
    //所在年级
    @Column(length=20,nullable = false)
    private String grade;
    //所属院系
    @Column(length=20,nullable = false)
    private String department;
    //所学专业
    @Column(length=20,nullable = false)
    private String major;
    //联系电话
    @Column(length=20,nullable = false)
    private String tel;
    //电子邮件
    @Column(length=20,nullable = false)
    private String email;
    //咨询问题
    @Column(length = 100)
    private String problem;
    //审核状态
    @Column(length=20)
    private String auditStatus;
    //审核时间
    @Column
    private LocalDateTime auditTime;
    //审核人
    @Column(length=30)
    private String auditor;
    //签到时间
    @Column
    private LocalDateTime signTime;

    public ConsultationAppointment() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(UUID arrangementId) {
        this.arrangementId = arrangementId;
    }

    public void setStuId(UUID stuId) {
        this.stuId = stuId;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public LocalDateTime getSignTime() {
        return signTime;
    }

    public void setSignTime(LocalDateTime signTime) {
        this.signTime = signTime;
    }
}
