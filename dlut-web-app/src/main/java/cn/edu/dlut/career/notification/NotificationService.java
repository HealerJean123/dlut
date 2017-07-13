package cn.edu.dlut.career.notification;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消息相关接口
 * @Author wangyj.
 * @Date 2017/3/28  16:25.
 */
public interface NotificationService {

    /**
     * @Description 添加一条消息.
     * @Author  wangyj
     * @CreateDate 2017/3/28 16:36
     * @Param
     * @Return
     */
    public List<Notifcations> findByToId(String id);

    /**
     * @Description 根据接受者id查询其所属消息.
     * @Author  wangyj
     * @CreateDate 2017/3/28 16:36
     * @Param
     * @Return
     */
    public void addNotification(Notifcations notifcations);

    /**
     * @Description 根据接收者id查询其所属消息.
     * @Author  wangyj
     * @CreateDate 2017/3/28 16:46
     * @Param
     * @Return
     */
    public Integer countNotification(String id);
}
