package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.CareerFairIssue;
import cn.edu.dlut.career.repository.company.CareerFairIssueRepository;
import cn.edu.dlut.career.service.company.CareerFairIssueCommandService;
import cn.edu.dlut.career.service.company.CareerFairIssueQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 *  双选会，招聘会信息 的相关事项表 服务层
 * Created by HealerJean on 2017/4/6.
 */
@Service
@Transactional
public class CareerFairIssueCommandServiceImpl implements CareerFairIssueCommandService {

    @Autowired
    CareerFairIssueRepository careerFairIssueRepository;
    @Override
    public CareerFairIssue saveCareerFairIssue(CareerFairIssue careerFairIssue) {
        return careerFairIssueRepository.save(careerFairIssue);
    }


    @Override
    public CareerFairIssue updateCareerFairIssue(CareerFairIssue careerFairIssue) {
        return careerFairIssueRepository.save(careerFairIssue);
    }

    @Override
    public String deleteCareerFairIssue(UUID id) {
        try {
            careerFairIssueRepository.delete(id);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
