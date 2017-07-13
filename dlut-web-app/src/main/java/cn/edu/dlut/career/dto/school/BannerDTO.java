package cn.edu.dlut.career.dto.school;

import java.util.UUID;

/**
 * Created by wei on 2017/5/25.
 */
public class BannerDTO {
    private UUID id;

    private String banner;

    public BannerDTO(UUID id, String banner) {
        this.id = id;
        this.banner = banner;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
