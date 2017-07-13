package cn.edu.dlut.career.dto.school;

import java.util.UUID;

/**
 *  教师登录基础信息
 * Created by HealerJean on 2017/4/13.
 */
public class TeacherLoginDTO {

    private UUID id;
    //姓名
    private String name;
    //用户名
    private String username;

    private String pwd;

    private String salt;

    //院系编号
    private  String departmentId;

    //院系名称
    private String department;

    //教师类型（院级0 /校级 1）
    private String type;

    //教师类型（院级0 /校级 1）
    private String isAdmin;

    public TeacherLoginDTO() {
    }

    public TeacherLoginDTO(UUID id, String name, String username, String pwd, String salt, String departmentId, String department, String type,String isAdmin) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.pwd = pwd;
        this.salt = salt;
        this.departmentId = departmentId;
        this.department = department;
        this.type = type;
        this.isAdmin = isAdmin;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
