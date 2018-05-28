/**
  * Copyright 2018 bejson.com 
  */
package com.cxyhome.webmagic.dataobject;

/**
 * Auto-generated: 2018-05-28 15:22:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */

/**
 * 权大师接口返回数据
 */
public class TrademarkInfo {


    private int code;
    private String message;
    private int subCode;
    private String subMessage;

    //权大师接口数据
    private QuanDaShiTradeInfo data;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setSubCode(int subCode) {
         this.subCode = subCode;
     }
     public int getSubCode() {
         return subCode;
     }

    public void setSubMessage(String subMessage) {
         this.subMessage = subMessage;
     }
     public String getSubMessage() {
         return subMessage;
     }

    public QuanDaShiTradeInfo getData() {
        return data;
    }

    public void setData(QuanDaShiTradeInfo data) {
        this.data = data;
    }
}