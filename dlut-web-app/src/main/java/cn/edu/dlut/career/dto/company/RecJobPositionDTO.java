package cn.edu.dlut.career.dto.company;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/6.
 *
 * 招聘职位 DTO
 */
public class RecJobPositionDTO {

    //招聘简章id
    private String bulletinId;

    //编号
    private UUID id;

    //公司编号(外键)
    @NotNull
    private UUID recId;

    //职位名称
    @NotNull
    @Max(100)
    private String name;

    //职位描述
    @NotNull
    @Max(1000)
    private String description;

    //职位类型 （0 兼职，1全职，2 实习）
    @NotNull
    @Max(3)
    private String type;

    //职位类别
    @NotNull
    @Max(3)
    private String category;

    //学历要求(0 不限，1 专科，2 本科，3 硕士，4 博士)
    @NotNull
    @Max(3)
    private String degree;

    //专业要求(企业手动输入，不关联字典)
    @NotNull
    @Max(3)
    private String major;

    //接受简历方式（01：两者都可,02：只用邮箱接收简历，03：只在该系统接收简历）
    @NotNull
    @Max(3)
    private String receiveMode;

    //接收简历邮箱
    @Max(100)
    private String recEmail;

    //工作城市
    @NotNull
    @Max(100)
    private String city;

    //详细地址
    @NotNull
    @Max(1000)
    private String address;

    //招聘人数
    @NotNull
    private Integer recruitmentNum;

    //薪资待遇 （关联字典 0:0-3000,1:3000-5000....）
    @NotNull
    @Max(3)
    private String salary;


    //有效开始时间
    @NotNull
    private String startTime;

    //有效结束时间
    @NotNull
    private String endTime;

    public RecJobPositionDTO() {
    }


    public RecJobPositionDTO(String name, String description, String type, String category, String degree, String major, String receiveMode, String recEmail, String city, String address, Integer recruitmentNum, String salary, String startTime, String endTime) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.category = category;
        this.degree = degree;
        this.major = major;
        this.receiveMode = receiveMode;
        this.recEmail = recEmail;
        this.city = city;
        this.address = address;
        this.recruitmentNum = recruitmentNum;
        this.salary = salary;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(String bulletinId) {
        this.bulletinId = bulletinId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRecId() {
        return recId;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getReceiveMode() {
        return receiveMode;
    }

    public void setReceiveMode(String receiveMode) {
        this.receiveMode = receiveMode;
    }

    public String getRecEmail() {
        return recEmail;
    }

    public void setRecEmail(String recEmail) {
        this.recEmail = recEmail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRecruitmentNum() {
        return recruitmentNum;
    }

    public void setRecruitmentNum(Integer recruitmentNum) {
        this.recruitmentNum = recruitmentNum;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


}
