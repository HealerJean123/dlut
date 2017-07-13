package cn.edu.dlut.career.notification;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author wangyj.
 * @Date 2017/3/28  16:58.
 */
public interface TemplateRepository extends CrudRepository<Template,String> {
    /**
     * @Description 根据action查询模板.
     * @Author  wangyj
     * @CreateDate 2017/3/28 17:02
     * @Param
     * @Return
     */
    @Query(value = "select t.template from Template t where t.action=?1")
    String  findTemplateByAction(String action);
}
