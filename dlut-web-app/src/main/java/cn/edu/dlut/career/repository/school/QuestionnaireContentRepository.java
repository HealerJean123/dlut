package cn.edu.dlut.career.repository.school;

import cn.edu.dlut.career.domain.school.QuestionnaireContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * 问卷问题内容 数据操作层
 * Created by HealerJean on 2017/4/6.
 */
@Repository
public interface QuestionnaireContentRepository extends CrudRepository<QuestionnaireContent ,UUID>{
    //查找全部信息
    List<QuestionnaireContent> findAll();

    //根据id进行查找
    QuestionnaireContent findById(UUID id);}
