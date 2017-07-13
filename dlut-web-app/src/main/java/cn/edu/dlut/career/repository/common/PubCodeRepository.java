package cn.edu.dlut.career.repository.common;

import cn.edu.dlut.career.domain.common.PubCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * 数据字典持久层
 * @Author wangyj.
 * @Date 2017/4/24  10:16.
 */
@Repository
public interface PubCodeRepository extends CrudRepository<PubCode,UUID>{

    //根据类型进行查询
    @Query("from PubCode c where (c.type = ?1 or ?1 = null)")
    Page<PubCode> findByType(String type, Pageable pageable);



    //将类型进行分组
    @Query(value = "SELECT DISTINCT type from pub_code  ORDER BY type DESC",nativeQuery = true)
    String[] findTypeArray();

    //根据代码和类型查看有没有数据
    @Query("from  PubCode c where c.code = ?1 and c.type = ?2")
    PubCode findByCodeAndType(String code , String type);



    @Query(" from  PubCode c where  c.type = ?1")
    List<PubCode> findTypeByType(String type);


}
