package cn.edu.dlut.career.service.consultation;

import cn.edu.dlut.career.domain.consultation.ConsultationAppointment;
import cn.edu.dlut.career.repository.company.ConsultationAppointmentRepository;
import cn.edu.dlut.career.service.consultation.ConsultationAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by liang on 2017/3/30.
 */
@Service
public class ConsultationAppointmentServiceImpl implements ConsultationAppointmentService {
    @Autowired
    private ConsultationAppointmentRepository consultationAppointmentRepository;
    @Override
    public List<ConsultationAppointment> findAll() {
        return consultationAppointmentRepository.findAll();
    }

    @Override
    public ConsultationAppointment finOne(UUID id) {
        return consultationAppointmentRepository.findOne(id);
    }

    @Override
    public void delete(UUID id) {
        consultationAppointmentRepository.delete(id);
    }

    @Override
    public void save(ConsultationAppointment consultationAppointment) {
        consultationAppointmentRepository.save(consultationAppointment);
    }
}
