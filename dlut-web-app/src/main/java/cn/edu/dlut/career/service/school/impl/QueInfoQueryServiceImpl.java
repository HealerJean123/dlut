package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.domain.school.QuestionnaireInfo;
import cn.edu.dlut.career.repository.school.QuestionnaireInfoRepository;
import cn.edu.dlut.career.service.school.QuestionnaireInfoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/5/5.
 *  问卷信息表  服务层 查询
 */
@Service
@Transactional(readOnly = true)
public class QueInfoQueryServiceImpl implements QuestionnaireInfoQueryService{

    @Autowired
    QuestionnaireInfoRepository questionnaireInfoRepository;

    @Override
    public QuestionnaireInfo findById(UUID id) {
        return questionnaireInfoRepository.findById(id);
    }

    @Override
    public List<QuestionnaireInfo> findAllQuestionnaireInfo() {
        return questionnaireInfoRepository.findAll();
    }
}
