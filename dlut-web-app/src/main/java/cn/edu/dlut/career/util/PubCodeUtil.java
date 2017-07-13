package cn.edu.dlut.career.util;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共代码处理类
 */
public final class PubCodeUtil {

    // 类型-性别
    public static final String TYPE_GENDER = "gender";
    // 类型-民族
    public static final String TYPE_ETHNIC = "ethnic";
    // 类型-政治面貌
    public static final String TYPE_POLITICAL = "political";
    // 类型-学历
    public static final String TYPE_EDUDEGREE = "eduDegree";
    // 类型-专业
    public static final String TYPE_MAJOR = "major";
    // 类型-培养方式
    public static final String TYPE_EDUMODE = "eduMode";
    // 类型-毕业去向
    public static final String TYPE_GRADUATETO = "graduateTo";
    // 类型-offer状态
    public static final String TYPE_OFFERSTATUS = "offerStatus";
    // 类型-审核状态
    public static final String TYPE_AUDITSTATUS = "auditStatus";
    //类型-总体审核状态
    public static final String TYPE_TOTALAUDITSTATUS ="totalAuditStatus";
    // 类型-院系
    public static final String TYPE_ACADEMY = "academy";
    // 类型-语种
    public static final String TYPE_LANGUAGE = "language";
    // 类型-学制
    public static final String TYPE_EDUYEAR = "eduYear";
    // 类型-单位性质
    public static final String TYPE_NATURE = "nature";
    // 类型-行业
    public static final String TYPE_INDUSTRY = "industry";
    // 类型-职位类别
    public static final String TYPE_JOBTYPE = "jobType";
    //类型-职位类型
    public static final String TYPE_POSITION_TYPE = "positionType";
    // 类型-改派申请原因
    public static final String TYPE_RESSIGNREASON = "reassignReason";
    //类型-空白协议书申请原因
    public static final String TYPE_BLANKPROREASON = "blankProReason";
    // 类型-违约申请原因
    public static final String TYPE_VIOLATEREASON = "violateReason";
    // 类型-省份
    public static final String TYPE_PROVINCE = "province";
    // 类型-派遣信息生源地代码
    public static final String TYPE_DISPATCHHOMECODE = "dispatchHomeCode";
    // 类型-新闻栏目
    public static final String TYPE_NEWS_COLUMN = "newsColumn";
    //类型-接收简历方式
    public static final String TYPE_RECEIVE_MODE = "receiveMode";
    //类型-薪资待遇
    public static final String TYPE_SALARY = "salary";
    //场地用途
    public static final String TYPE_AREA_USING = "areaUsing";
    //场地要求
    public static final String TYPE_AREA_REQUIRE = "areaRequire";
    //场地规模
    public static final String TYPE_AREA_SIZE = "areaSize";
    //场地审核状态
    public static final String TYPE_AREA_AUDITSTATUS="areaAuditStatus";
    //注册资金
    public static final String TYPE_COMPANY_CAPITAL="capital";
    //企业规模
    public static final String TYPE_COMPANY_SIZE="companySize";
    //主管部门
    public static final String TYPE_COMPANY_DEPARTMENT="department";



    private static boolean isInited = false;
    private static Map<String, Map<String, String>> pubDicts;

