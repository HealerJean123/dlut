package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.Field;
import cn.edu.dlut.career.dto.company.FieldDTOJ;

/**
 * Description
 * Created by HealerJean
 * DATE   2017/5/26 18:29.
 */
public interface FieldCommandService {
    Field addField(Field field);

    void audit(FieldDTOJ field);
}
