package cn.edu.dlut.career.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by HealerJean on 2017/5/10.
 */
@Entity
public class DispatchCode {

    //行政地区编码
    @Id
    @Column(length =8 ,nullable = false)
    private String areaCode;

    //行政地区名称
    @Column(length =50 ,nullable = true)
    private String areaName;

    //派遣单位名称
    @Column(length =50 ,nullable = true)
    private String dispatchRecName;

    //派遣单位地址
    @Column(length =50 ,nullable = true)
    private String dispatchRecAdd;

    //档案接收单位
    @Column(length =50 ,nullable = true)
    private String profileRecName;

    //档案接收地址
    @Column(length =50 ,nullable = true)
    private String profileRecAdd;

    //档案接收人
    @Column(length =10 ,nullable = true)
    private String profileReceiver;

    //档案接收人电话
    @Column(length =20 ,nullable = true)
    private String profileRecTel;


    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDispatchRecName() {
        return dispatchRecName;
    }

    public void setDispatchRecName(String dispatchRecName) {
        this.dispatchRecName = dispatchRecName;
    }

    public String getDispatchRecAdd() {
        return dispatchRecAdd;
    }

    public void setDispatchRecAdd(String dispatchRecAdd) {
        this.dispatchRecAdd = dispatchRecAdd;
    }

    public String getProfileRecName() {
        return profileRecName;
    }

    public void setProfileRecName(String profileRecName) {
        this.profileRecName = profileRecName;
    }

    public String getProfileRecAdd() {
        return profileRecAdd;
    }

    public void setProfileRecAdd(String profileRecAdd) {
        this.profileRecAdd = profileRecAdd;
    }

    public String getProfileReceiver() {
        return profileReceiver;
    }

    public void setProfileReceiver(String profileReceiver) {
        this.profileReceiver = profileReceiver;
    }

    public String getProfileRecTel() {
        return profileRecTel;
    }

    public void setProfileRecTel(String profileRecTel) {
        this.profileRecTel = profileRecTel;
    }
}
