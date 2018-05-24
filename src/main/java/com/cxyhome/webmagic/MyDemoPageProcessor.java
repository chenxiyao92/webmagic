package com.cxyhome.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

public class MyDemoPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setDomain("www.zhiguoguo.com")
            //添加抓包获取的cookie信息

            /**
             *      String cookie = "TY_SESSION_ID=14c833a5-2f35-4231-90ac-dcb02fe94e6f" +
             * //                ",_zg_user=%7B%22deptType%22%3A%22trdept%22%2C%22frontendId%22%3A%22211971%22%2C%22iMTokenId%22%3A%2249937f7dba8d4e258fd3366fc0f5c7fd%22%2C%22id%22%3A%22211971%22%2C%22loginType%22%3A%22frontend%22%2C%22money%22%3A0%2C%22passport%22%3A%2213405010402%22%2C%22timeout%22%3A604800%2C%22token%22%3A%2253a735daa4c3451d89f210d31af337a1%22%7D" +
             * //                ",connect.sid=s%3A4kdXGJkHop1w6Ws4k5Z8_itQEUH2xtxu.NtTxt8fNvaLa7bgq9JAhx%2B%2FRCJ24kU0LSFKzLyu%2F9to" +
             * //                ",nb-referrer-hostname=www.zhiguoguo.com" +
             * //                ",nb-start-page-url=http%3A%2F%2Fwww.zhiguoguo.com%2Fsearch%2FsearchTRDetail%2F5ad5907180e3f43294952967";
             */
            //模拟cookie
            .addCookie("TY_SESSION_ID", "14c833a5-2f35-4231-90ac-dcb02fe94e6f")
            .addCookie("_zg_user", "%7B%22deptType%22%3A%22trdept%22%2C%22frontendId%22%3A%22211971%22%2C%22iMTokenId%22%3A%2249937f7dba8d4e258fd3366fc0f5c7fd%22%2C%22id%22%3A%22211971%22%2C%22loginType%22%3A%22frontend%22%2C%22money%22%3A0%2C%22passport%22%3A%2213405010402%22%2C%22timeout%22%3A604800%2C%22token%22%3A%2295091b4f3c1f4d5a9a22357434fc99ad%22%7D")
            .addCookie("connect.sid", "s%3AdjUIGZOnNyGw3KOjVKFIbWMUUB1DFJtH.%2FX5OgfSFShxE0TKeQpcrDC9XSvx0zEeQ52OPclcRpSE")
            .addCookie("nb-referrer-hostname", "www.zhiguoguo.com")
            .addCookie("nb-start-page-url","http%3A%2F%2Fwww.zhiguoguo.com%2Fsearch%2FsearchTRDetail%2F5ad5907180e3f43294952967")


            //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的
            .addHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate, sdch").addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Connection", "keep-alive")
            .addHeader("Referer", "http://www.zhiguoguo.com");


    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {

        System.out.println("page的url为 "+page.getUrl());
        System.out.println("抓取的内容："+"/r/n"+
                page.getHtml().toString()
        );

        page.putField("information-content","");



        // 部分二：定义如何抽取页面信息，并保存下来
//      把正则匹配出来的数据保存到map中        使用XPath进行解析
//        page.putField("product",page.getUrl().all());

//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name") == null) {
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        //把页面中的符合正则的都加入到爬虫队列中
        page.addTargetRequests(page.getHtml().links().regex("http://www.zhiguoguo.com/*").all());

    }


    public Site getSite() {
        return site;
    }




    public static void main(String[] args) {
       /* long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();

        Spider.create(new MyDemoPageProcessor())
                //设置Scheduler，使用Redis来管理URL队列
                //.setScheduler(new RedisScheduler("localhost"))
                //设置Pipeline，将结果以json方式保存到文件
                .addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
                //从"https://github.com/code4craft"开始抓
                .addUrl("http://www.zhiguoguo.com/search/searchTRDetail/5ad5907180e3f43294952967/")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");*/



            //定义爬虫启动器的配置
            //定义关键词
            String keyWord = "java";
            Spider spider=Spider.create(new TestTaoBaoPageProcessor(keyWord));
            HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
            //免费代理不稳定老挂
            //httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("0.0.0.0",0000)));
            spider.setDownloader(httpClientDownloader);
            //定义url链接
            spider.addUrl("https://s.taobao.com/search?q="+keyWord).thread(1).run();

        }


    }




