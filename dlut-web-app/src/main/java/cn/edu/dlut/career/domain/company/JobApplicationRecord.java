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
 * 申请记录表 学生选择公司职位申请
 */
@Entity
@Table(name = "rec_job_require")
public class JobApplicationRecord {
  public JobApplicationRecord() {
  }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRecId() {
        return recId;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
    }

    public UUID getStuId() {
        return stuId;
    }

    public void setStuId(UUID stuId) {
        this.stuId = stuId;
    }

    public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public LocalDateTime getApplicationTime() {
    return applicationTime;
  }

  public void setApplicationTime(LocalDateTime applicationTime) {
    this.applicationTime = applicationTime;
  }

  //编号，主键UUID
  @Id
  @GenericGenerator(name = "idGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "idGenerator")
  private UUID id;

  //学生id
  @Column(nullable = true)
  private UUID stuId;

  //公司编号
  @Column(nullable = true)
  private UUID recId;

  //申请职位
  @Column(length = 500, nullable = true)
  private String job;

  //申请时间
  @Column(nullable = true)
  private LocalDateTime applicationTime;
}
