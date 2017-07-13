package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.JobFair;
import cn.edu.dlut.career.repository.company.JobFairReposiroty;
import cn.edu.dlut.career.service.company.JobFairQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会信息表 服务层
 * Created by HealerJean on 2017/4/6.
 */


@Service
@Transactional(readOnly = true)
public class JobFairQueryServiceImpl implements JobFairQueryService {
    @Autowired
    JobFairReposiroty jobFairReposiroty;

    @Override
    public JobFair findById(UUID id) {
        return jobFairReposiroty.findById(id);
    }


    @Override
    public List<JobFair> findAllJobFair() {
        return jobFairReposiroty.findAll();
    }
}
