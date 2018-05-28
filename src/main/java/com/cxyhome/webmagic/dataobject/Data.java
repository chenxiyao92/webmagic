/**
  * Copyright 2018 bejson.com 
  */
package com.cxyhome.webmagic.dataobject;
import java.util.List;

/**
 * Auto-generated: 2018-05-28 11:41:7
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private int totalResults;
    private int QTime;
    private int pages;
    private List<Items> items;
    private List<String> facets;
    public void setTotalResults(int totalResults) {
         this.totalResults = totalResults;
     }
     public int getTotalResults() {
         return totalResults;
     }

    public void setQTime(int QTime) {
         this.QTime = QTime;
     }
     public int getQTime() {
         return QTime;
     }

    public void setPages(int pages) {
         this.pages = pages;
     }
     public int getPages() {
         return pages;
     }

    public void setItems(List<Items> items) {
         this.items = items;
     }
     public List<Items> getItems() {
         return items;
     }

    public void setFacets(List<String> facets) {
         this.facets = facets;
     }
     public List<String> getFacets() {
         return facets;
     }

}