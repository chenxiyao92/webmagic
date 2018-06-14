package com.cxyhome.webmagic.domain;


public class ClassificationInfo {

  /**
   * 中类别号码
   */
  private long id;
  /**
   * 商品名称
   */
  private String productName;
  /**
   * 国际分类号
   */
  private long internationalClasses;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }


  public long getInternationalClasses() {
    return internationalClasses;
  }

  public void setInternationalClasses(long internationalClasses) {
    this.internationalClasses = internationalClasses;
  }

}
