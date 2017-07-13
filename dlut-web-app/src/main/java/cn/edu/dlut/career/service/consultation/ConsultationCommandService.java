package cn.edu.dlut.career.service.consultation;

import cn.edu.dlut.career.domain.consultation.Appointment;
import cn.edu.dlut.career.domain.consultation.ConsultationArrangement;
import cn.edu.dlut.career.domain.consultation.WorkshopScheduling;

import java.util.UUID;

/**
 * 咨询工作坊相关命令接口
 */
public interface ConsultationCommandService {
    /**
     * 添加或者修改咨询活动安排
     * @param consultationArrangement
     */
    void saveArrangement(ConsultationArrangement consultationArrangement);

    /**
     * 为某个咨询工作坊主题添加计划安排
     * @param scheduling
     */
    void createSchedule(WorkshopScheduling scheduling);

    /**
     * 更新某个咨询工作坊的任务安排
     * @param scheduling
     */
    void updateSchedule(WorkshopScheduling scheduling);

    /**
     * 删除指定的计划安排
     * @param scheduleId
     */
    void deleteSchedule(UUID scheduleId);

    /**
     * 学生申请咨询工作坊的活动预约
     * @param appointment
     */
    void applyAppointment(Appointment appointment);

    /**
     * 学生申请放弃咨询工作坊掉活动预约
     * @param appointId
     */
    void cancelAppointment(UUID appointId);

    /**
     * 教师审核确定是否通过学生掉预约申请
     * @param appointId
     * @param pass
     */
    void validAppointment(UUID appointId, boolean pass);

    /**
     * 删除指定id的咨询活动
     * 该咨询活动的相关安排、申请都会被删除
     * @param id 指定咨询活动的id
     */
    void deleteArrangement(UUID id);
}
