package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.CarrerFairEntry;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 双选会，招聘会预约申请 接口层
 * Created by HealerJean on 2017/4/6.
 */
public interface CareerFairEntryCommandService {
    //保存，添加
    CarrerFairEntry saveCarreFairEntry(CarrerFairEntry carrerFairEntry) ;

    // 更新
    CarrerFairEntry updateCarreFairEntry(CarrerFairEntry carrerFairEntry);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteCarreFairEntry(UUID id);



}
