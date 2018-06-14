package com.cxyhome.webmagic.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 */
public class SinaBlogProcessor implements PageProcessor {

//       博主页 http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html   最后一位1代表的着博主列表的第一页
    public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";

    public static final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

    private Site site = Site
            .me()
            .setDomain("blog.sina.com.cn")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
//       文章页 http://blog.sina.com.cn/s/blog_58ae76e80100s2mo.html
        if (page.getUrl().regex(URL_LIST).match()) {
            Html html = page.getHtml();
            //选择要取出的div部分
            Selectable xpath = html.xpath("//div[@class=\"articleList\"]");
            //获取所有的链接
            Selectable links = xpath.links();
            //匹配需要的链接
            Selectable regex = links.regex(URL_POST);
            //取出所有的需要的链接
            List<String> all = regex.all();
            //把第二页加入 待爬列表
            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_POST).all());
            //把文章内容加入待爬列表
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
        } else {
            //说明是在文章内页
            page.putField("title", page.getHtml().xpath("//div[@class='articalTitle']/h2"));
            page.putField("content", page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
            page.putField("date", page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)"));

        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new SinaBlogProcessor()).addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html")
                .run();
    }
}