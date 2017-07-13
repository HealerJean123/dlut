package cn.edu.dlut.career.service.consultation;

import cn.edu.dlut.career.domain.consultation.Appointment;
import cn.edu.dlut.career.domain.consultation.WorkshopScheduling;
import cn.edu.dlut.career.domain.consultation.WorkshopSubject;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * 咨询工作坊相关查询接口的实现
 */
@Service
@Transactional(readOnly = true)
public class ConsultationQueryServiceImpl implements ConsultationQueryService {
    @Override
    public Page<WorkshopSubject> findAll() {
        return null;
    }

    @Override
    public Page<WorkshopSubject> findAllEnabled() {
        return null;
    }

    @Override
    public WorkshopSubject findOne(UUID subjectId) {
        return null;
    }

    @Override
    public List<WorkshopScheduling> findSchedules(UUID subjectId) {
        return null;
    }

    /**
     * 查询某个具体的咨询工作坊指定的时间段内的计划安排
     * @param subjectId 某个具体的咨询工作坊的编号
     * @param startDate 指定的开始日期
     * @param endDate 指定的结束日期
     * @return
     */
    @Override
    public List<WorkshopScheduling> findSchedules(UUID subjectId, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    /**
     * 查询某个具体的咨询工作坊当前日期指定天数内的计划安排
     * @param subjectId 某个具体的咨询工作坊的编号
     * @param afterDays 从当前开始的天数
     * @return
     */
    @Override
    public List<WorkshopScheduling> findSchedules(UUID subjectId, int afterDays) {
        return findSchedules(subjectId, LocalDate.now(), LocalDate.now().plusDays(afterDays));
    }

    @Override
    public Page<Appointment> findAllAppointment(UUID subjectId) {
        return null;
    }

    @Override
    public Page<Appointment> findScheduleAppointment(UUID subjectId, LocalDate scheduleDate) {
        return null;
    }
}
