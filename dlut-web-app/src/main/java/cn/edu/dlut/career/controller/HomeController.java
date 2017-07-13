package cn.edu.dlut.career.controller;

import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private HttpServletRequest request;


    /**
     * 登录后展示的首页
     *
     * @return
     */
    @GetMapping({"showPages","/"})
    public ModelAndView dashboard() {
        Subject currentUser = SecurityUtils.getSubject();
        logger.info("Show index");
        UserPrincipal userPrincipal = (UserPrincipal) currentUser.getPrincipal();
        ModelAndView mav = new ModelAndView();
        HttpSession session= request.getSession();
        //身份信息放入session
        session.setAttribute("user",userPrincipal);
        logger.info(userPrincipal+"**");
        if ("STUDENT".equals(userPrincipal.getRole())){
            mav.setViewName("redirect:/student/index.html");
        }else if("COMPANY".equals(userPrincipal.getRole())){
            mav.setViewName("redirect:/company/index.html");
            //教师类型（院级/校级）
        }else if ("TEACHER".equals(userPrincipal.getRole())){
            if("SCHOOL".equals(userPrincipal.getPrincipal())||"SCHADMIN".equals(userPrincipal.getPrincipal())){
                //校级教师
                mav.setViewName("redirect:/teacher/tea_index.html");
            }else{
                //院级教师
                mav.setViewName("redirect:/teacher/tea_academicIndex.html");
            }
        }else if("PREPCOMPANY".equals(userPrincipal.getRole())){
            String url = "redirect:/prepCompany/companyInfo?id="+userPrincipal.getId();
            mav.setViewName(url);
        }
        return mav;
    }
}
