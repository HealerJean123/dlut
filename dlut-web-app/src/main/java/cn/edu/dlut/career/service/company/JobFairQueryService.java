package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.JobFair;

import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会信息表 接口层
 * Created by HealerJean on 2017/4/6.
 */
public interface JobFairQueryService {
    //根据id查询
    JobFair findById(UUID id);

    //查询所有的数据
    List<JobFair> findAllJobFair();
}
