package cn.edu.dlut.career.domain.consultation;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 咨询工作坊的时间进度安排
 */
@Entity
@Table(name = "consult_schedule")
public class WorkshopScheduling {
    // 进度安排的主键
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    @Type(type = "pg-uuid")
    private UUID id;
    // 咨询工作坊的主题
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private WorkshopSubject subject;
    // 咨询安排的日期
    private LocalDateTime startDate;
    // 具体的时间段
    private LocalDateTime endDate;
    // 计划接待的人数
    @Column(nullable = false)
    private int planBookNum = 0;
    // 实际接待的人数
    @Column(nullable = false)
    private int actualBookNum = 0;

    public WorkshopScheduling(WorkshopSubject subject) {
        this.subject = subject;
    }

    public WorkshopScheduling(WorkshopSubject subject, LocalDateTime startDate, LocalDateTime endDate, int planBookNum) {
        this.subject = subject;
        this.startDate = startDate;
        this.endDate = endDate;
        this.planBookNum = planBookNum;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public WorkshopSubject getSubject() {
        return subject;
    }

    public void setSubject(WorkshopSubject subject) {
        this.subject = subject;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getPlanBookNum() {
        return planBookNum;
    }

    public void setPlanBookNum(int planBookNum) {
        this.planBookNum = planBookNum;
    }

    public int getActualBookNum() {
        return actualBookNum;
    }

    public void setActualBookNum(int actualBookNum) {
        this.actualBookNum = actualBookNum;
    }
}
