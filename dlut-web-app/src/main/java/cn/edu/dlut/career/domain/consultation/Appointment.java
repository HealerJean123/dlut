package cn.edu.dlut.career.domain.consultation;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 咨询工作坊的学生预约
 */
@Entity
public class Appointment {
    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    @Type(type = "pg-uuid")
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private WorkshopSubject subject;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private WorkshopScheduling scheduling;
    //学生id
    @Column(length = 32,nullable = false)
    private UUID stuId;
    //姓名
    @Column(length=30,nullable = false)
    private String realname = "";
    //学历层次（关联字典）
    @Column(length = 20,nullable = false)
    private String eduDegree = "";
    // 所在年级
    @Column(length=20,nullable = false)
    private String grade = "";
    // 所属院系
    @Column(length=20,nullable = false)
    private String department = "";
    // 所学专业
    @Column(length=20,nullable = false)
    private String major = "";
    // 联系电话
    @Column(length=20,nullable = false)
    private String tel = "";
    // 电子邮件
    @Column(length=20,nullable = false)
    private String email = "";
    // 咨询问题
    @Column(length = 100)
    private String problem;
    // 创建时间，申请预约时间
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdOn = LocalDateTime.now();
    // 状态：默认、作废、审核通过
    @Column(length=20)
    @Enumerated(EnumType.STRING)
    private Status staus = Status.DEFAULT;
    // 审核时间
    @Column
    private LocalDateTime auditTime;
    // 审核人
    @Column(length = 30)
    private String auditor;
    // 签到时间
    private LocalDateTime signTime;

    public Appointment() {
    }

    public Appointment(WorkshopSubject subject, WorkshopScheduling scheduling, UUID stuId,
                       String realname, String eduDegree, String grade, String department, String major,
                       String tel, String email, String problem) {
        this.subject = subject;
        this.scheduling = scheduling;
        this.stuId = stuId;
        this.realname = realname;
        this.eduDegree = eduDegree;
        this.grade = grade;
        this.department = department;
        this.major = major;
        this.tel = tel;
        this.email = email;
        this.problem = problem;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public WorkshopSubject getSubject() {
        return subject;
    }

    public void setSubject(WorkshopSubject subject) {
        this.subject = subject;
    }

    public WorkshopScheduling getScheduling() {
        return scheduling;
    }

    public void setScheduling(WorkshopScheduling scheduling) {
        this.scheduling = scheduling;
    }

    public UUID getStuId() {
        return stuId;
    }

    public void setStuId(UUID stuId) {
        this.stuId = stuId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEduDegree() {
        return eduDegree;
    }

    public void setEduDegree(String eduDegree) {
        this.eduDegree = eduDegree;
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

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Status getStaus() {
        return staus;
    }

    public void setStaus(Status staus) {
        this.staus = staus;
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

    /**
     * 咨询工作坊的预约申请状态
     */
    public enum Status {
        /**
         * 默认状态
         */
        DEFAULT,
        /**
         * 学生申请取消
         */
        CANCEL,
        /**
         * 教师审核通过
         */
        PASSED,
        /**
         * 教师审核不通过
         */
        NOT_PASS,
        /**
         * 学生到场签到
         */
        SIGNED
    }
}
