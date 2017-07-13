package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.OfferTemplate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/28.
 */
public interface OfferTemplateRepositry extends CrudRepository<OfferTemplate,UUID> {
    List<OfferTemplate> findAll();

    @Query(value = "from OfferTemplate where recId=?1 AND (name = ?2 or ?2 = '') AND (stuType = ?3 or ?3 = '') ")
    List<OfferTemplate> findByRecId(UUID recId,String name,String stuType);
}
