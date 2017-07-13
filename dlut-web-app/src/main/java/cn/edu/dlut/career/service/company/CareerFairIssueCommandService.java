package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.CareerFairIssue;

import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会信息 的相关事项表 接口层
 * Created by HealerJean on 2017/4/6.
 */
public interface CareerFairIssueCommandService {

    //保存，添加
    CareerFairIssue saveCareerFairIssue(CareerFairIssue careerFairIssue) ;

    // 更新
    CareerFairIssue updateCareerFairIssue(CareerFairIssue careerFairIssue);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteCareerFairIssue(UUID id);

}
