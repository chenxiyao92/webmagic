package com.cxyhome.webmagic.domain;

public class TradeClassify {

    private Integer id;

    private String shortDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public TradeClassify() {
    }

    public TradeClassify(Integer id, String shortDesc) {
        this.id = id;
        this.shortDesc = shortDesc;
    }

    @Override
    public String toString() {
        return "TradeClassify{" +
                "id=" + id +
                ", shortDesc='" + shortDesc + '\'' +
                '}';
    }
}
