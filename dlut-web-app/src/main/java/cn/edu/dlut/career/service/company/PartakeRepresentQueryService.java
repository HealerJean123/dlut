package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.PartakeRepresent;

import java.util.List;
import java.util.UUID;

/**
 * 参与招聘会代表  接口层
 * Created by HealerJean on 2017/4/6.
 */
public interface PartakeRepresentQueryService {
    //根据id查询
    PartakeRepresent findById(UUID id);

    //查询所有的数据
    List<PartakeRepresent> findAllPartakeRepresent();

}
