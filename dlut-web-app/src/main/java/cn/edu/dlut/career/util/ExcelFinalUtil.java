package cn.edu.dlut.career.util;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.student.StudentInfoDTO;
import cn.edu.dlut.career.service.student.GraduateDestinationCommandService;
import cn.edu.dlut.career.service.student.StudentInfoCommandService;
import cn.edu.dlut.career.service.student.StudentInfoQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * @Description
 * @Author healerjean.
 * @Date 2017/5/17  下午7:14.
 */
@Component
public class ExcelFinalUtil {

    @Autowired
    private StudentInfoCommandService studentInfoCommandService;
    @Autowired
    private GraduateDestinationCommandService graduateDestinationCommandService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StudentInfoQueryService studentInfoQueryService;
    private Logger logger = LoggerFactory.getLogger(ExcelFinalUtil.class);

    public static int totalNum  = 0; //总数
    public  static int successNum = 0; //记录导入成功的数量
    public  static int dealNum = 0; //处理数字
    public static List<StudentInfoDTO> errorStuInfos = new ArrayList<StudentInfoDTO>();
    public static  List<String> titles = new  ArrayList<String>();

    /**
     * @Description  excel 标题 供给线程使用
     * @Author healerjean.
     * @Date   2017/5/17 下午7:42.
     */
    public static List<String> getStaticTitles(){
        return  titles;
    }


    /**
     * @Description 取得  学生学籍错误的所有人
     */
    public static List<StudentInfoDTO> getErrorStuInfos(){
        return  errorStuInfos;
    }

    /**
     * 添加 学籍错误的学生
     * @param errorStuInfo
     */
    public synchronized static void addErrorStuInfo(StudentInfoDTO errorStuInfo) {
        errorStuInfos.add(errorStuInfo);
    }

    /**
     * 下次上传设置为空
     */
    public static void setErrorStuInfo() {
        errorStuInfos  = new ArrayList<StudentInfoDTO>();;
    }

    public static int getSuccessNum() {
        return successNum;
    }
    public static void setSuccessNum(int s) {
        successNum = s;
    }
    public synchronized static void addSuccessNum() {
        successNum = successNum+1;
    }

    public static int getDealNum() {
        return dealNum;
    }
    public static void setDealNum(int d) {
        dealNum = d;
    }
    public synchronized static void addDealNum() {
        dealNum = dealNum+1;
    }

    public static void setTotalNum(int t) {
        totalNum = t;
    }

