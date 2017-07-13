package cn.edu.dlut.career.dto.school;

import java.util.List;
import java.util.Map;

/**
 * 校级教师首页统计dto
 * @Author wangyj.
 * @Date 2017/4/28  9:23.
 */
public class SchTeacherStatDTO {


    //各学院已就业统计
    private Map<String ,Integer> academyEmplo;

    //各学院总人数统计
    private Map<String ,Integer> academyTotal;


    //当届毕业生总数
    private int countAll;

    //总体就业率统计
    private Double emploRate;

    //待处理事项统计
    private Map<String ,Integer> todoList;

    //就业地区分布统计
    private List<Map<String ,Object>> emploArea;

    //就业性质分布（国有、民营）统计
    private List<Map<String ,Object>> emploNature;

    //就业行业分布统计
    private List<Map<String ,Object>> emploCategory;

    public SchTeacherStatDTO() {
    }

    public SchTeacherStatDTO(Map<String, Integer> academyEmplo, Map<String, Integer> academyTotal, int countAll, Double emploRate,
                             Map<String, Integer> todoList, List<Map<String, Object>> emploArea, List<Map<String, Object>> emploNature,
                             List<Map<String, Object>> emploCategory) {
        this.academyEmplo = academyEmplo;
        this.academyTotal = academyTotal;
        this.countAll = countAll;
        this.emploRate = emploRate;
        this.todoList = todoList;
        this.emploArea = emploArea;
        this.emploNature = emploNature;
        this.emploCategory = emploCategory;
    }

    public Map<String, Integer> getAcademyEmplo() {
        return academyEmplo;
    }

    public void setAcademyEmplo(Map<String, Integer> academyEmplo) {
        this.academyEmplo = academyEmplo;
    }

    public Map<String, Integer> getAcademyTotal() {
        return academyTotal;
    }

    public void setAcademyTotal(Map<String, Integer> academyTotal) {
        this.academyTotal = academyTotal;
    }

    public int getCountAll() {
        return countAll;
    }

    public void setCountAll(int countAll) {
        this.countAll = countAll;
    }

    public Double getEmploRate() {
        return emploRate;
    }

    public void setEmploRate(Double emploRate) {
        this.emploRate = emploRate;
    }

    public Map<String, Integer> getTodoList() {
        return todoList;
    }

    public void setTodoList(Map<String, Integer> todoList) {
        this.todoList = todoList;
    }

    public List<Map<String, Object>> getEmploArea() {
        return emploArea;
    }

    public void setEmploArea(List<Map<String, Object>> emploArea) {
        this.emploArea = emploArea;
    }

    public List<Map<String, Object>> getEmploNature() {
        return emploNature;
    }

    public void setEmploNature(List<Map<String, Object>> emploNature) {
        this.emploNature = emploNature;
    }

    public List<Map<String, Object>> getEmploCategory() {
        return emploCategory;
    }

    public void setEmploCategory(List<Map<String, Object>> emploCategory) {
        this.emploCategory = emploCategory;
    }
}
