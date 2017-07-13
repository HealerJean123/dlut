package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.domain.school.QuestionnaireStatInfo;
import cn.edu.dlut.career.repository.school.QuestionnaireStatInfoRespository;
import cn.edu.dlut.career.service.school.QuestionnaireStatInfoCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 问卷统计信息表 服务层
 * Created by HealerJean on 2017/4/6.
 */
@Service
@Transactional
public class QueStatInfoCommandServiceImpl implements QuestionnaireStatInfoCommandService {
    @Autowired
    QuestionnaireStatInfoRespository questionnaireStatInfoRespository;
    @Override
    public QuestionnaireStatInfo saveQuestionnaireStatInfo(QuestionnaireStatInfo questionnaireStatInfo) {

        return questionnaireStatInfoRespository.save(questionnaireStatInfo);
    }

    @Override
    public QuestionnaireStatInfo updateQuestionnaireStatInfo(QuestionnaireStatInfo questionnaireStatInfo) {
        return questionnaireStatInfoRespository.save(questionnaireStatInfo);
    }

    @Override
    public String deleteQuestionnaireStatInfo(UUID id) {
        try {
            questionnaireStatInfoRespository.delete(id);
            return  "ok";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
