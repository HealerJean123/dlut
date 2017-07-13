package cn.edu.dlut.career.repository.school;

import cn.edu.dlut.career.domain.school.TeacherInfo;
import cn.edu.dlut.career.dto.school.TeacherLoginDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by HealerJean on 2017/4/13.
 */
@Transactional
@Repository
public interface TeacherInfoRepository extends CrudRepository<TeacherInfo,UUID>{

        List<TeacherInfo> findAll();

        @Query("select  new cn.edu.dlut.career.dto.school.TeacherLoginDTO( t.id,  t.name,  t.username,  t.pwd,  t.salt ,t.departmentId, t.department,t.type,t.isAdmin) from TeacherInfo t where t.username=?1")
        TeacherLoginDTO findLoginInfo(String username);
}
