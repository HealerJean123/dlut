package cn.edu.dlut.career.dto.school;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by wei on 2017/5/19.
 * 用于首页新闻的展示
 */
public class NewsDTO {

    private UUID id;
    //新闻标题
    private String title;

    //新闻发布日期
    private String publishDate;
    //是否置顶
    private Boolean isTop;

    public NewsDTO(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public NewsDTO(UUID id, String title, String publishDate) {
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
    }

    public NewsDTO(UUID id, String title, String publishDate, Boolean isTop) {
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
        this.isTop = isTop;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }
}
