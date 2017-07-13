package cn.edu.dlut.career.domain.school;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by wei on 2017/4/5.
 * 教师信息表
 */
@Entity
@Table(name = "teacher")
public class TeacherInfo {
    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    @Type(type = "pg-uuid")
    private UUID id;
    //用户名
    @Column(length = 50,nullable = false)
    private String username;
    //教师姓名
    @Column(length = 20,nullable = false)
    private String name;
    //密码
    @Column(length = 100,nullable = false)
    private String pwd;
    //随机盐
    @Column(length = 50,nullable = false)
    private String salt;
    //教师类型 0:校级老师/1:院级老师
    @Column(length = 1)
    private String type;
    //所在院系/部门编号
    @Column(length = 32,nullable = false)
    private String departmentId;
    //所在院系/部门
    @Column(length = 30,nullable = false)
    private String department;
    //电子邮件
    @Column(length = 20,nullable = false)
    private String email;
    // 联系电话
    @Column(length = 20,nullable = false)
    private String tel;
    // 是否是管理员
    @Column(length = 1,nullable = false)
    private String isAdmin;
    // 是否冻结
    @Column(length = 1,nullable = false)
    private String isFrozen;
    // 创建时间
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createOn;
    // 最后修改时间
    @Column(nullable = true)
    @UpdateTimestamp
    private LocalDateTime lastUpdateTime;
    // 最后登陆时间
    @Column(nullable = true)
    private LocalDateTime lastLoginTime;
    // 功能模块
    @Column(nullable = true)
    private String functionalModule;
    // 权限级别
    @Column(nullable = true)
    private String jurisdiction;

    public TeacherInfo() {
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(String isFrozen) {
        this.isFrozen = isFrozen;
    }

    public LocalDateTime getCreateOn() {
        return createOn;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setCreateOn(LocalDateTime createOn) {
        this.createOn = createOn;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getFunctionalModule() {
        return functionalModule;
    }

    public void setFunctionalModule(String functionalModule) {
        this.functionalModule = functionalModule;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
