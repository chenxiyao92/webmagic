package com.cxyhome.webmagic.domain;



import com.cxyhome.webmagic.domain.quandashi.FlowList;
import com.cxyhome.webmagic.domain.quandashi.GoodsList;

import java.util.Date;
import java.util.List;

public class Info {

  private Long id;

  private Long mid;
  /**
   * 商标图片地址
   * 不能删除 需要访问下载图片
   */
  private String imgAddr;

  /**
   * 本地地址图片地址
   */
  private String localImageAddr;

  /**
   * 商标名称
   */
  private String mark;
  /**
   * 序列号
   */
  private String serialNumber;
  /**
   * 类似群
   */
  private String classificationInfo;
  /**
   * 申请/注册号
   * 不能用数字 23147195A
   */
  private String  registrationNumber;
  /**
   * 申请日期
   */
  private Date filingDate;

  /**
   * 展示的申请日期
   */
  private String showFilingDate;
  /**
   * 国际分类
   */
  private Integer internationalClasses;

  private String internationalClassesZh;
  /**
   * 申请人名称（中文）
   */
  private String applicantName;
  /**
   * 申请人名称（英文）
   */
  private String applicantNameEn;

  /**
   * 申请人地址
    */
  private String applicantAddress;
  /**
   * 申请人地址（英文）
   */
  private String applicantAddressEn;
  /**
   * 初审公告期号
   */
  private Integer firstPublicationNumber;
  /**
   初审公告日期
   */
  private Date publishedOppositionDate;

  private String showPublishedOppositionDate;
  /**
   * 注册公告期号
   */
  private Integer registrationPublicationNumber;

  /**
   *注册公告日期
   */
  private Date registrationDate;
  private  String showRegistrationDate;
  /**
   * 是否共有商标
   */
  private String isMultipleOwners;

  /**
   * 商标共有人
   */
  private String multipleOwners;

  /**
   * 商标类型
   */
  private String registerType;
  /**
   * 专用权期限开始
   */
  private Date possessionTermStart;
  private String showPossessionTermStart;
  /**
   * 专用权期限结束
   */
  private Date possessionTermEnd;
  private String showPossessionTermEnd;
  /**
   * 商标形式
   */
  private String markType;
  /**
   * 国际注册日期
   */
  private Date internationalRegistrationDate;
  private String showInternationalRegistrationDate;
  /**
   * 后期指定日期
   */
  private Date  lateSpecifiedDate;
  private String showLateSpecifiedDate;
  /**
   * 优先权日期
   */
  private String priorityDate;
  /**
   * 代理/办理机构
   */
  private String correspondent;
  /**
   * 商标状态图标
   *
   */
  private String brandStatus;

  /**
   * 商品和服务信息
   */
  private List<ClassificationInfo> classificationInfos;
  /**
   * 商标流程状态
   */
  private List<ProcessState> processStates;

  /**
   * TODO: 待定是否需要删除
   */
  private List<FlowList> flowLists;

  /**
   * TODO:待定是否需要
   *
   * @return
   */
  private List<GoodsList> goodsLists;


  public List<GoodsList> getGoodsLists() {
    return goodsLists;
  }

  public void setGoodsLists(List<GoodsList> goodsLists) {
    this.goodsLists = goodsLists;
  }

  public List<FlowList> getFlowLists() {
    return flowLists;
  }

  public void setFlowLists(List<FlowList> flowLists) {
    this.flowLists = flowLists;
  }

  public List<ClassificationInfo> getClassificationInfos() {
    return classificationInfos;
  }

  public void setClassificationInfos(List<ClassificationInfo> classificationInfos) {
    this.classificationInfos = classificationInfos;
  }

  public List<ProcessState> getProcessStates() {
    return processStates;
  }

  public void setProcessStates(List<ProcessState> processStates) {
    this.processStates = processStates;
  }

  public String getInternationalClassesZh() {
    return internationalClassesZh;
  }

  public void setInternationalClassesZh(String internationalClassesZh) {
    this.internationalClassesZh = internationalClassesZh;
  }

  private List<ProcessState>  trademarkProcessStates;

  public List<ProcessState> getTrademarkProcessStates() {
    return trademarkProcessStates;
  }

