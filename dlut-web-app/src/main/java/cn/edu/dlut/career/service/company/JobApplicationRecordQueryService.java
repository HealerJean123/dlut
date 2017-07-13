package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.JobApplicationRecord;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/24.
 */
public interface JobApplicationRecordQueryService {

  List<JobApplicationRecord> findAll();

  JobApplicationRecord finOne(UUID id);
}
