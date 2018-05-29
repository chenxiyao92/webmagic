package com.cxyhome.webmagic.demo;

import com.cxyhome.webmagic.util.HttpUtil;

public class ProxyDemo {

    public static void main(String[] args) {


        String s = HttpUtil.ProxydoGet("http://2018.ip138.com/ic.asp");
        System.out.println(s);


    }

}
