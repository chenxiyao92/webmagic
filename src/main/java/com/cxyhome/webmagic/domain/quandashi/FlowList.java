package com.cxyhome.webmagic.domain.quandashi;

import java.util.Date;

/**
 * Auto-generated: 2018-05-31 14:37:40
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class FlowList {

    private String code;
    private String name;
    private Date lastTime;

    private String showLastTime;

    public String getShowLastTime() {
        return showLastTime;
    }

    public void setShowLastTime(String showLastTime) {
        this.showLastTime = showLastTime;
    }

    public void setCode(String code) {
         this.code = code;
     }
     public String getCode() {
         return code;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setLastTime(Date lastTime) {
         this.lastTime = lastTime;
     }
     public Date getLastTime() {
         return lastTime;
     }

}