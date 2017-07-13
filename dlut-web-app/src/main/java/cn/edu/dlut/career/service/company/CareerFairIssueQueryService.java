package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.CareerFairIssue;

import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会信息 的相关事项表 接口层
 * Created by HealerJean on 2017/4/6.
 */
public interface CareerFairIssueQueryService {

    //根据id查询
    CareerFairIssue findById(UUID id);

    //查询所有的数据
    List<CareerFairIssue> findAllCareerFairIssue();

}
