package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.CarrerFairEntry;
import cn.edu.dlut.career.domain.company.InvoiceInfo;
import cn.edu.dlut.career.dto.company.JobFairDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会预约申请 接口层
 * Created by HealerJean on 2017/4/6.
 */
public interface CareerFairEntryQueryService {

    /**
     * @Description 招聘会信息分页查询
     * @Author HealerJean
     * @CreateDate 2017/5/27 16:44
     */
    // Page<JobFairDTO> getComJobFairDTOPage(String name, String location, String fairStartFrom, String FairStartTo,String auditStatus,Pageable pageable);

    /**
     * @Autor 史念念 2017/6/6
     * @Description 学生端 查询组团/大招招聘会信息
     * @param fairType 招聘会类型
     * @param fairStartTime 开始时间
     * @param fairEndTime 结束时间
     * @return
     */
    Page<JobFairDTO> stuFindJobFair(String fairType, LocalDateTime fairStartTime, LocalDateTime fairEndTime,Pageable pageable);
}
