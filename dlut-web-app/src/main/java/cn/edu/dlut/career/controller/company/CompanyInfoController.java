package cn.edu.dlut.career.controller.company;
import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.dto.ResponseInfo;
import cn.edu.dlut.career.service.company.CompanyInfoCommandService;
import cn.edu.dlut.career.service.company.CompanyInfoQueryService;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wei on 2017/3/23.
 */
@Transactional
@Controller
public class CompanyInfoController {
    @Autowired
    private CompanyInfoQueryService companyInfoQueryService;
    @Autowired
    private CompanyInfoCommandService companyInfoCommandService;


    /**
     * 审核状态
     * @return
     */
    @ModelAttribute("auditStatus")
    public Map<String, String> auditStatus() {
        return PubCodeUtil.getDictMap("auditStatus");
    }

    /**
     * 审核状态
     * @return
     */
    @ModelAttribute("industry")
    public Map<String, String> industry() {
        return PubCodeUtil.getDictMap("industry");
    }

    /**
     * 审核状态
     * @return
     */
    @ModelAttribute("nature")
    public Map<String, String> nature() {
        return PubCodeUtil.getDictMap("nature");
    }

    /**
     * 用户注册页面的跳转
     * @return
     */
    @GetMapping("/register/company/register.html")
    public String register(){
        return "companyHTML/register";
    }
    /**
     * 企业信息修改页面跳转
     * @return
     */
    @GetMapping("/company/recInfo.html")
    public String unitInformation(){
        return "companyHTML/recInfo";
    }


    /**
     * 公司注册信息
     * @param companyInfo
     * @return
     */
    @PostMapping("/register/company/companyInfo")
    public String saveCompany(CompanyInfo companyInfo,@RequestParam("file") MultipartFile file) throws IOException {
        String path ="";
        if (!file.isEmpty()) {
            try {
                // 上传图片
                path = "C:/upload/";
                BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(path+file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            companyInfo.setLicense(path+file.getOriginalFilename());
            companyInfoCommandService.saveCompany(companyInfo);
            return "login";
        }
        return "companyHTML/register";
    }
    /**
     * 查看表中是否已经存在该邮箱
     * @param email
     * @return
     */
    @GetMapping("/register/company/findByEmail")
    @ResponseBody
    public ResponseInfo findByEmail(String email){
        CompanyInfo companyInfo = companyInfoQueryService.findByEmail(email);
        ResponseInfo responseInfo = new ResponseInfo();
        if(companyInfo != null){
            responseInfo.setStatus(403);
            responseInfo.setMessage("邮箱已存在");
        }else {
            responseInfo.setStatus(200);
            responseInfo.setMessage("邮箱不存在");
        }
        return responseInfo;
    }
    /**
     * 已审核通过企业用户对企业信息的维护修改
     * @param companyInfo
     * @return
     */
    @PostMapping("/company/companyInfo")
    public String updateCompany(CompanyInfo companyInfo){
        companyInfoCommandService.update(companyInfo);
        return "companyHTML/prepRecInfo";
    }

    /**
     * 尚未审核企业用户对企业信息的维护修改
     * @param companyInfo
     * @return
     */
    @PostMapping("/prepCompany/companyInfo")
    public String updatePrepCompany(CompanyInfo companyInfo){
        companyInfoCommandService.update(companyInfo);
        return "companyHTML/recInfo";
    }



    /**
     * @Description 尚未通过审核根据id查询公司信息.
     * @Author  wangyj
     * @CreateDate 2017/5/27 9:45
     */
    @GetMapping("/prepCompany/companyInfo")
    public String findPrepCompany(@RequestParam("id") UUID id, Model model){
        CompanyInfo companyInfo = companyInfoQueryService.findOne(id);
        model.addAttribute("companyInfo",companyInfo);
        return "companyHTML/prepRecInfo";
    }

    /**
     * 通过审核后根据id查询公司信息。
     * @param id
     * @return
     */
    @GetMapping("/company/companyInfo")
    public String findCompany(@RequestParam("id") UUID id, Model model){
        CompanyInfo companyInfo = companyInfoQueryService.findOne(id);
        model.addAttribute("companyInfo",companyInfo);
        return "companyHTML/recInfo";
    }



    @GetMapping("/teacher/tea_company.html")
    /**
    * @author wei  2017/6/5
    * @method teaCompany
    * @param []
    * @return java.lang.String
    * @description  跳转到查询页面
    */
    public String teaCompany(){
        return "teacherHTML/tea_company";
    }


    @GetMapping("/teacher/companyList")
    @ResponseBody
    /**
    * @author wei  2017/6/5
    * @method companyList
    * @param [name, nature, industry, registerTime, auditStatus]
    * @return java.util.List<cn.edu.dlut.career.domain.company.CompanyInfo>
    * @description  教师查询企业信息列表
    */
    public Page<CompanyInfo> companyList(String name, String nature, String industry, String registerTime, String auditStatus, Pageable pageable){
        Page<CompanyInfo> companyInfos = companyInfoQueryService.findList(name,nature,industry,registerTime,auditStatus,pageable);
        return companyInfos;
    }

    @GetMapping("/teacher/companyOne")
    /**
    * @author wei  2017/6/5
    * @method companyOne
    * @param [id, model]
    * @return java.lang.String
    * @description  老师查看企业详情
    */
    public String companyOne(@RequestParam UUID id,Model model){
        CompanyInfo companyInfo = companyInfoQueryService.findOne(id);
        if(companyInfo!=null){
            model.addAttribute("companyInfo",companyInfo);
        }
        return "teacherHTML/tea_companyDetails";
    }

    @GetMapping("/teacher/updateCompany")
    /**
    * @author wei  2017/6/5
    * @method update
    * @param [id, auditStatus, reason]
    * @return java.lang.String
    * @description   教师审核企业信息
    */
    public String update(@RequestParam UUID id,String auditStatus,String reason){
        CompanyInfo companyInfo = companyInfoCommandService.auditing(id,auditStatus,reason);
        return "redirect:/teacher/companyOne?id="+id;
    }

    @GetMapping("/teacher/updateList")
    @ResponseBody
    /**
     * @author wei  2017/6/5
     * @method updateList
     * @param [ids]
     * @return void
     * @description  批量通过企业信息
     */
    public String updateList(@RequestParam UUID[] ids){
        companyInfoCommandService.updateList(ids);
        return "批量审核通过成功！";
    }
}
