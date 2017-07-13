package cn.edu.dlut.career.util;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.student.StudentInfoDTO;
import cn.edu.dlut.career.service.student.GraduateDestinationCommandService;
import cn.edu.dlut.career.service.student.StudentInfoCommandService;
import cn.edu.dlut.career.service.student.StudentInfoQueryService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author healerjean.
 * @Date 2017/5/17  下午7:12.
 */
public class ExcelIFinalThead implements Runnable  {


    private static Logger logger = LoggerFactory.getLogger(ExcelIFinalThead.class);

    private Sheet sheet = null;
    private int tempSize = 0;
    private int insertNum = 0;
    private StudentInfoCommandService studentInfoCommandService =null;
    private GraduateDestinationCommandService graduateDestinationCommandService = null;
    private StudentInfoQueryService studentInfoQueryService;
    private  ExcelFinalUtil excelFinalUtil = null;
    public ExcelIFinalThead(int insertNum,Sheet sheet, int tempSize, GraduateDestinationCommandService graduateDestinationCommandService, StudentInfoCommandService studentInfoCommandService, StudentInfoQueryService studentInfoQueryService, ExcelFinalUtil excelFinalUtil) {
        this.studentInfoCommandService = studentInfoCommandService;
        this.graduateDestinationCommandService =graduateDestinationCommandService;
        this.studentInfoQueryService =studentInfoQueryService;
        this.excelFinalUtil =excelFinalUtil;
        this.sheet =sheet;
        this.tempSize = tempSize;
        this.insertNum= insertNum;
    }



    /**
     * 处理的进度
     * @return
     */

    public void run() {
        Row row = null;
       List<String>  titles = ExcelFinalUtil.getStaticTitles();
       int j = 0;
       //如果是正常2个开启一个线程
           if(tempSize%2==0) {
               j = tempSize - insertNum+1;

           }else {
               j = (tempSize/insertNum)*insertNum +1;

           }
           for ( ; j < tempSize+ 1; j++) {
               row = sheet.getRow(j);
               Map<String, String> map = new HashMap<String, String>();
               String strValue = null;
               for (int i = 0; i < row.getLastCellNum(); i++) {
                   if (row.getCell(i) != null) {
                       row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                       strValue = row.getCell(i).getStringCellValue();
                   }
                   map.put(titles.get(i), strValue);
               }try {
                   logger.info("*********");
                   excelFinalUtil.insertStudentInfo(map);
               }catch (Exception e){
                   continue;
               }


           }
       }


}
