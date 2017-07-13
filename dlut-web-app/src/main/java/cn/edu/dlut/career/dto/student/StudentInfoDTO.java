package cn.edu.dlut.career.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 学生学籍信息
 * Created by HealerJean on 2017/4/11.
 */

public class StudentInfoDTO {

    //主键id
    private UUID id;

    //学号
    private String stuNo;



    //姓名
    private String name;

    //考生号
    private String examId;

    //身份证号
    private String idCard;

    //性别代码
    private String gender;

    //民族代码
    private String ethnic;

    //政治面貌代码
    private String political;

    //学历代码
    private String eduDegree;

    //专业代码
    private String majorCode;

    //专业名称（标准的专业名称）
    private String majorName;

    //外语语种
    private String flangType;

    //第二外语语种
    private String flangType2;

    //培养方式代码(1 非定向 ,2 定向 ,3 在职 ,4 委培 ,5自筹)
    private String eduMode;

    //定向委培单位
    private String trustee;

    //生源地代码／名称
    private String homelandCode;

    //生源地代码／名称
    private String homelandName;

    //学制
    private String eduYear;

    //入学时间
    private String startDate;

    //毕业时间
    private String endDate;

    //院系Id
    private String departmentId;

    //所在院系
    private String department;

    //所在班级
    private String className;

    //导师姓名
    private String tutorName;

    //辅导员姓名
    private String counselor;

    //出生日期
    private String birthdate;

    //户口是否转入学校（1，是，2 不是）
    private String haveEduHukou;

    //手机号
    private String mobilephone;

    //电子邮箱
    private String email;

    //QQ号码
    private String qqNo;

    //微信号
    private String wechatNo;

    //家庭地址
    private String homeAddress;

    //家庭电话
    private String homePhone;

    //是否已经上报（1 是，2 不是）
    private String haveReport;

    //核对状态（1，已经核对，2，未核对）
    private String status;

    //添加时间
    private String createTime;

    //添加人
    private String creator;

    //最后修改时间
    private String updateTime;

    //最后修改人updator
    private String updator;



    //学生和教师修改  学籍时候的判定状态
    private  String allupdateStatus;

    private String excelErrorMsg ;



    public StudentInfoDTO() {
    }
    public String getExcelErrorMsg() {
        return excelErrorMsg;
    }

    public void setExcelErrorMsg(String excelErrorMsg) {
        this.excelErrorMsg = excelErrorMsg;
    }

    public String getAllupdateStatus() {
        return allupdateStatus;
    }

    public void setAllupdateStatus(String allupdateStatus) {
        this.allupdateStatus = allupdateStatus;
    }
    public UUID getId() {
        return id;
    }




    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
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
        return majorName;
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
        return department;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }


}
