package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.dto.school.AcademicIndexDTO;
import cn.edu.dlut.career.repository.company.RecOfferRepository;
import cn.edu.dlut.career.repository.student.*;
import cn.edu.dlut.career.service.school.AcademicIndexQueryService;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 史念念 on 2017/4/28.
 *
 * 院系老师 首页信息 服务层实现类
 */
@Service
public class AcademicIndexQueryServiceImpl implements AcademicIndexQueryService {
    @Autowired
    private StudentInfoRepository studentInfoRepository;
    @Autowired
    private GraduateDestinationRepostiory graduateDestinationRepostiory;
    @Autowired
    private RecOfferRepository recOfferRepository;
    @Autowired
    private ViolateApplicationRepository violateApplicationRepository;
    @Autowired
    private BlankProtocolRepository blankProtocolRepository;
    @Autowired
    private ReassignApplicationRespository reassignApplicationRespository;

    /**
     * 查询首页所需信息
     * @return
     */
    @Override
    public AcademicIndexDTO findAll(String departmentId,String graduateDate) {
        AcademicIndexDTO aid = new AcademicIndexDTO();
        Map<String ,Integer> todoList = new HashMap<>();//待处理事项统计

        //查询本院系 共有多少学生
        int stuTotalNum = studentInfoRepository.findStuTotalNum(departmentId,graduateDate);
        //查询本院系 就业学生人数
        int stuEmpNum = graduateDestinationRepostiory.findStuEmpNum(departmentId,graduateDate);
        DecimalFormat    df   = new DecimalFormat("######0.0000");
        double empRate = (double)stuEmpNum/stuTotalNum;
        //院系就业率统计
        double emploRate = Double.parseDouble(df.format(empRate));


        //就业去向统计
        List<Map<String,Object>> emploDestination = graduateDestinationRepostiory.findEmpDesList(departmentId,graduateDate);
        for (Map<String,Object> map :emploDestination){
            //把编码映射为其对应的字段
            map.put("name", PubCodeUtil.getName("graduateTo",map.get("name").toString()));
        }

        //就业地区分布统计
        List<Map<String,Object>> emploArea = graduateDestinationRepostiory.findEmpAreaList(departmentId,graduateDate);
        for (Map<String,Object> map :emploArea){
            //把编码映射为其对应的字段
            map.put("name", PubCodeUtil.getName("province",map.get("name").toString()));
        }

        //就业单位性质统计
        List<Map<String,Object>> emploNature = graduateDestinationRepostiory.findEmpNatureList(departmentId,graduateDate);
        for (Map<String,Object> map :emploNature){
            //把编码映射为其对应的字段
            map.put("name", PubCodeUtil.getName("nature",map.get("name").toString()));
        }

        //就业行业分布统计
        List<Map<String,Object>> emploCategory = graduateDestinationRepostiory.findEmpCategoryList(departmentId,graduateDate);
        for (Map<String,Object> map :emploCategory){
            //把编码映射为其对应的字段
            map.put("name", PubCodeUtil.getName("industry",map.get("name").toString()));
        }

        //待处理事项
        //学籍带审核数量
        int stuInfoAuditNum = studentInfoRepository.findStuInfoAuditNum(departmentId,graduateDate);
        //签约待审核数量
        int signAuditNum = recOfferRepository.findSignAuditNum(departmentId,graduateDate);
        //违约待审核数量
        int violateAuditNum = violateApplicationRepository.findViolateAuditNum(departmentId,graduateDate);
        //空白协议书申请待审核数量
        int blankAuditNum = blankProtocolRepository.findBlankAuditNum(departmentId,graduateDate);
        todoList.put("stuInfoAuditNum",stuInfoAuditNum);
        todoList.put("signAuditNum",signAuditNum);
        todoList.put("violateAuditNum",violateAuditNum);
        todoList.put("blankAuditNum",blankAuditNum);

        aid.setCountAll(stuTotalNum);
        aid.setEmploRate(emploRate);
        aid.setEmploDestination(emploDestination);
        aid.setEmploArea(emploArea);
        aid.setEmploNature(emploNature);
        aid.setEmploCategory(emploCategory);
        aid.setTodoList(todoList);

        return aid;
    }
}
