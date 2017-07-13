package cn.edu.dlut.career.dto;

import java.io.Serializable;

/**
 * 前后端交互类.
 * Created by xiachao on 2017/4/6.
 */
public class ResponseInfo implements Serializable {

    private static final long serialVersionUID = -3844687636445941017L;
    //状态码
    private Integer status;

    //消息
    private String message;

    public ResponseInfo() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
