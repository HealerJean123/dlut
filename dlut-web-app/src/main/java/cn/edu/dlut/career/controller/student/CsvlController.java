package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.repository.company.CompanyInfoRepository;
import cn.edu.dlut.career.service.company.CompanyInfoQueryService;
import cn.edu.dlut.career.service.student.StudentInfoCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by HealerJean on 2017/4/12.
 */
@Controller
public class CsvlController {
    @Autowired
    private CompanyInfoQueryService companyInfoQueryService;

    private CompanyInfoRepository companyInfoRepository;


    @Autowired
    private StudentInfoCommandService studentInfoCommandService;

    @GetMapping("register/csv.html")
    public String csv(){
        return "csv";
    }

    //通过csv文件导入学生数据。
    @PostMapping("register/csvInfo")
    public String csvInfo(@RequestParam("file") MultipartFile file) throws IOException {
        List<StudentInfo> studentInfos = null;
        if (file != null) {
            //获取后缀
            String prefix = file.getOriginalFilename();

            prefix = prefix.substring(prefix.lastIndexOf(".") + 1);
            if (prefix.trim().equals("csv")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                //reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
                String line = null;
                while ((line = reader.readLine()) != null) {
                    String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                    StudentInfo studentInfo = new StudentInfo();
                    for(int i = 0;i<=item.length-1;i++){
                        //根据具体csv做补充


                    }
                    studentInfos.add(studentInfo);
                    studentInfoCommandService.saveStudentInfos(studentInfos);
                }

            }
        }
        return "csv";
    }
}
