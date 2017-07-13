package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.domain.school.QuestionnaireStatInfo;
import cn.edu.dlut.career.repository.school.QuestionnaireStatInfoRespository;
import cn.edu.dlut.career.service.school.QuestionnaireStatInfoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/5/5.
 * 问卷统计信息表 服务层
 */
@Service
@Transactional(readOnly = true)
public class QueStatInfoQueryServiceImpl implements QuestionnaireStatInfoQueryService{
    @Autowired
    QuestionnaireStatInfoRespository questionnaireStatInfoRespository;
    @Override
    public QuestionnaireStatInfo findById(UUID id) {
        return questionnaireStatInfoRespository.findById(id);
    }

    @Override
    public List<QuestionnaireStatInfo> findAllQuestionnaireStatInfo() {
        return questionnaireStatInfoRespository.findAll();
    }
}
