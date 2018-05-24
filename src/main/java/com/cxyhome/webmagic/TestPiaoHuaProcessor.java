package com.cxyhome.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class TestPiaoHuaProcessor  implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);


//    https://www.piaohua.com/html/xiju/index.html

    //定义电影详情的正则 https://www.piaohua.com/html/dongzuo/2018/0409/33537.html
    public static final String URL_LIST = "http://www\\.piaohua\\.com/html/xiju/\\d+/\\d+/\\d+\\.html";


    //定义电影页码的正则 https://www.piaohua.com/html/dongzuo/list_2.html
    public static final String URL_POST = "http://www\\.piaohua\\.com/html/xiju/list_\\d\\.html";


    //确认加入爬取网址 和 取出结果的对象
    public void process(Page page) {
        //如果是电影页码 则加入匹配队列
        if (page.getUrl().regex(URL_POST).match()){
            //把第二页加入 待爬列表
            page.addTargetRequests(page.getHtml().xpath("//*[@id=\"nml\"]").links().regex(URL_POST).all());
            //把文章内容加入待爬列表
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
        }else{
            //电影详情页
            page.putField("title", page.getHtml().xpath("//*[@id=\"showinfo\"]/div[2]/div"));

            Selectable xpath = page.getHtml().xpath("//*[@id=\"showinfo\"]/table[1]");


        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new TestPiaoHuaProcessor())
                .addUrl("http://www.piaohua.com/html/xiju/list_2.html")
//                .addUrl("https://www.piaohua.com/html/dongzuo/2018/0409/33537.html")
                .thread(5)
                .run();
    }
}
