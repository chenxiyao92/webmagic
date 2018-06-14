package com.cxyhome.webmagic.processor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;

public class TestZhiGuoGuoProcessor  implements PageProcessor {


    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setDomain("www.zhiguoguo.com")
            //添加抓包获取的cookie信息

            //模拟cookie
            .addCookie("TY_SESSION_ID", "cde75843-099f-4c8a-b776-6fe1be9886ec")
            .addCookie("_zg_user", "%7B%22deptType%22%3A%22trdept%22%2C%22frontendId%22%3A%22211971%22%2C%22iMTokenId%22%3A%2249937f7dba8d4e258fd3366fc0f5c7fd%22%2C%22id%22%3A%22211971%22%2C%22loginType%22%3A%22frontend%22%2C%22money%22%3A0%2C%22passport%22%3A%2213405010402%22%2C%22timeout%22%3A604800%2C%22token%22%3A%2210e8c01b602845b79a48b52f9b4e6caa%22%7D")
            .addCookie("connect.sid", "s%3ACKQemetoRwlH2OiwlJuDEtgzZHzkGAAZ.RpGWjS3kgDp94ugU3RiOlT7PGQQ1mNqDXPcbDGBGN3U")
            .addCookie("nb-referrer-hostname", "www.zhiguoguo.com")
            .addCookie("nb-start-page-url","s%3ACKQemetoRwlH2OiwlJuDEtgzZHzkGAAZ.RpGWjS3kgDp94ugU3RiOlT7PGQQ1mNqDXPcbDGBGN3U")


            //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的
            .addHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate, sdch").addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Connection", "keep-alive")
            .addHeader("Referer", "http://www.zhiguoguo.com");


    public void process(Page page) {


//        System.out.println(page);

//        System.out.println(page);

        //获取 phantomJSDriver对象
//        WebDriver driver= TestPhantomJsDriver.getPhantomJSDriver();

        WebDriver driver = null;
        try {
            driver = TestChromeDriver.getChromeDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 让浏览器访问 Baidu
        // 用下面代码也可以实现
        //driver.navigate().to("http://www.baidu.com");
        // 获取 网页的 title

//        WebDriver driver= null;
//        try {
//            driver = TestChromeDriver.getChromeDriver();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //讲page的rul传入driver的url
        driver.get(page.getRequest().getUrl());

        WebElement passport =driver.findElement(By.id("passport"));
        // 输入关键字
        passport.sendKeys("13405010402");

        WebElement passwd = driver.findElement(By.xpath("//*[@id=\"passwd\"]"));

        // 输入关键字
        passwd.sendKeys("jonas65610112");


        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div/form/div/button"));
        // 提交 input 所在的 form
        submitButton.click();


//        WebElement webElement = driver.findElement(By.id("ad_entry_b2"));
//        String str = webElement.getAttribute("outerHTML");
//        WebElement webElement = driver.findElement(By.id("index-nav-2"));
//        WebElement webElement = ((PhantomJSDriver) driver).findElementById("tr_detail");
//        WebElement webElement = driver.findElement(By.id("onLine"));
        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"tr_detail\"]"));


        String str = webElement.getAttribute("outerHTML");
        System.out.println(str);

    }

    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {

        //定义爬虫启动器的配置
        //定义关键词
//        String keyWord = "凯谦";
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
