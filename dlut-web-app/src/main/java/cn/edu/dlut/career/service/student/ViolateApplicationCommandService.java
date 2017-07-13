package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.ViolateApplication;
import cn.edu.dlut.career.dto.student.ViolateApplicationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 违约申请 服务层接口
 * Created by HealerJean on 2017/4/13.
 */
public interface ViolateApplicationCommandService {
    //保存，添加
    ViolateApplication saveViolateApply(ViolateApplication violateApplication) ;

    // 更新
    ViolateApplication updateViolateApply(ViolateApplication violateApplication);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteViolateApply(UUID id);


    /**
     * 修改违约申请审核信息
     * @param id 违约申请id
     * @param departAuditStatus 院级审核状态
     * @param departNoPassReason 院级审核不通过原因
     * @param departAuditor 院级审核人
     * @param schAuditStatus 校级审核状态
     * @param schNoPassReason 校级审核不通过原因
     * @param schAuditor 校级审核人
     * @return
     */

    String updateAudit(UUID id,
                       String departAuditStatus,
                       String departNoPassReason,
                       String departAuditor,
                       String schAuditStatus,
                       String schNoPassReason,
                       String schAuditor);
}
