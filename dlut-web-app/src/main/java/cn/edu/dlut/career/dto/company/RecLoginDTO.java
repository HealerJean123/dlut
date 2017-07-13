package cn.edu.dlut.career.dto.company;

import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/4/6  11:05.
 */
public class RecLoginDTO {

    private UUID id;

    private String name;

    private String email;

    private String pwd;

    private String salt;

    private String auditStatus;

    public RecLoginDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public RecLoginDTO(UUID id, String name, String email, String pwd, String salt, String auditStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.salt = salt;
        this.auditStatus = auditStatus;
    }
}
