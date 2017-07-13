package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.domain.school.QuestionnaireStatInfo;

import java.util.List;
import java.util.UUID;

/**
 * 问卷统计信息表 接口层
 * Created by 史念念 on 2017/5/5.
 */
public interface QuestionnaireStatInfoQueryService {
    //根据id查询
    QuestionnaireStatInfo findById(UUID id);

    //查询所有的数据
    List<QuestionnaireStatInfo> findAllQuestionnaireStatInfo();
}
