package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.CarrerFairEntry;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会预约申请 数据操作层
 * Created by HealerJean on 2017/4/6.
 */
@Repository
public interface CarrerFairEntryRepository extends CrudRepository<CarrerFairEntry,UUID>{
    //查找全部信息
    List<CarrerFairEntry> findAll();

    //根据id进行查找
    CarrerFairEntry findById(UUID id);



}
