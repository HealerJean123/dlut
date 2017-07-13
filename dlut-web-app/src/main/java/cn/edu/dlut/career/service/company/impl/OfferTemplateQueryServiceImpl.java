package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.OfferTemplate;
import cn.edu.dlut.career.repository.company.OfferTemplateRepositry;
import cn.edu.dlut.career.service.company.OfferTemplateQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/28.
 */
@Service
@Transactional(readOnly = true)
public class OfferTemplateQueryServiceImpl implements OfferTemplateQueryService {

    @Autowired
    private OfferTemplateRepositry offerTemplateRepositry;


    @Override
    public List<OfferTemplate> findAll() {
        return offerTemplateRepositry.findAll();
    }

    @Override
    public OfferTemplate findOne(UUID id) {
        return offerTemplateRepositry.findOne(id);
    }

    @Override
    public List<OfferTemplate> findAllByRecId(UUID recId,String name,String stuType) {
        return offerTemplateRepositry.findByRecId(recId,name,stuType);
    }

}
