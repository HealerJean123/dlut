package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.domain.school.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/5/18  9:22.
 */
public interface NewsCommandService {
    /**
     * @Description 添加一条新闻.
     * @Author  wangyj
     * @CreateDate 2017/5/22 9:14
     */
    void addNews(News news,MultipartFile file);

    /**
    * @Description 根据单个id删除新闻
    * @Author HealerJean
    * @CreateDate 2017/5/19 13:03
    */
    void deleteNewsByIds(UUID[] id);

    /**
     * @Description 修改新闻.
     * @Author  wangyj
     * @CreateDate 2017/5/22 9:15
     */
    void updateNews(News oldNews,MultipartFile file);

    /**
     * @Description pv+1.
     * @Author  wangyj
     * @CreateDate 2017/5/25 11:43
     * @Param
     * @Return
     */
    void addPv(UUID id );
}
