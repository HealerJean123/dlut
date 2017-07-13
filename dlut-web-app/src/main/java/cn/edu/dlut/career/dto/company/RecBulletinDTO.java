package cn.edu.dlut.career.dto.company;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by wei on 2017/4/10.
 * 筛选招聘简章的信息
 */
public class RecBulletinDTO {
    //企业唯一识别ID
    private UUID recId;
    //招聘简章标题
    private String title;
    //招聘简章内容
    private String content;
    //有效开始时间
    private String startTime;
    //有效结束时间
    private String endTime;

    //备注
    private String remarks;

    public RecBulletinDTO() {
    }

    public UUID getRecId() {
        return recId;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
