package com.cxyhome.webmagic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestQuanDaShiProcessor  implements PageProcessor {

    public static  int id = 10001;


//    a href="/index/searchdetail?brand=4f4f574c4156513355474876687255554f38725058673d3d
    public static final String SEARCH_KEY_URL = "https://so.quandashi.com/";

    //正则
    public static  final String SEARCH_REGEX  =  "/index/searchdetail?brand=";



    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setDomain("www.zhiguoguo.com")
            //添加抓包获取的cookie信息


            //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的
            .addHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate, sdch").addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Connection", "keep-alive")
            .addHeader("Referer", "http://www.zhiguoguo.com");


    public void process(Page page) {
        //匹配器
        Pattern compile = Pattern.compile(SEARCH_REGEX);

        WebDriver driver = null;
        try {
             driver = TestChromeDriver.getChromeDriver();
            driver.get(page.getUrl().toString());
            WebElement element = driver.findElement(By.xpath("//*[@id=\"qds-searchkey\"]"));
            element.sendKeys(id+"");

            WebElement element1 = driver.findElement(By.xpath("//*[@id=\"com-topSearch\"]/div/div/input[2]"));
            element1.click();

            //TODO: 待解决 点击验证 和 点击图形验证


            //获取搜索页的结果
            List<WebElement> allResult = driver.findElements(By.xpath("/html/body/div[4]/div/div[7]"));
            for (WebElement e: allResult) {
                Matcher matcher = compile.matcher(e.toString());
                if (matcher.find()){
                    String detailurl = matcher.group();
                    System.out.println(detailurl);
                }


            }




        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (driver!=null){
                driver.quit();
            }
        }
    }

    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {

        //定义爬虫启动器的配置
        //定义关键词
//        String keyWord = "凯谦";
        Spider spider=Spider.create(new TestQuanDaShiProcessor());
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //免费代理不稳定老挂
        //httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("0.0.0.0",0000)));
        spider.setDownloader(httpClientDownloader);
        //定义url链接
        spider.addUrl("https://www.quandashi.com/")
                .thread(3)
                .run();

    }


}
