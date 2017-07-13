package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.domain.company.OfferTemplate;
import cn.edu.dlut.career.domain.company.RecOffer;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.repository.company.CompanyInfoRepository;
import cn.edu.dlut.career.repository.company.OfferTemplateRepositry;
import cn.edu.dlut.career.repository.company.RecOfferRepository;
import cn.edu.dlut.career.repository.student.StudentInfoRepository;
import cn.edu.dlut.career.service.company.RecOfferCommandService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/4/13.
 */
@Service
@Transactional
public class RecOfferCommandServiceImpl implements RecOfferCommandService{

    @Autowired
    private RecOfferRepository recOfferRepository;
    @Autowired
    private StudentInfoRepository studentInfoRepository;
    @Autowired
    private CompanyInfoRepository companyInfoRepository;
    @Autowired
    private OfferTemplateRepositry offerTemplateRepositry;







    /**
     * 保存、更新offer信息
     * @param recOffer
     */
    @Override
    public void save(RecOffer recOffer) {
        recOfferRepository.save(recOffer);
    }


    @Override
    /**
    * @author wei  2017/6/6
    * @method update
    * @param [uuid, auditStatus, stuReceivingStatus]
    * @return void
    * @description  审核学生offer
    */
    public void update(UUID uuid ,String auditStatus,String stuReceivingStatus) {
        recOfferRepository.update(uuid,auditStatus,stuReceivingStatus);
    }

    @Override
    /**
     * @author wei  2017/6/6
     * @method sendOffer
     * @param [stuId, templateId]
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description  企业发送offer
     */
    public RecOffer sendOffer(UUID stuId, UUID templateId) {
        //查到学生信息
        StudentInfo studentInfo = studentInfoRepository.findById(stuId);
        //拿到公司信息
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        CompanyInfo companyInfo = companyInfoRepository.findOne(user.getId());
        List<RecOffer> r = recOfferRepository.findByRecIdAndStuId(user.getId(),stuId);
        if(!r.isEmpty()){
            return null;
        }else{
        //查询模板信息
        OfferTemplate offerTemplate = offerTemplateRepositry.findOne(templateId);
        RecOffer recOffer = new RecOffer();
        recOffer.setStuNo(studentInfo.getStuNo());
        recOffer.setStuId(studentInfo.getId());
        recOffer.setRealName(studentInfo.getName());
        recOffer.setIdCard(studentInfo.getIdCard());
        recOffer.setEduDegree(studentInfo.getEduDegree());
        recOffer.setMajorCode(studentInfo.getMajorCode());
        recOffer.setEthnic(studentInfo.getEthnic());
        recOffer.setDepartmentId(studentInfo.getDepartmentId());
        recOffer.setEduMode(studentInfo.getEduMode());
        recOffer.setHomelandName(studentInfo.getHomelandName());
        recOffer.setEduYear(studentInfo.getEduYear());
        recOffer.setStartDate(studentInfo.getStartDate());
        recOffer.setEndDate(studentInfo.getEndDate());

        recOffer.setRecId(companyInfo.getId());
        recOffer.setRecName(companyInfo.getName());
        recOffer.setOrgCode(companyInfo.getOrgCode());
        recOffer.setNature(companyInfo.getNature());
        recOffer.setIndustry(companyInfo.getIndustry());
        recOffer.setDepartmentBelong(companyInfo.getDepartment());
        recOffer.setRecAddress(companyInfo.getAddress());
        //所在省份???
        recOffer.setRecProvince(companyInfo.getProvince());
        recOffer.setRecCity(companyInfo.getCity());
        recOffer.setRecDepartment(companyInfo.getDepartment());
        recOffer.setRecLinker(companyInfo.getContacts());
        recOffer.setRecMobile(companyInfo.getTelphone());
        recOffer.setRecTelphone(companyInfo.getFixedTelphone());
        recOffer.setRecZipcode(companyInfo.getZipCode());

        recOffer.setCategory(offerTemplate.getJobType());
        //是否需要报到证
        recOffer.setHasReportCard("0");
        //是否接受档案
        recOffer.setIsPfile(offerTemplate.getIsPfile());
        recOffer.setPfileToName(offerTemplate.getPfileToName());
        recOffer.setPfileToDepart(offerTemplate.getPfileToDepart());
        recOffer.setPfileToAddress(offerTemplate.getPfileToAddress());
        recOffer.setPfileToLocal(offerTemplate.getPfileToLocal());
        recOffer.setPfileToRecipient(offerTemplate.getPfileToRecipient());
        recOffer.setPfileToPhone(offerTemplate.getPfileToPhone());
        //是否解决户口(1解决0不解决)
        recOffer.setIsSolveHukou(offerTemplate.getIsSolveHukou());
        //是否允许不迁入(1允许不迁入0不允许不迁入)
        recOffer.setIsNotMoveHuKou(offerTemplate.getIsNotMoveHuKou());
        //确认是否迁入户口
        recOffer.setIsConfirmHuKou(offerTemplate.getIsNotMoveHuKou());
        //户口是否在学校
        recOffer.setIsSchHuKou(offerTemplate.getIsNotMoveHuKou());
        recOffer.setHukouToAddress(offerTemplate.getHukouToAddress());
        recOffer.setStuReceivingStatus("00");
        recOffer.setAuditStatus("00");
        recOffer.setCreateOn(LocalDate.now());
        recOffer.setClosingDate(offerTemplate.getClosingDate());
        recOffer.setHasAgreement("0");
        recOffer.setRemarks(offerTemplate.getRemarks());
        recOffer.setDestinationType("70");

        RecOffer offer = recOfferRepository.save(recOffer);
        return offer;
        }
    }

