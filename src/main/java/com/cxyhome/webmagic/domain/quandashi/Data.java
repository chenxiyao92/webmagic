package com.cxyhome.webmagic.domain.quandashi;

import java.util.List;

/**
 * Auto-generated: 2018-05-31 14:37:40
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private List<NoticeList> noticeList;
    private List<GoodsList> goodsList;
    private List<String> graphList;
    private Brand brand;
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