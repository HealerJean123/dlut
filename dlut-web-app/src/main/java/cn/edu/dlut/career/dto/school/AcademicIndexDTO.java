package cn.edu.dlut.career.dto.school;

import java.util.List;
import java.util.Map;

/**
 * Created by 史念念 on 2017/4/28.
 *
 * 院系老师 首页信息
 */
public class AcademicIndexDTO {

    //当届院系毕业总数
    private int countAll;
    //院系就业率统计
    private double emploRate;

    //就业去向统计（升学，工作，出国）
    private List<Map<String,Object>> emploDestination;

    //就业地区分布统计
    private List<Map<String,Object>> emploArea;

    //就业单位性质分布（国有、民营）统计
    private List<Map<String,Object>> emploNature;

    //就业行业分布统计
    private List<Map<String,Object>> emploCategory;

    //待处理事项统计
    private Map<String ,Integer> todoList;

    public int getCountAll() {
        return countAll;
    }

    public void setCountAll(int countAll) {
        this.countAll = countAll;
    }

    public double getEmploRate() {
        return emploRate;
    }

    public void setEmploRate(double emploRate) {
        this.emploRate = emploRate;
    }

    public List<Map<String, Object>> getEmploDestination() {
        return emploDestination;
    }

    public void setEmploDestination(List<Map<String, Object>> emploDestination) {
        this.emploDestination = emploDestination;
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

    public Map<String, Integer> getTodoList() {
        return todoList;
    }

    public void setTodoList(Map<String, Integer> todoList) {
        this.todoList = todoList;
    }
}
