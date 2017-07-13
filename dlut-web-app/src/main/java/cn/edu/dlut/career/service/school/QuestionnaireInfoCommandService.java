package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.domain.school.QuestionnaireInfo;

import java.util.List;
import java.util.UUID;

/**
 * 问卷信息表  服务层接口
 * Created by HealerJean on 2017/4/6.
 */
public interface QuestionnaireInfoCommandService {
    //保存，添加
    QuestionnaireInfo saveQuestionnaireInfo(QuestionnaireInfo questionnaireInfo) ;

    // 更新
    QuestionnaireInfo updateQuestionnaireInfo(QuestionnaireInfo questionnaireInfo);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteQuestionnaireInfo(UUID id);

}
