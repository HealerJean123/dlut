package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.service.student.RecruitmentCommandService;
import cn.edu.dlut.career.service.student.RecruitmentQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 史念念 on 2017/4/14.
 * <p>
 * 招聘会查询 （专场，组团，大招）
 */
@Controller
@RequestMapping("/student/recruitmentQuery")
public class RecruitmentController {
    @Autowired
    private RecruitmentCommandService recruitmentCommandService;
   @Autowired
   private RecruitmentQueryService recruitmentQueryService;
    /**
     * 页面挑转
     */
    @GetMapping("/stuRecruitment.html")
    public String stuRecruitment() {
        return "studentHTML/stu_recruitment";
    }

    /**
     * 招聘会查询
     *
     * @return
     */
//    @GetMapping("/query")
//    @ResponseBody
//    public Map<String, Page> recruitQuery(String fairType, String fairTime, Pageable pageable) {
//        Map<String, Page> map = new HashMap();
//        LocalDateTime fairEndTime=null;
//        LocalDateTime fairStartTime=null;
//        if ("0".equals(fairTime)) {
//            fairEndTime = null;
//            fairStartTime = null;
//        } else if ("1".equals(fairTime)) {
//            fairEndTime = LocalDateTime.now().plusWeeks(1);
//            fairStartTime = LocalDateTime.now();
//        } else if ("2".equals(fairTime)) {
//            fairEndTime = LocalDateTime.now().plusMonths(1);
//            fairStartTime = LocalDateTime.now();
//        } else if ("3".equals(fairTime)) {
//            fairEndTime = LocalDateTime.now();
//            fairStartTime = null;
//        }
//
//        if(fairType.equals("0")){
//            fairType = null;
//        }
//
//        map = recruitmentQueryService.recruitQuery(fairType,fairStartTime,fairEndTime,pageable);
//
//        return map;
//    }
}
