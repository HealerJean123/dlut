package cn.edu.dlut.career.service.consultation;

import cn.edu.dlut.career.domain.consultation.Appointment;
import cn.edu.dlut.career.domain.consultation.ConsultationArrangement;
import cn.edu.dlut.career.domain.consultation.WorkshopScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * 咨询工作坊相关命令接口实现
 */
@Service
@Transactional
public class ConsultationCommandServiceImpl implements ConsultationCommandService {
    @Override
    public void saveArrangement(ConsultationArrangement consultationArrangement) {
        //
    }

    @Override
    public void createSchedule(WorkshopScheduling scheduling) {
    }

    @Override
    public void updateSchedule(WorkshopScheduling scheduling) {

    }

    @Override
    public void deleteSchedule(UUID scheduleId) {

    }

    @Override
    public void applyAppointment(Appointment appointment) {

    }

    @Override
    public void cancelAppointment(UUID appointId) {

    }

    @Override
    public void validAppointment(UUID appointId, boolean pass) {

    }

    @Override
    public void deleteArrangement(UUID id) {

    }
}
