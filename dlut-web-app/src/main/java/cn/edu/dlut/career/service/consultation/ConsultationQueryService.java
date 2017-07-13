package cn.edu.dlut.career.service.consultation;

import cn.edu.dlut.career.domain.consultation.Appointment;
import cn.edu.dlut.career.domain.consultation.WorkshopScheduling;
import cn.edu.dlut.career.domain.consultation.WorkshopSubject;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * 咨询工作坊相关查询接口
 */
public interface ConsultationQueryService {
    /**
     * 查询所有的咨询工作坊主题
     * @return
     */
    Page<WorkshopSubject> findAll();

    /**
     * 查询所有有效的咨询工作坊的主题
     * @return
     */
    Page<WorkshopSubject> findAllEnabled();

    /**
     * 查询某个具体的咨询工作坊主题
     * @param id 具体的咨询工作坊主题的编号
     * @return
     */
    WorkshopSubject findOne(UUID subjectId);

    /**
     * 查询某个具体的咨询工作坊所有计划安排
     * @param subjectId 某个具体的咨询工作坊的编号
     * @return
     */
    List<WorkshopScheduling> findSchedules(UUID subjectId);

    /**
     * 查询某个具体的咨询工作坊指定的时间段内的计划安排
     * @param subjectId 某个具体的咨询工作坊的编号
     * @param startDate 指定的开始日期
     * @param endDate 指定的结束日期
     * @return
     */
    List<WorkshopScheduling> findSchedules(UUID subjectId, LocalDate startDate, LocalDate endDate);

    /**
     * 查询某个具体的咨询工作坊当前日期指定天数内的计划安排
     * @param subjectId 某个具体的咨询工作坊的编号
     * @param afterDays 从当前开始的天数
     * @return
     */
    List<WorkshopScheduling> findSchedules(UUID subjectId, int afterDays);

    /**
     * 查询某个具体的咨询工作坊所有的报名情况
     * @param subjectId 某个具体的咨询工作坊的编号
     * @return
     */
    Page<Appointment> findAllAppointment(UUID subjectId);

    /**
     * 查询某个具体的咨询工作坊指定日期的报名情况
     * @param subjectId 某个具体的咨询工作坊的编号
     * @param scheduleDate 指定日期
     * @return
     */
    Page<Appointment> findScheduleAppointment(UUID subjectId, LocalDate scheduleDate);
}
