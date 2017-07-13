package cn.edu.dlut.career.shiro;

import java.io.Serializable;
import java.util.UUID;

/**
 * 用户登录后携带的信息
 */
public class UserPrincipal implements Serializable {
    private static final long serialVersionUID = 1L;
    //用户id
    private UUID id;
    //用户姓名
    private String realName;
    //部门id
    private String depId;
    //部门名称
    private String depName;
    //登录名
    private String userName;
    //身份类型
    private String role;
    // 是
    private String principal;

    public UserPrincipal() {
    }

    public UserPrincipal(UUID id, String realName, String depId, String depName, String userName, String role, String principal) {
        this.id = id;
        this.realName = realName;
        this.depId = depId;
        this.depName = depName;
        this.userName = userName;
        this.role = role;
        this.principal = principal;
    }

    public UserPrincipal(UUID id, String realName, String depId, String depName, String userName, String role) {
        this.id = id;
        this.realName = realName;
        this.depId = depId;
        this.depName = depName;
        this.userName = userName;
        this.role = role;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return depName;
    }
}
