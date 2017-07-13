package cn.edu.dlut.career.repository.school;

import cn.edu.dlut.career.domain.school.News;
import cn.edu.dlut.career.dto.school.BannerDTO;
import cn.edu.dlut.career.dto.school.NewsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 新闻信息持久层
 *
 * @Author wangyj.
 * @Date 2017/5/18  9:14.
 */
@Repository
public interface NewsRepository extends CrudRepository<News, UUID> {


    /**
     * @Description 页面访问量加1.
     * @Author wangyj
     * @CreateDate 2017/5/18 17:11
     * @Param
     * @Return
     */
    @Modifying
    @Query(value = "update news set pv = pv + 1 where id  =?1 ", nativeQuery = true)
    void addPv(UUID id);


    /**
     * 首页新闻展示查询置顶的数据
     *
     * @param
     * @return
     */
    @Query(value = "select new cn.edu.dlut.career.dto.school.NewsDTO(n.id,n.title,n.publishDate) from News n where (n.newsColumn = ?1 AND (n.isTop = FALSE OR (n.isTop = TRUE AND n.topEndDate <= to_char(now(), 'YYYY-MM-DD')))\n" +
        "AND n.endDate>= to_char(now(), 'YYYY-MM-DD')) OR (n.newsColumn = ?1 AND n.isTop = true AND n.topEndDate >= to_char(now(), 'YYYY-MM-DD')) order by n.isTop DESC,n.topEndDate DESC,n.sortNo ASC,n.publishDate DESC")
    LinkedList<NewsDTO> findByNewsColumnTop(String s, Pageable pageable);

    /**
     * @Description 根据新闻编号查询新闻.
     * @Author wangyj
     * @CreateDate 2017/5/19 16:53
     * @Param
     * @Return
     */
    @Query(value = "select * from news n where n.news_no=?1", nativeQuery = true)
    News findByNewsNo(String newsNo);

    /**
     * @Description 教师端分页查询 新闻 news
     * @Author HealerJean
     * @CreateDate 2017/5/19 11:12
     */

    @Query(value = "from News as n where " +
        "(n.title like %?1% or ?1 ='') and " +
        "(n.publishDate  >= ?2  or ?2  ='') and " +
        "(n.publishDate  <= ?3 or ?3  ='') and  " +
        "(n.endDate  >= ?4  or ?4  ='') and " +
        "(n.endDate  <= ?5   or ?5  ='') and " +
        "(n.newsColumn = ?6 or ?6 ='')")
    Page<News> listPageNews(String title, String publishStartFrom, String publishStartTo, String publisEndFrom, String publishEndTo, String newsColumn, Pageable pageable);


    /**
     * 新闻展示查询
     *
     * @param s
     * @return
     */
    @Query(value = "select new cn.edu.dlut.career.dto.school.NewsDTO(n.id,n.title,n.publishDate,n.isTop) from News n where (n.newsColumn = ?1 AND (n.isTop = FALSE OR (n.isTop = TRUE AND n.topEndDate <= to_char(now(), 'YYYY-MM-DD')))\n" +
        "AND n.endDate>= to_char(now(), 'YYYY-MM-DD')) OR (n.newsColumn = ?1 AND n.isTop = true AND n.topEndDate >= to_char(now(), 'YYYY-MM-DD')) order by n.isTop DESC,n.topEndDate DESC,n.sortNo ASC,n.publishDate DESC")
    Page<NewsDTO> findByNewsColumn(String s, Pageable pageable);

    /**
     * 查询轮播图新闻列表
     *
     * @return
     * @Author liangw
     */
    @Query(value = "select new cn.edu.dlut.career.dto.school.BannerDTO(n.id,n.banner) from News n where n.isBanner=true order by n.isTop DESC,n.topEndDate DESC,n.sortNo ASC,n.publishDate DESC")
    LinkedList<BannerDTO> findBanner(Pageable pageable);

    /**
     * @Description 获取全年有招聘的日期.
     * @Author wangyj
     * @CreateDate 2017/5/27 12:11
     */
    @Query(value = "select new map (n.recruiterDate as date) from News n where n.newsColumn ='08' and n.recruiterDate like ?1% group by n.recruiterDate")
    List<Map<String, String>> getRecruiterDate(String year);

    /**
     * @Description 根据日期获得当天的招聘新闻.
     * @Author wangyj
     * @CreateDate 2017/5/27 14:14
     */
    @Query(value = "select new cn.edu.dlut.career.dto.school.NewsDTO(n.id,n.title) from News n where n.newsColumn ='08' and n.recruiterDate =?1")
    List<NewsDTO> getRecruiterNewsByDate(String date);

    /**
     * @Description .
     * @Author  wangyj
     * @CreateDate 2017/5/31 16:02
     * @Param  param  搜索参数
     * @Return
     */
    @Query(value = "select new cn.edu.dlut.career.dto.school.NewsDTO(n.id,n.title,n.publishDate) from News n where n.title like %?1% order by n.publishDate DESC")
    Page<NewsDTO> getNewsListBySearch(String param ,Pageable pageable);

    /**
     * @Description 根据身份类型-教师获取新闻列表.
     * @Author  wangyj
     * @CreateDate 2017/6/5 9:45
     * @Param  '09' 企业需求  ‘15’留学升学  ‘03’协议  ‘16‘ 校园地图
     * @Return
     */
    @Query(value = "select new cn.edu.dlut.career.dto.school.NewsDTO(n.id,n.title,n.publishDate) from News n where n.newsColumn in ('09','15','03','16') order by n.publishDate DESC")
    Page<NewsDTO> getTeacherNewsList(Pageable pageable);


    /**
     * @Description 根据身份类型-企业获取新闻列表.
     * @Author  wangyj
     * @CreateDate 2017/6/5 9:45
     * @Param  '12' 政策法规  ‘13’企业招聘指南  ‘03’协议  ‘01‘ 新闻
     * @Return
     */
    @Query(value = "select new cn.edu.dlut.career.dto.school.NewsDTO(n.id,n.title,n.publishDate) from News n where n.newsColumn in ('01','12','13','03') order by n.publishDate DESC")
    Page<NewsDTO> getRecNewsList(Pageable pageable);

    /**
     * @Description 根据身份类型-校友获取新闻列表.
     * @Author  wangyj
     * @CreateDate 2017/6/5 9:45
     * @Param  '09' 企业需求  ‘15’留学升学  ‘17’兄弟高校  ‘16‘ 校园地图
     * @Return
     */
    @Query(value = "select new cn.edu.dlut.career.dto.school.NewsDTO(n.id,n.title,n.publishDate) from News n where n.newsColumn in ('09','15','16','17') order by n.publishDate DESC")
    Page<NewsDTO> getSchoolfellowNewsList(Pageable pageable);
}
