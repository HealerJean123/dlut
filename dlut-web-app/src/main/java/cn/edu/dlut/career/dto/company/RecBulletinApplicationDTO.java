package cn.edu.dlut.career.dto.company;

import cn.edu.dlut.career.domain.company.RecJobPosition;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/7.
 *
 * 招聘简章申请DTO
 */
public class RecBulletinApplicationDTO {

    //招聘简章标题
    @NotNull
    @Max(100)
    private String title;

    //企业ID
    @NotNull
    private UUID recId;

    //招聘简章内容
    @NotNull
    @Max(2000)
    private String content;

    //有效开始时间
    @NotNull
    private String startTime;

    //有效结束时间
    @NotNull
    private String endTime;

    //备注信息
    private String remarks;

    //上下线状态
    @NotNull
    private String onlineStatus;

    //招聘职位
    private List<RecJobPosition> recJobPositions = new ArrayList<RecJobPosition>();


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getRecId() {
        return recId;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
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

    public List<RecJobPosition> getRecJobPositions() {
        return recJobPositions;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public void setRecJobPositions(List<RecJobPosition> recJobPositions) {
        this.recJobPositions = recJobPositions;
    }
}
