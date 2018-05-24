package com.cxyhome.webmagic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;

public class TestWSJSProcessor  implements PageProcessor {


    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(3000)

            //添加抓包获取的cookie信息
            .setDomain("wsjs.saic.gov.cn/")
//            //模拟cookie
//            .addCookie("FSSBBIl1UgzbN7N80S", "8gD_A8zQZomNlqhtt_HtRAJ9U3IXpwLsnYugrTc6GqMZDqy2nxuRtrj4.rJ73SRR")
//            .addCookie("FSSBBIl1UgzbN7N80T", "1j8nLHynbwMHmjan6uUdIlctN6hsKxDOEnCbd6frI.zCCt11b3W5TL8Wob8kfCnEm5xrL4Z73zxT3h2Xu7AXf07I8OnQXqlEpZ07Qr9TM8g6LdtuTiBDjgEe2MnDceDAQ6EVIRtlAvYM_18cPICVHFFU20WxiNcuctkmBLx5L9aRu7eX6Jjip9VKbnAU4QcnurMAoH0FqX0EQIkGiFXMNON0fUECkPtjDawO5DjltPj1HnuY6m2vLO_o.Efc4sWOqDDhvzL6SvQAC.zHHKZh0bFAsXSn8Wmfij0s3.wlxdrnMQBcUmceOx4NZEDoODGjnEXo6zkQ037RwFMrQgcG17_XQWlH.MFARBwipYPuoI7SIE06sTNvDRwB9Yle4qe7v7HA")
//            .addCookie("JSESSIONID", "1FAA70B3E08472DE828C47BBCA3CB32F")
//            .addCookie("__jsluid", "1632b5e48a317c2e72a09a7dad36227f")
//            .addCookie("tmrpToken","E14F711C69DB508B8E898844636CFF80")

            //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的
            .addHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate, sdch")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Connection", "keep-alive");


    public void process(Page page) {
        WebDriver driver = null;
        try {
            driver = TestChromeDriver.getChromeDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.get(page.getRequest().getUrl());
        //点击商标状态
        WebElement state = driver.findElement(By.xpath(" //*[@id=\"txnS03\"]/a"));
//        state.click();

        //选中
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"submitForm\"]/div/div[1]/table/tbody/tr/td[2]/div/input"));
        submitButton.sendKeys("8097339");

        // 提交 input 所在的 form
        WebElement buttom = driver.findElement(By.xpath("//*[@id=\"_searchButton\"]"));
//        buttom.click();

        //点击进入详情页
//        WebElement detail = driver.findElement(By.xpath("//*[@id=\"list_box\"]/table/tbody/tr[2]/td[5]/a"));
//        detail.click();

        //详情页获取数据
        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"tr_detail\"]"));
        String str = webElement.getAttribute("outerHTML");
        System.out.println(str);

    }

    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {

        //定义爬虫启动器的配置
        Spider spider=Spider.create(new TestWSJSProcessor());
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //免费代理不稳定老挂
        //httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("0.0.0.0",0000)));
        spider.setDownloader(httpClientDownloader);
               //定义url链接
               spider
             .addUrl("http://wsjs.saic.gov.cn/")
               //商标状态查询
//               .addUrl("http://wsjs.saic.gov.cn/txnS03.do?y7bRbp=qmMnBeGEhk8NwOlgxU2K0TQEm1V8vFVe6PuLGVwyry9aJnFghbpvEm0IhhC7.IgEzJmAzUejSD3Xvx650mnvAnSKRroZrBdS5naw8PYnKUD8zMxkw6LGA0cT5l65YchdTadsJ0wUXEs2spq6u5O75yOSqke&c1K5tw0w6_=1pnh63NpaYwoHJ6_un7GehLPx2VRbo.gXNe.HBxqg2qBfQrthg8aRscL99LT1eggXKbY2fka0Z0OORdt5wYw4OXQg9ktXy0KFDo0Up0SpEFMiX2WRGqFRSmv.wMbEKftk")
               .addPipeline(new JsonFilePipeline("D:\\webmagic\\download"))
               .thread(3)
               .run();

    }
}
