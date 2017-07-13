package cn.edu.dlut.career.controller.company;


import cn.edu.dlut.career.domain.company.CompanyInfo;

import cn.edu.dlut.career.service.company.CompanyInfoQueryService;

import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 教师登录首页控制
 * Created by HealerJean on 2017/4/17.
 */
@Controller
public class CompanyHomeController {

    @Autowired
    private CompanyInfoQueryService companyInfoQueryService;
    @Autowired
    private HttpServletRequest request ;
    @GetMapping("/company/index.html")
    public ModelAndView companyHome(){
        UserPrincipal userPrincipal = (UserPrincipal)  SecurityUtils.getSubject().getPrincipal();
        CompanyInfo companyInfo = companyInfoQueryService.findOne(userPrincipal.getId());

        ModelAndView mav = new ModelAndView("companyHTML/index");
        mav.addObject("companyInfo",companyInfo);
        return mav;
    }
}
