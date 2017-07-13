package cn.edu.dlut.career.controller.company;

import cn.edu.dlut.career.domain.company.CarrerFairEntry;
import cn.edu.dlut.career.dto.company.JobFairDTO;
import cn.edu.dlut.career.service.company.CareerFairEntryCommandService;
import cn.edu.dlut.career.service.company.CareerFairEntryQueryService;
import cn.edu.dlut.career.service.company.JobFairQueryService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 双选会，招聘会 这个和 JobFair 教师端招聘会信息 有所联系
 * Created by HealerJean on 2017/4/7.
 */

@Controller
@Transactional
public class CareerFairEntryController {
    private  Logger logger = LoggerFactory.getLogger(CareerFairEntryController.class);
    @Autowired
    private  CareerFairEntryQueryService careerFairEntryQueryService;

    /**
     * @Autor 史念念 2017/6/6
     * @Description 学生端跳转到招聘会列表页
     */
    @GetMapping("/student/stuCareerFair")
    public String stuCareerFair(){
        return "studentHTML/stu_careerFair";
    }

    /**
     * @Autor 史念念 2017/6/6
     * @Description 学生端 查询组团/大招
     * @param fairType 招聘会类型
     * @param fairTime 时间
     * @param pageable
     * @return
     */
    @GetMapping("/student/stuFindJobFair")
    public Page<JobFairDTO> stuFindJobFair(String fairType, String fairTime, Pageable pageable){
        //准备数据
        LocalDateTime fairEndTime=null;
        LocalDateTime fairStartTime=null;
        if ("00".equals(fairTime)) {
            fairEndTime = null;
            fairStartTime = null;
        } else if ("01".equals(fairTime)) {
            fairEndTime = LocalDateTime.now().plusWeeks(1);
            fairStartTime = LocalDateTime.now();
        } else if ("02".equals(fairTime)) {
            fairEndTime = LocalDateTime.now().plusMonths(1);
            fairStartTime = LocalDateTime.now();
        } else if ("03".equals(fairTime)) {
            fairEndTime = LocalDateTime.now();
            fairStartTime = null;
        }

        if(fairType.equals("0")){
            fairType = null;
        }

        //调用查询方法
        Page<JobFairDTO> jobFairDTOS = careerFairEntryQueryService.stuFindJobFair(fairType,fairStartTime,fairEndTime,pageable);

        return jobFairDTOS;
    }


    /**
     * @Description 企业端查询教师发布的 招聘会信息 进行分页
     * @Author HealerJean
     * @CreateDate 2017/5/27 10:32
     *  @Param name　招聘会名称
     */
//   @GetMapping("/company/jobFairPage")
//    @ResponseBody
//    public Page<JobFairDTO> getComJobFairDTO(String name, String location, String fairStartFrom, String fairStartTo, String auditStatus, Pageable pageable){
//        Page<JobFairDTO> jobFairDTOS =careerFairEntryQueryService.getComJobFairDTOPage(name, location, fairStartFrom, fairStartTo,auditStatus,pageable);
//       return jobFairDTOS;
//    }
}
