package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.ApplicationProcessing;
import cn.edu.dlut.career.repository.company.ApplicationProcessingRepository;
import cn.edu.dlut.career.service.company.ApplicationProcessingQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/24.
 */
@Service
@Transactional(readOnly = true)
public class ApplicationProcessingQueryServiceImpl implements ApplicationProcessingQueryService {
  @Autowired
  private ApplicationProcessingRepository applicationProcessingRepository;







  /**
   * 查询所有offer信息
   * @return
   */
  @Override
  public List<ApplicationProcessing> findAll() {
    return applicationProcessingRepository.findAll();
  }

  /**
   * 按照ID查询offer信息
   * @param id
   * @return
   */
  @Override
  public ApplicationProcessing findOne(UUID id) {
    return applicationProcessingRepository.findOne(id);
  }
}
