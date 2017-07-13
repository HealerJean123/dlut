package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.common.OperateLog;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.student.StudentInfoDTO;
import cn.edu.dlut.career.service.common.OperateLogService;
import cn.edu.dlut.career.service.student.GraduateDestinationCommandService;
import cn.edu.dlut.career.service.student.StudentInfoCommandService;
import cn.edu.dlut.career.service.student.StudentInfoQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.ExcelFinalUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by HealerJean on 2017/4/22.
 */
@Controller
public class ExcelController {

    @Autowired
    private StudentInfoCommandService studentInfoCommandService;
    @Autowired
    private StudentInfoQueryService studentInfoQueryService;
    @Autowired
    private GraduateDestinationCommandService graduateDestinationCommandService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperateLogService operateLogService;
/*    @Autowired
    private ExcelUtil excelUtil;*/
    @Autowired
    private  ExcelFinalUtil excelFinalUtil;
    Logger logger = LoggerFactory.getLogger(ExcelController.class);





    //教师端 导入学籍页面
    @GetMapping("/teacher/stuInfoFile.html")
    public ModelAndView insertExcelIntoStuInfo() {
        ModelAndView mav = new ModelAndView("teacherHTML/tea_file");
        List<StudentInfoDTO> errorStuInfos =  ExcelFinalUtil.getErrorStuInfos();
        mav.addObject("errorStuInfosSize",errorStuInfos.size());
        return mav;
    }

    @GetMapping("/teacher/excelinfo.html")
    public String excel(){
        return "excel";
    }




    /**
     * @Description 上传文件，获得表头并向前台反馈
     * @Author HealerJean
     * @CreateDate 2017/5/16 10:51
     */

    @PostMapping("/teacher/intoStuInfoFinal")
    @ResponseBody
    public List<String> intoStuInfoFinal(@RequestParam("file") MultipartFile multfile) {
        ExcelFinalUtil.setSuccessNum(0);
        ExcelFinalUtil.setTotalNum(0);
        ExcelFinalUtil.setDealNum(0);

        ExcelFinalUtil.setErrorStuInfo();

        List<String> fieldName =  excelFinalUtil.insertBeforeUpload(multfile);
        logger.info(fieldName.toString());
        return fieldName;
    }

    /**
     * @Description 在用户分析表头之后进行上传
     * @Author HealerJean
     * @CreateDate 2017/5/16 10:52
     */
    @ResponseBody
    @GetMapping("/teacher/insertSuccess")
    public String insertSuccess() {
        List<String> fileTitles =  ExcelFinalUtil.getStaticTitles();
        //防止空指针（防止用户不上传就点击）
        if(fileTitles.size()!=0) {
            int titleSize = excelFinalUtil.judkeFileTitle(fileTitles);
            //判断标题是不是都匹配到了
            logger.info(titleSize + "数量：" + fileTitles.size());
            if (titleSize == fileTitles.size()) {
                //找到刚刚那个文件的路径，开始进行导入
                String filePath = (String) request.getSession().getAttribute("filePath");
                File file = new File(filePath);
                excelFinalUtil.getStuInfos(file);
                //操作日志
                UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
                OperateLog operateLog = new OperateLog(userPrincipal.getId(), "学籍导入");
                operateLogService.addOptLog(operateLog, "学籍导入");

                File minFile = new File(request.getSession().getServletContext().getRealPath("/") + "excelUpload");
                excelFinalUtil.delteFile(minFile);
                request.getSession().setAttribute("filePath", null);
                return "success";
            } else {
                File minFile = new File(request.getSession().getServletContext().getRealPath("/") + "excelUpload");
                excelFinalUtil.delteFile(minFile);
                request.getSession().setAttribute("filePath", null);
                return null;
            }
        }else {
            return null;
        }

    }


    /**
     * ajax 进行调用的 导入数量状态
     * totalNum   总数
     * successNum  导入成功数
     * @return
     */
    @GetMapping("/teacher/insertNumStatus")
    @ResponseBody
    public Map<String ,Integer> getInsertNumStatus() {
        Map<String ,Integer> insertNumStatus =  excelFinalUtil.getInsertNumStatus();
        return insertNumStatus;
    }






    //下载错误的文件
    @RequestMapping(value = "/teacher/downErrorStuInfo", method = RequestMethod.GET)
    public void downErrorStuInfo(HttpServletResponse response){
        excelFinalUtil.getErrorStuInfos(response);
    }



