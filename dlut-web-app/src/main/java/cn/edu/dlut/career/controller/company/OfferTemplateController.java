package cn.edu.dlut.career.controller.company;

import cn.edu.dlut.career.domain.company.OfferTemplate;
import cn.edu.dlut.career.dto.company.OfferTemplateDTO;
import cn.edu.dlut.career.service.company.OfferTemplateCommandService;
import cn.edu.dlut.career.service.company.OfferTemplateQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Description offer模板
 * Created by HealerJean
 * DATE   2017/5/26 10:36.
 */
@Controller
public class OfferTemplateController {

    @Autowired
    private OfferTemplateQueryService offerTemplateQueryService;
    @Autowired
    private OfferTemplateCommandService offerTemplateCommandService;

    /**
    * @Description 进入模板管理
    * @Author HealerJean
    * @CreateDate 2017/5/26 11:04
    */
    @GetMapping("/company/issuingOffer.html")
    public String intoOfferTemplateHtml(){
        return "companyHTML/offerTemplate";
    }


    @GetMapping("/company/offerAdd.html")
    /**
    * @author wei  2017/5/31
    * @method intoOfferTemplateHtml
    * @param
    * @return java.lang.String
    * @description  进入模板添加页面
    */
    public String intoOfferTemplateAdd(){
        return "companyHTML/offerAdd";
    }

    /**
    * @Description  企业端 添加offer 模板 功能的实现
    * @Author HealerJean
    * @CreateDate 2017/5/26 10:50
    */
    @PostMapping("/company/addOfferTemplate")
    public String addOfferTemplate(OfferTemplateDTO offerTemplateDTO, Model model){

        OfferTemplate offerTemplateNow = offerTemplateCommandService.save(offerTemplateDTO);
        if(offerTemplateNow!=null){
            model.addAttribute("successMsg","offer模板添加成功");
            return "companyHTML/offerAdd";
        }
        return null;
    }

    @GetMapping("/company/showTemplate")
    @ResponseBody
    /**
    * @author wei  2017/5/27
    * @method showTemplate
    * @param [name,jobType]
    * @return org.springframework.web.servlet.ModelAndView
    * @description  发offer前，展示要选择的模板
    */
    public List<OfferTemplate> showTemplate(String name,String stuType){
        UserPrincipal user = (UserPrincipal)  SecurityUtils.getSubject().getPrincipal();
        List<OfferTemplate> offerTemplates = offerTemplateQueryService.findAllByRecId(user.getId(),name,stuType);
        return offerTemplates;
    }

    @GetMapping("/company/deleteTemplate")
    /**
    * @author wei  2017/6/1
    * @method deleteTemplate
    * @param [id]
    * @return void
    * @description  删除模板
    */
    public void deleteTemplate (@RequestParam UUID id){
        offerTemplateCommandService.delete(id);
    }

    @PostMapping("/company/updateTemplate")
    /**
    * @author wei  2017/6/1
    * @method updateTemplate
    * @param [offerTemplateDTO, model]
    * @return void
    * @description  修改模板
    */
    public String updateTemplate(OfferTemplateDTO offerTemplateDTO){
        offerTemplateCommandService.update(offerTemplateDTO);
       return "redirect:/company/updateTemplate.html?id="+offerTemplateDTO.getId();
    }


    @GetMapping(value = "/company/updateTemplate.html")
    public ModelAndView update(@RequestParam UUID id){
        ModelAndView mv = new ModelAndView("companyHTML/offerAlter");
        OfferTemplate offerTemplate = offerTemplateQueryService.findOne(id);
        mv.addObject("offerTemplate",offerTemplate);
        return mv;
    }

    /**
     * 职位类别
     * @return
     */
    @ModelAttribute("jobType")
    public Map<String, String> major() {
        return PubCodeUtil.getDictMap("jobType");
    }

}
