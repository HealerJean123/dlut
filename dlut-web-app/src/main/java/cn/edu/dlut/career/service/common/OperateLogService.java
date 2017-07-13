package cn.edu.dlut.career.service.common;

import cn.edu.dlut.career.domain.common.OperateLog;

import javax.servlet.http.HttpServletRequest;

/**
 * Description 操作日志command 接口
 * Created by HealerJean
 * DATE   2017/5/19 15:09.
 */
public interface OperateLogService {

    /**
    * @Description 操作日志保存
    * @Author HealerJean
    * @CreateDate 2017/5/19 15:26
    */
    void addOptLog(OperateLog operateLog,String operateString);
}
