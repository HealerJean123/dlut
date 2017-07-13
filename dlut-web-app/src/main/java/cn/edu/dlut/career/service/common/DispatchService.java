package cn.edu.dlut.career.service.common;

import cn.edu.dlut.career.domain.common.DispatchCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @Author wangyj.
 * @Date 2017/5/16  10:54.
 */
public interface DispatchService {
    public List<Map<String ,Object>> getAreaNameList(String areaName,Pageable pageable);

    public DispatchCode findByHomelandCode(String homelandCode);
}
