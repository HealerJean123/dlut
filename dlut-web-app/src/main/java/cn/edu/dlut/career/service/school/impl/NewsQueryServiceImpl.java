package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.domain.school.News;
import cn.edu.dlut.career.dto.school.BannerDTO;
import cn.edu.dlut.career.dto.school.NewsDTO;
import cn.edu.dlut.career.repository.school.NewsRepository;
import cn.edu.dlut.career.service.school.NewsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import java.time.LocalDate;

import java.util.Map;
import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/5/18  17:01.
 */
@Service
@Transactional(readOnly = true)
public class NewsQueryServiceImpl implements NewsQueryService{

    @Autowired
    private NewsRepository newsRepository;

    /**
     * @Description 根据id查询该条新闻.
     * @Author  wangyj
     * @CreateDate 2017/5/18 17:05
     * @Param
     * @Return
     */
    @Override
    public News findById(UUID id) {

        News news = newsRepository.findOne(id);
        // TODO: 2017/5/18 用码表替换掉新闻栏目
        return news;
    }


    @Override
    public LinkedList<NewsDTO> findByNewsColumn(String s) {
        Pageable pageable = new PageRequest(0,10);
        LinkedList<NewsDTO> list = newsRepository.findByNewsColumnTop(s,pageable);
        return list;
    }


    /**
     * @Description 根据新闻编号查询新闻用以修改编辑.
     * @Author  wangyj
     * @CreateDate 2017/5/19 11:18
     * @Param
     * @Return
     */
    @Override
    public News findByNewsNo(String newsNo) {
        News news = newsRepository.findByNewsNo(newsNo);
        return news;
    }
    @Override
    public Page<NewsDTO> findByNewsColumnAll(String newsColumn,Pageable pageable) {

        Page<NewsDTO> list = newsRepository.findByNewsColumn(newsColumn,pageable);
        return list;
    }

    /**
     * 查询轮播图新闻列表
     * @Author liangw
     * @return
     */
    @Override
    public LinkedList<BannerDTO> findBanner() {
        Pageable pageable = new PageRequest(0,5);
        LinkedList<BannerDTO> list  = newsRepository.findBanner(pageable);
        return list;
    }


    /**
     * @Description 获取全年有招聘的日期.
     * @Author  wangyj
     * @CreateDate 2017/5/27 12:11
     * @Param
     * @Return
     */
    @Override
    public List<Map<String, String>> getRecruiterDate() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy");
        String year = LocalDateTime.now().format(fmt);
        List<Map<String, String>> list =newsRepository.getRecruiterDate(year);
        return list;
    }

    /**
     * @Description 根据日期获得当天的招聘新闻.
     * @Author  wangyj
     * @CreateDate 2017/5/27 14:23
     */
    @Override
    public List<NewsDTO> getRecruiterNewsByDate(String date) {
        List<NewsDTO> list = newsRepository.getRecruiterNewsByDate(date);

        return list;
    }

    /**
     * @Description 教师端分页查询 新闻 news
     * @Author HealerJean
     * @CreateDate 2017/5/19 11:12
     */
    @Override
    public Page<News> listPageNews(String title, String publishStartFrom, String publishStartTo, String publisEndFrom,String publishEndTo, String newsColumn , Pageable pageable) {
        Page<News> news = newsRepository.listPageNews(title, publishStartFrom, publishStartTo, publisEndFrom, publishEndTo, newsColumn, pageable);
        return news;

    }

    /**
     * @Description 门户根据标题搜索新闻.
     * @Author  wangyj
     * @CreateDate 2017/5/31 16:02
     * @Param  param  搜索参数
     * @Return
     */
    @Override
    public Page<NewsDTO> getNewsListBySearch(String param,Pageable pageable) {
        Page<NewsDTO> list =newsRepository.getNewsListBySearch(param,pageable);
        return list;
    }

    /**
     * @Description 根据身份类型(教师、校友、企业)获取不同新闻列表.
     * @Author  wangyj
     * @CreateDate 2017/6/5 9:48
     * @Param
     * @Return
     */
    @Override
    public Page<NewsDTO> getMultiList(String type,Pageable pageable) {
        Page<NewsDTO> list = null;
        if("TEACHER".equals(type)){
            //教师
            list = newsRepository.getTeacherNewsList(pageable);
        }else if ("COMPANY".equals(type)){
            //企业
            list = newsRepository.getRecNewsList(pageable);
        }else if("SCHOOLFELLOW".equals(type)){
            //校友
            list = newsRepository.getSchoolfellowNewsList(pageable);
        }
        return list;
    }
}
