package cn.edu.dlut.career.repository.common;

import cn.edu.dlut.career.domain.common.DispatchCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by HealerJean on 2017/5/10.
 */
@Repository
public interface DispatchCodeRespository extends CrudRepository<DispatchCode ,String>{

    /**
     * @Description 根据生源地县、区获取派遣地信息.
     * @Author  wangyj
     * @CreateDate 2017/5/11 13:55
     * @Param
     * @Return
     */
    @Query(value = "from DispatchCode where areaName like %?1% ")
    DispatchCode findByHomeland(String homeland);

    /**
     * @Description 根据输入生源地模糊匹配出生源地列表.
     * @Author  wangyj
     * @CreateDate 2017/5/16 10:52
     * @Param
     * @Return
     */
    @Query(value="select new map(d.areaCode as id,d.areaName as text) from DispatchCode d where d.areaName like %?1% or ?1=null ")
    List<Map<String ,Object>> getAreaNameList(String areaName,Pageable pageable);


    @Query(value = "from DispatchCode where areaCode =?1")
    DispatchCode findByHomelandCode(String homelandCode);

}
