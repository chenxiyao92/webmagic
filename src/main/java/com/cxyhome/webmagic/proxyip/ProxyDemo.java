package com.cxyhome.webmagic.proxyip;

import com.cxyhome.webmagic.util.HttpUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProxyDemo {

    public static void main(String[] args) {
        HashMap<String, String> maps = new HashMap<>();
        maps.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("www.baidu.com");

        for (Map.Entry<String, String> entry : maps.entrySet()) {
            httpGet.setHeader(entry.getKey(),entry.getValue());
        }
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应数据
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //转换为文本
                Document parse = Jsoup.parse(content);
                Elements elements = parse.getElementsByClass("upbox");
                for (Element link : elements) {
                    System.out.println("专利名称:"+link.getElementsByClass("detailtitle").first().text());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
