package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.domain.school.Logo;
import cn.edu.dlut.career.dto.school.LogoDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/6/1  10:40.
 */
public interface LogoQueryService {
    List<Logo> getLogoListByteacher(Pageable pageable);

    List<LogoDTO> getLogoListByPortals();

    Logo getById(UUID id);
}
