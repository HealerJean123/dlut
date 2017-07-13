package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.OfferTemplate;
import cn.edu.dlut.career.dto.company.OfferTemplateDTO;
import cn.edu.dlut.career.repository.company.OfferTemplateRepositry;
import cn.edu.dlut.career.service.company.OfferTemplateCommandService;
import cn.edu.dlut.career.service.company.OfferTemplateQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/28.
 */
@Service
@Transactional
public class OfferTemplateCommandServiceImpl implements OfferTemplateCommandService {

    @Autowired
    private OfferTemplateRepositry offerTemplateRepositry;

    @Override
    public OfferTemplate save(OfferTemplateDTO offerTemplateDTO) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        OfferTemplate offerTemplate = new OfferTemplate();
        offerTemplate.setRecId(userPrincipal.getId());
        offerTemplate.setName(offerTemplateDTO.getName());
        offerTemplate.setJobType(offerTemplateDTO.getJobType());
        offerTemplate.setStuType(offerTemplateDTO.getStuType());
        offerTemplate.setClosingDate(offerTemplateDTO.getClosingDate());
        offerTemplate.setRemarks(offerTemplateDTO.getRemarks());
        offerTemplate.setIsPfile(offerTemplateDTO.getIsPfile());
        offerTemplate.setPfileToName(offerTemplateDTO.getPfileToName());
        offerTemplate.setPfileToDepart(offerTemplateDTO.getPfileToDepart());
        offerTemplate.setPfileToAddress(offerTemplateDTO.getPfileToAddress());
        offerTemplate.setPfileToLocal(offerTemplateDTO.getPfileToLocal());
        offerTemplate.setPfileToRecipient(offerTemplateDTO.getPfileToRecipient());
        offerTemplate.setPfileToPhone(offerTemplateDTO.getPfileToPhone());
        offerTemplate.setIsSolveHukou(offerTemplateDTO.getIsSolveHukou());
        offerTemplate.setIsNotMoveHuKou(offerTemplateDTO.getIsNotMoveHuKou());
        offerTemplate.setHukouToAddress(offerTemplateDTO.getHukouToAddress());
        OfferTemplate offerTemplateFinal = offerTemplateRepositry.save(offerTemplate);
        return  offerTemplateFinal;
    }

    @Override
    public void delete(UUID id) {
        offerTemplateRepositry.delete(id);
    }


    @Override
    public void update(OfferTemplateDTO offerTemplateDTO) {
        OfferTemplate offerTemplate = offerTemplateRepositry.findOne(offerTemplateDTO.getId());
        offerTemplate.setName(offerTemplateDTO.getName());
        offerTemplate.setJobType(offerTemplateDTO.getJobType());
        offerTemplate.setStuType(offerTemplateDTO.getStuType());
        offerTemplate.setClosingDate(offerTemplateDTO.getClosingDate());
        offerTemplate.setRemarks(offerTemplateDTO.getRemarks());
        offerTemplate.setIsPfile(offerTemplateDTO.getIsPfile());
        offerTemplate.setPfileToName(offerTemplateDTO.getPfileToName());
        offerTemplate.setPfileToDepart(offerTemplateDTO.getPfileToDepart());
        offerTemplate.setPfileToAddress(offerTemplateDTO.getPfileToAddress());
        offerTemplate.setPfileToLocal(offerTemplateDTO.getPfileToLocal());
        offerTemplate.setPfileToRecipient(offerTemplateDTO.getPfileToRecipient());
        offerTemplate.setPfileToPhone(offerTemplateDTO.getPfileToPhone());
        offerTemplate.setIsSolveHukou(offerTemplateDTO.getIsSolveHukou());
        offerTemplate.setIsNotMoveHuKou(offerTemplateDTO.getIsNotMoveHuKou());
        offerTemplate.setHukouToAddress(offerTemplateDTO.getHukouToAddress());
        OfferTemplate offerTemplateFinal = offerTemplateRepositry.save(offerTemplate);
    }
}
