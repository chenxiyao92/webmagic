package com.cxyhome.webmagic.domain.Patent;

import java.util.Date;
import java.util.List;

public class PatentInfo {

    /**
     * id
     */
    private Integer id;

    /**
     *
     */
    private String patentId;

    /**
     * 专利名称
     * 如：美容仪器及具有其的美容系统
     */
    private String title;

    /**
     * 摘要
     * 如：本发明公开了一种美容仪器及具有其的美容系统，
     * 所述美容仪器包括：导电膜片....
     */
    private String summary;

    /**
     * 国际分类号
     * 如：A61N1/30
     */
    private String ipc;

    /**
     * 优先权号
     */
    private String priorityNumber;

    /**
     * 优先权日
     * TODO: SOOIP 没有数据
     * 如：
     */
    private Date priorityDate;

    /**
     * 申请人
     * 如：美的集团股份有限公司
     */
    private String applicant;

    /**
     * 申请人地址邮政编码
     */
    private String postalcode;

    public String getCpc() {
        return cpc;
    }

    public void setCpc(String cpc) {
        this.cpc = cpc;
    }

    /**
     * 申请人区域代码
     */
    private String applicationAreaCode;

    public String getApplicationAreaCode() {
        return applicationAreaCode;
    }

    public void setApplicationAreaCode(String applicationAreaCode) {
        this.applicationAreaCode = applicationAreaCode;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    /**
     * 申请人地址
     */
    private String applicantAddress;

    public String getApplicantAddress() {
        return applicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    /**
     * 申请日

     */
    private Date applicationDate;

    /**
     * 申请号
     */
    private String applicationNumber;

    /**
     * 专利权人
     */
    private String assignee;

    /**
     * 发明人
     */
    private String inventor;

    /**
     * 公开号
     */
    private String publicationNumber;

    /**
     * 公开日
     */
    private Date publicationDate;

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * 专利类型
     */
    private String type;

    /**
     * 专利代理机构
     */
    private String agency;

    /**
     * 专利机构号码
     */
    private String agenyCode;

    public String getAgenyCode() {
        return agenyCode;
    }

    public void setAgenyCode(String agenyCode) {
        this.agenyCode = agenyCode;
    }


    private List<LawStatus> lawStates;

    public List<LawStatus> getLawStates() {
        return lawStates;
    }

    public void setLawStates(List<LawStatus> lawStates) {
        this.lawStates = lawStates;
    }

    /**
     * 代理人
     */
    private String agent;

    /**
     * 专利附图地址
     */
    private String imagePath;

    /**
     * 授权日
     */
    private String grantDate;

    /**
     * 主分类号
     */
    private String mainIpc;

    /**
     * 第一发明人
     */
    private String firstInventor;

    /**
     * 第一申请人
     */
    private String firstApplicant;

    /**
     * LOC分类号
     */
    private String loc;

    /**
     * 专利文献全文PDF地址列表
     */
    private String pdfUrl;

    /**
     * 专利说明
     */
    private Instruction instruction;

    /**
     洛迦诺分类
     */
    private String locarnoClassification;

    public String getLocarnoClassification() {
        return locarnoClassification;
    }

    public void setLocarnoClassification(String locarnoClassification) {
        this.locarnoClassification = locarnoClassification;
    }

    /**
     * 权利
     */
    private String right;
    /**
     *
     */
    private String cpc;

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatentId() {
        return patentId;
    }

    public void setPatentId(String patentId) {
        this.patentId = patentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIpc() {
        return ipc;
    }

    public void setIpc(String ipc) {
        this.ipc = ipc;
    }

    public String getPriorityNumber() {
        return priorityNumber;
    }

    public void setPriorityNumber(String priorityNumber) {
        this.priorityNumber = priorityNumber;
    }

    public Date getPriorityDate() {
        return priorityDate;
    }

    public void setPriorityDate(Date priorityDate) {
        this.priorityDate = priorityDate;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getInventor() {
        return inventor;
    }

    public void setInventor(String inventor) {
        this.inventor = inventor;
    }

    public String getPublicationNumber() {
        return publicationNumber;
    }

    public void setPublicationNumber(String publicationNumber) {
        this.publicationNumber = publicationNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(String grantDate) {
        this.grantDate = grantDate;
    }

    public String getMainIpc() {
        return mainIpc;
    }

    public void setMainIpc(String mainIpc) {
        this.mainIpc = mainIpc;
    }

    public String getFirstInventor() {
        return firstInventor;
    }

    public void setFirstInventor(String firstInventor) {
        this.firstInventor = firstInventor;
    }

    public String getFirstApplicant() {
        return firstApplicant;
    }

    public void setFirstApplicant(String firstApplicant) {
        this.firstApplicant = firstApplicant;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }


    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }
}
