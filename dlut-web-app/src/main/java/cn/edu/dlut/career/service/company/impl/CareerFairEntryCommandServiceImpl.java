package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.CarrerFairEntry;
import cn.edu.dlut.career.repository.company.CarrerFairEntryRepository;
import cn.edu.dlut.career.service.company.CareerFairEntryCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 双选会，招聘会预约申请 服务层
 * Created by HealerJean on 2017/4/6.
 */
@Service
@Transactional
public class CareerFairEntryCommandServiceImpl implements CareerFairEntryCommandService {

    @Autowired
    CarrerFairEntryRepository carrerFairEntryRepository;
//    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public CarrerFairEntry saveCarreFairEntry(CarrerFairEntry carrerFairEntry) {
       return carrerFairEntryRepository.save(carrerFairEntry);
      /*  CarrerFairEntry carrerFairEntry = new CarrerFairEntry();
        try {
            //将InvoiceInfo对象转换为json
            String invoiceInfo = objectMapper.writeValueAsString(carreFairEntryDTO.getInvoiceInfo());
            carrerFairEntry.setInvoiceInfo(invoiceInfo);

            carrerFairEntry.setId(carreFairEntryDTO.getId());
            carrerFairEntry.setRecJobFairId(carreFairEntryDTO.getRecJobFairId());
            carrerFairEntry.setRecBulletinId(carreFairEntryDTO.getRecBulletinId());
            carrerFairEntry.setRecName(carreFairEntryDTO.getRecName());
            carrerFairEntry.setRecAddress(carreFairEntryDTO.getRecAddress());
            carrerFairEntry.setUnitFinaPhone(carreFairEntryDTO.getUnitFinaPhone());
            carrerFairEntry.setIsSign(carreFairEntryDTO.getIsSign());
            carrerFairEntry.setAccoHotel(carreFairEntryDTO.getAccoHotel());
            carrerFairEntry.setApplyTime(carreFairEntryDTO.getApplyTime());
            carrerFairEntry.setEhxPlace(carreFairEntryDTO.getEhxPlace());
            carrerFairEntry.setPayType(carreFairEntryDTO.getPayType());
            carrerFairEntry.setPayTime(carreFairEntryDTO.getPayTime());
            carrerFairEntry.setReceStatus(carreFairEntryDTO.getReceStatus());
            carrerFairEntry.setAuditStatus(carreFairEntryDTO.getAuditStatus());
            carrerFairEntry.setAuditor(carreFairEntryDTO.getAuditor());
            carrerFairEntry.setAuditTime(carreFairEntryDTO.getAuditTime());
            carrerFairEntry.setNopassReason(carreFairEntryDTO.getNopassReason());

*/


    }


    @Override
    public CarrerFairEntry updateCarreFairEntry(CarrerFairEntry carrerFairEntry) {
        return carrerFairEntryRepository.save(carrerFairEntry);
    }

    @Override
    public String deleteCarreFairEntry(UUID id) {
        try {
            carrerFairEntryRepository.delete(id);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }







}
