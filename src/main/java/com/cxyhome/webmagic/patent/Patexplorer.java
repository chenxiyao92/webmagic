package com.cxyhome.webmagic.patent;

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

public class Patexplorer {

    public static void main(String[] args) {
        HashMap<String, String> maps = new HashMap<>();
        maps.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        maps.put("Accept-Language","zh-CN,zh;q=0.9");
        maps.put("Accept-Encoding","gzip, deflate");
        maps.put("Cache-Control", "max-age=0");
        maps.put("Cookie","UM_distinctid=163bae27ea4382-0051c6d1de2278-444a012d-1fa400-163bae27ea5793; yunsuo_session_verify=9f6e4c6e67af1d9d975176199dd044a3; JSESSIONID=F4E627CF5B03481598765EF752E610CC; PD=4aca4411bfc809164771fb87e3dac77fdfb8f7724724f152a48dd9a35173af1c306a760f30897603; CNZZDATA1261546263=1073925687-1527844710-null%7C1528086071");
        maps.put("Host","www.patexplorer.com");
        maps.put("Proxy-Connection","keep-alive");
        maps.put("Upgrade-Insecure-Requests","1");
        maps.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.patexplorer.com/patent/view.html?patid=CN201810022993.0");


        for (Map.Entry<String, String> entry : maps.entrySet()) {
            httpGet.setHeader(entry.getKey(),entry.getValue());
        }
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应数据
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
                //转换为文本
//                Document parse = Jsoup.parse(content);
//                Elements elements = parse.getElementsByClass("upbox");
//                for (Element link : elements) {
//                    System.out.println("专利名称:"+link.getElementsByClass("detailtitle").first().text());
//                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
