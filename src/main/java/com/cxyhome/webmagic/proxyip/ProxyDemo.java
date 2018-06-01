package com.cxyhome.webmagic.proxyip;

import com.cxyhome.webmagic.util.HttpUtil;

public class ProxyDemo {

    public static void main(String[] args) {


//        String s = HttpUtil.ProxydoGet("http://www2.soopat.com/Patent/201730410297");
        String s = HttpUtil.doGet("www.baidu.com");
        System.out.println(s);


    }

}
