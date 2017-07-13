package cn.edu.dlut.career.domain.consultation;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 咨询工作坊的主题
 */
@Entity
@Table(name = "consult_subject")
public class WorkshopSubject {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    @Type(type = "pg-uuid")
    private UUID id;
    // 咨询活动的标题／主题
    @Column(length=50, nullable = false)
    private String title = "";
    // 该主题活动的详细介绍，包含活动介绍、讲师介绍等。内含HTML字符
    @Column(length=5000, nullable = false)
    private String description = "";
    // 是否有效
    private boolean isEnabled;
    //创建时间
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createOn = LocalDateTime.now();
    //最后修改时间
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateTime;
    /**
     * 本次活动的所有计划安排
     */
    @OneToMany(fetch = FetchType.LAZY)
    @OrderBy("startDate DESC")
    private List<WorkshopScheduling> schedules;
    /**
     * 本次活动的报名学生列表
     */
    @OneToMany(fetch = FetchType.LAZY)
    @OrderBy("createdOn DESC")
    private List<Appointment> appointments;

    public WorkshopSubject() {
    }

    public WorkshopSubject(String title, String description) {
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public LocalDateTime getCreateOn() {
        return createOn;
    }

    public void setCreateOn(LocalDateTime createOn) {
        this.createOn = createOn;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<WorkshopScheduling> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<WorkshopScheduling> schedules) {
        this.schedules = schedules;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
