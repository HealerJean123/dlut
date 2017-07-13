package cn.edu.dlut.career.domain.common;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Description 操作日志表
 * Created by HealerJean
 * DATE   2017/5/19 14:50.
 */
@Entity
@Table(name = "operate_log")
public class OperateLog {

    //操作日志主键
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //操作人id
    @Column(length = 32 ,nullable = false)
    private UUID optId;

    //操作人姓名
    @Column(length = 20,nullable = false)
    private String optName;

    //被操作事件id 例如三方协议 id  改派审核id
    @Column(length = 32,nullable = false)
    private UUID optionId;

    //操作类型
    @Column(length = 20,nullable = false)
    private String optType;

    // 操作描述
    @Column(length = 100,nullable = false)
    private String  description;

    //操作人ip
    @Column(length = 15,nullable = false)
    private String ip;

    //日志创建时间（操作时间）
    @CreationTimestamp
    private LocalDateTime createTime;

    public OperateLog() {
    }

    //本构造器用于再 操作执行时候进行构造，并执行保存操作日志
    public OperateLog(UUID optionId, String optType) {
        this.optionId = optionId;
        this.optType = optType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOptId() {
        return optId;
    }

    public void setOptId(UUID optId) {
        this.optId = optId;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public UUID getOptionId() {
        return optionId;
    }

    public void setOptionId(UUID optionId) {
        this.optionId = optionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }
}
