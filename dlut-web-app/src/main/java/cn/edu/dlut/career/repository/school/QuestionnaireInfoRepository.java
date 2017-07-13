package cn.edu.dlut.career.repository.school;

import cn.edu.dlut.career.domain.school.QuestionnaireInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * 问卷信息表  数据操作层
 * Created by HealerJean on 2017/4/6.
 */
@Repository
public interface QuestionnaireInfoRepository extends CrudRepository<QuestionnaireInfo,UUID>{
    //查找全部信息
    List<QuestionnaireInfo> findAll();

    //根据id进行查找
    QuestionnaireInfo findById(UUID id);
}
