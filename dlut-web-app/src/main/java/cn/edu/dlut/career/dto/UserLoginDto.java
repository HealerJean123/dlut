package cn.edu.dlut.career.dto;

import java.util.UUID;

/**
 * 统一的用户登录信息DTO，屏蔽掉各类用户的身份细节
 */
public class UserLoginDto {
    // 用户id
    private UUID uid;
    // 用户真实姓名
    private String realname;
    // 企业：单位名称；学生：所在院系；院系老师：所在院系；学校老师：所在部门
    private String department;
    // 用户加密后的登录密码
    private String hashedPassword;
    // 用户加密盐
    private String salt;
    // 用户角色，
    private String role;
}
