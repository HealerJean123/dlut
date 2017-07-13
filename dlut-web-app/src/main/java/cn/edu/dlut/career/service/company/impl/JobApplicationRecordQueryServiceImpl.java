package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.JobApplicationRecord;
import cn.edu.dlut.career.repository.company.JobApplicationRecordRepository;
import cn.edu.dlut.career.service.company.JobApplicationRecordQueryService;
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
public class JobApplicationRecordQueryServiceImpl implements JobApplicationRecordQueryService {
  @Autowired
  private JobApplicationRecordRepository jobApplicationRecordRepository;


  /**
   * 查询所有职位申请记录
   * @return
   */
  @Override
  public List<JobApplicationRecord> findAll() {
    return jobApplicationRecordRepository.findAll();
  }

  /**
   * 根据Id查询申请记录
   * @param id
   * @return
   */
  @Override
  public JobApplicationRecord finOne(UUID id) {
    return jobApplicationRecordRepository.findOne(id);
  }
}
