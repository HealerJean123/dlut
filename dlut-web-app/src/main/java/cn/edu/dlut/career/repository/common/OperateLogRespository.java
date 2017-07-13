package cn.edu.dlut.career.repository.common;

import cn.edu.dlut.career.domain.common.OperateLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Description
 * Created by HealerJean
 * DATE   2017/5/19 15:05.
 */
@Repository
public interface OperateLogRespository extends CrudRepository<OperateLog,UUID> {


}
