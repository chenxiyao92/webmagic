package com.cxyhome.webmagic.patent;

import com.cxyhome.webmagic.util.HttpUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PssGovDetail {
    public static void main(String[] args) {
        HashMap<String, String> maps = new HashMap<>();
        maps.put("Accept", "application/json, text/javascript, */*; q=0.01");
        maps.put("Accept-Encoding", "gzip, deflate");
        maps.put("Accept-Language", "zh-CN,zh;q=0.9");
        //其中cookie用户验证用户信息;
        maps.put("Cookie", "WEE_SID=8SLNd0ny_yt2lKUe5nTL9Gq0h4Y-tdJlZRkLBPcjtLiq9PFUhzmA!-155051809!-1122471164!1528160537074; IS_LOGIN=true; wee_username=Y3h5NDM3NjAxNTQ1; wee_password=am9uYXM2NTYxMDExMg%3D%3D; locale=en_US; avoid_declare=declare_pass; JSESSIONID=8SLNd0ny_yt2lKUe5nTL9Gq0h4Y-tdJlZRkLBPcjtLiq9PFUhzmA!-155051809!-1122471164");
        maps.put("Host", "www.pss-system.gov.cn");
        maps.put("Origin", "http://www.pss-system.gov.cn");
        maps.put("Proxy-Connection", "keep-alive");
        maps.put("Referer", "http://www.pss-system.gov.cn/sipopublicsearch/patentsearch/showViewList-jumpToView.shtml");
        maps.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");
        maps.put("X-Requested-With", "XMLHttpRequest");



        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.pss-system.gov.cn/sipopublicsearch/patentsearch/showViewList-showViewList.shtml");
        httpPost.setConfig(HttpUtil.getRequestConfig());
//        HttpGet httpPost = new HttpGet("http://www.pss-system.gov.cn/sipopublicsearch/portal/uiIndex.shtml");
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }

//        // 设置post请求体，设置post请求参数
//        MyList<NameValuePair> parameters = new ArrayList<>();
//        /**
//         * searchCondition.searchExp=((%E5%8F%91%E6%98%8E%E5%90%8D%E7%A7%B0%3D(%E7%BE%8E%E7%9A%84)))&
//         * searchCondition.dbId=VDB&
//         * searchCondition.searchType=Sino_foreign&
//         * searchCondition.extendInfo%5B'MODE'%5D=MODE_TABLE&
//         * searchCondition.extendInfo%5B'STRATEGY'%5D=STRATEGY_CALCULATE&
//         * searchCondition.originalLanguage=&
//         * searchCondition.targetLanguage=&
//         * wee.bizlog.modulelevel=0200201&
//         * resultPagination.limit=12
//         */
//
//        parameters.add(new BasicNameValuePair("searchCondition.searchExp","((发明名称=(隔离)))"));
//        parameters.add(new BasicNameValuePair("searchCondition.dbId", "VDB"));
//        parameters.add(new BasicNameValuePair("searchCondition.searchType", "Sino_foreign"));
//        parameters.add(new BasicNameValuePair("searchCondition.extendInfo['MODE']", "MODE_TABLE"));
//        parameters.add(new BasicNameValuePair("earchCondition.extendInfo['STRATEGY']", "STRATEGY_CALCULATE"));
//        parameters.add(new BasicNameValuePair("searchCondition.originalLanguage", ""));
//        parameters.add(new BasicNameValuePair("searchCondition.targetLanguage", ""));
//        parameters.add(new BasicNameValuePair("wee.bizlog.modulelevel", "0200201"));
//        parameters.add(new BasicNameValuePair("resultPagination.limit", "12"));

        // 构造一个form表单式的实体
//        UrlEncodedFormEntity formEntity = null;
//        try {
//            formEntity = new UrlEncodedFormEntity(parameters);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        // 将请求实体设置到httpPost对象中
//        httpPost.setEntity(formEntity);

        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应数据
                String   content = EntityUtils.toString(response.getEntity(), "UTF-8");
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
        }finally {
//            System.out.println(response);
        }

    }


}

