package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.BlankProtocol;
import cn.edu.dlut.career.domain.student.StudentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/25.
 *
 * 空白协议书 服务层接口
 */
public interface BlankProtocolCommandService {

    /**
     * 添加空白协议书
     * @param student 学生实体类
     * @param applicationReason 申请理由
     * @param applicationReasonRemarks 申请理由备注
     * @return
     */
    String saveBPro(StudentInfo student, String applicationReason, String applicationReasonRemarks);




    /**
     * 教师端 审核状态修改
     * @param id 空白协议书申请表id
     * @param departAuditStatus 院审核状态
     * @param departAuditor 院审核人
     * @param departNoPassReason 院审核不通过原因
     * @param schAuditStatus 校审核状态
     * @param schAuditor 校审核人
     * @param schNoPassReason 校审核不通过原因
     * @return
     */
    String updateAudit(UUID id, String departAuditStatus, String departAuditor, String departNoPassReason, String schAuditStatus, String schAuditor, String schNoPassReason);
}
