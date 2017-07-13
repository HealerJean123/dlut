package cn.edu.dlut.career.notification;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 消息(站内信）
 * @Author wangyj.
 * @Date 2017/3/28  16:00.
 */
@Entity
public class Notifcations
{
    @Id
    @GenericGenerator(name="idGenericGenrator",strategy = "uuid")
    @GeneratedValue(generator = "idGenericGenrator")
    private String id;

    @Column(length = 32,nullable = false)
    private String senderId;

    @Column(length = 20,nullable = false)
    private String senderType;

    @Column(length = 20)
    private String sendNickname;

    @Column(length = 32,nullable = false)
    private String toId;

    @Column(length = 20,nullable = false)
    private String toType;

    @Column(length = 20)
    private String toNickname;

    @Column(length = 20,nullable = false)
    private String action;

    @Column(length = 255,nullable = false)
    private String content;

    @Column(length = 32)
    private String targetOid;

    @Column(length = 20)
    private String targetOtype;

    @Column(length = 1)
    private int status;

    @Transient
    private Map<String,String> var;

    @CreationTimestamp
    private LocalDateTime sendTime;

    /**
     * @Description .
     * @Author  wangyj
     * @CreateDate 2017/3/28 16:13
     */
    public Notifcations() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public String getSendNickname() {
        return sendNickname;
    }

    public void setSendNickname(String sendNickname) {
        this.sendNickname = sendNickname;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public String getToNickname() {
        return toNickname;
    }

    public void setToNickname(String toNickname) {
        this.toNickname = toNickname;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTargetOid() {
        return targetOid;
    }

    public void setTargetOid(String targetOid) {
        this.targetOid = targetOid;
    }

    public String getTargetOtype() {
        return targetOtype;
    }

    public void setTargetOtype(String targetOtype) {
        this.targetOtype = targetOtype;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public Map<String, String> getVar() {
        return var;
    }

    public void setVar(Map<String, String> var) {
        this.var = var;
    }
}
