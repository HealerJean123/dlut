package cn.edu.dlut.career.dto.company;

import cn.edu.dlut.career.util.PubCodeUtil;

import java.util.UUID;

/**
 * Created by wei on 2017/5/27.
 * 企业查询生源信息dto
 */
public class CStudentDTO {
    //主键id
    private UUID id;

    //学号
    private String stuNo;



    //姓名
    private String name;

    //性别代码
    private String gender;

    //性别名称
    private String genderName;

    //学历代码
    private String eduDegree;

    //学历名称
    private String eduDegreeName;

    //专业代码
    private String majorCode;

    //专业名称（标准的专业名称）
    private String majorName;

    //培养方式代码(1 非定向 ,2 定向 ,3 在职 ,4 委培 ,5自筹)
    private String eduMode;

    //培养方式名称(1 非定向 ,2 定向 ,3 在职 ,4 委培 ,5自筹)
    private String eduModeName;

    //生源地代码
    private String homelandCode;

    //生源地名称
    private String homelandName;

    //学制
    private String eduYear;

    //学制名称
    private String eduYearName;
    //毕业时间
    private String endDate;

    public CStudentDTO() {}

    public UUID getId() {
        return id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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



    public String getEduMode() {
        return eduMode;
    }

    public void setEduMode(String eduMode) {
        this.eduMode = eduMode;
    }

    public String getHomelandCode() {
        return homelandCode;
    }

    public void setHomelandCode(String homelandCode) {
        this.homelandCode = homelandCode;
    }



    public String getEduYear() {
        return eduYear;
    }

    public void setEduYear(String eduYear) {
        this.eduYear = eduYear;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getHomelandName() {
        return homelandName;
    }

    public void setHomelandName(String homelandName) {
        this.homelandName = homelandName;
    }



    public String getGenderName() {
        return PubCodeUtil.getName("gender",this.getMajorCode());
    }

    public String getEduDegreeName() {
        return PubCodeUtil.getName("eduDegree",this.getMajorCode());
    }

    public String getMajorName() {
        return PubCodeUtil.getName("major",this.getMajorCode());
    }

    public String getEduModeName() {
        return PubCodeUtil.getName("eduMode",this.getMajorCode());
    }

    public String getEduYearName() {
        return PubCodeUtil.getName("eduYear",this.getMajorCode());
    }

    public CStudentDTO(UUID id, String stuNo, String name, String gender, String eduDegree, String majorCode, String eduMode, String homelandCode, String homelandName, String eduYear, String endDate) {
        this.id = id;
        this.stuNo = stuNo;
        this.name = name;
        this.gender = gender;
        this.eduDegree = eduDegree;
        this.majorCode = majorCode;
        this.eduMode = eduMode;
        this.homelandCode = homelandCode;
        this.homelandName = homelandName;
        this.eduYear = eduYear;
        this.endDate = endDate;
    }
}
