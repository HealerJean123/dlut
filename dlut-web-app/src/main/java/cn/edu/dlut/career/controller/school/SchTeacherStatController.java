package cn.edu.dlut.career.controller.school;

import cn.edu.dlut.career.dto.school.SchTeacherStatDTO;
import cn.edu.dlut.career.service.school.SchTeacherStatQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author wangyj.
 * @Date 2017/4/28  16:34.
 */
@Controller
public class SchTeacherStatController {

    @Autowired
    private SchTeacherStatQueryService schTeacherStatService;

    @GetMapping("/stat/schTeacher")
    @ResponseBody
    public SchTeacherStatDTO getSchTeacherStat(@RequestParam String graduateDate ){
        return schTeacherStatService.getStat(graduateDate);
    }
}
