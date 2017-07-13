package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.Field;
import cn.edu.dlut.career.dto.company.FieldDTO;
import cn.edu.dlut.career.dto.company.FieldDTO2;
import cn.edu.dlut.career.repository.company.FieldRepository;
import cn.edu.dlut.career.service.company.FieldQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/5/26.
 */
@Service
@Transactional(readOnly = true)
public class FieldQueryServiceImpl implements FieldQueryService {
    @Autowired
    private FieldRepository fieldRepository;
    /**
     * @author wei  2017/5/27
     * @method
     * @param  fieldSize  场地规模
     * @param  fieldRequire 场地类型
     * @param  fieldUsing 场地用途
     * @param  auditStatus 审核状态
     * @param  startDate 开始日期
     * @return List<FieldDTO>
     * @description 动态查询场地租用信息
     */
    @Override
    public List<FieldDTO2> findDynamic(String fieldSize, String fieldRequire, String fieldUsing, String auditStatus, String startDate, String recName) {
        List<FieldDTO2> list = fieldRepository.findDynamic(fieldSize,fieldRequire,fieldUsing,auditStatus,startDate,recName);
        return list;
    }

    @Override
    /**
     * 根据Id查询场地
     * @param id
     */
    public Field findOne(UUID id) {
        Field field = fieldRepository.findOne(id);
        return field;
    }

    @Override
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
    public List<FieldDTO> findDynamic(String fieldSize, String fieldRequire, String fieldUsing, String auditStatus, String startDate, UUID id) {
        return fieldRepository.findDynamic(fieldSize,fieldRequire,fieldUsing,auditStatus,startDate,id);
    }
}
