package com.cxyhome.webmagic;

public class Test {
    public static void main(String[] args) {

//        https://www.piaohua.com/html/xiju/index.html

        String index = "http://www.piaohua.com/html/xiju/list_2.html";

//定义电影页码的正则 https://www.piaohua.com/html/dongzuo/list_2.html
         String URL_POST = "http://www\\.piaohua\\.com/html/xiju/list_\\d\\.html";


        System.out.println(index.matches(URL_POST));


    }
}
