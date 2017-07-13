package cn.edu.dlut.career.controller;

import cn.edu.dlut.career.shiro.UserLoginToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login.html")
    public String login() {
        logger.info("Show Login Page");
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return "redirect:showPage";
        }
        return "login";
    }

//    @GetMapping("/")
//    public String defaultIndex() {
//        logger.info("Show Login Page");
//        Subject currentUser = SecurityUtils.getSubject();
//        if (currentUser.isAuthenticated()) {
//            return "redirect:index.html";
//        }
//        return "login";
//    }

    @PostMapping("/login.html")
    public String login(String username, String password,String type, Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            //拓展token，加入登录类型，实现统一登录入口
            UserLoginToken token = new UserLoginToken(username, password,type);
            try {
                logger.info("Begin dto process user login ...");
                currentUser.login(token);
                return "redirect:showPages";
            } catch (Exception e) {
                logger.info(e.getMessage());
                model.addAttribute("errorMessage", "您的用户名或密码错误，请重新登录！");
            }
        }
        return "login";
    }

    @GetMapping("/logout.html")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/index.html";
    }

    @ModelAttribute("currentUser")
    protected Subject currentUser() {
        return SecurityUtils.getSubject();
    }
}
