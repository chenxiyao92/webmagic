package com.cxyhome.webmagic.dataobject;

import java.util.List;

public class ProxyObj {

    private String code;

    private List<ProxyKV> msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ProxyKV> getMsg() {
        return msg;
    }

    public void setMsg(List<ProxyKV> msg) {
        this.msg = msg;
    }
}
