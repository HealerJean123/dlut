package cn.edu.dlut.career.repository.consultation;

import cn.edu.dlut.career.domain.consultation.WorkshopSubject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.UUID;

/**
 * 咨询工作坊相关的数据库持久层接口
 */
public interface WorkshopSubjectRepository extends Repository<WorkshopSubject, UUID> {
    WorkshopSubject save(WorkshopSubject subject);

    /**
     * 删除指定uuid的咨询工作坊
     * @param subjectId
     */
    void deleteById(UUID subjectId);
}
