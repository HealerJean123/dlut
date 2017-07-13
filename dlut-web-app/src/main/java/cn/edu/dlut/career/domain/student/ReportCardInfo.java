package cn.edu.dlut.career.domain.student;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 报到证信息 表
 * Created by HealerJean on 2017/4/14.
 */
@Entity
@Table(name = "stu_report_card")
public class ReportCardInfo {

    //主键
    @Id
    @GenericGenerator(strategy = "uuid2",name = "idGenerator")
    @GeneratedValue(generator="idGenerator")
    private UUID id;

    //学生id （外键，连接学生学籍信息表）
    @Column(length = 32,nullable = false)
    private  UUID stuId;

    //报到证编号
    @Column(length = 30,nullable = false)
    private  UUID rcId;

    //报到证签发类别 关联字典
    @Column(length = 3,nullable = true)
    private String category;

    //报到证签发时间
    @Column(nullable = false)
    private LocalDate time;

    //报到证签往单位 名称
    @Column(length = 30,nullable = false)
    private String rec_name;

    //报到单位组织机构代码
    @Column(length = 20,nullable = false)
    private  String org_code;

    //签往单位所在地地址
    @Column(length = 50,nullable = false)
    private  String address;

    //违约金金额
    @Column(nullable = true)
    private  float cost;

    public ReportCardInfo() {
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getStuId() {
        return stuId;
    }

    public void setStuId(UUID stuId) {
        this.stuId = stuId;
    }

    public UUID getRcId() {
        return rcId;
    }

    public void setRcId(UUID rcId) {
        this.rcId = rcId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getRec_name() {
        return rec_name;
    }

    public void setRec_name(String rec_name) {
        this.rec_name = rec_name;
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
