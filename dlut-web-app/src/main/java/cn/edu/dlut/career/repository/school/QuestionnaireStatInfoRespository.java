package cn.edu.dlut.career.repository.school;

import cn.edu.dlut.career.domain.school.QuestionnaireInfo;
import cn.edu.dlut.career.domain.school.QuestionnaireStatInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * 问卷统计信息表 数据操作层
 * Created by HealerJean on 2017/4/6.
 */
@Repository
public interface QuestionnaireStatInfoRespository extends CrudRepository<QuestionnaireStatInfo,UUID>{
    //查找全部信息
    List<QuestionnaireStatInfo> findAll();

    //根据id进行查找
    QuestionnaireStatInfo findById(UUID id);
}
