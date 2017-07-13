package cn.edu.dlut.career.domain.student;

import cn.edu.dlut.career.util.PubCodeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 学生学籍信息
 */
@Entity
@Table(name = "stu_information",uniqueConstraints = {@UniqueConstraint(columnNames="stuNo")})
public class StudentInfo {
    //主键id
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    @Type(type = "pg-uuid")
    private UUID id;
    //学号
    @Column(length = 32, nullable = false,unique = true)
    private String stuNo;
    //登录密码
    @JsonIgnore
    @Column(length = 32,nullable = false)
    private String pwd;

    //随机盐
    @JsonIgnore
    @Column(length = 50,nullable = false)
    private String salt;

    //姓名
    @Column(length = 40, nullable = false)
    private String name;

    //考生号
    @Column(length = 20, nullable = true)
    private String examId;

    //身份证号
    @Column(length = 20, nullable = false)
    private String idCard;

    //性别代码
    @Column(length = 3, nullable = false)
    private String gender;

    //性别 对应名称
    @Transient
    private String genderName;

    //民族代码
    @Column(length = 2, nullable = false)
    private String ethnic;

    //民族 对应名称
    @Transient
    private String ethnicName;

    //政治面貌代码
    @Column(length = 2, nullable = false)
    private String political;

    //学历代码
    @Column(length = 2, nullable = false)
    private String eduDegree;

    //学历对应名称
    @Transient
    private String eduDegreeName;

    //专业代码

    @Column(length = 6, nullable = false)
    private String majorCode;

    @Transient
    private String majorCodeName;
    //专业名称（标准的专业名称）
    @Column(length = 32, nullable = true)
    private String majorName;

    //外语语种
    @Column(length = 20, nullable = true)
    private String flangType;

    //第二外语语种
    @Column(length = 20, nullable = true)
    private String flangType2;

    //培养方式代码(1 非定向 ,2 定向 ,3 在职 ,4 委培 ,5自筹)
    @Column(length = 3, nullable = false)
    private String eduMode;

    //培养方式 对应名称
    @Transient
    private String eduModeName;

    //定向委培单位
    @Column(length = 40, nullable = true)
    private String trustee;

    //生源地代码
    @Column(length = 6, nullable = true)
    private String homelandCode;

    //生源地名称
    @Column(length = 50, nullable = true)
    private String homelandName;


    //学制
    @Column(length = 3, nullable = false)
    private String eduYear;

    //学制对应名称
    @Transient
    private String eduYearName;

    //入学时间 日期格式 YYYY-MM
    @Column(nullable = true,length = 10)
    private String startDate;

    //毕业时间 日期格式 YYYY-MM
    @Column(nullable = true,length = 10)
    private String endDate;

    //所在院系/部门编号
    @Column(length = 20,nullable = true)
    private String departmentId;
    @Transient
    private String departmentIdName;

    //所在院系
    @Column(length = 32, nullable = true)
    private String department;

    //所在班级
    @Column(length = 32, nullable = true)
    private String className;

    //导师姓名
    @Column(length = 10, nullable = true)
    private String tutorName;

    //辅导员姓名
    @Column(length = 10, nullable = true)
    private String counselor;

    //出生日期
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate birthdate;

    //户口是否转入学校（1，是，2 不是）
    @Column(length = 3, nullable = true)
    private String haveEduHukou;

    //手机号
    @Column(length = 32, nullable = true)
    private String mobilephone;

    //电子邮箱
    @Column(length = 32, nullable = true)
    private String email;

    //QQ号码
    @Column(length = 15, nullable = true)
    private String qqNo;

    //微信号
    @Column(length = 30, nullable = true)
    private String wechatNo;

    //家庭地址
    @Column(length = 50, nullable = true)
    private String homeAddress;

    //家庭电话
    @Column(length = 32, nullable = true)
    private String homePhone;

    //是否已经上报（0 未上报，1.已上报）
    @Column(length = 3, nullable = true)
    private String haveReport;

    //教师审核状态
    @Column(length = 3, nullable = true)
    private String status;

    //教师审核状态 对应名称
    @Transient
    private String statusName;

    //添加人
    @Column(length = 10, nullable = true)
    private String creator;


    //最后修改人updator
    @Column(length = 10, nullable = true)
    private String updator;

    //学生生源信息核对确认时间
    private LocalDateTime stuCheckTime;

    //学校审核信息时间
    private LocalDateTime schCheckTime;


    //添加时间
    @CreationTimestamp
    @Column(nullable = true)
    private LocalDateTime createTime;

    //最后修改时间
    @UpdateTimestamp
    @Column(nullable = true)
    private LocalDateTime updateTime;

    public StudentInfo() {
    }

