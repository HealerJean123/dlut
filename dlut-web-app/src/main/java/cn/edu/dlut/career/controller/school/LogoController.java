package cn.edu.dlut.career.controller.school;

import cn.edu.dlut.career.domain.school.Logo;
import cn.edu.dlut.career.service.school.LogoCommandService;
import cn.edu.dlut.career.service.school.LogoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

/**
 * 合作企业logo
 * @Author wangyj.
 * @Date 2017/5/31  16:26.
 */
@Controller
public class LogoController {

    @Autowired
    private LogoCommandService logoCommandService;

    @Autowired
    private LogoQueryService logoQueryService;

    /**
     * @Description 添加一个logo信息.
     * @Author  wangyj
     * @CreateDate 2017/5/31 16:28
     * @Param
     * @Return
     */
    @PostMapping("/teacher/logo")
    public void addLogo(Logo logo,MultipartFile file){
        logoCommandService.addLogo(logo,file);
    }




    /**
     * @Description 跳转logo列表页.
     * @Author  wangyj
     * @CreateDate 2017/5/31 17:49
     * @Param
     * @Return
     */
    @GetMapping("/teacher/logoList")
    public String goLogolist(){
        return "teacherHTML/tea_companyLogo";
    }

    /**
     * @Description 教师查询logo列表.
     * @Author  wangyj
     * @CreateDate 2017/5/31 17:49
     * @Param
     * @Return
     */
    @GetMapping("/teacher/logo/list")
    @ResponseBody
    public List<Logo> getLogolistByteacher(Pageable pageable){
        List<Logo> list = logoQueryService.getLogoListByteacher(pageable);
        return list;
    }

    /**
     * @Description 教师根据id查询一个logo信息.
     * @Author  wangyj
     * @CreateDate 2017/6/5 11:15
     * @Param
     * @Return
     */
    @GetMapping("/teacher/logo/{id}")
    public ModelAndView getLogo(@PathVariable UUID id){
        Logo logo = logoQueryService.getById(id);
        ModelAndView mav = new ModelAndView("teacherHTML/tea_companyLogoDetail");
        mav.addObject("logo",logo);
        return mav;
    }

    /**
     * @Description 修改logo.
     * @Author  wangyj
     * @CreateDate 2017/6/5 11:25
     * @Param
     * @Return
     */
    @PostMapping("/teacher/update/logo")
    public String updateLogo(Logo logo ,@RequestParam MultipartFile file){
        logoCommandService.updateLogo(logo,file);
        return "teacherHTML/tea_companyLogoDetail";
    }


    /**
     * @Description 根据id删除logo.
     * @Author  wangyj
     * @CreateDate 2017/6/5 15:34
     * @Param
     * @Return
     */
    @GetMapping("//teacher/delete/logo/{id}")
    public String deleteLogo(@PathVariable UUID id ){
        logoCommandService.deleteLogo(id);
        return "teacherHTML/tea_companyLogoDetail";
    }
}
