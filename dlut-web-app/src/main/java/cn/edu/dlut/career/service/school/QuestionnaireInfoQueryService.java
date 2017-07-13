package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.domain.school.QuestionnaireInfo;

import java.util.List;
import java.util.UUID;

/**
 * 问卷信息表  服务层接口
 * Created by 史念念 on 2017/5/5.
 */
public interface QuestionnaireInfoQueryService {
    //根据id查询
    QuestionnaireInfo findById(UUID id);

    //查询所有的数据
    List<QuestionnaireInfo> findAllQuestionnaireInfo();
}
