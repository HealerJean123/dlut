package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.OfferTemplate;
import cn.edu.dlut.career.dto.company.OfferTemplateDTO;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/28.
 */
public interface OfferTemplateCommandService {
    OfferTemplate save(OfferTemplateDTO offerTemplateDTO);

    void delete(UUID id);

    void update(OfferTemplateDTO offerTemplateDTO);
}
