package cn.edu.dlut.career.controller.common;

import cn.edu.dlut.career.service.common.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author wangyj.
 * @Date 2017/5/16  11:10.
 */
@RestController
public class DispatchController {

    @Autowired
    private DispatchService dispatchService;

    @GetMapping("/common/dispatches")
    public List<Map<String,Object>> getAreaNameList(String areaName, Pageable pageable){
        List<Map<String,Object>> list = dispatchService.getAreaNameList(areaName,pageable);
        return list;
    }
}
