package cn.edu.dlut.career.notification;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 消息模板
 * @Author wangyj.
 * @Date 2017/3/28  16:16.
 */

@Entity
public class Template {

    @Id
    @GenericGenerator(name="Generator",strategy = "uuid")
    @GeneratedValue(generator = "Generator")
    private String id;

    @Column(length = 20,nullable = false)
    private String action;

    @Column(length = 20,nullable = false)
    private String name;

    @Column(length = 255,nullable = false)
    private String template;

    public Template() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}

