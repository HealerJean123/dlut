package cn.edu.dlut.career.dto.school;

import java.util.LinkedList;

/**
 * Created by wei on 2017/5/19.
 */
public class NewsListDTO {
    private LinkedList<NewsDTO> news;
    private LinkedList<NewsDTO> notice;
    private LinkedList<NewsDTO> agreement;
    private LinkedList<NewsDTO> consultation;
    private LinkedList<NewsDTO> activity;
    private LinkedList<NewsDTO> match;
    private LinkedList<NewsDTO> special;
    private LinkedList<NewsDTO> demand;
    private LinkedList<NewsDTO> internship;
    private LinkedList<NewsDTO> overseas;
    private LinkedList<NewsDTO> lecture;
    private LinkedList<BannerDTO> banner;

    public LinkedList<NewsDTO> getNews() {
        return news;
    }

    public void setNews(LinkedList<NewsDTO> news) {
        this.news = news;
    }

    public LinkedList<NewsDTO> getNotice() {
        return notice;
    }

    public void setNotice(LinkedList<NewsDTO> notice) {
        this.notice = notice;
    }

    public LinkedList<NewsDTO> getAgreement() {
        return agreement;
    }

    public void setAgreement(LinkedList<NewsDTO> agreement) {
        this.agreement = agreement;
    }

    public LinkedList<NewsDTO> getConsultation() {
        return consultation;
    }

    public void setConsultation(LinkedList<NewsDTO> consultation) {
        this.consultation = consultation;
    }

    public LinkedList<NewsDTO> getActivity() {
        return activity;
    }

    public void setActivity(LinkedList<NewsDTO> activity) {
        this.activity = activity;
    }

    public LinkedList<NewsDTO> getMatch() {
        return match;
    }

    public void setMatch(LinkedList<NewsDTO> match) {
        this.match = match;
    }

    public LinkedList<NewsDTO> getSpecial() {
        return special;
    }

    public void setSpecial(LinkedList<NewsDTO> special) {
        this.special = special;
    }

    public LinkedList<NewsDTO> getDemand() {
        return demand;
    }

    public void setDemand(LinkedList<NewsDTO> demand) {
        this.demand = demand;
    }

    public LinkedList<NewsDTO> getInternship() {
        return internship;
    }

    public void setInternship(LinkedList<NewsDTO> internship) {
        this.internship = internship;
    }

    public LinkedList<NewsDTO> getOverseas() {
        return overseas;
    }

    public void setOverseas(LinkedList<NewsDTO> overseas) {
        this.overseas = overseas;
    }

    public LinkedList<NewsDTO> getLecture() {
        return lecture;
    }

    public void setLecture(LinkedList<NewsDTO> lecture) {
        this.lecture = lecture;
    }

    public LinkedList<BannerDTO> getBanner() {
        return banner;
    }

    public void setBanner(LinkedList<BannerDTO> banner) {
        this.banner = banner;
    }
}
