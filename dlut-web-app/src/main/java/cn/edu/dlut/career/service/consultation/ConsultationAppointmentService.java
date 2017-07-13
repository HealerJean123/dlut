package cn.edu.dlut.career.service.consultation;


import cn.edu.dlut.career.domain.consultation.ConsultationAppointment;

import java.util.List;
import java.util.UUID;

/**
 * Created by liang on 2017/3/30.
 */
public interface ConsultationAppointmentService {
    List<ConsultationAppointment> findAll();

    ConsultationAppointment finOne(UUID id);

    void delete(UUID id);

    void save(ConsultationAppointment consultationAppointment);
}
