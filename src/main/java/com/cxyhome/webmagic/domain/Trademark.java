package com.cxyhome.webmagic.domain;



public class Trademark {

    private long id;
    private String number;
    private String title;
    private String productList;
    private String related;
    private String applyDate;
    private String i18NType;
    private String thumbnail;
    private String applicantZh;
    private String applicantEn;
    private String applicantAddrZh;
    private String applicantAddrEn;
    private String review1StCode;
    private String review1StDate;
    private String registerCode;
    private String registerDate;
    private String brandShared;
    private String sharedList;
    private String brandType;
    private String brandExpired;
    private String brandForm;
    private String i18NRegisterDate;
    private String targetDate;
    private String priorityDate;
    private String agentName;
    private String brandStatus;
    private String brandProcedure;

    public String getPossessionTermStart(){
        String brandExpired = getBrandExpired();
        if (brandExpired!=null && brandExpired.length()>0){
            return brandExpired.substring(0,11);
        }
       return  null;
    }

    public String getPossessionTermEnd(){
        String brandExpired = getBrandExpired();
        if (brandExpired!=null && brandExpired.length()>0){
            return brandExpired.substring(12);
        }
        return  null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }


    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }


    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }


    public String getI18NType() {
        return i18NType;
    }

    public void setI18NType(String i18NType) {
        this.i18NType = i18NType;
    }


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public String getApplicantZh() {
        return applicantZh;
    }

    public void setApplicantZh(String applicantZh) {
        this.applicantZh = applicantZh;
    }


    public String getApplicantEn() {
        return applicantEn;
    }

    public void setApplicantEn(String applicantEn) {
        this.applicantEn = applicantEn;
    }


    public String getApplicantAddrZh() {
        return applicantAddrZh;
    }

    public void setApplicantAddrZh(String applicantAddrZh) {
        this.applicantAddrZh = applicantAddrZh;
    }


    public String getApplicantAddrEn() {
        return applicantAddrEn;
    }

    public void setApplicantAddrEn(String applicantAddrEn) {
        this.applicantAddrEn = applicantAddrEn;
    }


    public String getReview1StCode() {
        return review1StCode;
    }

    public void setReview1StCode(String review1StCode) {
        this.review1StCode = review1StCode;
    }


    public String getReview1StDate() {
        return review1StDate;
    }

    public void setReview1StDate(String review1StDate) {
        this.review1StDate = review1StDate;
    }


    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }


    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }


    public String getBrandShared() {
        return brandShared;
    }

    public void setBrandShared(String brandShared) {
        this.brandShared = brandShared;
    }


    public String getSharedList() {
        return sharedList;
    }

    public void setSharedList(String sharedList) {
        this.sharedList = sharedList;
    }


    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }


    public String getBrandExpired() {
        return brandExpired;
    }

    public void setBrandExpired(String brandExpired) {
        this.brandExpired = brandExpired;
    }


    public String getBrandForm() {
        return brandForm;
    }

    public void setBrandForm(String brandForm) {
        this.brandForm = brandForm;
    }


    public String getI18NRegisterDate() {
        return i18NRegisterDate;
    }

    public void setI18NRegisterDate(String i18NRegisterDate) {
        this.i18NRegisterDate = i18NRegisterDate;
    }


    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }


    public String getPriorityDate() {
        return priorityDate;
    }

    public void setPriorityDate(String priorityDate) {
        this.priorityDate = priorityDate;
    }


    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }


    public String getBrandStatus() {
        return brandStatus;
    }

    public void setBrandStatus(String brandStatus) {
        this.brandStatus = brandStatus;
    }


    public String getBrandProcedure() {
        return brandProcedure;
    }

    public void setBrandProcedure(String brandProcedure) {
        this.brandProcedure = brandProcedure;
    }

}
