package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.PartakeRepresent;
import cn.edu.dlut.career.repository.company.PartakeRepresentRepository;
import cn.edu.dlut.career.service.company.PartakeRepresentCommandService;
import cn.edu.dlut.career.service.company.PartakeRepresentQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 参与招聘会代表   服务层
 * Created by HealerJean on 2017/4/6.
 */
@Service
@Transactional
public class PartakeRepresentCommandServiceImpl implements PartakeRepresentCommandService {
    @Autowired
    PartakeRepresentRepository partakeRepresentRepository;
    @Override
    public PartakeRepresent savePartakeRepresent(PartakeRepresent partakeRepresent) {
        return partakeRepresentRepository.save(partakeRepresent);
    }


    @Override
    public PartakeRepresent updatePartakeRepresent(PartakeRepresent partakeRepresent) {
        return partakeRepresentRepository.save(partakeRepresent);
    }

    @Override
    public String deletePartakeRepresent(UUID id) {
        try {
            partakeRepresentRepository.delete(id);
            return  "ok";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
