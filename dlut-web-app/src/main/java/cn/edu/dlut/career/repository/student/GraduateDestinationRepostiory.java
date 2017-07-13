package cn.edu.dlut.career.repository.student;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wei on 2017/4/12.
 */
public interface GraduateDestinationRepostiory extends CrudRepository<GraduateDestination,UUID> {
    List<GraduateDestination> findAll();

    @Query(value = "From GraduateDestination g where g.studentInfo.id=?1")
    GraduateDestination findByStuId(UUID stuId);

    @Query(value = "From GraduateDestination g where g.studentInfo.id=?1 and stuStatus = ?2")
    GraduateDestination findByStuIdAndStatus(UUID stuId,String stuStatus);

    @Query(value = "From GraduateDestination as s where " +
                "(s.studentInfo.stuNo = ?1 or ?1 = '') and "  +
                "(s.studentInfo.name like %?2% or ?2 = '') and " +
                "(s.studentInfo.departmentId = ?3 or ?3 = '') and "  +
                "(s.studentInfo.eduDegree = ?4 or ?4 = '') and "  +
                "(s.studentInfo.endDate like ?5% or ?5= '') and " +
                "(s.stuStatus = ?6 or ?6 = '') and "  +
                "(s.studentInfo.majorCode = ?7 or ?7 = '') and " +
                "(s.recName = ?8 or ?8 = '')")
    Page<GraduateDestination> findList(String stuNo, String name, String departmentId, String eduDegree, String endDate, String stuStatus, String majorCode, String recName, Pageable pageable);




    /**
     * @Description 根据毕业时间获取各学院已就业人数.
     * @Author  wangyj
     * @CreateDate 2017/4/28 9:57
     * @Param
     * @Return
     */
    @Query(value = "select new map(g.studentInfo.department as department,count(*) as count) from GraduateDestination g where g.studentInfo.endDate like ?1% and g.stuStatus='02' and g.destinationType !='70' group by g.studentInfo.department")
    List<Map<String,Object>> getAcademyEmplo(String graduateDate);

    /**
     * @Description 根据毕业时间获取各学院总人数.
     * @Author  wangyj
     * @CreateDate 2017/4/28 15:26
     * @Param
     * @Return
     */
    @Query(value = "select new map(g.studentInfo.department as department,count(*) as count) from GraduateDestination g where g.studentInfo.endDate like ?1%  group by g.studentInfo.department")
    List<Map<String,Object>> getAcademyTotal(String graduateDate);

    /**
     * @Description 根据毕业时间统计该届毕业生总人数.
     * @Author  wangyj
     * @CreateDate 2017/4/28 14:20
     * @Param
     * @Return
     */
    @Query(value = "select count(*) from GraduateDestination g where g.studentInfo.endDate like ?1% ")
    int countAllGraduate(String graduateDate);

    /**
     * @Description 根据毕业时间统计该届毕业生已就业的总数.
     * @Author  wangyj
     * @CreateDate 2017/4/28 14:21
     * @Param
     * @Return
     */
    @Query(value = "select count(*) from GraduateDestination g where g.studentInfo.endDate like ?1% and g.stuStatus='02' and g.destinationType !='70'")
    int countEmplo(String graduateDate);

    /**
     * @Description 根据毕业时间统计就业地区信息.
     * @Author  wangyj
     * @CreateDate 2017/4/28 14:31
     * @Param
     * @Return
     */
    @Query(value = "select new map(g.recProvince as name,count(*) as value) from GraduateDestination g where g.studentInfo.endDate like ?1% and g.stuStatus='02' and g.destinationType !='70' group by g.recProvince")
    List<Map<String,Object>> getEmploArea(String graduateDate);

    /**
     * @Description 根据毕业时间统计就业性质信息.
     * @Author  wangyj
     * @CreateDate 2017/4/28 15:35
     * @Param
     * @Return
     */
    @Query(value = "select new map(g.recNature as name,count(*) as value) from GraduateDestination g where g.studentInfo.endDate like ?1% and g.stuStatus='02' and g.destinationType !='70' group by g.recNature")
    List<Map<String,Object>> getEmploNature(String graduateDate);

    /**
     * @Description 根据毕业时间统计业行业信息.
     * @Author  wangyj
     * @CreateDate 2017/4/28 15:39
     * @Param
     * @Return
     */
    @Query(value = "select new map(g.recIndustry as name,count(*) as value) from GraduateDestination g where g.studentInfo.endDate like ?1% and g.stuStatus='02' and g.destinationType !='70' group by g.recIndustry")
    List<Map<String,Object>> getEmploIndustry(String graduateDate);

    /**
     * 查询某一院系在某学年的就业人数
     * @param departmentId 院系id
     * @param endDate 毕业时间
     * @return
     */
    @Query(value="select count(*) from GraduateDestination g " +
        "where g.studentInfo.departmentId=?1 and " +
        "g.studentInfo.endDate like ?2% and " +
        "g.stuStatus='02'")
    int findStuEmpNum(String departmentId, String endDate);