    /**
     * 上传状态 显示总共记录和 成功记录
     * @return
     */
    public Map<String ,Integer> getInsertNumStatus(){
        Map<String,Integer> map = new HashMap<>();
        map.put("totalNum",totalNum);
        map.put("successNum",successNum);
        map.put("dealNum",dealNum);
        return  map;
    }




/**
 * @Description  判断传入的Excel格式 取得相应的book对象
 * @Author healerjean.
 * @Date   2017/5/17 下午7:20.
 */
    public Workbook judgeExcelFile(File file){
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Workbook book = null;

        try {
            if ((file.getName()).endsWith("xls".trim())) {
                //2003
                book = new HSSFWorkbook(new FileInputStream(file));
            } else if ((file.getName()).endsWith("xlsx".trim())) {
                //2007
                book = new XSSFWorkbook(new FileInputStream(file));
            } else {
                System.out.println("文件格式不支持");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  book;
    }

    /**
     * @Description  给静态变量titles赋予值
     * @Author healerjean.
     * @Date   2017/5/17 下午7:40.
     */
    public void getTitles(Sheet sheet){
        //得到标题
        List<String> sheetTitles = new ArrayList<String>();
        //表头那一行
        Row titleRow = sheet.getRow(0);
        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
            String titleCell = titleRow.getCell(i).getStringCellValue();
            sheetTitles.add(titleCell);
        }
        //给静态变量值 标题
        titles = sheetTitles;
    }




    /**
     * @Description  执行线程
     * @Author healerjean.
     * @Date   2017/5/17 下午7:35.
     */
    public void executeThead(Sheet sheet,int tempSize){

        int insertNum = 2;
        ExcelIFinalThead excelIFinalThead =new ExcelIFinalThead(insertNum,sheet,tempSize,graduateDestinationCommandService,studentInfoCommandService,studentInfoQueryService,this);

        Thread thread = new Thread(excelIFinalThead);// 设置线程名称
        thread.setName("Thread-" + tempSize);
        // 启动线程
        thread.start();
    }



    /**
     * @Description  controller 执行的导入学生学籍的方法
     * @Author healerjean.
     * @Date   2017/5/17 下午7:36.
     */
    public String getStuInfos(File file){
        //取得对应格式的workbook
        Workbook book = judgeExcelFile(file);
        Sheet sheet = book.getSheetAt(0);
        //总共多少行数据 直接取得数据
        totalNum = sheet.getLastRowNum();
        int tempSize = 0;
        int insertNum = 2; //每2个开启一个线程
        for (int i = 0; i < totalNum; i++) {
            tempSize = tempSize+1 ;
            //每2个执行一次线程
            if(tempSize%insertNum==0){
                this.executeThead(sheet, tempSize);
            }
        }
        if(tempSize%insertNum!=0) {
                //将剩下不足 的数据导入
                logger.info("剩下的数据为" + tempSize );
                this.executeThead(sheet, tempSize);

        }
        return "success";

    }

    /**
     * @Description  供给线程调用导入学生
     * @Author healerjean.
     * @Date   2017/5/17 下午9:37.
     */
    public void insertStudentInfo(Map<String,String> map) {

        StudentInfo studentInfo = new StudentInfo();
        StudentInfoDTO studentInfoDTO = new StudentInfoDTO();
        String  birthdateString  = null;
        try {
            for (String key : map.keySet()) {
                String value = map.get(key);
                //储备错误信息
                switch (key) {
                    case "stuNo": //学号
                        studentInfoDTO.setStuNo(value);
                        break;
                    case "name": //姓名
                        studentInfoDTO.setName(value);
                        break;
                    case "examId":
                        studentInfoDTO.setExamId(value);
                        break;
                    case "idCard"://身份证号
                        studentInfoDTO.setIdCard(value);
                        break;
                    case "gender":   //性别
                        studentInfoDTO.setGender(value);
                        break;
                    case "ethnic": //民族
                        studentInfoDTO.setEthnic(value);
                        break;
                    case "political"://政治面貌
                        studentInfoDTO.setPolitical(value);
                        break;
                    case "eduDegree"://学历
                        studentInfoDTO.setEduDegree(value);
                        break;
                    case "majorCode"://专业
                        studentInfoDTO.setMajorCode(value);
                        break;
                    case "flangType"://语种1
                        studentInfoDTO.setFlangType(value);
                        break;
                    case "flangType2"://语种2
                        studentInfoDTO.setFlangType2(value);
                        break;
                    case "eduMode"://培养方式
                        studentInfoDTO.setEduMode(value);
                        break;
                    case "homeland"://生源地
                        studentInfoDTO.setHomelandCode(value);
                        break;
                    case "eduYear"://学制
                        studentInfoDTO.setEduYear(value);
                        break;
                    case "startDate": //入学时间
                        studentInfoDTO.setStartDate(value);
                        break;
                    case "endDate": //毕业时间
                        studentInfoDTO.setEndDate(value);
                        break;
                    case "departmentId"://院系
                        studentInfoDTO.setDepartmentId(value);
                        break;
                    case "className"://班级
                        studentInfoDTO.setClassName(value);
                        break;
                    case "tutorName"://导师
                        studentInfoDTO.setTutorName(value);
                        break;
                    case "counselor"://辅导员
                        studentInfoDTO.setCounselor(value);
                        break;
                    case "birthdate"://出生日期
                        studentInfoDTO.setBirthdate(value);
                        break;
                    case "haveEduHukou"://户口是否在校
                        studentInfoDTO.setHaveEduHukou(value);
                        break;
                    case "mobilephone"://	联系电话
                        studentInfoDTO.setMobilephone(value);
                        break;
                    case "email": //邮箱
                        studentInfoDTO.setEmail(value);
                        break;
                    case "qqNo":
                        studentInfoDTO.setQqNo(value);
                        break;
                    case "wechatNo":
                        studentInfoDTO.setWechatNo(value);
                        break;
                    case "homeAddress":
                        studentInfoDTO.setHomeAddress(value);
                        break;
                    case "homePhone":
                        studentInfoDTO.setHomePhone(value);
                        break;
                    case "trustee"://定向或委培单位
                        studentInfoDTO.setTrustee(value);
                        break;
                }


                switch (key) {
                    case "stuNo": //学号
                        studentInfo.setStuNo(value);
                        break;
                    case "name": //姓名
                        studentInfo.setName(value);
                        break;
                    case "examId":
                        studentInfo.setExamId(value);
                        break;
                    case "idCard"://身份证号
                        studentInfo.setIdCard(value);
                        break;
                    case "gender":   //性别
                        studentInfo.setGender(value);
                        break;
                    case "ethnic": //民族
                        studentInfo.setEthnic(value);
                        break;
                    case "political"://政治面貌
                        studentInfo.setPolitical(value);
                        break;
                    case "eduDegree"://学历
                        studentInfo.setEduDegree(value);
                        break;
                    case "majorCode"://专业
                        studentInfo.setMajorCode(value);
                        studentInfo.setMajorName(PubCodeUtil.getName("major", value));
                        break;
                    case "flangType"://语种1
                        studentInfo.setFlangType(value);
                        break;
                    case "flangType2"://语种2
                        studentInfo.setFlangType2(value);
                        break;
                    case "eduMode"://培养方式
                        studentInfo.setEduMode(value);
                        break;
                    case "homeland"://生源地
                        studentInfo.setHomelandCode(value);
                        break;
                    case "eduYear"://学制
                        studentInfo.setEduYear(value);
                        break;
                    case "startDate": //入学时间
                        studentInfo.setStartDate(value);
                        break;
                    case "endDate": //毕业时间
                        studentInfo.setEndDate(value);
                        break;
                    case "departmentId"://院系
                        studentInfo.setDepartmentId(value);
                        studentInfo.setDepartment(PubCodeUtil.getName("academy", value));
                        break;
                    case "className"://班级
                        studentInfo.setClassName(value);
                        break;
                    case "tutorName"://导师
                        studentInfo.setTutorName(value);
                        break;
                    case "counselor"://辅导员
                        studentInfo.setCounselor(value);
                        break;
                    case "birthdate"://出生日期
                        birthdateString = value;
                        break;
                    case "haveEduHukou"://户口是否在校
                        studentInfo.setHaveEduHukou(value);
                        break;
                    case "mobilephone"://	联系电话
                        studentInfo.setMobilephone(value);
                        break;
                    case "email": //邮箱
                        studentInfo.setEmail(value);
                        break;
                    case "qqNo":
                        studentInfo.setQqNo(value);
                        break;
                    case "wechatNo":
                        studentInfo.setWechatNo(value);
                        break;
                    case "homeAddress":
                        studentInfo.setHomeAddress(value);
                        break;
                    case "homePhone":
                        studentInfo.setHomePhone(value);
                        break;
                    case "trustee"://定向或委培单位
                        studentInfo.setTrustee(value);
                        break;
                }


            }
            StudentInfo studentInfoTest = null;
            if(studentInfo.getStuNo()!=null) {
                //查看数据库中有没有这个学生 根据学号判定 如果存在直接 更新操作
                 studentInfoTest = studentInfoQueryService.findByStuNo(studentInfo.getStuNo());

            }if (studentInfoTest != null) {
                logger.info("更新学号"+studentInfo.getStuNo());
                studentInfo.setBirthdate(LocalDate.parse(birthdateString));
                studentInfo = updateStuInfo(studentInfoTest, studentInfo);
                studentInfoCommandService.saveStudentInfo(studentInfo);
                ExcelFinalUtil.addSuccessNum();
                ExcelFinalUtil.addDealNum();
            } else {
                logger.info("添加学号"+studentInfo.getStuNo()+"姓名："+studentInfo.getName());
                studentInfo.setBirthdate(LocalDate.parse(birthdateString));

                //获得随机盐
                SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
                String salt = secureRandomNumberGenerator.nextBytes().toHex();
                //对密码加密后,将加密后的密码和盐存入对象
                String password = studentInfo.getIdCard().substring(studentInfo.getIdCard().length() - 6);
                String pwd = new Md5Hash(password, salt).toString();
                studentInfo.setSalt(salt);
                studentInfo.setPwd(pwd);
                //教师审核状态 未审核  设置为00
                studentInfo.setStatus("00");
                //学生没有上报
                studentInfo.setHaveReport("0");
                UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
                studentInfo.setCreator(userPrincipal.getRealName());
                studentInfo.setUpdator(userPrincipal.getRealName());

                //就业去向
                GraduateDestination graduateDestination = new GraduateDestination();
                graduateDestination.setStudentInfo(studentInfo);
                graduateDestination.setStuStatus("00");
                graduateDestinationCommandService.save(graduateDestination);

                studentInfoCommandService.saveStudentInfo(studentInfo);
                ExcelFinalUtil.addSuccessNum();
                ExcelFinalUtil.addDealNum();
            }
        } catch (Exception e) {
            studentInfoDTO.setExcelErrorMsg(e.getMessage());
            ExcelFinalUtil.addErrorStuInfo(studentInfoDTO);

            logger.info("错误的学生为：" + studentInfo.toString());
            //即使错了进度还是加1
            ExcelFinalUtil.addDealNum();
        }
    }


    /**
     * @Description 如果数据库中存在则是更新操作
     * @Author HealerJean
     * @CreateDate 2017/5/16 16:13
     */
    public StudentInfo updateStuInfo(StudentInfo studentInfo,StudentInfo studentInfoNow){

        studentInfo.setStuNo(studentInfoNow.getStuNo());
        studentInfo.setName(studentInfoNow.getName());
        studentInfo.setExamId(studentInfoNow.getExamId());
        studentInfo.setIdCard(studentInfoNow.getIdCard());
        studentInfo.setGender(studentInfoNow.getGender());
        studentInfo.setEthnic(studentInfoNow.getEthnic());
        studentInfo.setPolitical(studentInfoNow.getPolitical());
        studentInfo.setEduDegree(studentInfoNow.getEduDegree());
        studentInfo.setMajorCode(studentInfoNow.getMajorCode());
        studentInfo.setMajorName(PubCodeUtil.getName("major",studentInfoNow.getMajorCode()));
        studentInfo.setFlangType(studentInfoNow.getFlangType());
        studentInfo.setFlangType2(studentInfoNow.getFlangType2());
        studentInfo.setEduMode(studentInfoNow.getEduMode());
        studentInfo.setHomelandCode(studentInfoNow.getHomelandCode());
        studentInfo.setEduYear(studentInfoNow.getEduYear());
        studentInfo.setStartDate(studentInfoNow.getStartDate());
        studentInfo.setEndDate(studentInfoNow.getEndDate());
        studentInfo.setDepartmentId(studentInfoNow.getDepartmentId());
        studentInfo.setDepartment(PubCodeUtil.getName("academy",studentInfoNow.getDepartmentId()));
        studentInfo.setClassName(studentInfoNow.getClassName());
        studentInfo.setTutorName(studentInfoNow.getTutorName());
        studentInfo.setCounselor(studentInfoNow.getCounselor());
        studentInfo.setBirthdate(studentInfoNow.getBirthdate());
        studentInfo.setHaveEduHukou(studentInfoNow.getHaveEduHukou());
        studentInfo.setMobilephone(studentInfoNow.getMobilephone());
        studentInfo.setEmail(studentInfoNow.getEmail());
        studentInfo.setQqNo(studentInfoNow.getQqNo());
        studentInfo.setWechatNo(studentInfoNow.getWechatNo());
        studentInfo.setHomeAddress(studentInfoNow.getHomeAddress());
        studentInfo.setHomePhone(studentInfoNow.getHomePhone());
        studentInfo.setTrustee(studentInfoNow.getTrustee());
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        studentInfo.setCreator(userPrincipal.getRealName());
        studentInfo.setUpdator(userPrincipal.getRealName());

        return  studentInfo;
    }

    /**
    * @Description 在controller 导入数据的时候进行判断 是否要 导入数据
    * @Author HealerJean
    * @CreateDate 2017/5/18 10:56
    */
    public int judkeFileTitle( List<String> fileTitles){
        int titleSize =0;
        for (String strTitle :fileTitles){
                switch (strTitle){
                    case "stuNo": //学号
                        titleSize=titleSize+1;
                        break;
                    case "name": //姓名
                        titleSize=titleSize+1;
                        break;
                    case "examId":
                        titleSize=titleSize+1;
                        break;
                    case "idCard"://身份证号
                        titleSize=titleSize+1;
                        break;
                    case "gender":   //性别
                        titleSize=titleSize+1;
                        break;
                    case "ethnic": //民族
                        titleSize=titleSize+1;
                        break;
                    case "political"://政治面貌
                        titleSize=titleSize+1;
                        break;
                    case "eduDegree"://学历
                        titleSize=titleSize+1;
                        break;
                    case "majorCode"://专业
                        titleSize=titleSize+1;
                        break;
                    case "flangType"://语种1
                        titleSize=titleSize+1;
                        break;
                    case "flangType2"://语种2
                        titleSize=titleSize+1;
                        break;
                    case "eduMode"://培养方式
                        titleSize=titleSize+1;
                        break;
                    case "homeland"://生源地
                        titleSize=titleSize+1;
                        break;
                    case "eduYear"://学制
                        titleSize=titleSize+1;
                        break;
                    case "startDate": //入学时间
                        titleSize=titleSize+1;
                        break;
                    case "endDate": //毕业时间
                        titleSize=titleSize+1;
                        break;
                    case "departmentId"://院系
                        titleSize=titleSize+1;
                        break;
                    case "className"://班级
                        titleSize=titleSize+1;
                        break;
                    case "tutorName"://导师
                        titleSize=titleSize+1;
                        break;
                    case "counselor"://辅导员
                        titleSize=titleSize+1;
                        break;
                    case "birthdate"://出生日期
                        titleSize=titleSize+1;
                        break;
                    case "haveEduHukou"://户口是否在校
                        titleSize=titleSize+1;
                        break;
                    case "mobilephone"://	联系电话
                        titleSize=titleSize+1;
                        break;
                    case "email": //邮箱
                        titleSize=titleSize+1;
                        break;
                    case "qqNo":
                        titleSize=titleSize+1;
                        break;
                    case "wechatNo":
                        titleSize=titleSize+1;
                        break;
                    case "homeAddress":
                        titleSize=titleSize+1;
                        break;
                    case "homePhone":
                        titleSize=titleSize+1;
                        break;
                    case "trustee"://定向或委培单位
                        titleSize=titleSize+1;
                        break;
                }

            }
            return titleSize;
    }

    /**
     * 导入数据之前进行上传和查看表头
     * @param multfile
     * @return
     */
    public List<String> insertBeforeUpload(MultipartFile multfile){
        //取得上传文件名
        String multfileName = multfile.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        FileOutputStream fos = null;

        //上传中间建立的文件夹
        String fileMdName = "excelUpload";
        File minFile = new File(request.getSession().getServletContext().getRealPath("/")+fileMdName);
        if(!minFile.exists()) {
            minFile.mkdirs();
        }

        try {
            fos = new FileOutputStream(request.getSession().getServletContext().getRealPath("/")+ fileMdName+"/"+sdf.format(new Date())+multfileName.substring(multfileName.lastIndexOf(".")));
            fos.write(multfile.getBytes());
            fos.flush();
            fos.close();

            File file = new File(request.getSession().getServletContext().getRealPath("/")+ fileMdName+"/"+sdf.format(new Date())+multfileName.substring(multfileName.lastIndexOf(".")));
            //将上传的文件保存到session中 ，用于再读取数据之后进行导入
            request.getSession().setAttribute("filePath",file.getAbsolutePath());
            // /执行
            //取得对应格式的workbook
            Workbook book = judgeExcelFile(file);
            Sheet sheet = book.getSheetAt(0);
            getTitles(sheet);//给静态变量title 赋予值

            return  titles;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
        return null;
    }


    /**
     * 得到excel表头
     * @param file
     * @return
     */
    public List<String>  getExcelTitleContent(File file){
        Workbook book = null;
        try {

            if ((file.getName()).endsWith("xls".trim())) {
                //2003
                book = new HSSFWorkbook(new FileInputStream(file));
            } else if ((file.getName()).endsWith("xlsx".trim())) {
                //2007
                book = new XSSFWorkbook(new FileInputStream(file));
            } else {
                System.out.println("文件格式不支持");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Sheet sheet = book.getSheetAt(0);
        //得到标题
        List<String> titles = new ArrayList<String>();
        //表头那一行
        Row titleRow = sheet.getRow(0);
        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
            String titleCell = titleRow.getCell(i).getStringCellValue();
            titles.add(titleCell);
        }

        return titles;
    }






    /**
     * @Description  下载错误的学生Excel
     * @Author healerjean.
     * @Date   2017/5/17 下午10:12.
     */
    public void getErrorStuInfos(HttpServletResponse response){
        List<StudentInfoDTO> errorStuInfos = ExcelFinalUtil.getErrorStuInfos();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("countryDB");
            XSSFRow row = sheet.createRow((short) 0);
            XSSFCell cell = null;

            cell = row.createCell((short) 0);
            cell.setCellValue("stuNo");
            cell = row.createCell((short) 1);
            cell.setCellValue("name");
            cell = row.createCell((short) 2);
            cell.setCellValue("examId");
            cell = row.createCell((short) 3);
            cell.setCellValue("idCard");
            cell = row.createCell((short) 4);
            cell.setCellValue("gender");
            cell = row.createCell((short) 5);
            cell.setCellValue("ethnic");
            cell = row.createCell((short) 6);
            cell.setCellValue("political");
            cell = row.createCell((short) 7);
            cell.setCellValue("eduDegree");
            cell = row.createCell((short) 8);
            cell.setCellValue("majorCode");
            cell = row.createCell((short) 9);
            cell.setCellValue("flangType");
            cell = row.createCell((short) 10);
            cell.setCellValue("flangType2");
            cell = row.createCell((short) 11);
            cell.setCellValue("eduMode");
            cell = row.createCell((short) 12);
            cell.setCellValue("homeland");
            cell = row.createCell((short) 13);
            cell.setCellValue("eduYear");
            cell = row.createCell((short) 14);
            cell.setCellValue("startDate");
            cell = row.createCell((short) 15);
            cell.setCellValue("endDate");
            cell = row.createCell((short) 16);
            cell.setCellValue("departmentId");
            cell = row.createCell((short) 17);
            cell.setCellValue("className");
            cell = row.createCell((short) 18);
            cell.setCellValue("tutorName");
            cell = row.createCell((short) 19);
            cell.setCellValue("counselor");
            cell = row.createCell((short) 20);
            cell.setCellValue("birthdate");
            cell = row.createCell((short) 21);
            cell.setCellValue("haveEduHukou");
            cell = row.createCell((short) 22);
            cell.setCellValue("mobilephone");
            cell = row.createCell((short) 23);
            cell.setCellValue("email");
            cell = row.createCell((short) 24);
            cell.setCellValue("qqNo");
            cell = row.createCell((short) 25);
            cell.setCellValue("wechatNo");
            cell = row.createCell((short) 26);
            cell.setCellValue("homeAddress");
            cell = row.createCell((short) 27);
            cell.setCellValue("homePhone");
            cell = row.createCell((short) 28);
            cell.setCellValue("trustee");
            cell = row.createCell((short) 29);
            cell.setCellValue("错误说明");
            int i = 1;
            for (StudentInfoDTO studentInfo : errorStuInfos) {
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
                cell.setCellValue(studentInfo.getFlangType());
                cell = row.createCell(10);
                cell.setCellValue(studentInfo.getFlangType2());
                cell = row.createCell(11);
                cell.setCellValue(studentInfo.getEduMode());
                cell = row.createCell(12);
                cell.setCellValue(studentInfo.getHomelandCode());
                cell = row.createCell(13);
                cell.setCellValue(studentInfo.getEduYear());
                cell = row.createCell(14);
                cell.setCellValue(studentInfo.getStartDate());
                cell = row.createCell(15);
                cell.setCellValue(studentInfo.getEndDate());
                cell = row.createCell(16);
                cell.setCellValue(studentInfo.getDepartmentId());
                cell = row.createCell(17);
                cell.setCellValue(studentInfo.getClassName());
                cell = row.createCell(18);
                cell.setCellValue(studentInfo.getTutorName());
                cell = row.createCell(19);
                cell.setCellValue(studentInfo.getCounselor());
                cell = row.createCell(20);
                if(studentInfo.getBirthdate()!=null) {
                    cell.setCellValue(studentInfo.getBirthdate().toString());
                }else {
                    cell.setCellValue("");
                }
                cell = row.createCell(21);
                cell.setCellValue(studentInfo.getHaveEduHukou());
                cell = row.createCell(22);
                cell.setCellValue(studentInfo.getMobilephone());
                cell = row.createCell(23);
                cell.setCellValue(studentInfo.getEmail());
                cell = row.createCell(24);
                cell.setCellValue(studentInfo.getQqNo());
                cell = row.createCell(25);
                cell.setCellValue(studentInfo.getWechatNo());
                cell = row.createCell(26);
                cell.setCellValue(studentInfo.getHomeAddress());
                cell = row.createCell(27);
                cell.setCellValue(studentInfo.getHomePhone());
                cell = row.createCell(28);
                cell.setCellValue(studentInfo.getTrustee());
                cell = row.createCell(29);
                cell.setCellValue(studentInfo.getExcelErrorMsg());
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

    /**
     * @Description  清除缓存文件夹
     * @Author healerjean.
     * @Date   2017/5/17 下午11:11.
     */
    public void  delteFile(File file){
        File[] files=file.listFiles(); //file.listFiles()是获取file这个对象也就是file这个目录下面的文件和文件夹的集合
        for(File f:files)
        {
            if(f.isDirectory())//递归调用
            {
                delteFile(f);
            }
            else {
                f.delete();
            }
        }
        file.delete();
    }
}