  public void setTrademarkProcessStates(List<ProcessState> trademarkProcessStates) {
    this.trademarkProcessStates = trademarkProcessStates;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getMid() {
    return mid;
  }

  public void setMid(Long mid) {
    this.mid = mid;
  }

  public String getImgAddr() {
    return imgAddr;
  }

  public void setImgAddr(String imgAddr) {
    this.imgAddr = imgAddr;
  }

  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getClassificationInfo() {
    return classificationInfo;
  }

  public void setClassificationInfo(String classificationInfo) {
    this.classificationInfo = classificationInfo;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public Date getFilingDate() {
    return filingDate;
  }

  public void setFilingDate(Date filingDate) {
    this.filingDate = filingDate;
  }

  public Integer getInternationalClasses() {
    return internationalClasses;
  }

  public void setInternationalClasses(Integer internationalClasses) {
    this.internationalClasses = internationalClasses;
  }

  public String getApplicantName() {
    return applicantName;
  }

  public void setApplicantName(String applicantName) {
    this.applicantName = applicantName;
  }

  public String getApplicantNameEn() {
    return applicantNameEn;
  }

  public void setApplicantNameEn(String applicantNameEn) {
    this.applicantNameEn = applicantNameEn;
  }

  public String getApplicantAddress() {
    return applicantAddress;
  }

  public void setApplicantAddress(String applicantAddress) {
    this.applicantAddress = applicantAddress;
  }

  public String getApplicantAddressEn() {
    return applicantAddressEn;
  }

  public void setApplicantAddressEn(String applicantAddressEn) {
    this.applicantAddressEn = applicantAddressEn;
  }

  public Integer getFirstPublicationNumber() {
    return firstPublicationNumber;
  }

  public void setFirstPublicationNumber(Integer firstPublicationNumber) {
    this.firstPublicationNumber = firstPublicationNumber;
  }

  public Date getPublishedOppositionDate() {
    return publishedOppositionDate;
  }

  public void setPublishedOppositionDate(Date publishedOppositionDate) {
    this.publishedOppositionDate = publishedOppositionDate;
  }

  public Integer getRegistrationPublicationNumber() {
    return registrationPublicationNumber;
  }

  public void setRegistrationPublicationNumber(Integer registrationPublicationNumber) {
    this.registrationPublicationNumber = registrationPublicationNumber;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public String getIsMultipleOwners() {
    return isMultipleOwners;
  }

  public void setIsMultipleOwners(String isMultipleOwners) {
    this.isMultipleOwners = isMultipleOwners;
  }

  public String getMultipleOwners() {
    return multipleOwners;
  }

  public void setMultipleOwners(String multipleOwners) {
    this.multipleOwners = multipleOwners;
  }

  public String getRegisterType() {
    return registerType;
  }

  public void setRegisterType(String registerType) {
    this.registerType = registerType;
  }

  public Date getPossessionTermStart() {
    return possessionTermStart;
  }

  public void setPossessionTermStart(Date possessionTermStart) {
    this.possessionTermStart = possessionTermStart;
  }

  public Date getPossessionTermEnd() {
    return possessionTermEnd;
  }

  public void setPossessionTermEnd(Date possessionTermEnd) {
    this.possessionTermEnd = possessionTermEnd;
  }

  public String getMarkType() {
    return markType;
  }

  public void setMarkType(String markType) {
    this.markType = markType;
  }

  public Date getInternationalRegistrationDate() {
    return internationalRegistrationDate;
  }

  public void setInternationalRegistrationDate(Date internationalRegistrationDate) {
    this.internationalRegistrationDate = internationalRegistrationDate;
  }

  public Date getLateSpecifiedDate() {
    return lateSpecifiedDate;
  }

  public void setLateSpecifiedDate(Date lateSpecifiedDate) {
    this.lateSpecifiedDate = lateSpecifiedDate;
  }

  public String getPriorityDate() {
    return priorityDate;
  }

  public void setPriorityDate(String priorityDate) {
    this.priorityDate = priorityDate;
  }

  public String getCorrespondent() {
    return correspondent;
  }

  public void setCorrespondent(String correspondent) {
    this.correspondent = correspondent;
  }

  public String getBrandStatus() {
    return brandStatus;
  }

  public void setBrandStatus(String brandStatus) {
    this.brandStatus = brandStatus;
  }

  public String getShowFilingDate() {
    return showFilingDate;
  }

  public void setShowFilingDate(String showFilingDate) {
    this.showFilingDate = showFilingDate;
  }

  public String getShowPublishedOppositionDate() {
    return showPublishedOppositionDate;
  }

  public void setShowPublishedOppositionDate(String showPublishedOppositionDate) {
    this.showPublishedOppositionDate = showPublishedOppositionDate;
  }

  public String getShowRegistrationDate() {
    return showRegistrationDate;
  }

  public void setShowRegistrationDate(String showRegistrationDate) {
    this.showRegistrationDate = showRegistrationDate;
  }

  public String getShowPossessionTermStart() {
    return showPossessionTermStart;
  }

  public void setShowPossessionTermStart(String showPossessionTermStart) {
    this.showPossessionTermStart = showPossessionTermStart;
  }

  public String getShowPossessionTermEnd() {
    return showPossessionTermEnd;
  }

  public void setShowPossessionTermEnd(String showPossessionTermEnd) {
    this.showPossessionTermEnd = showPossessionTermEnd;
  }

  public String getShowInternationalRegistrationDate() {
    return showInternationalRegistrationDate;
  }

  public void setShowInternationalRegistrationDate(String showInternationalRegistrationDate) {
    this.showInternationalRegistrationDate = showInternationalRegistrationDate;
  }

  public String getShowLateSpecifiedDate() {
    return showLateSpecifiedDate;
  }

  public void setShowLateSpecifiedDate(String showLateSpecifiedDate) {
    this.showLateSpecifiedDate = showLateSpecifiedDate;
  }


  public String getLocalImageAddr() {
    return localImageAddr;
  }

  public void setLocalImageAddr(String localImageAddr) {
    this.localImageAddr = localImageAddr;
  }
}