    /**
     * 某一院系在某学年 学生就业去向统计
     * @param departmentId
     * @param graduateDate
     * @return
     */
    @Query("select new Map(g.destinationType as name,count(*) as value) " +
        "from GraduateDestination g " +
        "where g.studentInfo.departmentId=?1 and " +
        "g.studentInfo.endDate like ?2% and " +
        "g.stuStatus='02'" +
        "group by g.destinationType")
    List<Map<String,Object>> findEmpDesList(String departmentId, String graduateDate);

    /**
     * 某一院系在某学年 学生就业地区分布统计
     * @param departmentId
     * @param graduateDate
     * @return
     */
    @Query("select new Map(g.recProvince as name,count(*) as value) " +
        "from GraduateDestination g " +
        "where g.studentInfo.departmentId=?1 and " +
        "g.studentInfo.endDate like ?2% and " +
        "g.stuStatus='02'" +
        "group by g.recProvince")
    List<Map<String,Object>> findEmpAreaList(String departmentId, String graduateDate);

    /**
     * 某一院系在某学年 学生就业单位性质统计
     * @param departmentId
     * @param graduateDate
     * @return
     */
    @Query("select new Map(g.recNature as name,count(*) as value) " +
        "from GraduateDestination g " +
        "where g.studentInfo.departmentId=?1 and " +
        "g.studentInfo.endDate like ?2% and " +
        "g.stuStatus='02'" +
        "group by g.recNature")
    List<Map<String,Object>> findEmpNatureList(String departmentId, String graduateDate);

    /**
     * 某一院系在某学年 学生就业行业分布统计
     * @param departmentId
     * @param graduateDate
     * @return
     */
    @Query("select new Map(g.recIndustry as name,count(*) as value) " +
        "from GraduateDestination g " +
        "where g.studentInfo.departmentId=?1 and " +
        "g.studentInfo.endDate like ?2% and " +
        "g.stuStatus='02'" +
        "group by g.recIndustry")
    List<Map<String,Object>> findEmpCategoryList(String departmentId, String graduateDate);

    @Modifying
    @Query(value = "UPDATE GraduateDestination as g SET stuStatus = '02' WHERE id = ?1")
    void upStatus(UUID id);




    @Query("from GraduateDestination g where g.stuStatus = ?1 and g.id = ?2 ")
    GraduateDestination findByStatusAndStuNo(String stuStatus, UUID id);

    /**
     * 根据学生学号查找就业去向
     * @param stuNo
     * @return
     */
    @Query(value = "From GraduateDestination g where g.studentInfo.stuNo=?1")
    GraduateDestination findByStuNo(String stuNo);

    /**
     * 修改应届毕业生就业去向表类型为派遣
     * @param graduateDate 毕业时间
     * @return
     */
   /* @Modifying
    @Query()
    int dispatchAll(String graduateDate);
*/
    /**
     * 修改应届毕业生就业去向学生核对状态
     * @param stuStatus 状态
     * @param graduateDate 毕业时间
     * @return
     */
    @Modifying
    @Query("update GraduateDestination g set g.stuStatus=?1 where g.studentInfo.endDate like ?2%")
    int upStuStatus(String stuStatus, int graduateDate);



    /**
     * @Description 向不是待就业且档案信息为空的的应届毕业生添加档案信息.
     * @Author  wangyj
     * @CreateDate 2017/5/10 18:28
     * @Param
     * @Return
     */
    @Modifying
    @Query(value = "UPDATE graduate_destination SET pfile_to_address = r.area_name, pfile_local = r.profile_rec_add, pfile_to_name = r.profile_rec_name,pfile_linker = r.profile_receiver, pfile_mobile = r.profile_rec_tel, report_card_address = r.area_name, report_card_rec = r.profile_rec_name FROM (SELECT s.id,s.end_date,d.area_name,d.profile_rec_name,d.profile_rec_add,d.profile_receiver,d.profile_rec_tel FROM dispatch_code d  RIGHT JOIN stu_information s ON d.area_code = s.homeland_code) r WHERE r.end_date LIKE ?1% AND graduate_destination.destination_type !='70' AND graduate_destination.id = r.id AND graduate_destination.pfile_to_name is NULL ",nativeQuery = true)
    int updateNotUnderemployed(String graduateDate);

    /**
     * @Description 将待就业的应届毕业生派遣回原籍，添加档案信息、单位信息.
     * @Author  wangyj
     * @CreateDate 2017/5/11 14:06
     * @Param
     * @Return
     */
    @Modifying
    @Query(value = "UPDATE graduate_destination SET pfile_to_address = r.area_name, pfile_local = r.profile_rec_add, pfile_to_name = r.profile_rec_name,pfile_linker = r.profile_receiver, pfile_mobile = r.profile_rec_tel, report_card_address = r.area_name, report_card_rec = r.profile_rec_name ,rec_name = r.profile_rec_name,rec_city = area_name FROM(SELECT s.id,s.end_date,d.area_name,d.profile_rec_name,d.profile_rec_add,d.profile_receiver,d.profile_rec_tel FROM dispatch_code d RIGHT JOIN stu_information s ON d.area_code = s.homeland_code) r WHERE r.end_date LIKE ?1% AND graduate_destination.destination_type ='70' AND graduate_destination.id = r.id ",nativeQuery = true)
    int updateUderemployed(String graduateDate);


}
