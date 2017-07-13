package cn.edu.dlut.career.dto.student;

import java.util.UUID;

/**
 *  学生登录基础信息 DTO
 * Created by HealerJean on 2017/4/13.
 */
public class StuLoginDTO {

    private UUID id;

    private String name;

    //学号
    private String stuNo;

    private String pwd;

    private String salt;

    public StuLoginDTO() {
    }

    public StuLoginDTO(UUID id, String name, String stuNo, String pwd, String salt) {
        this.id = id;
        this.name = name;
        this.stuNo = stuNo;
        this.pwd = pwd;
        this.salt = salt;
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
}
