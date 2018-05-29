package com.cxyhome.webmagic.domain;

public class TmProcessState {

  private long id;
  private String mark;
  private long serialNumber;
  private long internationalClasses;
  private String processName;
  private String segmentName;
  private String conclusion;
  private java.sql.Date conclusionDate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }


  public long getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(long serialNumber) {
    this.serialNumber = serialNumber;
  }


  public long getInternationalClasses() {
    return internationalClasses;
  }

  public void setInternationalClasses(long internationalClasses) {
    this.internationalClasses = internationalClasses;
  }


  public String getProcessName() {
    return processName;
  }

  public void setProcessName(String processName) {
    this.processName = processName;
  }


  public String getSegmentName() {
    return segmentName;
  }

  public void setSegmentName(String segmentName) {
    this.segmentName = segmentName;
  }


  public String getConclusion() {
    return conclusion;
  }

  public void setConclusion(String conclusion) {
    this.conclusion = conclusion;
  }


  public java.sql.Date getConclusionDate() {
    return conclusionDate;
  }

  public void setConclusionDate(java.sql.Date conclusionDate) {
    this.conclusionDate = conclusionDate;
  }

}
