package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.domain.school.News;
import cn.edu.dlut.career.repository.school.NewsRepository;
import cn.edu.dlut.career.service.school.NewsCommandService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.time.format.DateTimeFormatter;

/**
 * @Author wangyj.
 * @Date 2017/5/18  9:22.
 */
@Service
@Transactional
public class NewsCommandServiceImpl implements NewsCommandService {

    @Autowired
    private NewsRepository newsRepository;

    //文件保存后的访问路径
    @Value("${file.url}")
    private String fileUrl = "";

    //文件的上传路径
    @Value("${file.path}")
    private String filePath = "";

    @Override
    public void addNews(News news,MultipartFile file) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        //创建人姓名
        news.setCreator(userPrincipal.getRealName());
        //生成编号
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String newsNo = "xw"+LocalDateTime.now().format(fmt);
        news.setNewsNo(newsNo);
        //创建时间
        news.setCreatTime(LocalDateTime.now());
        //设置访问量为0
        news.setPv(0);
        //设置新闻状态
        news.setStatus("已发布");
        //图片上传
        String path ="";
        String url = null;
        if(news.getIsBanner()==true) {
            if (!file.isEmpty()) {
                try {
                    // 上传图片
                    path = filePath+"/news/banner";
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
                    url = fileUrl+"/news/banner/" + File.separator + newfile.getName();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                news.setBanner(url);
            }
        }
        newsRepository.save(news);
    }
    /**
    * @Description 根据id删除新闻
    * @Author HealerJean
    * @CreateDate 2017/5/19 12:56
    */
    @Override
    public void deleteNewsByIds(UUID[] id) {
        for (UUID idOne:id) {
            try {
                newsRepository.delete(idOne);
            }catch (Exception e){ //
                continue;
            }
        }
    }


    /**
     * @Description 修改新闻.
     * @Author  wangyj
     * @CreateDate 2017/5/22 9:16
     * @Param
     * @Return
     */
    @Override
    public void updateNews(News news,MultipartFile file) {
        UUID id = news.getId();
        News oldNews = newsRepository.findOne(id);
        oldNews.setTitle(news.getTitle());
        oldNews.setSubTitle(news.getSubTitle());
        oldNews.setIsBanner(news.getIsBanner());
        oldNews.setBanner(news.getBanner());
        oldNews.setContent(news.getContent());
        oldNews.setNewsColumn(news.getNewsColumn());
        oldNews.setPublishDate(news.getPublishDate());
        oldNews.setEndDate(news.getEndDate());
        oldNews.setSortNo(news.getSortNo());
        oldNews.setSortEndDate(news.getSortEndDate());
        oldNews.setIsTop(news.getIsTop());
        oldNews.setTopEndDate(news.getTopEndDate());
        oldNews.setUpdateTime(LocalDateTime.now());
        String path ="";
        String fileUrl = null;
        if(news.getIsBanner()==true) {
            if (!file.isEmpty()) {
                try {
                    // 上传图片
                    path = filePath+"/news/banner";
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
                    fileUrl = fileUrl+"/news/banner/" + File.separator + newfile.getName();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                oldNews.setBanner(fileUrl);
            }

        }
        newsRepository.save(oldNews);
    }

    /**
     * @Description 访问量增加1.
     * @Author  wangyj
     * @CreateDate 2017/5/25 11:44
     */
    @Override
    public void addPv(UUID id) {
        newsRepository.addPv(id);
    }

    public void addPv(){

    }
}

