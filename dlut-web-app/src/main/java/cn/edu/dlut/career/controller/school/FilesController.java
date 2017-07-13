package cn.edu.dlut.career.controller.school;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <p>FilesController</p>
 *
 * @author wuxinshui
 */
@Controller
public class FilesController {

	Logger logger = org.apache.log4j.Logger.getLogger(FilesController.class);

    //文件保存后的访问路径
    @Value("${file.url}")
    private String fileUrl = "";

    //文件的上传路径
    @Value("${file.path}")
    private String filePath = "";

    @RequestMapping(value = "/teacher/news/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImage(@RequestParam("upload") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String name = "";
        logger.info("上传开始");
        if (!file.isEmpty()) {
            try {
                response.setContentType("text/html; charset=UTF-8");
                response.setHeader("Cache-Control", "no-cache");
                //解决跨域问题
                //Refused to display 'http://localhost:8080/upload/mgmt/img?CKEditor=practice_content&CKEditorFuncNum=1&langCode=zh-cn' in a frame because it set 'X-Frame-Options' to 'DENY'.
                response.setHeader("X-Frame-Options", "SAMEORIGIN");
                PrintWriter out = response.getWriter();

                String fileClientName = file.getOriginalFilename();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
                fileClientName = date+fileClientName.substring(fileClientName.lastIndexOf("."));
                String rootFilePath=filePath+"/news";

                File rootFile=new File(rootFilePath);
                if (!rootFile.exists()){
                    rootFile.mkdir();
                }

                String pathName = rootFile +File.separator+ fileClientName;
                File newfile = new File(pathName);
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newfile));
                stream.write(bytes);
                stream.close();

                // 组装返回url，以便于ckeditor定位图片
                String url = fileUrl+"/news/"+ File.separator + newfile.getName();

                // 将上传的图片的url返回给ckeditor
                String callback = request.getParameter("CKEditorFuncNum");
                String script = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + callback + ", '" + url + "');</script>";

                out.println(script);
                out.flush();
                out.close();
            } catch (Exception e) {
                logger.info("You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            logger.info("You failed to upload " + name + " because the file was empty.");
        }
        return "SUCCESS";
    }

}