    /**
     * @Autor 史念念 2017/6/5
     * @Description 企业端取消已发送的offer
     * @param id offer id
     * @return
     */
    @Override
    public String deleteOffer(UUID id) {

        int result = recOfferRepository.deleteOffer(id);

        return result>0?"success":"error";
    }

    /**
     * @Autor 史念念 2017/6/6
     * @Description 企业端再次发送offer
     * @param stuId 学生id
     * @param templateId 模板id
     * @return
     */
    @Override
    public RecOffer sendOfferAgain(UUID stuId, UUID templateId) {
        //查到学生信息
        StudentInfo studentInfo = studentInfoRepository.findById(stuId);
        //拿到公司信息
        UserPrincipal user = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        CompanyInfo companyInfo = companyInfoRepository.findOne(user.getId());
        List<RecOffer> r = recOfferRepository.findByRecIdAndStuId(user.getId(),stuId);
        if(!r.isEmpty()){
            boolean flag = false;
            for(RecOffer recOffer :r){
                String stuReceivingStatus = recOffer.getStuReceivingStatus();
                if("01".equals(stuReceivingStatus) || "03".equals(stuReceivingStatus) || "04".equals(stuReceivingStatus) || "05".equals(stuReceivingStatus) ||"06".equals(stuReceivingStatus)){
                    flag = false;
                    break;
                }else if("00".equals(stuReceivingStatus) || "02".equals(stuReceivingStatus)){
                    flag = true;
                }
            }
            if(flag){
                //查询模板信息
                OfferTemplate offerTemplate = offerTemplateRepositry.findOne(templateId);
                RecOffer recOffer = new RecOffer();
                recOffer.setStuNo(studentInfo.getStuNo());
                recOffer.setStuId(studentInfo.getId());
                recOffer.setRealName(studentInfo.getName());
                recOffer.setIdCard(studentInfo.getIdCard());
                recOffer.setEduDegree(studentInfo.getEduDegree());
                recOffer.setMajorCode(studentInfo.getMajorCode());
                recOffer.setEthnic(studentInfo.getEthnic());
                recOffer.setDepartmentId(studentInfo.getDepartmentId());
                recOffer.setEduMode(studentInfo.getEduMode());
                recOffer.setHomelandName(studentInfo.getHomelandName());
                recOffer.setEduYear(studentInfo.getEduYear());
                recOffer.setStartDate(studentInfo.getStartDate());
                recOffer.setEndDate(studentInfo.getEndDate());

                recOffer.setRecId(companyInfo.getId());
                recOffer.setRecName(companyInfo.getName());
                recOffer.setOrgCode(companyInfo.getOrgCode());
                recOffer.setNature(companyInfo.getNature());
                recOffer.setIndustry(companyInfo.getIndustry());
                recOffer.setDepartmentBelong(companyInfo.getDepartment());
                recOffer.setRecAddress(companyInfo.getAddress());
                //所在省份???
                recOffer.setRecProvince(companyInfo.getProvince());
                recOffer.setRecCity(companyInfo.getCity());
                recOffer.setRecDepartment(companyInfo.getDepartment());
                recOffer.setRecLinker(companyInfo.getContacts());
                recOffer.setRecMobile(companyInfo.getTelphone());
                recOffer.setRecTelphone(companyInfo.getFixedTelphone());
                recOffer.setRecZipcode(companyInfo.getZipCode());

                recOffer.setCategory(offerTemplate.getJobType());
                //是否需要报到证
                recOffer.setHasReportCard("0");
                //是否接受档案
                recOffer.setIsPfile(offerTemplate.getIsPfile());
                recOffer.setPfileToName(offerTemplate.getPfileToName());
                recOffer.setPfileToDepart(offerTemplate.getPfileToDepart());
                recOffer.setPfileToAddress(offerTemplate.getPfileToAddress());
                recOffer.setPfileToLocal(offerTemplate.getPfileToLocal());
                recOffer.setPfileToRecipient(offerTemplate.getPfileToRecipient());
                recOffer.setPfileToPhone(offerTemplate.getPfileToPhone());
                //是否解决户口(1解决0不解决)
                recOffer.setIsSolveHukou(offerTemplate.getIsSolveHukou());
                //是否允许不迁入(1允许不迁入0不允许不迁入)
                recOffer.setIsNotMoveHuKou(offerTemplate.getIsNotMoveHuKou());
                //确认是否迁入户口
                recOffer.setIsConfirmHuKou(offerTemplate.getIsNotMoveHuKou());
                //户口是否在学校
                recOffer.setIsSchHuKou(offerTemplate.getIsNotMoveHuKou());
                recOffer.setHukouToAddress(offerTemplate.getHukouToAddress());
                recOffer.setStuReceivingStatus("00");
                recOffer.setAuditStatus("00");
                recOffer.setCreateOn(LocalDate.now());
                recOffer.setClosingDate(offerTemplate.getClosingDate());
                recOffer.setHasAgreement("0");
                recOffer.setRemarks(offerTemplate.getRemarks());
                recOffer.setDestinationType("70");

                RecOffer offer = recOfferRepository.save(recOffer);
                return offer;
            }else {
                return null;
            }

        }else{
           return null;
        }
    }
}
