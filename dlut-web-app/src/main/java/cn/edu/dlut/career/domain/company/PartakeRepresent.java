package cn.edu.dlut.career.domain.company;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/** 实体类 参与招聘会代表
 * Created by HealerJean on 2017/4/6.
 */
@Entity
@Table(name = "rec_partake_represent")
public class PartakeRepresent {

    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //双选会，招聘会预约申请表ID(外键)
    @Column(length = 30,nullable = false)
    private UUID recCareerFairEntryId;

    //姓名
    @Column(length = 10,nullable = false)
    private String name;

    //性别
    @Column(length = 5,nullable = false)
    private String gender;

    //职务
    @Column(length = 30,nullable = false)
    private String rank;

    //住宿房间
    @Column(length = 30,nullable = true)
    private String accoRoom;

    //报到时间
    @Column(nullable = true)
    private LocalDateTime signTime;



    public PartakeRepresent() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRecCareerFairEntryId() {
        return recCareerFairEntryId;
    }

    public void setRecCareerFairEntryId(UUID recCareerFairEntryId) {
        this.recCareerFairEntryId = recCareerFairEntryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAccoRoom() {
        return accoRoom;
    }

    public void setAccoRoom(String accoRoom) {
        this.accoRoom = accoRoom;
    }

    public LocalDateTime getSignTime() {
        return signTime;
    }

    public void setSignTime(LocalDateTime signTime) {
        this.signTime = signTime;
    }
}
