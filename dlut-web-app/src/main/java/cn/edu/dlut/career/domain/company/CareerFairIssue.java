package cn.edu.dlut.career.domain.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 实体类 双选会，招聘会的 相关事项表
 * Created by HealerJean on 2017/4/6.
 */
@Entity
@Table(name = "rec_career_fair_issue")
public class CareerFairIssue {
    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;


    //双选会，招聘会信息表ID(外键)
    @Column(length = 30,nullable = false)
    private UUID recJobFairId;

    //日期
    @Column(nullable = true)
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate issueDate;

    //事项名称
    @Column(length = 50,nullable = false)
    private String name;

    //地点
    @Column(length = 50,nullable = true)
    private String address;

    //内容
    @Column(length = 50,nullable = true)
    private String content;

    //联系人
    @Column(length = 10,nullable = false)
    private String principal;


    //联系电话
    @Column(length = 20,nullable = false)
    private String phone;

    //备注
    @Column(length = 100,nullable = true)
    private String ramarks;

    public CareerFairIssue() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRecJobFairId() {
        return recJobFairId;
    }

    public void setRecJobFairId(UUID recJobFairId) {
        this.recJobFairId = recJobFairId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRamarks() {
        return ramarks;
    }

    public void setRamarks(String ramarks) {
        this.ramarks = ramarks;
    }
}
