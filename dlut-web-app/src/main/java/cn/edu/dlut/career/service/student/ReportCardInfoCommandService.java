package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.ReportCardInfo;

import java.util.List;
import java.util.UUID;

/**
 * 报到证签到信息接口层
 * Created by HealerJean on 2017/4/14.
 */
public interface ReportCardInfoCommandService {

    //保存，添加
    ReportCardInfo saveReportCardInfo(ReportCardInfo reportCardInfo) ;

    // 更新
    ReportCardInfo updateReportCardInfo(ReportCardInfo reportCardInfo);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteReportCardInfo(UUID id);


}
