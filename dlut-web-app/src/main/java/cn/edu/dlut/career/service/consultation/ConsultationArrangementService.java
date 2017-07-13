package cn.edu.dlut.career.service.consultation;



import cn.edu.dlut.career.domain.consultation.ConsultationArrangement;

import java.util.List;
import java.util.UUID;

/**
 * 咨询服务Command相关的服务层接口
 */
public interface ConsultationArrangementService extends ConsultationCommandService {
    /**
     * 返回当前所有的咨询安排的列表
     */
    List<ConsultationArrangement> findAll();

    /**
     * 查找
     * @param id
     * @return
     */
    ConsultationArrangement finOne(UUID id);

}
