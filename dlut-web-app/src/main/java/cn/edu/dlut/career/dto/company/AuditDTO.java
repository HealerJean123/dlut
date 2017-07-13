package cn.edu.dlut.career.dto.company;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by 史念念 on 2017/4/7.
 *
 * 审核信息 DTO
 */
public class AuditDTO {

    //审核状态
    @NotNull
    @Max(3)
    private String auditStatus;

    //审核时间
    @NotNull
    private LocalDateTime auditTime;

    //审核人
    @NotNull
    @Max(50)
    private String auditor;

    //审核失败原因
    @Max(100)
    private String nopassReason;

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getNopassReason() {
        return nopassReason;
    }

    public void setNopassReason(String nopassReason) {
        this.nopassReason = nopassReason;
    }
}
