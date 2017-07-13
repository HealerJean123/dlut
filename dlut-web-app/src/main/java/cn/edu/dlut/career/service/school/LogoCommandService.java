package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.domain.school.Logo;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/5/31  15:23.
 */
public interface LogoCommandService {

    void addLogo(Logo logo, MultipartFile file);


    void updateLogo(Logo logo , MultipartFile file);

    void deleteLogo(UUID id);
}
