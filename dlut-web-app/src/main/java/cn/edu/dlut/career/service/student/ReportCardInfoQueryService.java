package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.ReportCardInfo;

import java.util.List;
import java.util.UUID;

/**
 * 报到证签到信息接口层
 * Created by HealerJean on 2017/4/14.
 */
public interface ReportCardInfoQueryService {

    //根据id查询
    ReportCardInfo findById(UUID id);

    //查询所有的数据
    List<ReportCardInfo> findAllReportCardInfo();


}
