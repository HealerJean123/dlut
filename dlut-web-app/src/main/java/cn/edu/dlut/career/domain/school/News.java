package cn.edu.dlut.career.domain.school;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 新闻信息实体
 * @Author wangyj.
 * @Date 2017/5/17  16:51.
 */
@Entity
public class News {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //新闻编号
    @Column(length = 32,nullable = false,unique = true)
    private String newsNo;

    //新闻标题
    @Column(length = 50,nullable = false)
    private String title;

    //新闻副标题
    @Column(length = 50,nullable = false)
    private String subTitle;

    //新闻栏目
    @Column(length = 15,nullable = false)
    private String newsColumn ;

    //该新闻是否为轮播图新闻
    @Column(nullable = false)
    private Boolean isBanner;
    //轮播图片
    @Column(nullable = true)
    private String banner ;

    //新闻内容
    @Type(type="text")
    private String content;

    //新闻发布日期
    @Column(nullable = false)
    private String publishDate;

    //新闻结束日期
    @Column(nullable = false)
    private String endDate;

    //新闻排序编号
    @Column(length = 8,nullable = false)
    private int sortNo;

    //新闻排序结束日期
    @Column(length = 50,nullable = false)
    private String sortEndDate;

    //新闻置顶结束日期
    @Column(length = 50,nullable = false)
    private String topEndDate;

    //是否置顶
    @Column(nullable = false)
    private Boolean isTop;

    //是否前台展示
    @Column(nullable = true)
    private Boolean isDisplay;

    //状态
    @Column(length = 10,nullable = true)
    private String  status;

    //页面访问统计
    @Column(length = 8,nullable = true)
    private int pv;

    //创建人
    @Column(length = 10,nullable = false)
    private String creator;

    //创建时间
    private LocalDateTime creatTime;

    //更新时间
    private LocalDateTime updateTime;

    //招聘会生成新闻中招聘会的召开日期
    @Column(length = 15)
    private String recruiterDate;

    public News() {
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNewsNo() {
        return newsNo;
    }

    public void setNewsNo(String newsNo) {
        this.newsNo = newsNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getNewsColumn() {
        return newsColumn;
    }

    public void setNewsColumn(String newsColumn) {
        this.newsColumn = newsColumn;
    }

    public Boolean getIsBanner() {
        return isBanner;
    }

    public void setIsBanner(Boolean isBanner) {
        this.isBanner = isBanner;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getSortEndDate() {
        return sortEndDate;
    }

    public void setSortEndDate(String sortEndDate) {
        this.sortEndDate = sortEndDate;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    public Boolean getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Boolean isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(LocalDateTime creatTime) {
        this.creatTime = creatTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getTopEndDate() {
        return topEndDate;
    }

    public void setTopEndDate(String topEndDate) {
        this.topEndDate = topEndDate;
    }

    public String getRecruiterDate() {
        return recruiterDate;
    }

    public void setRecruiterDate(String recruiterDate) {
        this.recruiterDate = recruiterDate;
    }
}
