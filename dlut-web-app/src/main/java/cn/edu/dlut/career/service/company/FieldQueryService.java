package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.Field;
import cn.edu.dlut.career.dto.company.FieldDTO;
import cn.edu.dlut.career.dto.company.FieldDTO2;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/5/26.
 */
public interface FieldQueryService {

    /**
     * @author wei  2017/5/27
     * @method
     * @param  fieldSize  场地规模
     * @param  fieldRequire 场地类型
     * @param  fieldUsing 场地用途
     * @param  auditStatus 审核状态
     * @param  startDate 开始日期
     * @return List<FieldDTO>
     * @description 学校动态查询场地租用信息
     */
    List<FieldDTO2> findDynamic(String fieldSize, String fieldRequire, String fieldUsing, String auditStatus, String startDate, String recName);

    /**
     * 根据Id查询产地
     * @param id
     */
    Field findOne(UUID id);

    /**
     * 公司动态查询场地租用
     * @param fieldSize
     * @param fieldRequire
     * @param fieldUsing
     * @param auditStatus
     * @param startDate
     * @param id
     * @return
     */
    List<FieldDTO> findDynamic(String fieldSize, String fieldRequire, String fieldUsing, String auditStatus, String startDate, UUID id);
}
