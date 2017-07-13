package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.JobApplicationRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/24.
 */
public interface JobApplicationRecordRepository extends CrudRepository<JobApplicationRecord,UUID> {
  @Override
  List<JobApplicationRecord> findAll();
}
