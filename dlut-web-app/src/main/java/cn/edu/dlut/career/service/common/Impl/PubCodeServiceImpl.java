package cn.edu.dlut.career.service.common.Impl;

import cn.edu.dlut.career.domain.common.OperateLog;
import cn.edu.dlut.career.domain.common.PubCode;
import cn.edu.dlut.career.repository.common.PubCodeRepository;
import cn.edu.dlut.career.service.common.OperateLogService;
import cn.edu.dlut.career.service.common.PubCodeServcie;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/4/24  10:20.
 */
@Service
@Transactional
public class PubCodeServiceImpl implements PubCodeServcie {
    @Autowired
    private PubCodeRepository pubCodeRepository;
    @Autowired
    private OperateLogService operateLogService;
    Logger logger = LoggerFactory.getLogger(PubCodeServiceImpl.class);

    /**
     * 添加 数据字典 代码表
     * @param pubCode
     * @return
     */
    @Override
    public PubCode addCodeMapping(PubCode pubCode) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal() ;
       //校级管理员才能添加
        if ("SCHADMIN".equals(userPrincipal.getPrincipal())||"SCHOOL".equals(userPrincipal.getPrincipal())) {
            PubCode pubCodeFinal = new PubCode();
            if (pubCode.getName() != null && !pubCode.getName().equals("") && pubCode.getType() != null && !pubCode.getType().equals("") && pubCode.getCode() != null && !pubCode.getCode().equals("")) {
               PubCode pubCodeStatus = pubCodeRepository.findByCodeAndType(pubCode.getCode().trim(), pubCode.getType().trim());
                if(pubCodeStatus ==null) {
                    pubCodeFinal.setCode(pubCode.getCode().trim());
                    pubCodeFinal.setName(pubCode.getName().trim());
                    pubCodeFinal.setType(pubCode.getType().trim());
                    PubCode pubCodeOperate = pubCodeRepository.save(pubCodeFinal);
                    //操作日志
                    OperateLog operateLog  = new OperateLog(pubCodeOperate.getId(),"数据字典");
                    operateLogService.addOptLog(operateLog,"数据字典添加");

                    return pubCodeOperate;
                }
            }
        }
        return  null;
    }

    @Override
    public Page<PubCode> findByType(String type , Pageable pageable) {
        if ("".equals(type)){
            type = null;
        }
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal() ;
        if ("SCHADMIN".equals(userPrincipal.getPrincipal())||"SCHOOL".equals(userPrincipal.getPrincipal())) {
            return pubCodeRepository.findByType(type, pageable);
        }
        return  null;
    }

    @Override
    public String deletCodeById(UUID id) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal() ;
        PubCode pubCode = pubCodeRepository.findOne(id);
        String status = null;
        if ("SCHADMIN".equals(userPrincipal.getPrincipal())||"SCHOOL".equals(userPrincipal.getPrincipal())) {
                if (pubCode !=null) {
                    pubCodeRepository.delete(id);
                    //操作日志
                    OperateLog operateLog  = new OperateLog(id,"数据字典");
                    operateLogService.addOptLog(operateLog,"数据字典删除");

                    List<PubCode> typeStatus = pubCodeRepository.findTypeByType(pubCode.getType());
                    if (typeStatus.size()!=0) {
                        status = "1";
                    }else {
                        status = "0";
                    }
                }
        }
        return status;
    }

    @Override
    public PubCode findById(UUID id) {
        return pubCodeRepository.findOne(id);
    }

    /**
     * 类型分组查看
     * @return
     */
    @Override
    public String[] findTypeArray() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal() ;
        if ("SCHADMIN".equals(userPrincipal.getPrincipal())||"SCHOOL".equals(userPrincipal.getPrincipal())) {
            return pubCodeRepository.findTypeArray();
        }else {
            return  null;
        }
    }

}
