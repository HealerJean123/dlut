package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.domain.school.Logo;
import cn.edu.dlut.career.repository.school.LogoRepository;
import cn.edu.dlut.career.service.school.LogoCommandService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/5/31  15:24.
 */
@Service
public class LogoCommandServiceImpl implements LogoCommandService {
    @Autowired
    private LogoRepository logoRepository;

    //文件保存后的访问路径
    @Value("${file.url}")
    private String fileUrl = "";

    //文件的上传路径
    @Value("${file.path}")
    private String filePath = "";

    /**
     * @Description 添加一个logo信息.
     * @Author  wangyj
     * @CreateDate 2017/5/31 17:53
     * @Param
     * @Return
     */
    @Override
    public void addLogo(Logo logo, MultipartFile file) {
        UserPrincipal principal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        //获取添加人姓名
        String realName = principal.getRealName();
        logo.setCreator(realName);
        logo.setCreatTime(LocalDateTime.now());
        //图片上传
        String path = "";
        String url = null;
            try {
                // 上传图片
                path = filePath+"/partnerLogo/logo";
                String fileClientName = file.getOriginalFilename();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
                fileClientName = date + fileClientName.substring(fileClientName.lastIndexOf("."));
                File rootFile = new File(path);
                if (!rootFile.exists()) {
                    rootFile.mkdir();
                }

                String pathName = rootFile + File.separator + fileClientName;
                File newfile = new File(pathName);
                BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(newfile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                // 组装返回url，
                url = fileUrl+"/partnerLogo/logo" + File.separator + newfile.getName();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            logo.setLogo(url);
        logoRepository.save(logo);
    }

    /**
     * @Description 修改logo.
     * @Author  wangyj
     * @CreateDate 2017/6/5 11:28
     * @Param
     * @Return
     */
    @Override
    public void updateLogo(Logo logo , MultipartFile file) {

        Logo oldLogo = logoRepository.findOne(logo.getId());
        oldLogo.setName(logo.getName());
        oldLogo.setLogo(logo.getLogo());
        oldLogo.setSortNo(logo.getSortNo());
        oldLogo.setUrl(logo.getUrl());
        //图片上传
        String path = "";
        String url = null;
        if(file!=null){
            try {
                // 上传图片
                path = filePath+"/partnerLogo/logo";
                String fileClientName = file.getOriginalFilename();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
                fileClientName = date + fileClientName.substring(fileClientName.lastIndexOf("."));
                File rootFile = new File(path);
                if (!rootFile.exists()) {
                    rootFile.mkdir();
                }

                String pathName = rootFile + File.separator + fileClientName;
                File newfile = new File(pathName);
                BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(newfile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                // 组装返回url
                url = fileUrl+"/partnerLogo/logo" + File.separator + newfile.getName();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            oldLogo.setLogo(url);
        }
        logoRepository.save(oldLogo);
    }

    /**
     * @Description 根据id删除一个logo.
     * @Author  wangyj
     * @CreateDate 2017/6/5 15:37
     * @Return
     */
    @Override
    public void deleteLogo(UUID id) {
        logoRepository.delete(id);
    }
}
