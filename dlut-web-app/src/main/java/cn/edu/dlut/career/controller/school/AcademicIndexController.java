package cn.edu.dlut.career.controller.school;

import cn.edu.dlut.career.dto.school.AcademicIndexDTO;
import cn.edu.dlut.career.service.school.AcademicIndexQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 史念念 on 2017/4/28.
 *
 * 院系老师首页信息 控制层
 */
@Controller
public class AcademicIndexController {
    @Autowired
    private AcademicIndexQueryService academicIndexService;

    /**
     * 查询首页信息
     * @param endDate 毕业时间
     * @return
     */
    @GetMapping("teacher/academicIndex")
    @ResponseBody
    public AcademicIndexDTO findAll(String endDate){
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();

        //院系id
        String departmentId = userPrincipal.getDepId();

        AcademicIndexDTO aid = academicIndexService.findAll(departmentId,endDate);

        return aid;
    }
}
