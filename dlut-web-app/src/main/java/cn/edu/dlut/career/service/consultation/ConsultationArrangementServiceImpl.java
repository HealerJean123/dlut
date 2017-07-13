package cn.edu.dlut.career.service.consultation;


import cn.edu.dlut.career.domain.consultation.Appointment;
import cn.edu.dlut.career.domain.consultation.ConsultationArrangement;
import cn.edu.dlut.career.domain.consultation.WorkshopScheduling;
import cn.edu.dlut.career.repository.company.ConsultationArrangementRepository;
import cn.edu.dlut.career.service.consultation.ConsultationArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by liang on 2017/3/30.
 */
@Service
public class ConsultationArrangementServiceImpl implements ConsultationArrangementService {
    @Autowired
    private ConsultationArrangementRepository consultationArrangementRepository;
    @Override
    public List<ConsultationArrangement> findAll() {
        return consultationArrangementRepository.findAll();
    }

    @Override
    public ConsultationArrangement finOne(UUID id) {
        return consultationArrangementRepository.findOne(id);
    }

//    @Override
//    public void delete(UUID id) {
//        consultationArrangementRepository.delete(id);
//    }

    @Override
    public void saveArrangement(ConsultationArrangement consultationArrangement) {
        consultationArrangementRepository.save(consultationArrangement);
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
