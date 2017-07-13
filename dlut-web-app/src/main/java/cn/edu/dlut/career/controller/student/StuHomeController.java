package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.service.student.StudentInfoCommandService;
import cn.edu.dlut.career.service.student.StudentInfoQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by HealerJean on 2017/4/13.
 */
@Controller
public class StuHomeController {
    @Autowired
    private StudentInfoCommandService studentInfoCommandService;
    @Autowired
    private StudentInfoQueryService studentInfoQueryService;
    @Autowired
    private HttpServletRequest request ;
    @GetMapping("/student/index.html")
    public ModelAndView stuHome(){
       UserPrincipal userPrincipal = (UserPrincipal)  SecurityUtils.getSubject().getPrincipal();
       StudentInfo studentInfo = studentInfoQueryService.findById(userPrincipal.getId());

       ModelAndView mav = new ModelAndView("studentHTML/stu_index");
       mav.addObject("studentInfo", studentInfo);
        return mav;
    }
}
