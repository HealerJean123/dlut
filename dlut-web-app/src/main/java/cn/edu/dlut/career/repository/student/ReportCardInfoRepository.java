package cn.edu.dlut.career.repository.student;

import cn.edu.dlut.career.domain.student.ReportCardInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 报到证签发信息数据操作层
 * Created by HealerJean on 2017/4/14.
 */
@Transactional
@Repository
public interface  ReportCardInfoRepository extends CrudRepository<ReportCardInfo,UUID>{

    List<ReportCardInfo> findAll();

}
