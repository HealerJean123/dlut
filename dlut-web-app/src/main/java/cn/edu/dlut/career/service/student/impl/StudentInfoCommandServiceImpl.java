package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.common.OperateLog;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.student.StuLoginDTO;
import cn.edu.dlut.career.dto.student.StudentInfoDTO;
import cn.edu.dlut.career.repository.common.DispatchCodeRespository;
import cn.edu.dlut.career.repository.student.StudentInfoRepository;
import cn.edu.dlut.career.service.common.DispatchService;
import cn.edu.dlut.career.service.common.OperateLogService;
import cn.edu.dlut.career.service.student.StudentInfoCommandService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *  学生学籍信息数据 服务层
 * Created by HealerJean on 2017/4/12.
 */
@Service
@Transactional
public class StudentInfoCommandServiceImpl implements StudentInfoCommandService {
    @Autowired
    private StudentInfoRepository studentInfoRepository;
    @Autowired
    private DispatchCodeRespository dispatchCodeRespository;
    @Autowired
    private DispatchService dispatchService;
    @Autowired
    private OperateLogService operateLogService;
    Logger logger = LoggerFactory.getLogger(StudentInfoCommandServiceImpl.class);


    @Override
    public StudentInfo saveStudentInfo(StudentInfo studentInfo) {
        return studentInfoRepository.save(studentInfo);
    }


    /**
     * 学生更新自己的信息
     */
    @Override
    public StudentInfo updateStudentInfo(StudentInfoDTO studentInfoDTO) {
        StudentInfo studentInfo = studentInfoRepository.findById(studentInfoDTO.getId());
        //判断是修改还是 全部提交进行上报
        if(studentInfoDTO.getAllupdateStatus()!=null){ //上报
            //学生审核时间
            studentInfo.setStuCheckTime(LocalDateTime.now());
            //学生是修改人
            studentInfo.setUpdator(studentInfoDTO.getName());
            //学生核对状态,上报状态，设置为 1，表示学生已经核对信息。
            studentInfo.setHaveReport("1");
            //教师审核状态 设置为待审核
            studentInfo.setStatus("00");
            return studentInfoRepository.save(studentInfo);
        }else {
            //学生没有审核才可以进行修改，1代表学生已经上报
            if (!studentInfo.getHaveReport().equals("1")) {
                studentInfo.setId(studentInfoDTO.getId());
                studentInfo.setIdCard(studentInfoDTO.getIdCard());
                studentInfo.setGender(studentInfoDTO.getGender());
                studentInfo.setEduDegree(studentInfoDTO.getEduDegree());
//   学号不能更改
//                studentInfo.setStuNo(studentInfoDTO.getStuNo());
                studentInfo.setBirthdate(LocalDate.parse(studentInfoDTO.getBirthdate()));
                studentInfo.setPolitical(studentInfoDTO.getPolitical());
                studentInfo.setEthnic(studentInfoDTO.getEthnic());
                studentInfo.setDepartmentId(studentInfoDTO.getDepartmentId());
                studentInfo.setMajorCode(studentInfoDTO.getMajorCode());
                studentInfo.setTutorName(studentInfoDTO.getTutorName());
                studentInfo.setStartDate(studentInfoDTO.getStartDate());
                studentInfo.setEduYear(studentInfoDTO.getEduYear());
                studentInfo.setClassName(studentInfoDTO.getClassName());
                studentInfo.setFlangType(studentInfoDTO.getFlangType());
                studentInfo.setFlangType2(studentInfoDTO.getFlangType2());
                studentInfo.setName(studentInfoDTO.getName());
                studentInfo.setCounselor(studentInfoDTO.getCounselor());
                studentInfo.setEduMode(studentInfoDTO.getEduMode());
                studentInfo.setTrustee(studentInfoDTO.getTrustee());
                studentInfo.setHaveEduHukou(studentInfoDTO.getHaveEduHukou());
                studentInfo.setEmail(studentInfoDTO.getEmail());
                studentInfo.setQqNo(studentInfoDTO.getQqNo());
                studentInfo.setWechatNo(studentInfoDTO.getWechatNo());
                studentInfo.setMobilephone(studentInfoDTO.getMobilephone());
                studentInfo.setHomePhone(studentInfoDTO.getHomePhone());

                /**
                 * 通过 departmentId 和 marjor 将关联的院系 和专业 同时存储进去
                 */
                studentInfo.setDepartment(PubCodeUtil.getName("academy", studentInfoDTO.getDepartmentId()));
                studentInfo.setMajorName(PubCodeUtil.getName("major", studentInfoDTO.getMajorCode()));
                //因为homelandCode和其他的不一样，所以需要这样进行设置
                if (studentInfoDTO.getHomelandCode()!=null) {
                    studentInfo.setHomelandCode(studentInfoDTO.getHomelandCode());
                    studentInfo.setHomelandName(dispatchService.findByHomelandCode(studentInfo.getHomelandCode()).getAreaName());
                }
                //学生审核时间
                studentInfo.setStuCheckTime(LocalDateTime.now());
                //学生是修改人
                studentInfo.setUpdator(studentInfoDTO.getName());
                return studentInfoRepository.save(studentInfo);

            }
        }
        return null;
    }