    //TODO: 锁机制
    private static void init(){
        pubDicts = new HashMap<String, Map<String, String>>();
        //性别
        pubDicts.put(TYPE_GENDER, new HashMap<String,String>(){
            {
                put("01","男");
                put("02","女");
            }
        });
        //民族
        pubDicts.put(TYPE_ETHNIC, new HashMap<String,String>(){
            {
                put("01","汉族");
                put("02","蒙古族");
                put("03","回族");
                put("04","藏族");
                put("05","维吾尔族");
                put("06","苗族");
            }
        });
        //政治面貌
        pubDicts.put(TYPE_POLITICAL, new HashMap<String,String>(){
            {
                put("10","共青团员");
                put("11","中共预备党员");
                put("12","中共党员");
                put("13","群众");
            }
        });
        //学历层次
        pubDicts.put(TYPE_EDUDEGREE, new HashMap<String,String>(){
            {
                put("00","专科毕业生");
                put("01","本科生毕业");
                put("02","硕士生毕业");
                put("03","博士生毕业");
            }
        });
        //专业
        pubDicts.put(TYPE_MAJOR, new HashMap<String,String>(){
            {
                put("01","计算机科学与技术");
                put("02","软件工程");
                put("03","数学与应用数学");
                put("04","统计学");
                put("05","自动化");
                put("06","电气工程及其自动化");
            }
        });
        //培养方式
        pubDicts.put(TYPE_EDUMODE, new HashMap<String,String>(){
            {
                put("01","定向");
                put("02","非定向");
                put("03","委培");
                put("02","保送");
            }
        });
        //毕业去向
        pubDicts.put(TYPE_GRADUATETO, new HashMap<String,String>(){
            {
                put("11","签劳动合同形式就业");
                put("10","签就业协议形式就业");
                put("12","其他录用形式就业");
                put("27","科研助理");
                put("50","国家基层项目");
                put("51","地方基层项目");
                put("46","应征义务兵");
                put("65","留级休学续读");
                put("70","待就业");
                put("75","自主创业");
                put("76","自由职业");
                put("80","升学");
                put("85","出国、出境");
            }
        });
        //OFFER状态
        pubDicts.put(TYPE_OFFERSTATUS, new HashMap<String,String>(){
            {
                put("00","待接收");
                put("01","已接收");
                put("02","已拒绝");
                put("03","已过期");
                put("04","已签约");
                put("05","毁约");
                put("06","已作废");
            }
        });
        //审核状态
        pubDicts.put(TYPE_AUDITSTATUS, new HashMap<String,String>(){
            {
                put("00","待审核");
                put("01","审核通过");
                put("02","审核不通过");
            }
        });
        //总审核状态
        pubDicts.put(TYPE_TOTALAUDITSTATUS,new HashMap<String,String>(){
            {
                put("00","待审核");
                put("01","院审核通过");
                put("02","院审核不通过");
                put("03","校审核通过");
                put("04","校审核不通过");
            }
        });
        //院系
        pubDicts.put(TYPE_ACADEMY, new HashMap<String,String>(){
            {
                put("00","校级");
                put("01","信息工程学院");
                put("02","数学与统计学院");
                put("03","电气工程学院");
            }
        });
        //语种
        pubDicts.put(TYPE_LANGUAGE, new HashMap<String,String>(){
            {
                put("01","英语");
                put("02","日语");
                put("03","法语");
                put("04","德语");
            }
        });
        //学制
        pubDicts.put(TYPE_EDUYEAR, new HashMap<String,String>(){
            {
                put("01","三年");
                put("02","四年");
                put("03","七年");
            }
        });
        //企业性质
        pubDicts.put(TYPE_NATURE, new HashMap<String,String>(){
            {
                put("01","机关");
                put("02","科研设计单位");
                put("03","高等教育单位");
                put("04","医疗卫生单位");
                put("05","国有企业");
                put("06","其他企业");
            }
        });
        //行业
        pubDicts.put(TYPE_INDUSTRY, new HashMap<String,String>(){
            {
                put("01","农、林、牧、渔业");
                put("02","建筑业");
                put("03","信息传输、软件和信息技术服务业");
                put("04","金融业");
                put("05","卫生和社会工作");
                put("06","文化、体育和娱乐业");
            }
        });
        //工作类型
        pubDicts.put(TYPE_JOBTYPE, new HashMap<String,String>(){
            {
                put("01","金融业务人员");
                put("02","公务员");
                put("03","科学研究人员");
                put("04","文学艺术工作人员");
                put("05","工程技术人员");
                put("06","其他人员");
            }
        });

        //职位类型
        pubDicts.put(TYPE_POSITION_TYPE,new HashMap<String,String>(){
            {
                put("01","兼职");
                put("02","实习");
                put("03","全职");
            }
        });
        //改派原因
        pubDicts.put(TYPE_RESSIGNREASON, new HashMap<String,String>(){
            {
                put("01","我想改派");
                put("02","家庭需要");
            }
        });
        //空白协议书申请理由
        pubDicts.put(TYPE_BLANKPROREASON, new HashMap<String,String>(){
            {
                put("01","博士首次申领");
                put("02","协议书遗失申领");
                put("03","休学复学申领协议书");
                put("04","2+2回国申领协议书");
                put("05","回家找工作");
                put("06","特殊单位（涉密单位）");
            }
        });
        pubDicts.put(TYPE_VIOLATEREASON, new HashMap<String,String>(){
            {
                put("01","个人违约-继续深造");
                put("02","个人违约-家庭原因");
                put("03","个人违约-身体原因");
                put("04","个人违约-单位承诺的条件无法兑现");
                put("05","个人违约-单位经营发生问题");
                put("06","个人违约-其他原因");
                put("07","单位违约");
            }
        });

        pubDicts.put(TYPE_PROVINCE, new HashMap<String,String>(){
            {
                put("01","北京");
                put("02","天津");
                put("03","上海");
                put("04","河南");
                put("05","广东");
            }
        });
        pubDicts.put(TYPE_NEWS_COLUMN, new HashMap<String,String>(){
            {
                put("01","新闻");
                put("02","公告");
                put("03","协议");
                put("04","咨询");
                put("05","讲座");
                put("06","活动");
                put("07","赛事");
                put("08","专场");
                put("09","需求");
                put("10","实习");
                put("11","海外");
                put("12","政策法规");
                put("13","企业招聘指南");
                put("14","海外");
                put("15","留学升学");
                put("16","校园地图");
                put("17","兄弟院校");
            }
        });
        //简历接收方式
        pubDicts.put(TYPE_RECEIVE_MODE, new HashMap<String,String>(){
            {
                put("01","两者都可");
                put("02","只邮箱接收");
                put("03","只在本系统接收");
            }
        });
        //薪资待遇
        pubDicts.put(TYPE_SALARY, new HashMap<String,String>(){
            {
                put("01","3000以下");
                put("02","3000-5000");
                put("03","5000-10000");
                put("04","10000-20000");
                put("05","20000以上");
            }
        });
        //场地用途
        pubDicts.put(TYPE_AREA_USING, new HashMap<String,String>(){
            {
                put("01","笔试场地");
                put("02","面试场地");
                put("03","签约场地");
                put("04","洽谈场地");
                put("05","专场招聘");
            }
        });
        //场地要求
        pubDicts.put(TYPE_AREA_REQUIRE, new HashMap<String,String>(){
            {
                put("01","多媒体教室");
                put("02","付费报告厅");
                put("03","面试间");
                put("04","机房");

            }
        });
        //场地规模
        pubDicts.put(TYPE_AREA_SIZE, new HashMap<String,String>(){
            {
                put("001","10人面试间");
                put("002","20-50人");
                put("003","50-100人");
                put("004","100-200人");
                put("005","图书馆报告厅（308人，付费");
                put("006","科技园报告厅（396人，付费");
                put("007","研教楼报告厅（330人，付费");
                put("008","百川报告厅（398人，付费");
                put("009","图书馆多功能厅（100人，付费");
            }
        });
        //场地审核状态
        pubDicts.put(TYPE_AREA_AUDITSTATUS,new HashMap<String,String>(){
            {
                put("00","待处理");
                put("01","借用中");
                put("02","已落实");
                put("03","已回复");
                put("04","未落实");
            }
        });
        //注册资金
        pubDicts.put(TYPE_COMPANY_CAPITAL,new HashMap<String,String>(){
            {
                put("00","50W");
                put("01","50-100W");
                put("02","100-150W");
                put("03","150-200W");
                put("04","200W以上");
            }
        });
        //公司规模
        pubDicts.put(TYPE_COMPANY_SIZE,new HashMap<String,String>(){
            {
                put("00","100人");
                put("01","500人");
                put("02","1000人");
                put("03","1500人");
                put("04","2000以上");
            }
        });
        //公司规模
        pubDicts.put(TYPE_COMPANY_DEPARTMENT,new HashMap<String,String>(){
            {
                put("00","组织部");
                put("01","研发部");
                put("02","运营部");
            }
        });
        isInited = true;
    }



    /**
     * 获取指定类别的公共代码表
     * @param type
     * @return
     */
    public static Map<String, String> getDictMap(String type){
        if (!isInited) init();
        return pubDicts.getOrDefault(type, null);
    }


    /**
     * 获取指定类别、指定代码的名称值
     * @param type
     * @param code
     * @return
     */
    public static String getName(String type, String code) {
        if (!isInited) init();
        return pubDicts.get(type).getOrDefault(code,"");
    }


}
