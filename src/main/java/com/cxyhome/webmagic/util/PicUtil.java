package com.cxyhome.webmagic.util;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.cxyhome.webmagic.util.HttpUtil.getRequestConfig;

/**
 * 图片下载类
 */
public class PicUtil {

    public static void main(String[] args) {
        String url = "http://wswj.saic.gov.cn:8080/images/TID/201611/966/093A959B9EC49287E3CFB4CBA7624B87/02/ORI.JPG";
        String path = "D:\\pic\\pic.jpg";
        try {
            downloadPicture(url, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //链接url下载图片
    private static void downloadPicture(String urlList, String path) throws IOException {

        HttpGet httpGet = new HttpGet(urlList);// 创建httpPost
        //设置代理
        RequestConfig config = getRequestConfig();
        HttpHost proxy = new HttpHost(config.getProxy().getHostName(), config.getProxy().getPort());
        System.out.println("代理ip地址是" + config.getProxy().getHostName() + ",代理端口为" + config.getProxy().getPort());

        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(config).build();

        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpGet.setHeader("Content-Type", "486");
        httpGet.setHeader("Connection", "Keep-Alive");
        httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Mobile Safari/537.36");
        String charSet = "UTF-8";
        CloseableHttpResponse response = null;
        response = httpclient.execute(httpGet);
        //通过response获取输入流
        InputStream in = response.getEntity().getContent();




    }
}
