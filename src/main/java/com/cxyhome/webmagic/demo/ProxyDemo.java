package com.cxyhome.webmagic.demo;

import com.cxyhome.webmagic.util.HttpUtil;
import org.apache.http.client.HttpClient;

public class ProxyDemo {

    public static void main(String[] args) {


        String s = HttpUtil.doGet("http://piping.mogumiao.com/proxy/api/get_ip_al?appKey=492fb0fc865646cba9b6798b4093f7ff&count=1&expiryDate=0&format=1&newLine=2");


        System.out.println(s);


    }

}
