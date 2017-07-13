package cn.edu.dlut.career.controller.common;

import cn.edu.dlut.career.domain.common.PubCode;
import cn.edu.dlut.career.service.common.PubCodeServcie;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/4/25  9:20.
 */
@Controller
public class PubCodeController {

    @Autowired
    private PubCodeServcie pubCodeServcie;
    Logger logger = LoggerFactory.getLogger(PubCodeServcie.class);
    //代码表展示页面
    @GetMapping("/teacher/listCode.html")
    public String listCode(){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal() ;
        if ("SCHADMIN".equals(userPrincipal.getPrincipal())||"SCHOOL".equals(userPrincipal.getPrincipal())) {
            return "teacherHTML/code";
        }else
        {
            return "teacherHTML/tea_index";
        }
    }

    /**
     * 码表添加
     */
    @PostMapping(value = "/teacher/addCode")
    @ResponseBody
    public ModelAndView addCodeMapping(PubCode pubCode){
        ModelAndView mav = new ModelAndView("teacherHTML/code");
        PubCode pubCode1 = pubCodeServcie.addCodeMapping(pubCode);
        if (pubCode1 !=null){
            mav.addObject("listType", pubCode.getType());
        }else {
            mav.addObject("listType", pubCode.getType());
            mav.addObject("errorMsg","请正确输入或已经存在");
        }
        return mav;
    }

    //根据类型异步查询 码表信息
    @ResponseBody
    @GetMapping("/teacher/listCodesByType")
    public Page<PubCode> listCodesByType(String codeType, Pageable pageable){
        Page<PubCode> codeMappings = pubCodeServcie.findByType(codeType,pageable);
        return  codeMappings;
    }

    //根据id删除码表
    @GetMapping("/teacher/deleteCodesById")
    @ResponseBody
    public String deleteCodesById(@RequestParam UUID id){
        String deleteStatus = pubCodeServcie.deletCodeById(id);
        String statusInfo = null;
        if (deleteStatus!=null){
            if (deleteStatus.equals("0")){ //类型为空 返回0
                statusInfo = "0";
            }else {
                statusInfo = "1"; //仅仅是删除，返回1
            }
        }else {
            statusInfo = "删除失败";
        }
            return  statusInfo;
    }

    /**
     * 查看所有的码表的类型
     * @return
     */
    @ResponseBody
    @GetMapping("/teacher/listTypeArray")
    public String[] listTypeArray(){
        return  pubCodeServcie.findTypeArray();
    }



}
