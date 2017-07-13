package cn.edu.dlut.career.service.common.Impl;

import cn.edu.dlut.career.domain.common.DispatchCode;
import cn.edu.dlut.career.repository.common.DispatchCodeRespository;
import cn.edu.dlut.career.service.common.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author wangyj.
 * @Date 2017/5/16  10:54.
 */
@Service
@Transactional(readOnly = true)
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    private DispatchCodeRespository dispatchCodeRespository;

    /**
     * @Description 根据输入生源地模糊匹配出生源地列表.
     * @Author  wangyj
     * @CreateDate 2017/5/16 11:06
     * @Param
     * @Return
     */
    public List<Map<String ,Object>> getAreaNameList(String areaName,Pageable pageable){
        List<Map<String ,Object>> areaList = dispatchCodeRespository.getAreaNameList(areaName,pageable);
        return areaList;
    }
    /**
    * @Description 根据代码获取 地区值
    * @Author HealerJean
    * @CreateDate 2017/5/16 17:48
    */
    @Override
    public DispatchCode findByHomelandCode(String homelandCode) {
        return dispatchCodeRespository.findByHomelandCode(homelandCode);
    }
}
