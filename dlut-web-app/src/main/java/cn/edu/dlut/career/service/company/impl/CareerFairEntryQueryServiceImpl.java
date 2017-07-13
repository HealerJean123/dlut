package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.CarrerFairEntry;
import cn.edu.dlut.career.domain.company.InvoiceInfo;
import cn.edu.dlut.career.dto.company.JobFairDTO;
import cn.edu.dlut.career.repository.company.CarrerFairEntryRepository;
import cn.edu.dlut.career.repository.company.JobFairReposiroty;
import cn.edu.dlut.career.service.company.CareerFairEntryQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.poi.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会预约申请 服务层
 * Created by HealerJean on 2017/4/6.
 */
@Service
@Transactional(readOnly = true)
public class CareerFairEntryQueryServiceImpl implements CareerFairEntryQueryService {

    @Autowired
    private JobFairReposiroty jobFairReposiroty;

    /**
    * @Description 企业进入招聘预约查询教师发布信息的页面
    * @Author HealerJean
    * @CreateDate 2017/5/27 17:24
    */
    @GetMapping("/company/recruitmentOrder.html")
    public String careerFairOrderHtml(){
        return "companyHTML/recruitmentOrder";
    }

//    /**
//    * @Description 企业端查询教师发布的 招聘会信息 进行分页方法
//    * @Author HealerJean
//    * @CreateDate 2017/5/27 10:32
//    *  @Param name　招聘会名称
//     */
//    @Override
//    public Page<JobFairDTO> getComJobFairDTOPage(String name, String location, String fairStartFrom, String fairStartTo,String auditStatus ,Pageable pageable) {
//        LocalDateTime fairStartFromTime = null;
//        LocalDateTime fairStartToTime = null;
//       if (!StringUtils.isEmpty(fairStartFrom)) {
//           fairStartFromTime = this.getLocalDateTime(fairStartFrom);
//       }else if (!StringUtils.isEmpty(fairStartFrom)){
//            fairStartToTime = this.getLocalDateTime(fairStartTo);
//       }
//        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
//        Page<JobFairDTO> jobFairDTOS = jobFairReposiroty.getComJobFairDTOPage(name, location, fairStartFromTime, fairStartToTime,auditStatus,userPrincipal.getId(),pageable);
//        return jobFairDTOS;
//    }

    /**
     * @Description 目前传来的日期的格式是 yyyy-MM-dd HH:mm:ss ，此时不能转化LocalDateTime，所以需要转化
     * @Author HealerJean
     * @CreateDate 2017/5/25 15:13
     */
    private LocalDateTime getLocalDateTime(String strDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(strDate);
            Instant instant = Instant.ofEpochMilli(date.getTime());
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            return localDateTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * @Autor 史念念 2017/6/6
     * @Description 学生端 查询组团/大招信息
     * @param fairType 招聘会类型
     * @param fairStartTime 开始时间
     * @param fairEndTime 结束时间
     * @return
     */
    @Override
    public Page<JobFairDTO> stuFindJobFair(String fairType, LocalDateTime fairStartTime, LocalDateTime fairEndTime,Pageable pageable) {
        Page<JobFairDTO> jobFairDTOS = jobFairReposiroty.stuFindJobFair(fairType,fairStartTime,fairEndTime,pageable);

        return jobFairDTOS;
    }
}
