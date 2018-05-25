package com.cxyhome.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

public class TestQuanDaShiAPPProcessor implements PageProcessor {


    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            //添加抓包获取的cookie信息

//            //模拟cookie
//            .addCookie("TY_SESSION_ID", "cde75843-099f-4c8a-b776-6fe1be9886ec")
//            .addCookie("_zg_user", "%7B%22deptType%22%3A%22trdept%22%2C%22frontendId%22%3A%22211971%22%2C%22iMTokenId%22%3A%2249937f7dba8d4e258fd3366fc0f5c7fd%22%2C%22id%22%3A%22211971%22%2C%22loginType%22%3A%22frontend%22%2C%22money%22%3A0%2C%22passport%22%3A%2213405010402%22%2C%22timeout%22%3A604800%2C%22token%22%3A%2210e8c01b602845b79a48b52f9b4e6caa%22%7D")
//            .addCookie("connect.sid", "s%3ACKQemetoRwlH2OiwlJuDEtgzZHzkGAAZ.RpGWjS3kgDp94ugU3RiOlT7PGQQ1mNqDXPcbDGBGN3U")
//            .addCookie("nb-referrer-hostname", "www.zhiguoguo.com")
//            .addCookie("nb-start-page-url","s%3ACKQemetoRwlH2OiwlJuDEtgzZHzkGAAZ.RpGWjS3kgDp94ugU3RiOlT7PGQQ1mNqDXPcbDGBGN3U")


            //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的


            //模拟手机app浏览器参数
            .addHeader("User-Agent",
                    "Dalvik/2.1.0 (Linux; U; Android 8.0.0; LLD-AL00 Build/HONORLLD-AL00")
            .addHeader("Accept-Encoding", "gzip")
            .addHeader("Connection", "keep-alive")
            .addHeader("Host","brandapi.quandashi.com");





    public void process(Page page) {
        System.out.println(page);


    }

    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Spider spider=Spider.create(new TestZhiGuoGuoProcessor());
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //免费代理不稳定老挂
        //httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("0.0.0.0",0000)));
        spider.setDownloader(httpClientDownloader);
        //定义url链接
        spider.addUrl("http://www.zhiguoguo.com/search/searchTRDetail/58fab148e138235fc5814274")
                .thread(2)
                .run();


    }
}
