package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.PartakeRepresent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * 参与招聘会代表 数据操作层
 * Created by HealerJean on 2017/4/6.
 */
@Repository
public interface PartakeRepresentRepository extends CrudRepository<PartakeRepresent,UUID>{

    //查找全部信息
    List<PartakeRepresent> findAll();

    //根据id进行查找
    PartakeRepresent findById(UUID id);
}
