package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.ApplicationProcessing;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/24.
 */
public interface ApplicationProcessingQueryService {


  List<ApplicationProcessing> findAll();

  ApplicationProcessing findOne(UUID id);
}
