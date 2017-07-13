package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.Field;
import cn.edu.dlut.career.dto.company.FieldDTO;
import cn.edu.dlut.career.dto.company.FieldDTO2;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/5/26.
 */
@Repository
public interface FieldRepository extends CrudRepository<Field,UUID> {


    /**
    * @author wei  2017/5/27
    * @method
    * @param  fieldSize  场地规模
     * @param  fieldRequire 场地类型
     * @param  fieldUsing 场地用途
     * @param  auditStatus 审核状态
     * @param  startDate 开始日期
    * @return List<FieldDTO>
    * @description 学校动态查询场地租用信息
    */
    @Query(value = "select new cn.edu.dlut.career.dto.company.FieldDTO2(f.id, f.fieldUsing, f.startDate, f.fieldAddress, f.fieldRequire, f.fieldSize, f.auditStatus,f.startTime,f.endTime,f.recName) From Field f WHERE (fieldSize = ?1 or ?1 = '') AND (fieldRequire = ?2 or ?2 = '') AND (fieldUsing = ?3 or ?3 = '')" +
        " AND (auditStatus = ?4 or ?4 = '') AND (startDate = ?5 or ?5 = '')  AND (recName like %?6% or ?6 = '')")
    List<FieldDTO2> findDynamic(String fieldSize,
                                String fieldRequire,
                                String fieldUsing,
                                String auditStatus,
                                String startDate, String recName);


    /**
     * @author wei  2017/5/27
     * @method
     * @param  fieldSize  场地规模
     * @param  fieldRequire 场地类型
     * @param  fieldUsing 场地用途
     * @param  auditStatus 审核状态
     * @param  startDate 开始日期
     * @return List<FieldDTO>
     * @description 公司动态查询场地租用信息
     */
    @Query(value = "select new cn.edu.dlut.career.dto.company.FieldDTO(f.id, f.fieldUsing, f.startDate, f.fieldAddress, f.fieldRequire, f.fieldSize, f.auditStatus,f.startTime,f.endTime) From Field f WHERE (fieldSize = ?1 or ?1 = '') AND (fieldRequire = ?2 or ?2 = '') AND (fieldUsing = ?3 or ?3 = '')" +
        " AND (auditStatus = ?4 or ?4 = '') AND (startDate = ?5 or ?5 = '') AND recId = ?6")
    List<FieldDTO> findDynamic(String fieldSize, String fieldRequire, String fieldUsing, String auditStatus, String startDate, UUID id);
}
