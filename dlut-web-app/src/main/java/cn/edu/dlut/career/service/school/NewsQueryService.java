package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.domain.school.News;

import cn.edu.dlut.career.dto.school.BannerDTO;
import cn.edu.dlut.career.dto.school.NewsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/5/18  17:00.
 */
public interface NewsQueryService {
    News findById(UUID id);



    LinkedList<NewsDTO> findByNewsColumn(String s);


    /**
    * @Description 教师端分页查询 新闻 news
    * @Author HealerJean
    * @CreateDate 2017/5/19 10:00
    */

    Page<News> listPageNews(String title, String publishStartFrom, String publishStartTo, String publisEndFrom,String publishEndTo, String newsColumn , Pageable pageable);
    News findByNewsNo(String newsNo);

    Page<NewsDTO> findByNewsColumnAll(String newsColumn,Pageable pageable);

    /**
     * 查询轮播图新闻列表
     * @Author liangw
     * @return
     */
    LinkedList<BannerDTO> findBanner();

    List<Map<String,String>> getRecruiterDate();

    List<NewsDTO> getRecruiterNewsByDate(String date);

    Page<NewsDTO> getNewsListBySearch(String param,Pageable pageable);

    Page<NewsDTO> getMultiList(String type,Pageable pageable);
}
