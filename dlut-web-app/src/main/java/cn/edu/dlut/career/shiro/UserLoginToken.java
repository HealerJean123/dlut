package cn.edu.dlut.career.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 拓展shiro框架中UsernamePasswordToken类，增加type参数
 * @Author wangyj.
 * @Date 2017/4/6  14:55.
 */
public class UserLoginToken extends UsernamePasswordToken{

    //身份类型
    private String type;

    public UserLoginToken(String username, String password, String type) {
        super(username, password);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
