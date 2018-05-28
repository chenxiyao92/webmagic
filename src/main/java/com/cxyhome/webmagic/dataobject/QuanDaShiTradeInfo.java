/**
  * Copyright 2018 bejson.com 
  */
package com.cxyhome.webmagic.dataobject;
import java.util.List;

/**
 * Auto-generated: 2018-05-28 15:22:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class QuanDaShiTradeInfo {
    /**
     * 商标历程状态
     */
    private List<NoticeList> noticeList;
    /**
     * 子行业
     */
    private List<GoodsList> goodsList;
    /**
     *
     */
    private List<String> graphList;
    /**
     *  商标详情
     */
    private Brand brand;
    /**
     *
     */
    private List<FlowList> flowList;
    public void setNoticeList(List<NoticeList> noticeList) {
         this.noticeList = noticeList;
     }
     public List<NoticeList> getNoticeList() {
         return noticeList;
     }

    public void setGoodsList(List<GoodsList> goodsList) {
         this.goodsList = goodsList;
     }
     public List<GoodsList> getGoodsList() {
         return goodsList;
     }

    public void setGraphList(List<String> graphList) {
         this.graphList = graphList;
     }
     public List<String> getGraphList() {
         return graphList;
     }

    public void setBrand(Brand brand) {
         this.brand = brand;
     }
     public Brand getBrand() {
         return brand;
     }

    public void setFlowList(List<FlowList> flowList) {
         this.flowList = flowList;
     }
     public List<FlowList> getFlowList() {
         return flowList;
     }

}