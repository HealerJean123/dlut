package cn.edu.dlut.career.controller.company;

import cn.edu.dlut.career.domain.company.Field;
import cn.edu.dlut.career.dto.company.FieldDTO;
import cn.edu.dlut.career.dto.company.FieldDTO2;
import cn.edu.dlut.career.dto.company.FieldDTOJ;
import cn.edu.dlut.career.service.company.FieldCommandService;
import cn.edu.dlut.career.service.company.FieldQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wei on 2017/5/26.
 */
@Controller
@Transactional
public class FieldController {
    @Autowired
    private FieldQueryService fieldQueryService;
    @Autowired
    private FieldCommandService fieldCommandService;


    /**
     * 场地用途
     * @return
     */
    @ModelAttribute("areaUsing")
    public Map<String, String> areaUsing() {
        return PubCodeUtil.getDictMap("areaUsing");
    }

    /**
     * 场地要求
     * @return
     */
    @ModelAttribute("areaRequire")
    public Map<String, String> areaRequire() {
        return PubCodeUtil.getDictMap("areaRequire");
    }

    /**
     * 场地规模
     * @return
     */
    @ModelAttribute("areaSize")
    public Map<String, String> areaSize() {
        return PubCodeUtil.getDictMap("areaSize");
    }

    /**
     * 场地审核状态
     * @return
     */
    @ModelAttribute("areaAuditStatus")
    public Map<String, String> areaAuditStatus() {
        return PubCodeUtil.getDictMap("areaAuditStatus");
    }

    /**
     * 场地用途
     * @return
     */
    @ModelAttribute("areaUsing")
    public Map<String, String> major() {
        return PubCodeUtil.getDictMap("areaUsing");
    }

    /**
     *场地预约页面跳转
     * @return
     */
    @GetMapping("/company/venue.html")
    public String vunue(){
        return "companyHTML/venue";
    }

    /**
     * 场地预约添加页面跳转
     */
    @GetMapping("/company/venueSubmit.html")
    public String submit(){
        return "companyHTML/venueSubmit";
    }


    /**
     * @author wei  2017/5/27
     * @method fieldList
     * @param  fieldSize  场地规模
     * @param  fieldRequire 场地类型
     * @param  fieldUsing 场地用途
     * @param  auditStatus 审核状态
     * @param  startDate 开始日期
     * @return List<FieldDTO>
     * @description 动态查询场地租用信息
    /company/fieldList     */
    @GetMapping("/company/fieldList")
    @ResponseBody
    public List<FieldDTO> fieldList(String fieldSize,String fieldRequire,String fieldUsing,String auditStatus,String startDate){
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        UUID id = user.getId();
        List<FieldDTO> fieldDTOS = fieldQueryService.findDynamic(fieldSize,fieldRequire,fieldUsing,auditStatus,startDate,id);
        return fieldDTOS;
    }


    @PostMapping("/company/applyField")
    /**
    * @author wei  2017/5/27
    * @method applyField
    * @param [recName, contacts, contactsPhone, startDate, fieldUsing, fieldNum, fieldSize, fieldRequire]
    * @return org.springframework.web.servlet.ModelAndView
    * @description  申请预约场地
    */
    public ModelAndView applyField(String recName,String contacts,String contactsPhone,String startDate,String fieldUsing,String fieldNum,String fieldSize,String fieldRequire,String startTime,String endTime){
        ModelAndView mv = new ModelAndView("companyHTML/venueSubmit");
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        Field field = new Field();
        field.setRecName(recName);
        field.setContacts(contacts);
        field.setContactsPhone(contactsPhone);
        field.setStartDate(startDate);
        field.setEndTime(endTime);
        field.setFieldUsing(fieldUsing);
        field.setStartTime(startTime);
        field.setFieldRequire(fieldRequire);
        field.setFieldSize(fieldSize);
        field.setFieldNum(Integer.parseInt(fieldNum));
        field.setRecId(user.getId());
        field.setAuditStatus("00");
        Field field1 = fieldCommandService.addField(field);
        mv.addObject("field",field1);
        return mv;
    }

    @GetMapping("/company/findFieldOne")
    /**
    * @author wei  2017/5/27
    * @method findOne
    * @param [id]
    * @return org.springframework.web.servlet.ModelAndView
    * @description  根据id查看场地租用详情
    */
    public ModelAndView findOne(@RequestParam UUID id){
        ModelAndView mv = new ModelAndView("companyHTML/venueDetails");
        Field field = fieldQueryService.findOne(id);
        mv.addObject("field",field);
        return mv;
    }


    @GetMapping("/teacher/fieldList")
    @ResponseBody
    /**
    * @author wei  2017/6/2
    * @method fields
    * @param [fieldSize, fieldRequire, fieldUsing, auditStatus, startDate]
    * @return java.util.List<cn.edu.dlut.career.dto.company.FieldDTO2>
    * @description 教师查询场地
    */
    public List<FieldDTO2> fields(String recName, String fieldSize, String fieldRequire, String fieldUsing, String auditStatus, String startDate){
        List<FieldDTO2> fieldDTOS = fieldQueryService.findDynamic(fieldSize,fieldRequire,fieldUsing,auditStatus,startDate,recName);
        return fieldDTOS;
    }

    @PostMapping("/teacher/audit")
    /**
    * @author wei  2017/6/2
    * @method audit
    * @param [auditStatus, id]
    * @return java.lang.String
    * @description  教师审核场地
    */
    public String audit(FieldDTOJ field){
        fieldCommandService.audit(field);
        return "teacherHTML/tea_venue";
    }

    @GetMapping("/teacher/tea_venue.html")
    /**
     * 教师查询field页面跳转
     */
    public String teaVenue(){
        return "teacherHTML/tea_venue";
    }

    @GetMapping("/teacher/findFieldOne")
    /**
    * @author wei  2017/6/2
    * @method findFieldOne
    * @param [id]
    * @return org.springframework.web.servlet.ModelAndView
    * @description  教师查看租用详情
    */
    public ModelAndView findFieldOne(@RequestParam UUID id){
        ModelAndView mv = new ModelAndView("teacherHTML/tea_venueDetails");
        Field field = fieldQueryService.findOne(id);
        mv.addObject("field",field);
        return mv;
    }
}