    @Override
    public String deleteStudentInfo(UUID id) {
        try {
            studentInfoRepository.delete(id);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void saveStudentInfos(List<StudentInfo> studentInfos) {
        studentInfoRepository.save(studentInfos);
    }



    /**
     * 修改密码
     * @param newPwd
     * @return
     */
    @Override
    public int updatePwd(UUID id,String newPwd,String salt) {
             String newPwdFinale = new Md5Hash(newPwd, salt).toString();
            return studentInfoRepository.updatePwd(id, newPwdFinale);
    }



    /**
     * 教师更新学生信息
     * @param studentInfoDTO
     * @return
     */
    @Override
    public StudentInfo teaUpdateStudentInfo(StudentInfoDTO studentInfoDTO, String teaDepartId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        StudentInfo studentInfo = studentInfoRepository.findById(studentInfoDTO.getId());
        if ((teaDepartId.equals(studentInfo.getDepartmentId()) || userPrincipal.getPrincipal().equals("SCHOOL") || userPrincipal.getPrincipal().equals("SCHADMIN"))) {

            if(studentInfoDTO.getAllupdateStatus()!=null&&studentInfo.getHaveReport().equals("1")) {
                //教师为更新人
                studentInfo.setUpdator(userPrincipal.getRealName());
                //教师审核，设置为1
                studentInfo.setStatus(studentInfoDTO.getStatus());
                //教师设置审核时间
                studentInfo.setSchCheckTime(LocalDateTime.now());
                //记录操作日志
                OperateLog operateLog = operateLog = new OperateLog(studentInfo.getId(),"学籍审核");
                String operateString = null;
                if("01".equals(studentInfo.getStatus())){

                    operateString = "学籍审核通过";
                }else if("02".equals(studentInfo.getStatus())){
                    operateString = "学籍审核不通过";

                }
                operateLogService.addOptLog(operateLog,operateString);
             }else {
            //教师只能修改本院系的学士的学籍，所以这里进行学士院系的判断 ,但是任意校级可以操作
                studentInfo.setIdCard(studentInfoDTO.getIdCard());
                studentInfo.setGender(studentInfoDTO.getGender());
                studentInfo.setEduDegree(studentInfoDTO.getEduDegree());
//                studentInfo.setStuNo(studentInfoDTO.getStuNo()); 学号不能修改的
                studentInfo.setBirthdate(LocalDate.parse(studentInfoDTO.getBirthdate()));
                studentInfo.setPolitical(studentInfoDTO.getPolitical());
                studentInfo.setEthnic(studentInfoDTO.getEthnic());
                studentInfo.setDepartmentId(studentInfoDTO.getDepartmentId());
                studentInfo.setMajorCode(studentInfoDTO.getMajorCode());
                studentInfo.setTutorName(studentInfoDTO.getTutorName());
                studentInfo.setStartDate(studentInfoDTO.getStartDate());
                studentInfo.setEduYear(studentInfoDTO.getEduYear());
                studentInfo.setClassName(studentInfoDTO.getClassName());
                studentInfo.setFlangType(studentInfoDTO.getFlangType());
                studentInfo.setFlangType2(studentInfoDTO.getFlangType2());
                studentInfo.setName(studentInfoDTO.getName());
                studentInfo.setCounselor(studentInfoDTO.getCounselor());
                studentInfo.setEduMode(studentInfoDTO.getEduMode());
                studentInfo.setTrustee(studentInfoDTO.getTrustee());
                studentInfo.setHaveEduHukou(studentInfoDTO.getHaveEduHukou());
                studentInfo.setEmail(studentInfoDTO.getEmail());
                studentInfo.setQqNo(studentInfoDTO.getQqNo());
                studentInfo.setWechatNo(studentInfoDTO.getWechatNo());
                studentInfo.setMobilephone(studentInfoDTO.getMobilephone());
                studentInfo.setHomePhone(studentInfoDTO.getHomePhone());

                /**
                 * 通过 departmentId 和 marjor 将关联的院系 和专业 同时存储进去
                 */
                studentInfo.setDepartment(PubCodeUtil.getName("academy", studentInfoDTO.getDepartmentId()));
                studentInfo.setMajorName(PubCodeUtil.getName("major", studentInfoDTO.getMajorCode()));
                //因为homelandCode和其他的不一样，所以需要这样进行设置
                  if (studentInfoDTO.getHomelandCode()!=null) {
                      studentInfo.setHomelandCode(studentInfoDTO.getHomelandCode());
                      studentInfo.setHomelandName(dispatchService.findByHomelandCode(studentInfo.getHomelandCode()).getAreaName());
                  }
                //教师为更新人
                studentInfo.setUpdator(userPrincipal.getRealName());

                return studentInfoRepository.save(studentInfo);
            }
        }
        return  null;
    }

    /**
     * 教师审核某个学生学籍
     * @param id
     * @return
     */
    @Override
    public StudentInfo teaUpdateStuInfoAuditStatus(UUID id) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        String departmentId = studentInfoRepository.findById(id).getDepartmentId();
        StudentInfo studentInfo = studentInfoRepository.findById(id);
        if ((userPrincipal.getDepId().equals(departmentId)||userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN"))&&studentInfo.getHaveReport().equals("1")){
            //1 表示审核通过
            String status = "01";
            studentInfo.setStatus(status);
            studentInfo.setSchCheckTime(LocalDateTime.now());
            studentInfo.setUpdator(userPrincipal.getRealName());
            OperateLog operateLog = new OperateLog(studentInfo.getId(),"学籍审核");
            operateLogService.addOptLog(operateLog,"学籍审核通过");
            return studentInfoRepository.save(studentInfo);
         }else{
        return null;
        }
    }


    /**
     * 教师批量审核学生学籍
     */
    @Override
    public List<StudentInfo> stuInfoBatchAudit(UUID[] id) {

        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();

            List<StudentInfo> studentInfos = new ArrayList<StudentInfo>();
            for (UUID sid : id) {
                String departmentId = studentInfoRepository.findById(sid).getDepartmentId();
                StudentInfo studentInfo = studentInfoRepository.findById(sid);
                if ((userPrincipal.getDepId().equals(departmentId)||userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN"))&&studentInfo.getHaveReport().equals("1")) {
                    studentInfo.setStatus("01");
                    studentInfo.setSchCheckTime(LocalDateTime.now());
                    studentInfo.setUpdator(userPrincipal.getRealName());
                    OperateLog operateLog = new OperateLog(studentInfo.getId(),"学籍审核");
                    operateLogService.addOptLog(operateLog,"学籍审核通过");
                    studentInfos.add(studentInfoRepository.save(studentInfo));
                    }else {
                    return  null;
                }
            }
            return studentInfos;
    }


}
