package cn.edu.dlut.career.dto.company;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Description 教师发布的大型/组团招聘会信息 ，用于企业端
 * Created by HealerJean
 * DATE   2017/5/26 14:59.
 */
public class ComoanyJobFairDTO {

    //主键
    private UUID id;

    //参会公司数量
    private long recNum;

    //招聘会名称
    private String name;

    //招聘会简介
    private String description;

    //类型：1 大型招聘,2 组团招聘
    private String type;

    //招聘会开始时间
    private LocalDateTime fairStartTime;

    //招聘会结束时间
    private LocalDateTime fairEndTime;

    //报名开始时间
    private LocalDateTime startTime;

    //报名结束时间
    private LocalDateTime endTime;

    //召开地点
    private String location;

    //是否收取服务费：1收取，2，不收取
    private String needCost;

    //	邀请函
    private String invitation;

    //	校验码
    private String checkCode;

    //	创建人
    private String creator;

    //创建时间
    private LocalDateTime createTime;

    //最后修改时间
    private LocalDateTime updateTime;

    //	最后修改人
    private String updatePerson;
}
