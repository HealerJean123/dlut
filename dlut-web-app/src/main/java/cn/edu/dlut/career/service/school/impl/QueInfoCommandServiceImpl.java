package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.domain.school.QuestionnaireInfo;
import cn.edu.dlut.career.repository.school.QuestionnaireInfoRepository;
import cn.edu.dlut.career.service.school.QuestionnaireInfoCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 问卷信息表  服务层 增删改
 * Created by HealerJean on 2017/4/6.
 */
@Service
@Transactional
public class QueInfoCommandServiceImpl implements QuestionnaireInfoCommandService {
    @Autowired
    QuestionnaireInfoRepository questionnaireInfoRepository;
    @Override
    public QuestionnaireInfo saveQuestionnaireInfo(QuestionnaireInfo questionnaireInfo) {
        return questionnaireInfoRepository.save(questionnaireInfo);
    }

    @Override
    public QuestionnaireInfo updateQuestionnaireInfo(QuestionnaireInfo questionnaireInfo) {
        return questionnaireInfoRepository.save(questionnaireInfo);
    }

    @Override
    public String deleteQuestionnaireInfo(UUID id) {
        try {
            questionnaireInfoRepository .delete(id);
            return  "ok";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
