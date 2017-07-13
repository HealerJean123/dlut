package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.ReassignApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * 改派申请 服务层接口
 * Created by HealerJean on 2017/4/13.
 */
public interface ReassignApplicationCommandService {
    //保存，添加
    ReassignApplication saveReassignApply(ReassignApplication reassignApplication) ;

    // 更新
    ReassignApplication updateReassignApply(ReassignApplication reassignApplication);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteReassignApply(UUID id);

    //教师更新单个学生的改派申请
    ReassignApplication updateReassAppAuditStatus(ReassignApplication reassignApplication);

    //批量审核改派申请
    int stuReassAppBatchAudit(UUID[] id);

    //通过单个学生的改派申请审核
    ReassignApplication stuReassAppAudit(UUID id);





    }
