package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.domain.school.QuestionnaireContent;

import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/5/5.
 *
 * 问卷问题内容 接口层 增删改
 */
public interface QuestionnaireContentCommandService {
    //保存，添加
    QuestionnaireContent saveQuestionnaireContent(QuestionnaireContent questionnaireContent) ;

    // 更新
    QuestionnaireContent updateQuestionnaireContent(QuestionnaireContent questionnaireContent);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteQuestionnaireContent(UUID id);

}
