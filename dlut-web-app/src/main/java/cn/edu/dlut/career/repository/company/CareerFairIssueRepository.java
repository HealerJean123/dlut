package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.CareerFairIssue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会信息 的相关事项表 数据操作层
 * Created by HealerJean on 2017/4/6.
 */
@Repository
public interface CareerFairIssueRepository extends CrudRepository<CareerFairIssue,UUID>{

    //查找全部信息
    List<CareerFairIssue> findAll();

    //根据id进行查找
    CareerFairIssue findById(UUID id);
}
