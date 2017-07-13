package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.student.ReportCardInfo;
import cn.edu.dlut.career.repository.student.ReportCardInfoRepository;
import cn.edu.dlut.career.service.student.ReportCardInfoCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 *  报到证签发信息 服务层
 * Created by HealerJean on 2017/4/14.
 */
@Service
@Transactional
public class ReportCardInfoCommandServiceImpl implements ReportCardInfoCommandService {
    @Autowired
    private ReportCardInfoRepository reportCardInfoRepository;
    @Override
    public ReportCardInfo saveReportCardInfo(ReportCardInfo reportCardInfo) {
        return reportCardInfoRepository.save(reportCardInfo);
    }


    @Override
    public ReportCardInfo updateReportCardInfo(ReportCardInfo reportCardInfo) {
        return reportCardInfoRepository.save(reportCardInfo);
    }

    @Override
    public String deleteReportCardInfo(UUID id) {
        try {
            reportCardInfoRepository.delete(id);
            return  "ok";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
   }
