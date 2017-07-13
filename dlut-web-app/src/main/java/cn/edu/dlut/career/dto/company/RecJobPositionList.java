package cn.edu.dlut.career.dto.company;

import java.util.List;

/**
 * Created by 史念念 on 2017/6/1.
 */
public class RecJobPositionList {
    private RecJobPositionDTO[] recJobPositionDTOS;
    private String bulletinId;

    public RecJobPositionList() {
    }

    public RecJobPositionList(RecJobPositionDTO[] recJobPositionDTOS, String bulletinId) {
        this.recJobPositionDTOS = recJobPositionDTOS;
        this.bulletinId = bulletinId;
    }

    public String getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(String bulletinId) {
        this.bulletinId = bulletinId;
    }

    public RecJobPositionDTO[] getRecJobPositionDTOS() {
        return recJobPositionDTOS;
    }

    public void setRecJobPositionDTOS(RecJobPositionDTO[] recJobPositionDTOS) {
        this.recJobPositionDTOS = recJobPositionDTOS;
    }
}
