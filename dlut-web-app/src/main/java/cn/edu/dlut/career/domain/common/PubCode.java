package cn.edu.dlut.career.domain.common;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

/**
 * 数据字典 记录系统的编码和映射关系
 * @Author wangyj.
 * @Date 2017/4/24  10:06.
 */
@Entity
public class PubCode implements Serializable{

    private static final long SERIVALVERSIONUID = 876280513342148585L;

    @Id
    @GenericGenerator(name = "generator",strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    private UUID id;

    //编码
    private String code;

    //编码对应的名称,说明
    private String name;

    //该条数据字典所属的类型
    private String type;

    public PubCode(UUID id, String code, String name, String type) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "PubCode{" +
            ", code='" + code + '\'' +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            '}';
    }

    public PubCode() {
    }

    public static long getSERIVALVERSIONUID() {
        return SERIVALVERSIONUID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
