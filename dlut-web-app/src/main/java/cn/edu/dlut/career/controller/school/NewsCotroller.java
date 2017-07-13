package cn.edu.dlut.career.controller.school;

import cn.edu.dlut.career.domain.school.News;
import cn.edu.dlut.career.dto.school.BannerDTO;
import cn.edu.dlut.career.dto.school.LogoDTO;
import cn.edu.dlut.career.dto.school.NewsDTO;
import cn.edu.dlut.career.dto.school.NewsListDTO;
import cn.edu.dlut.career.service.school.LogoQueryService;
import cn.edu.dlut.career.service.school.NewsCommandService;
import cn.edu.dlut.career.service.school.NewsQueryService;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @Author wangyj.
 * @Date 2017/5/18  9:26.
 */
@Controller
public class NewsCotroller {

    @Autowired
    private NewsCommandService newsCommandService;

    @Autowired
    private NewsQueryService newsQueryService;

    @Autowired
    private LogoQueryService logoQueryService;


    @GetMapping("teacher/tea_newsAdd.html")
    public String getNewsAdd() {
        return "teacherHTML/tea_newsAdd";
    }

    /**
     * @Description 新闻栏目码表.
     * @Author wangyj
     * @CreateDate 2017/5/22 17:20
     * @Param
     * @Return
     */
    @ModelAttribute("newsColumn")
    public Map<String, String> nature() {
        return PubCodeUtil.getDictMap("newsColumn");
    }

    //获取展示页面所需logo相关
    @ModelAttribute("logoList")
    public List<LogoDTO> getLogoList(){
        List<LogoDTO> logoList =logoQueryService.getLogoListByPortals();
        return logoList;
    }

    /**
     * @Description 添加一条新闻.
     * @Author wangyj
     * @CreateDate 2017/5/18 16:55
     * @Param
     * @Return
     */
    @PostMapping("/teacher/news")
    public String addNews(News news,@RequestParam("file") MultipartFile file) {
        newsCommandService.addNews(news,file);
        return "teacherHTML/tea_news";
    }

    /**
     * 跳转到新闻主页，并将查询到的数据展示出来
     *
     * @return
     */
    @GetMapping("/index.html")
    public ModelAndView queryNews() {
        ModelAndView mv = new ModelAndView("index");
        //新闻数据
        LinkedList<NewsDTO> news =newsQueryService.findByNewsColumn("01");
        // 咨询
        LinkedList<NewsDTO> consultation =newsQueryService.findByNewsColumn("04");
        // 专场
        LinkedList<NewsDTO> special =newsQueryService.findByNewsColumn("08");
        //轮播图
        LinkedList<BannerDTO> banner = newsQueryService.findBanner();

        NewsListDTO newsListDTO = new NewsListDTO();

        if(consultation!=null) {
            newsListDTO.setConsultation(consultation);
        }
        if(news!=null) {
            newsListDTO.setNews(news);
        }
        if(special!=null) {
            newsListDTO.setSpecial(special);
        }
        if(banner!=null){
            newsListDTO.setBanner(banner);
        }
        mv.addObject("newsListDTO", newsListDTO);
        return mv;
    }

    /**
     * 新闻主页单独选项卡的列表
     * @param newsColumn 新闻选项卡参数
     * @return
     */
    @GetMapping("/portals/columnList")
    @ResponseBody
    public  LinkedList<NewsDTO> queryNewsByColumn(String newsColumn){
        //新闻数据
        LinkedList<NewsDTO> news =newsQueryService.findByNewsColumn(newsColumn);
        return news;
    }

    /**
     * @Description .
     * @Description 根据新闻编号查询新闻(用以修改编辑新闻).
     * @Author wangyj
     * @CreateDate 2017/5/18 16:59
     * @Param
     * @Return
     */
    @GetMapping("/teacher/news/{newsNo}")
    @ResponseBody
    public ModelAndView getNewsByNewsNo(@PathVariable String newsNo) {
        ModelAndView mv = new ModelAndView("teacherHTML/tea_newsDetails");
        News news = newsQueryService.findByNewsNo(newsNo);
        mv.addObject(news);
        return mv;
    }


    /**
     * @Description 修改一条新闻.
     * @Author wangyj
     * @CreateDate 2017/5/19 17:55
     * @Param
     * @Return
     */
    @PostMapping("/teacher/update/news")
    public String updateNews(News news,@RequestParam MultipartFile file) {

        newsCommandService.updateNews(news,file);
        return "teacherHTML/tea_news";
    }

    /**
     * @Description 教师进入news 分页 查询
     * @Author HealerJean
     * @CreateDate 2017/5/19 11:13
     */
    @GetMapping("/teacher/teaNews.html")
    public String getNewsHtml() {
        return "teacherHTML/tea_news";
    }


