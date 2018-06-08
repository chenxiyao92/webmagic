package com.cxyhome.webmagic.domain.Patent;

import java.util.Date;

public class LawStatus {

    /**
     * 法律状态公告日
     */
    private Date lawStatusPublicationDate;

    /**
     * 法律状态公告日展示
     */
    private String showLawStatusPublicationDate;

    public String getShowLawStatusPublicationDate() {
        return showLawStatusPublicationDate;
    }

    public void setShowLawStatusPublicationDate(String showLawStatusPublicationDate) {
        this.showLawStatusPublicationDate = showLawStatusPublicationDate;
    }

    /**
     * 法律状态
     */
    private String lawStatus;

    /**
     * 法律状态信息
     */
    private String lawStatusInfo;

    public LawStatus(Date lawStatusPublicationDate, String lawStatus, String lawStatusInfo) {
        this.lawStatusPublicationDate = lawStatusPublicationDate;
        this.lawStatus = lawStatus;
        this.lawStatusInfo = lawStatusInfo;
    }

    public LawStatus() {
    }

    public Date getLawStatusPublicationDate() {
        return lawStatusPublicationDate;
    }

    public void setLawStatusPublicationDate(Date lawStatusPublicationDate) {
        this.lawStatusPublicationDate = lawStatusPublicationDate;
    }

    public String getLawStatus() {
        return lawStatus;
    }

    public void setLawStatus(String lawStatus) {
        this.lawStatus = lawStatus;
    }

    public String getLawStatusInfo() {
        return lawStatusInfo;
    }

    public void setLawStatusInfo(String lawStatusInfo) {
        this.lawStatusInfo = lawStatusInfo;
    }
}
