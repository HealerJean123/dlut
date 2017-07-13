package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.domain.school.QuestionnaireContent;

import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/5/5.
 *
 * 问卷问题内容 接口层
 * 查询
 */
public interface QuestionnaireContentQueryService {

    //根据id查询
    QuestionnaireContent findById(UUID id);

    //查询所有的数据
    List<QuestionnaireContent> findAllQuestionnaireContent();
}
