package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.domain.school.QuestionnaireContent;
import cn.edu.dlut.career.repository.school.QuestionnaireContentRepository;
import cn.edu.dlut.career.service.school.QuestionnaireContentQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/5/5.
 * 问卷问题内容 实现类 查询
 */
@Service
@Transactional(readOnly = true)
public class QueConentQueryServiceImpl implements QuestionnaireContentQueryService{

    @Autowired
    QuestionnaireContentRepository questionnaireContentRepository;

    @Override
    public QuestionnaireContent findById(UUID id) {
        return questionnaireContentRepository.findById(id);
    }

    @Override
    public List<QuestionnaireContent> findAllQuestionnaireContent() {
        return questionnaireContentRepository.findAll();
    }
}
