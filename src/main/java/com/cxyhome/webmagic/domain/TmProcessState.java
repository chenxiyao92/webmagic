package com.cxyhome.webmagic.domain;

import java.util.Date;

public class TmProcessState {

  /**
   * 序列号
   */
  private Long id;
  /**
   * 商标名称
   */
  private String mark;
  /**
   * 注册号
   */
  private String serialNumber;
  /**
   * 国际分类
   */
  private Long internationalClasses;

  /**
   * 商标流程 存储为json格式
   */
  private String brandProcessState;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Long getInternationalClasses() {
    return internationalClasses;
  }

  public void setInternationalClasses(Long internationalClasses) {
    this.internationalClasses = internationalClasses;
  }

  public String getBrandProcessState() {
    return brandProcessState;
  }

  public void setBrandProcessState(String brandProcessState) {
    this.brandProcessState = brandProcessState;
  }
}