    ////从数据库中读取，创建excel 文件 ,提供下载用
    @RequestMapping(value = "/teacher/downloaStuExcelFinal", method = RequestMethod.GET)
    public void downloadFileFinal(HttpServletResponse response)
        throws IOException {
        List<StudentInfo> studentInfos = studentInfoQueryService.findAllStudentInfo();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("countryDB");
            XSSFRow rowTitle = sheet.createRow((short) 0);
            XSSFRow row = sheet.createRow((short) 1);
            XSSFCell cell = null;

            cell = rowTitle.createCell((short) 0);
            cell.setCellValue("学号");
            cell = row.createCell((short) 0);
            cell.setCellValue("stuNo");
            cell = rowTitle.createCell((short) 1);
            cell.setCellValue("姓名");
            cell = row.createCell((short) 1);
            cell.setCellValue("name");
            cell = rowTitle.createCell((short) 2);
            cell.setCellValue("准考证号");
            cell = row.createCell((short) 2);
            cell.setCellValue("examId");
            cell = rowTitle.createCell((short) 3);
            cell.setCellValue("身份证号");
            cell = row.createCell((short) 3);
            cell.setCellValue("idCard");
            cell = rowTitle.createCell((short) 4);
            cell.setCellValue("性别");
            cell = row.createCell((short) 4);
            cell.setCellValue("gender");
            cell = rowTitle.createCell((short) 5);
            cell.setCellValue("民族");
            cell = row.createCell((short) 5);
            cell.setCellValue("ethnic");
            cell = rowTitle.createCell((short) 6);
            cell.setCellValue("政治面貌");
            cell = row.createCell((short) 6);
            cell.setCellValue("political");
            cell = rowTitle.createCell((short) 7);
            cell.setCellValue("学历");
            cell = row.createCell((short) 7);
            cell.setCellValue("eduDegree");
            cell = rowTitle.createCell((short) 8);
            cell.setCellValue("专业代码");
            cell = row.createCell((short) 8);
            cell.setCellValue("majorCode");
            cell = rowTitle.createCell((short) 9);
            cell.setCellValue("专业名称");
            cell = row.createCell((short) 9);
            cell.setCellValue("majorName");
            cell = rowTitle.createCell((short) 10);
            cell.setCellValue("外语语种1");
            cell = row.createCell((short) 10);
            cell.setCellValue("flangType");
            cell = rowTitle.createCell((short) 11);
            cell.setCellValue("外语语种2");
            cell = row.createCell((short) 11);
            cell.setCellValue("flangType2");
            cell = rowTitle.createCell((short) 12);
            cell.setCellValue("培养方式");
            cell = row.createCell((short) 12);
            cell.setCellValue("eduMode");
            cell = rowTitle.createCell((short) 13);
            cell.setCellValue("生源地");
            cell = row.createCell((short) 13);
            cell.setCellValue("homeland");
            cell = rowTitle.createCell((short) 14);
            cell.setCellValue("学制");
            cell = row.createCell((short) 14);
            cell.setCellValue("eduYear");
            cell = rowTitle.createCell((short) 15);
            cell.setCellValue("入学时间");
            cell = row.createCell((short) 15);
            cell.setCellValue("startDate");
            cell = rowTitle.createCell((short) 16);
            cell.setCellValue("毕业时间");
            cell = row.createCell((short) 16);
            cell.setCellValue("endDate");
            cell = rowTitle.createCell((short) 17);
            cell.setCellValue("院系Id");
            cell = row.createCell((short) 17);
            cell.setCellValue("departmentId");
            cell = rowTitle.createCell((short) 18);
            cell.setCellValue("院系名称");
            cell = row.createCell((short) 18);
            cell.setCellValue("department");
            cell = rowTitle.createCell((short) 19);
            cell.setCellValue("班级名称");
            cell = row.createCell((short) 19);
            cell.setCellValue("className");
            cell = rowTitle.createCell((short) 20);
            cell.setCellValue("导师姓名");
            cell = row.createCell((short) 20);
            cell.setCellValue("tutorName");
            cell = rowTitle.createCell((short) 21);
            cell.setCellValue("辅导员姓名");
            cell = row.createCell((short) 21);
            cell.setCellValue("counselor");
            cell = rowTitle.createCell((short) 22);
            cell.setCellValue("出生日期");
            cell = row.createCell((short) 22);
            cell.setCellValue("birthdate");
            cell = rowTitle.createCell((short) 23);
            cell.setCellValue("户口是否转入学校（1，是，2 不是）");
            cell = row.createCell((short) 23);
            cell.setCellValue("haveEduHukou");
            cell = rowTitle.createCell((short) 24);
            cell.setCellValue("手机号码");
            cell = row.createCell((short) 24);
            cell.setCellValue("mobilephone");
            cell = rowTitle.createCell((short) 25);
            cell.setCellValue("邮箱");
            cell = row.createCell((short) 25);
            cell.setCellValue("email");
            cell = rowTitle.createCell((short) 26);
            cell.setCellValue("qq号码");
            cell = row.createCell((short) 26);
            cell.setCellValue("qqNo");
            cell = rowTitle.createCell((short) 27);
            cell.setCellValue("微信号码");
            cell = row.createCell((short) 27);
            cell.setCellValue("wechatNo");
            cell = rowTitle.createCell((short) 28);
            cell.setCellValue("家庭住址");
            cell = row.createCell((short) 28);
            cell.setCellValue("homeAddress");
            cell = rowTitle.createCell((short) 29);
            cell.setCellValue("家庭手机号");
            cell = row.createCell((short) 29);
            cell.setCellValue("homePhone");
            cell = rowTitle.createCell((short) 30);
            cell.setCellValue("定向委培单位");
            cell = row.createCell((short) 30);
            cell.setCellValue("trustee");

            int i = 2;
            for (StudentInfo studentInfo : studentInfos) {
                logger.info(studentInfo.toString() + "*****************88");
                row = sheet.createRow(i);

                cell = row.createCell(0);
                cell.setCellValue(studentInfo.getStuNo());
                cell = row.createCell(1);
                cell.setCellValue(studentInfo.getName());
                cell = row.createCell(2);
                cell.setCellValue(studentInfo.getExamId());
                cell = row.createCell(3);
                cell.setCellValue(studentInfo.getIdCard());
                cell = row.createCell(4);
                cell.setCellValue(studentInfo.getGender());
                cell = row.createCell(5);
                cell.setCellValue(studentInfo.getEthnic());
                cell = row.createCell(6);
                cell.setCellValue(studentInfo.getPolitical());
                cell = row.createCell(7);
                cell.setCellValue(studentInfo.getEduDegree());
                cell = row.createCell(8);
                cell.setCellValue(studentInfo.getMajorCode());
                cell = row.createCell(9);
                cell.setCellValue(studentInfo.getMajorName());
                cell = row.createCell(10);
                cell.setCellValue(studentInfo.getFlangType());
                cell = row.createCell(11);
                cell.setCellValue(studentInfo.getFlangType2());
                cell = row.createCell(12);
                cell.setCellValue(studentInfo.getEduMode());
                cell = row.createCell(13);
                cell.setCellValue(studentInfo.getHomelandCode());
                cell = row.createCell(14);
                cell.setCellValue(studentInfo.getEduYear());
                cell = row.createCell(15);
                cell.setCellValue(studentInfo.getStartDate());
                cell = row.createCell(16);
                cell.setCellValue(studentInfo.getEndDate());
                cell = row.createCell(17);
                cell.setCellValue(studentInfo.getDepartmentId());
                cell = row.createCell(18);
                cell.setCellValue(studentInfo.getDepartment());
                cell = row.createCell(19);
                cell.setCellValue(studentInfo.getClassName());
                cell = row.createCell(20);
                cell.setCellValue(studentInfo.getTutorName());
                cell = row.createCell(21);
                cell.setCellValue(studentInfo.getCounselor());
                cell = row.createCell(22);
                cell.setCellValue(studentInfo.getBirthdate().toString());
                cell = row.createCell(23);
                cell.setCellValue(studentInfo.getHaveEduHukou());
                cell = row.createCell(24);
                cell.setCellValue(studentInfo.getMobilephone());
                cell = row.createCell(25);
                cell.setCellValue(studentInfo.getEmail());
                cell = row.createCell(26);
                cell.setCellValue(studentInfo.getQqNo());
                cell = row.createCell(27);
                cell.setCellValue(studentInfo.getWechatNo());
                cell = row.createCell(28);
                cell.setCellValue(studentInfo.getHomeAddress());
                cell = row.createCell(29);
                cell.setCellValue(studentInfo.getHomePhone());
                cell = row.createCell(30);
                cell.setCellValue(studentInfo.getTrustee());
                i++;
            }

            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();

                workbook.write(os);
                byte[] bytes = os.toByteArray();

                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment;filename=stuInformationFinal.xlsx");
                response.setContentLength(bytes.length);
                response.getOutputStream().write(bytes);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
