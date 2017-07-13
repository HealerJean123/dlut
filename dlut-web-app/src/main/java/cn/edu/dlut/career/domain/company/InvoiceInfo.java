package cn.edu.dlut.career.domain.company;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 发票信息 表
 * Created by HealerJean on 2017/4/6.
 */
@Entity
@Table(name = "rec_invoice_info")
public class InvoiceInfo {

    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;


    //专场招聘预约或双选会，招聘会预约申请表ID(也可以作为其他表发票关联)外键)
    @Column(length = 30,nullable = false)
    private UUID recCareerId;

    //发票抬头
    @Column(length = 50,nullable = false)
    private String title;

    //发票类型：1 增值税普通发票，2 增值税专用发票
    @Column(length = 3,nullable = false)
    private String type;

    //税务登记号
    @Column(length = 20,nullable = false)
    private String taxNum;

    //开户行名称
    @Column(length = 80,nullable = false)
    private String bankName;

    //开户行账号
    @Column(length = 80,nullable = false)
    private String accountNum;

    //注册场所地址
    @Column(length = 50,nullable = false)
    private String address;

    //固定电话
    @Column(length = 20,nullable = true)
    private String telphone;

    //营业执照复印件(url地址)
    @Column(length = 100,nullable = true)
    private String busiIcence;

    //税务登记复印件(url地址)
    @Column(length = 100,nullable = true)
    private String taxRegister;

    //纳税人资格认证复印件(url地址)
    @Column(length = 100,nullable = true)
    private String taxpayer;

    //发票内容
    @Column(length = 200,nullable = true)
    private String content;

    //发票时间
    @Column(nullable = true)
    private LocalDateTime time;

    //开票人
    @Column(length = 10,nullable = true)
    private  String drawer;



    public InvoiceInfo() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRecCareerId() {
        return recCareerId;
    }

    public void setRecCareerId(UUID recCareerId) {
        this.recCareerId = recCareerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTaxNum() {
        return taxNum;
    }

    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getBusiIcence() {
        return busiIcence;
    }

    public void setBusiIcence(String busiIcence) {
        this.busiIcence = busiIcence;
    }

    public String getTaxRegister() {
        return taxRegister;
    }

    public void setTaxRegister(String taxRegister) {
        this.taxRegister = taxRegister;
    }

    public String getTaxpayer() {
        return taxpayer;
    }

    public void setTaxpayer(String taxpayer) {
        this.taxpayer = taxpayer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }
}


