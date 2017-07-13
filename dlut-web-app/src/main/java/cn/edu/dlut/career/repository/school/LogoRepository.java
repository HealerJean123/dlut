package cn.edu.dlut.career.repository.school;

import cn.edu.dlut.career.domain.school.Logo;
import cn.edu.dlut.career.dto.school.LogoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/5/31  16:39.
 */
@Repository
public interface LogoRepository extends CrudRepository<Logo,UUID> {

    /**
     * @Description 教师获取logo列表.
     * @Author  wangyj
     * @CreateDate 2017/5/31 17:55
     * @Param
     * @Return
     */
    @Query(value = "From Logo l order by l.creatTime desc")
    List<Logo> getLogolistByteacher(Pageable pageable);

    /**
     * @Description 门户获取企业logo列表.
     * @Author  wangyj
     * @CreateDate 2017/6/1 10:04
     * @Param
     * @Return
     */
    @Query(value = "select new cn.edu.dlut.career.dto.school.LogoDTO(l.logo,l.url) from Logo l order by l.sortNo asc,l.creatTime desc")
    List<LogoDTO> getLogoListByPortals(Pageable pageable);
}
