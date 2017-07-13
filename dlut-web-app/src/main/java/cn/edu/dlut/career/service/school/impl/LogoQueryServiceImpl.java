package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.domain.school.Logo;
import cn.edu.dlut.career.dto.school.LogoDTO;
import cn.edu.dlut.career.repository.school.LogoRepository;
import cn.edu.dlut.career.service.school.LogoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/6/1  10:41.
 */
@Service
@Transactional(readOnly = true)
public class LogoQueryServiceImpl implements LogoQueryService{

    @Autowired
    private LogoRepository logoRepository;

    /**
     * @Description 教师管理logo列表.
     * @Author  wangyj
     * @CreateDate 2017/5/31 17:53
     * @Param
     * @Return
     */
    @Override
    public List<Logo> getLogoListByteacher(Pageable pageable) {
        List<Logo> list =  logoRepository.getLogolistByteacher(pageable);
        return list;
    }

    /**
     * @Description 门户获取logo列表.
     * @Author  wangyj
     * @CreateDate 2017/6/1 11:03
     * @Param
     * @Return
     */
    @Override
    public List<LogoDTO> getLogoListByPortals() {
        Pageable pageable = new PageRequest(0,10);
        return logoRepository.getLogoListByPortals(pageable);
    }

    /**
     * @Description 教师根据id查询一个logo信息.
     * @Author  wangyj
     * @CreateDate 2017/6/5 11:18
     * @Param
     * @Return
     */
    @Override
    public Logo getById(UUID id) {
        Logo logo = logoRepository.findOne(id);
        return logo;
    }
}
