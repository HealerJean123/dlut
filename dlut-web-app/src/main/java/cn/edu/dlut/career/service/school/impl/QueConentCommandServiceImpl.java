package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.domain.school.QuestionnaireContent;
import cn.edu.dlut.career.repository.school.QuestionnaireContentRepository;
import cn.edu.dlut.career.service.school.QuestionnaireContentCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by 史念念 on 2017/5/5.
 * 问卷问题内容 实现类 增删改
 */
@Service
@Transactional
public class QueConentCommandServiceImpl implements QuestionnaireContentCommandService{
    @Autowired
    QuestionnaireContentRepository questionnaireContentRepository;

    @Override
    public QuestionnaireContent saveQuestionnaireContent(QuestionnaireContent questionnaireContent) {
        return questionnaireContentRepository.save(questionnaireContent);
    }

    @Override
    public QuestionnaireContent updateQuestionnaireContent(QuestionnaireContent questionnaireContent) {
        return questionnaireContentRepository.save(questionnaireContent);
    }

    @Override
    public String deleteQuestionnaireContent(UUID id) {
        try {
            questionnaireContentRepository.delete(id);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
