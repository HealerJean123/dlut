package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.dto.school.SchTeacherStatDTO;
import cn.edu.dlut.career.repository.company.RecOfferRepository;
import cn.edu.dlut.career.repository.student.BlankProtocolRepository;
import cn.edu.dlut.career.repository.student.GraduateDestinationRepostiory;
import cn.edu.dlut.career.repository.student.ReassignApplicationRespository;
import cn.edu.dlut.career.repository.student.ViolateApplicationRepository;
import cn.edu.dlut.career.service.school.SchTeacherStatQueryService;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教师首页就业信息统计
 * @Author wangyj.
 * @Date 2017/4/28  9:37.
 */
@Service
@Transactional(readOnly = true)
public class SchTeacherStatQueryServiceImpl implements SchTeacherStatQueryService {

    @Autowired
    private GraduateDestinationRepostiory destinationRepostiory;

    @Autowired
    private RecOfferRepository recOfferRepository;

    @Autowired
    private ViolateApplicationRepository violateApplicationRepository;

    @Autowired
    private BlankProtocolRepository blankProtocolRepository;

    @Autowired
    private ReassignApplicationRespository reassignApplicationRespository;

  /**
   * @Description 获取教师首页就业信息.
   * @Author  wangyj
   * @CreateDate 2017/4/28 9:55
   * @Param
   * @Return
   */
    public SchTeacherStatDTO getStat(String graduateDate){

        SchTeacherStatDTO schTeacherStatDTO = new SchTeacherStatDTO();
        //各学院已就业人数统计
        List<Map<String,Object>> academyEmploList = destinationRepostiory.getAcademyEmplo(graduateDate);
        Map<String ,Integer> academyEmploMap=new HashMap<>();
        for(Map<String,Object> map:academyEmploList){
            academyEmploMap.put(map.get("department").toString(),Integer.parseInt(map.get("count").toString()));
        }
        schTeacherStatDTO.setAcademyEmplo(academyEmploMap);

        //各学院已总人数统计
        List<Map<String,Object>> academyTotalList = destinationRepostiory.getAcademyTotal(graduateDate);
        Map<String ,Integer> academyTotalMap=new HashMap<>();
        for(Map<String,Object> map:academyTotalList){
            academyTotalMap.put(map.get("department").toString(),Integer.parseInt(map.get("count").toString()));
        }
        schTeacherStatDTO.setAcademyTotal(academyTotalMap);

        //当届毕业生总数
        int allCount = destinationRepostiory.countAllGraduate(graduateDate);
        schTeacherStatDTO.setCountAll(allCount);

        //总体就业率
        int emploCount= destinationRepostiory.countEmplo(graduateDate);
        if(allCount!=0){
            schTeacherStatDTO.setEmploRate((double)emploCount/allCount);
        }

        //就业地区分布统计
        List<Map<String,Object>> emplAreaList = destinationRepostiory.getEmploArea(graduateDate);
        for (Map<String,Object> map :emplAreaList){
            //把编码映射为其对应的字段
            if (map.get("name")!=null){
                map.put("name",PubCodeUtil.getName("province",map.get("name").toString()));
            }
        }
        schTeacherStatDTO.setEmploArea(emplAreaList);
        //就业性质分布统计
        List<Map<String,Object>> emploNatureList = destinationRepostiory.getEmploNature(graduateDate);
        for (Map<String,Object> map :emploNatureList){
            //把编码映射为其对应的字段
            if(map.get("name")!=null){
                map.put("name",PubCodeUtil.getName("nature",map.get("name").toString()));
            }
        }
        schTeacherStatDTO.setEmploNature(emploNatureList);

        //就业行业分布统计
        List<Map<String,Object>> emploIndustryList = destinationRepostiory.getEmploIndustry(graduateDate);
        for (Map<String,Object> map :emploIndustryList){
            //把编码映射为其对应的字段
            if(map.get("name")!=null){
                map.put("name",PubCodeUtil.getName("industry",map.get("name").toString()));
            }
        }
        schTeacherStatDTO.setEmploCategory(emploIndustryList);

        //待处理事件统计
        Map<String ,Integer> todolistMap = new HashMap<>();
        //签约申请总数
        int signCount = recOfferRepository.countSign(graduateDate);
        todolistMap.put("signCount",signCount);
        //违约申请总数
        int unsignCount = violateApplicationRepository.countUnsign(graduateDate);
        todolistMap.put("unsignCount",unsignCount);
        //空白协议书申请总数
        int blankCount = blankProtocolRepository.coutApplay(graduateDate);
        todolistMap.put("blankCount",blankCount);
        //改派审核总数
        int ressignCount = reassignApplicationRespository.ressignCount(graduateDate);
        todolistMap.put("ressignCount",ressignCount);
        // TODO: 2017/4/28 单位审核总数  、职位审核总数
        schTeacherStatDTO.setTodoList(todolistMap);
        return schTeacherStatDTO;
    }
}
