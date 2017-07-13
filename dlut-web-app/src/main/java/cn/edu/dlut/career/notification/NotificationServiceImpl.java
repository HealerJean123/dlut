package cn.edu.dlut.career.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author wangyj.
 * @Date 2017/3/28  16:26.
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TemplateRepository templateRepository;

    /**
     * @Description 添加一条消息.
     * @Author  wangyj
     * @CreateDate 2017/3/28 16:36
     * @Param
     * @Return
     */
    @Override
    public void addNotification(Notifcations notifcations){
        //跟据action查询消息所使用的模板
        String action = notifcations.getAction();
        String template = templateRepository.findTemplateByAction(action);
        //获取notification中需要替换的变量var
        Map<String,String> varMap = notifcations.getVar();
        //替换模板中变量
        for (String key :varMap.keySet()){
            template= StringUtils.replace(template,"{"+key+"}",varMap.get(key));
        }
        notifcations.setContent(template);
        notificationRepository.save(notifcations);
    }

    /**
     * @Description 根据接受者id查询其所属消息.
     * @Author  wangyj
     * @CreateDate 2017/3/28 16:36
     * @Param
     * @Return
     */
    @Override
    public List<Notifcations> findByToId(String id){
        List<Notifcations> notifcationsList = notificationRepository.findByToId(id);
        return notifcationsList;
    }

    /**
     * @Description 根据接收者id查询其所属消息总数.
     * @Author  wangyj
     * @CreateDate 2017/3/28 16:46
     * @Param
     * @Return
     */
    @Override
    public Integer countNotification(String id) {

        return notificationRepository.countNotification(id);
    }
}
