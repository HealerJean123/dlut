package cn.edu.dlut.career.notification;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author wangyj.
 * @Date 2017/3/28  16:24.
 */
public interface NotificationRepository extends CrudRepository<Notifcations,String> {

    /**
     * @Description 根据接受者id查询其所属消息.
     * @Author  wangyj
     * @CreateDate 2017/3/28 16:36
     * @Param
     * @Return
     */
    List<Notifcations> findByToId(String recId);

    /**
     * @Description 根据接受者id查询其所属消息总数.
     * @Author  wangyj
     * @CreateDate 2017/3/28 17:02
     * @Param
     * @Return
     */
    @Query(value = "select count(*) from notifications n where n.toId =?1 and n.status = 0",nativeQuery = true)
    Integer countNotification(String id);

}
