package cn.edu.dlut.career.dto.school;

/**
 * @Author wangyj.
 * @Date 2017/6/1  9:55.
 */
public class LogoDTO {
    private String logo;

    private String url;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LogoDTO() {
    }

    public LogoDTO(String logo, String url) {
        this.logo = logo;
        this.url = url;
    }
}