    /**
     * @Description 教师端分页查询 新闻 news
     * @Author HealerJean
     * @CreateDate 2017/5/19 11:12
     */
    @GetMapping("/teacher/pageNews")
    @ResponseBody
    public Page<News> pageNews(String title, String publishStartFrom, String publishStartTo, String publisEndFrom, String publishEndTo, String newsColumn, Pageable pageable) {
        Page<News> news = newsQueryService.listPageNews(title, publishStartFrom, publishStartTo, publisEndFrom, publishEndTo, newsColumn, pageable);
        if (news != null) {
            return news;
        } else {
            return null;
        }
    }

    /**
     * @param id 传入数组id
     * @Description 批量删除
     * @Author HealerJean
     * @CreateDate 2017/5/24 11:22
     */
    @GetMapping("/teacher/deleteBatchNewsByIds")
    @ResponseBody
    public String deleteNewsByIds(@RequestParam UUID[] id) {
        try {
            newsCommandService.deleteNewsByIds(id);
            return "删除成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 进入新闻详情页面
     *
     * @param id
     * @return
     */
    @GetMapping("/portals/newspage.html")
    public ModelAndView findNewsById(@RequestParam UUID id) {
        ModelAndView mv = new ModelAndView("portals/newspage");
        News news = newsQueryService.findById(id);
        //访问量增加1
        newsCommandService.addPv(id);
        mv.addObject("news", news);
        return mv;
    }

    /**
     * 新闻更多页面
     *
     * @param newsColumn
     * @return
     */
    @GetMapping("/portals/moreNews")
    @ResponseBody
    public Page<NewsDTO> findMoreNews(String newsColumn, Pageable pageable) {
        Page<NewsDTO> page = newsQueryService.findByNewsColumnAll(newsColumn, pageable);
        return page;
    }

    /**
     * 进入“更多”页面
     * @param newsColumn
     * @return
     */
    @GetMapping("/portals/newslist.html")
    public ModelAndView goMore(String newsColumn) {
        ModelAndView mv = new ModelAndView("portals/newslist");
        mv.addObject("newsColumn",newsColumn);
        return mv;
    }


    /**
     * @Description 获取全年有招聘的日期.
     * @Author  wangyj
     * @CreateDate 2017/5/27 11:21
     */
    @GetMapping("/portals/recruiterDate")
    @ResponseBody
    public List<Map<String,String>> getRecruiterDate(){
        List<Map<String,String>> list =newsQueryService.getRecruiterDate();
        return list;
    }

    /**
     * @Description 根据日期获得当天的招聘新闻.
     * @Author  wangyj
     * @CreateDate 2017/5/27 14:23
     */
    @GetMapping("/portals/recruiterNews")
    @ResponseBody
    public List<NewsDTO> getRecruiterNewsByDate(String date){
        List<NewsDTO> list = newsQueryService.getRecruiterNewsByDate(date);
        return list;
    }

    /**
     * 进入搜索结果页面
     * @param param 搜索参数
     * @return
     */
    @GetMapping("/portals/newsSearchList")
    public ModelAndView goSearchList(String param) {
        ModelAndView mv = new ModelAndView("portals/lists");
        mv.addObject("searchParam",param);
        return mv;
    }

    /**
     * @Description 搜索框根据新闻标题查询新闻列表.
     * @Author  wangyj
     * @CreateDate 2017/5/31 16:08
     * @Param  param 搜索参数
     * @Return
     */
    @GetMapping("/portals/searchBox")
    @ResponseBody
    public Page<NewsDTO> getNewsListBySearch(String param,Pageable pageable){
        Page<NewsDTO> newsListBySearch = newsQueryService.getNewsListBySearch(param,pageable);
        return newsListBySearch;
    }


    /**
     * 进入教师、校友、企业新闻展示页面
     * @param  type 类型
     * @return
     */
    @GetMapping("/portals/newsMultiList")
    public ModelAndView goNewsList(String type) {
        ModelAndView mv = new ModelAndView("portals/multiList");
        mv.addObject("type",type);
        return mv;
    }


    /**
     * @Description 根据身份类型(教师、校友、企业)获取不同新闻列表.
     * @Author  wangyj
     * @CreateDate 2017/6/5 9:41
     * @Param
     * @Return
     */
    @GetMapping("/portals/multiTypeList")
    @ResponseBody
    public Page<NewsDTO> getMultiList(String type,Pageable pageable){
        Page<NewsDTO> newsMultiList = newsQueryService.getMultiList(type,pageable);
        return  newsMultiList;
    }
}
