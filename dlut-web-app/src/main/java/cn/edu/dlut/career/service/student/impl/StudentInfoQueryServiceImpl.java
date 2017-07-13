package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.company.CStudentDTO;
import cn.edu.dlut.career.dto.student.StuLoginDTO;
import cn.edu.dlut.career.dto.student.StudentInfoDTO;
import cn.edu.dlut.career.repository.student.StudentInfoRepository;
import cn.edu.dlut.career.service.student.StudentInfoCommandService;
import cn.edu.dlut.career.service.student.StudentInfoQueryService;
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
@Transactional(readOnly = true)
public class StudentInfoQueryServiceImpl implements StudentInfoQueryService {
    @Autowired
    private StudentInfoRepository studentInfoRepository;

    Logger logger = LoggerFactory.getLogger(StudentInfoQueryServiceImpl.class);

    /**
     * 通过id寻找学生信息
     */
    @Override
    public StudentInfo findById(UUID id) {
        return studentInfoRepository.findOne(id);
    }

    /**
     * 查询所有的学生信息 制作为List集合
     */
    @Override
    public List<StudentInfo> findAllStudentInfo() {
        return studentInfoRepository.findAll();
    }


    /**
     * 查询学生端登录的用户 信息
     */
    @Override
    public StuLoginDTO findLoginInfo(String stuNo) {
        return studentInfoRepository.findLoginInfo(stuNo);
    }


    /**
     * 查看用户修改密码时，输入的原密码是否正确
     */
    @Override
    public String findPwd(UUID id, String pwd, String originalPwd, String salt) {
        String password = new Md5Hash(pwd, salt).toString();
        if (originalPwd.trim().equals(password.trim())) {
            return "ok";
        }
            return null;

    }

    /**
     * 教师端
     * 查看所有学生的学籍信息
     */
    @Override
    public Page<StudentInfo> listDepartStuPage(String name, String stuNo, String gender, String ethnic, String departmentId, String majorCode, String eduYear, String eduMode, String endDate, String tutorName, String status, String eduDegree, Pageable pageable) {
        if(name.equals("")){
            name = null;
        }if(stuNo.equals("")){
            stuNo = null;
        }if(gender.equals("")){
            gender = null;
        }if(ethnic.equals("")){
            ethnic = null;
        }if("".equals(departmentId)||departmentId==null){
            departmentId = null;
        }if(majorCode.equals("")){
            majorCode = null;
        }if(eduYear.equals("")){
            eduYear = null;
        }if(eduMode.equals("")){
            eduMode = null;
        }if(tutorName.equals("")){
            tutorName = null;
        }if(status.equals("")){
            status = null;
        }if(eduDegree.equals("")){
            eduDegree = null;
        }if(endDate.equals("")){
            endDate = null;
        }
    UserPrincipal userPrincipal = (UserPrincipal)SecurityUtils.getSubject().getPrincipal();
      //如果身份是校级可以查看所有学生信息
        if (userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN")) {
            return studentInfoRepository.queryGetDepartStusPage(name, stuNo, gender, ethnic, departmentId, majorCode, eduYear, eduMode, endDate, tutorName, status, eduDegree,pageable);
        } else { //否则只能查看本院系的学生信息
            return studentInfoRepository.queryGetDepartStusPage(name, stuNo, gender, ethnic, userPrincipal.getDepId(), majorCode, eduYear, eduMode, endDate, tutorName, status, eduDegree,pageable);
        }
    }

    /**
     * 教师端
     * 查看本学院 学生的学籍信息
     */
    @Override
    public Page<StudentInfo> listMyDepartStuPage(String name, String stuNo, String gender, String ethnic,String majorCode,String departmentId, String eduYear, String eduMode, String endDate, String tutorName, String status, String eduDegree, String haveReport, Pageable pageable) {
        if(name.equals("")){
            name = null;
        }if(stuNo.equals("")){
            stuNo = null;
        }if(gender.equals("")){
            gender = null;
        }if(ethnic.equals("")){
            ethnic = null;
        }if(majorCode==null||majorCode.equals("")){
            majorCode = null;
        }if(eduYear.equals("")){
            eduYear = null;
        }if(eduMode.equals("")){
            eduMode = null;
        }if(tutorName.equals("")){
            tutorName = null;
        }if(status.equals("")){
            status = null;
        }if(eduDegree.equals("")){
            eduDegree = null;
        }if(haveReport.equals("")){
            haveReport = null;
        }if(endDate.equals("")){
            endDate = null;
        }if(departmentId==null||departmentId.equals("")){
            departmentId = null;
        }
        UserPrincipal userPrincipal = (UserPrincipal)SecurityUtils.getSubject().getPrincipal();
        if (userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN")){
         //校级
            return studentInfoRepository.queryGetMyDepartStusPage(name, stuNo, gender, ethnic, departmentId, majorCode, eduYear, eduMode, endDate, tutorName, status, eduDegree, haveReport,pageable);
        }else {
         //院级
            return studentInfoRepository.queryGetMyDepartStusPage(name, stuNo, gender, ethnic, userPrincipal.getDepId(), majorCode, eduYear, eduMode, endDate, tutorName, status, eduDegree, haveReport, pageable);
        }
    }


    /**
     * 通过学号查找学生
     */
    @Override
    public StudentInfo findByStuNo(String stuNo) {
        return studentInfoRepository.findByStuNo(stuNo);
    }

    /**
     * @author wei  2017/5/27
     * @method findDynamic
     * @param stuNo 学号, mobilephone 电话, name 姓名
     * @return java.util.List<cn.edu.dlut.career.dto.company.CStudentDTO>
     * @description  企业端查询生源信息
     */
    @Override
    public List<CStudentDTO> findDynamic(String stuNo, String mobilephone, String name) {
        //不允许什么也不输入就查询
        if(stuNo==null || stuNo ==""){
            if (mobilephone==null || mobilephone =="") {
                if(name==null || name ==""){
                    return null;
                }
            }
        }
        List<CStudentDTO> list = studentInfoRepository.findDynamic(stuNo,mobilephone,name);
        return list;
    }


}
