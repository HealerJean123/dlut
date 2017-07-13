package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.Field;
import cn.edu.dlut.career.dto.company.FieldDTOJ;
import cn.edu.dlut.career.repository.company.FieldRepository;
import cn.edu.dlut.career.service.company.FieldCommandService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description
 * Created by HealerJean
 * DATE   2017/5/26 18:30.
 */
@Service
@Transactional
public class FieldCommandServiceImpl implements FieldCommandService{
    @Autowired
    private FieldRepository fieldRepository;
    /**
    * @Description 添加场地预约，在企业专场招聘中进行添加场地预约申请
    * @Author HealerJean
    * @CreateDate 2017/5/26 18:40
    */
    @Override
    public Field addField(Field field) {
        return fieldRepository.save(field);
    }

    @Override
    public void audit(FieldDTOJ fieldDTO) {
        Field field = fieldRepository.findOne(fieldDTO.getId());
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        field.setAuditStatus(fieldDTO.getAuditStatus());
        field.setAuditSuggest(fieldDTO.getAuditSuggest());
        field.setFieldAddress(fieldDTO.getFieldAddress());
        field.setReceiver(fieldDTO.getReceiver());
        field.setReceiverTel(fieldDTO.getReceiverTel());
        field.setAuditTime(fieldDTO.getAuditTime());
        field.setAuditor(user.getUserName());
        fieldRepository.save(field);
    }
}
