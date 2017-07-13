package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.dto.company.JobFairDTO;
import cn.edu.dlut.career.dto.company.RecCareerEventDTO2;
import cn.edu.dlut.career.repository.company.CarrerFairEntryRepository;
import cn.edu.dlut.career.repository.company.JobFairReposiroty;
import cn.edu.dlut.career.repository.company.RecCareerEventRepository;
import cn.edu.dlut.career.repository.company.RecJobPositionRepository;
import cn.edu.dlut.career.service.student.RecruitmentCommandService;
import cn.edu.dlut.career.service.student.RecruitmentQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 史念念 on 2017/4/14.
 *
 * 招聘会查询 服务层实现类
 */
@Service
@Transactional(readOnly = true)
public class RecruitmentQueryServiceImpl implements RecruitmentQueryService {
    @Autowired
    private JobFairReposiroty jobFairReposiroty;
    @Autowired
    private RecCareerEventRepository recCareerEventRepository;


//    @Override
//    public Map<String, Page> recruitQuery(String type, LocalDateTime fairStartTime, LocalDateTime fairEndTime, Pageable pageable) {
//        Map<String,Page> map = new HashMap<>();
//
//        //组团，大招 ：根据招聘会类型、开始结束时间查找招聘会
//        Page<JobFairDTO> jobFairDTOS = jobFairReposiroty.findByTypeAndFairEndTime(type,fairStartTime,fairEndTime,pageable);
//
//        //将jobFairDtos放入到map中
//        map.put("jobFairDTOS",jobFairDTOS);
//
//        //专场：输入类型为空 或是字符串3时查找专场招聘会
//        if(type==null||type.equals("")||type.equals("3")){
//            //查找专场招聘会
//            Page<RecCareerEventDTO2> recCareerEventDTO2s = recCareerEventRepository.findByFairEndTime(fairStartTime,fairEndTime,pageable);
//            map.put("recCareerEventDTO2s",recCareerEventDTO2s);
//        }
//        return map;
//    }
}
