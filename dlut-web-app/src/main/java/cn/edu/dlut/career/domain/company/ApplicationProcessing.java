package cn.edu.dlut.career.domain.company;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by wei on 2017/3/23.
 * offer处理表
 */
@Entity
@Table(name = "rec_require_deal")
public class ApplicationProcessing {
  public ApplicationProcessing() {
  }

  //编号，主键UUID
  @Id
  @GenericGenerator(name = "idGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "idGenerator")
  private UUID id;

  //申请编号
  @Column(nullable = false)
  private String reqId;

  //offer状态
  /**
   * 0待审核
   * 1通过
   * 2未通过
   */
  @Column(length = 100)
  private String status;

  //处理时间
  @Column(nullable = false)
  private LocalDateTime processingTime;

  //备注
  @Column(length = 500)
  private String remarks;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getReqId() {
    return reqId;
  }

  public void setReqId(String reqId) {
    this.reqId = reqId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getProcessingTime() {
    return processingTime;
  }

  public void setProcessingTime(LocalDateTime processingTime) {
    this.processingTime = processingTime;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }
}
