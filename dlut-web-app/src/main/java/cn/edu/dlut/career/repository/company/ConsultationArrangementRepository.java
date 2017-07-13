package cn.edu.dlut.career.repository.company;


import cn.edu.dlut.career.domain.consultation.ConsultationArrangement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by liang on 2017/3/30.
 */
public interface ConsultationArrangementRepository extends CrudRepository<ConsultationArrangement,UUID> {
    List<ConsultationArrangement> findAll();
}
