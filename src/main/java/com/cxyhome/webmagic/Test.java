package com.cxyhome.webmagic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {

//        https://www.piaohua.com/html/xiju/index.html

//        String index = "http://www.piaohua.com/html/xiju/list_2.html";
//
////定义电影页码的正则 https://www.piaohua.com/html/dongzuo/list_2.html
//         String URL_POST = "http://www\\.piaohua\\.com/html/xiju/list_\\d\\.html";
//
//
//        System.out.println(index.matches(URL_POST));


        String url = "<tr>\n" +
                "\">magnet:?xt=urn:btih:ZEY5WXM2JIAD6NUUSRAJFQH57CG2ZCJL </a></td>\n" +
                "</tr>";
        String regex = "magnet\\:\\?xt=urn\\:btih:[1-9a-zA-Z]+";

        //比较器对象
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(url);
        if (matcher.find()){
            System.out.println(matcher.group());
        }

    }
}