    /**
     * 数据字典
     * @return
     */
    public String getMajorCodeName() {
        return PubCodeUtil.getName("major",this.getMajorCode());
    }

    public String getDepartmentIdName() {
        return PubCodeUtil.getName("academy",this.getDepartmentId());
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
            ", id='" + id + '\'' +
            ", stuNo='" + stuNo + '\'' +
            ", pwd='" + pwd + '\'' +
            ", salt='" + salt + '\'' +
            ", name='" + name + '\'' +
            ", examId='" + examId + '\'' +
            ", idCard='" + idCard + '\'' +
            ", gender='" + gender + '\'' +
            ", ethnic='" + ethnic + '\'' +
            ", political='" + political + '\'' +
            ", eduDegree='" + eduDegree + '\'' +
            ", majorCode='" + majorCode + '\'' +
            ", majorName='" + majorName + '\'' +
            ", flangType='" + flangType + '\'' +
            ", flangType2='" + flangType2 + '\'' +
            ", eduMode='" + eduMode + '\'' +
            ", trustee='" + trustee + '\'' +
            ", homelandCode='" + homelandCode + '\'' +
            ", homelandName='" + homelandName + '\'' +
            ", eduYear='" + eduYear + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", departmentId='" + departmentId + '\'' +
            ", department='" + department + '\'' +
            ", className='" + className + '\'' +
            ", tutorName='" + tutorName + '\'' +
            ", counselor='" + counselor + '\'' +
            ", birthdate=" + birthdate +
            ", haveEduHukou='" + haveEduHukou + '\'' +
            ", mobilephone='" + mobilephone + '\'' +
            ", email='" + email + '\'' +
            ", qqNo='" + qqNo + '\'' +
            ", wechatNo='" + wechatNo + '\'' +
            ", homeAddress='" + homeAddress + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", haveReport='" + haveReport + '\'' +
            ", status='" + status + '\'' +
            ", creator='" + creator + '\'' +
            ", updator='" + updator + '\'' +
            ", stuCheckTime=" + stuCheckTime +
            ", schCheckTime=" + schCheckTime +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            '}';
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }


    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getEduDegree() {
        return eduDegree;
    }

    public void setEduDegree(String eduDegree) {
        this.eduDegree = eduDegree;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getMajorName() {
        return PubCodeUtil.getName("major",this.getMajorCode());
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getFlangType() {
        return flangType;
    }

    public void setFlangType(String flangType) {
        this.flangType = flangType;
    }

    public String getFlangType2() {
        return flangType2;
    }

    public void setFlangType2(String flangType2) {
        this.flangType2 = flangType2;
    }

    public String getEduMode() {
        return eduMode;
    }

    public void setEduMode(String eduMode) {
        this.eduMode = eduMode;
    }

    public String getTrustee() {
        return trustee;
    }

    public void setTrustee(String trustee) {
        this.trustee = trustee;
    }

    public String getHomelandCode() {
        return homelandCode;
    }

    public void setHomelandCode(String homelandCode) {
        this.homelandCode = homelandCode;
    }

    public String getHomelandName() {
        return homelandName;
    }

    public void setHomelandName(String homelandName) {
        this.homelandName = homelandName;
    }

    public String getEduYear() {
        return eduYear;
    }

    public void setEduYear(String eduYear) {
        this.eduYear = eduYear;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDepartment() {
        return PubCodeUtil.getName("academy",this.getDepartmentId());
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getHaveEduHukou() {
        return haveEduHukou;
    }

    public void setHaveEduHukou(String haveEduHukou) {
        this.haveEduHukou = haveEduHukou;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo;
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getHaveReport() {
        return haveReport;
    }

    public void setHaveReport(String haveReport) {
        this.haveReport = haveReport;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public LocalDateTime getStuCheckTime() {
        return stuCheckTime;
    }

    public void setStuCheckTime(LocalDateTime stuCheckTime) {
        this.stuCheckTime = stuCheckTime;
    }

    public LocalDateTime getSchCheckTime() {
        return schCheckTime;
    }

    public void setSchCheckTime(LocalDateTime schCheckTime) {
        this.schCheckTime = schCheckTime;
    }

    public String getGenderName() {
        return PubCodeUtil.getName("gender",this.getGender());
    }

    public String getEthnicName() {
        return PubCodeUtil.getName("ethnic",this.getEthnic());
    }

    public String getEduModeName() {
        return PubCodeUtil.getName("eduMode",this.getEduMode());
    }

    public String getEduYearName() {
        return PubCodeUtil.getName("eduYear",this.getEduYear());
    }

    public String getEduDegreeName() {
        return PubCodeUtil.getName("eduDegree",this.getEduDegree());
    }

    public String getStatusName() {
        return PubCodeUtil.getName("auditStatus",this.getStatus());
    }
}
