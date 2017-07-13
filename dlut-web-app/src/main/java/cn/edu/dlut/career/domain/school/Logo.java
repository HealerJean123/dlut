package cn.edu.dlut.career.domain.school;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 合作伙伴logo实体
 * @Author wangyj.
 * @Date 2017/5/31  15:06.
 */
@Entity
public class Logo {
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //合作企业名称
    @Column(length = 32 ,nullable = false)
    private String name;

    //企业logo
    @Column(nullable = false)
    private String logo;

    //对应链接
    @Column(nullable = false)
    private String url;

    //排序编号
    @Column(length = 3)
    private int sortNo;

    //创建时间
    private LocalDateTime creatTime;

    //创建人
    @Column(length = 10)
    private String creator;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public LocalDateTime getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(LocalDateTime creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Logo() {
    }
}
