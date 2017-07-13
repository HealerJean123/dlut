package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.ApplicationProcessing;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/24.
 */

public interface ApplicationProcessingCommandService {
  ApplicationProcessing save(ApplicationProcessing applicationProcessing);

  void delete(UUID id);

  ApplicationProcessing update(ApplicationProcessing applicationProcessing);

}
