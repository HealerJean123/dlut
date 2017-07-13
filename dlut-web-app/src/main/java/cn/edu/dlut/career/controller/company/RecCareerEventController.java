package cn.edu.dlut.career.controller.company;

import cn.edu.dlut.career.domain.company.Field;
import cn.edu.dlut.career.domain.company.RecCareerEvent;
import cn.edu.dlut.career.dto.company.RecCareerEventDTO;
import cn.edu.dlut.career.dto.company.SpecialDTO;
import cn.edu.dlut.career.service.company.FieldQueryService;
import cn.edu.dlut.career.service.company.RecCareerEventCommandService;
import cn.edu.dlut.career.service.company.RecCareerEventQueryService;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 专场招聘预约 预约申请
 * Created by HealerJean on 2017/4/10.
 */
@Controller
@Transactional
public class RecCareerEventController {

    Logger logger = LoggerFactory.getLogger(RecCareerEventController.class);
    @Autowired
    private RecCareerEventQueryService recCareerEventQueryService;
    @Autowired
    private RecCareerEventCommandService recCareerEventCommandService;

    @Autowired
    private FieldQueryService fieldQueryService;

    @ModelAttribute
    public void getMap(Map<String, Object> map){
        map.put("allAreaRequire",  PubCodeUtil.getDictMap("areaRequire"));
        map.put("allAreaSize",   PubCodeUtil.getDictMap("areaSize"));
    }

    /**
     * 场地审核状态
     * @return
     */
    @ModelAttribute("auditStatus")
    public Map<String, String> auditStatus() {
        return PubCodeUtil.getDictMap("auditStatus");
    }
    /**
     * 专场招聘预约 进入添加页面html
     */
    @GetMapping("/company/appointment.html")
    public String saveRecCareerEventHtml()  {
        return "companyHTML/appointment";
    }

    /**
     * 专场招聘预约申请表添加
     * @return
     */
    @PostMapping("/company/addRecCareerEvent")
    public String saveRecCareerEvent(RecCareerEventDTO recCareerEventDTO, Model model)  {

       RecCareerEvent recCareerEvent= recCareerEventCommandService.saveRecCareerEvent(recCareerEventDTO);
       if (recCareerEvent!=null) {
           model.addAttribute("successMsg","预约成功，请等待审核通过");
          return "redirect:/company/findOne?id="+recCareerEvent.getId();
       }
        return "companyHTML/appointment";
    }


    @GetMapping("/company/appointmentList.html")
    /**
    * @author wei  2017/6/6
    * @method appointmentList
    * @param []
    * @return java.lang.String
    * @description  跳转到查询页面
    */
    public String appointmentList(){
        return "companyHTML/appointmentList";
            }

    @GetMapping("/company/recCareerList")
    @ResponseBody
    /**
    * @author wei  2017/6/6
    * @method findList
    * @param [fairName, applicationDate, startDate, auditsStatus, pageable]
    * @return org.springframework.data.domain.Page<cn.edu.dlut.career.dto.company.SpecialDTO>
    * @description  企业动态查询招聘会
    */
    public Page<SpecialDTO> findList(String fairName, String applicationDate, String startDate, String auditsStatus, Pageable pageable){
        Page<SpecialDTO> specialDTOS = recCareerEventQueryService.findList(fairName,applicationDate,startDate,auditsStatus,pageable);
        return specialDTOS;
    }

    @GetMapping("/company/findOne")
    /**
    * @author wei  2017/6/6
    * @method findOne
    * @param [id, model]
    * @return java.lang.String
    * @description  跳转到详情页面
    */
    public String findOne(@RequestParam UUID id,Model model){
        RecCareerEvent recCareerEvent = recCareerEventQueryService.findById(id);
        Field field = fieldQueryService.findOne(recCareerEvent.getFieldId());
        if(recCareerEvent!=null){
            model.addAttribute("recCareerEvent",recCareerEvent);
            model.addAttribute("field",field);
        }
        return "companyHTML/appointmentDetails";
    }


    @GetMapping("/teacher/tea_appointmentList.html")
    /**
    * @author wei  2017/6/6
    * @method teaAppointmentList
    * @param []
    * @return java.lang.String
    * @description  跳转到教师查询页面
    */
    public String teaAppointmentList(){
        return "teacherHTML/tea_appointmentList";
    }

    @GetMapping("/teacher/recCareerList")
    @ResponseBody
    /**
     * @author wei  2017/6/6
     * @method findList
     * @param [fairName, applicationDate, startDate, auditsStatus, pageable]
     * @return org.springframework.data.domain.Page<cn.edu.dlut.career.dto.company.SpecialDTO>
     * @description  教师企业动态查询招聘会
     */
    public Page<SpecialDTO> findListBySchool(String fairName, String applicationDate, String startDate, String auditsStatus, Pageable pageable){
        Page<SpecialDTO> specialDTOS = recCareerEventQueryService.findList(fairName,applicationDate,startDate,auditsStatus,pageable);
        return specialDTOS;
    }

    @GetMapping("/teacher/findOne")
    /**
     * @author wei  2017/6/6
     * @method findOne
     * @param [id, model]
     * @return java.lang.String
     * @description  跳转到详情页面
     */
    public String findOneBySchool(@RequestParam UUID id,Model model){
        RecCareerEvent recCareerEvent = recCareerEventQueryService.findById(id);
        Field field = fieldQueryService.findOne(recCareerEvent.getFieldId());
        if(recCareerEvent!=null){
            model.addAttribute("recCareerEvent",recCareerEvent);
            model.addAttribute("field",field);
        }
        return "teacherHTML/tea_appointmentDetails";
    }

    @PostMapping("/teacher/auditCareer")
    /**
    * @author wei  2017/6/6
    * @method update
    * @param [id, auditsStatus]
    * @return java.lang.String
    * @description  学校审核专场
    */
    public String update(@RequestParam UUID id,String auditsStatus){
        RecCareerEvent recCareerEvent = recCareerEventCommandService.update(id,auditsStatus);
        return "redirect:/teacher/findOne?id="+recCareerEvent.getId();
    }
}
