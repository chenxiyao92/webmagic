package com.cxyhome.webmagic.patent;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SooIpList {


    public static void main(String[] args) throws DocumentException {
        //设置heads配置
        String pid = getPID("CN201010271917.7");
        if (pid==null){
            return;
        }
        getZhuLuInfo(pid);




    }

    private static void getZhuLuInfo(String pid) {


//      url中未进行_sessionID  拼接   String _sessionID = "&_sessionID=b6XMPBzZN7MrmStW";
        String url = "http://www.so.iptrm.com/app/patentdetail?isNewWindow=yes&pid="+pid+"&patentType=patent2&patentLib=";

        Map<String, String> maps = setHead();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            httpGet.setHeader(entry.getKey(), entry.getValue());
        }
        try {

            CloseableHttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应数据 讲string转换为xml
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");

                //转换为文本　解析页面
                Document parse = Jsoup.parse(content);
                Elements elements = parse.getElementsByClass("qwb_box_tab");
                for (org.jsoup.nodes.Element link : elements) {

                    System.out.println("专利名称:"+link.text());
                    System.out.println("专利名称:"+link.text());
                    System.out.println("专利名称:"+link.text());
                    System.out.println("专利名称:"+link.text());
                    System.out.println("专利名称:"+link.text());
                    System.out.println("专利名称:"+link.text());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static String getPID(String PID) throws DocumentException {
        Map<String, String> maps = setHead();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.so.iptrm.com/txnPatentData01.ajax");
//        httpPost.setConfig(HttpUtil.getRequestConfig());
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }
        // 构造一个form表单式的实体
        StringEntity formEntity = null;
        try {
            //这里填写申请号
            formEntity = new StringEntity(fillParameters(PID));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 将请求实体设置到httpPost对象中
        httpPost.setEntity(formEntity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应数据 讲string转换为xml
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                // 将xml格式字符串转化为DOM对象
                org.dom4j.Document document = DocumentHelper.parseText(content);
                // 获取根结点对象
                Element rootElement = document.getRootElement();
                // 循环根节点，获取其子节点
                for (Iterator iter = rootElement.elementIterator(); iter.hasNext(); ) {
                    Element element = (Element) iter.next(); // 获取标签对象
                    // 循环第一层节点，获取其子节点
                    for (Iterator iterInner = element.elementIterator(); iterInner
                            .hasNext(); ) {
                        // 获取标签对象
                        Element elementOption = (Element) iterInner.next();
                        // 获取该标签对象的名称
                        String tagName = elementOption.getName();
                        // 获取该标签对象的内容
                        String tagContent = elementOption.getTextTrim();
                        if ("PID".equals(tagName)) {
//                            System.out.println("标签名=" + tagName + ",url=" + tagContent);
                            return tagContent;
                        }
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Map<String, String> setHead() {
        //查询商标
        HashMap<String, String> maps = new HashMap<>();
        maps.put("Accept", "*/*");
        maps.put("Accept-Encoding", "gzip, deflate");
        maps.put("Accept-Language", "zh-CN,zh;q=0.9");
        maps.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        //其中cookie用户验证用户信息;
        maps.put("Cookie", "Hm_lvt_c30cfe64c111172dcd13abe3d7532080=1527910296,1528074267,1528090573,1528181420;" +
                "Hm_lpvt_c30cfe64c111172dcd13abe3d7532080=1528184217; " +
                "JSESSIONID=5EB35CB9361D2D7408176C487AF1191B");
        maps.put("Host", "www.so.iptrm.com");
        maps.put("Origin", "http://www.so.iptrm.com");
        maps.put("Proxy-Connection", "keep-alive");
        maps.put("Referer", "http://www.so.iptrm.com/app/patentlist");
        maps.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");
        maps.put("X-Requested-With", "XMLHttpRequest");
        return maps;

    }


    /**
     *
     * @param express 为申请号
     * @return
     */
   public static String fillParameters(String express) {
       String parameters = "secondKeyWord=%E7%94%B3%E8%AF%B7%E5%8F%B7" + "&" +
               "secondkeyWordVal="+ express + "&" +
               "secondSearchType=NOT" + "&" +
               "express2=" + "&" +
               "express=" + URLEncoder.encode("(申请号 =  ( "+express+"% ) )") + "&" +
               "isFamily=" + "&" +
               "categoryIndex=&selectedCategory=" + "&" +
               "patentLib=" + "&" +
               "patentType=patent2" + "&" +
               "order=" + "&" +
               "pdbt=" + "&" +
               "attribute-node:patent_cache-flag=false" + "&" +
               "attribute-node:patent_start-row=1" + "&" +
               "attribute-node:patent_page-row=10" + "&" +
               "attribute-node:patent_sort-column=ano" + "&" +
               "attribute-node:patent_page=1";
        return parameters;
    }

}

