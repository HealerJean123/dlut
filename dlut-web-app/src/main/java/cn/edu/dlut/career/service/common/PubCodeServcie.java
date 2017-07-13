package cn.edu.dlut.career.service.common;

import cn.edu.dlut.career.domain.common.PubCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/4/24  10:19.
 */

public interface PubCodeServcie {


    PubCode addCodeMapping(PubCode pubCode);

    //根据类型进行查询
    Page<PubCode> findByType(String type, Pageable pageable);

    // 根据id删除
    String deletCodeById(UUID id);

    PubCode findById(UUID id);

    String[] findTypeArray();
}
