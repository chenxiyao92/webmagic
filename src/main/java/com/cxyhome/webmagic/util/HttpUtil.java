package com.cxyhome.webmagic.util;

import com.alibaba.fastjson.JSONObject;
import com.cxyhome.webmagic.dataobject.ProxyKV;
import com.cxyhome.webmagic.dataobject.ProxyObj;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * @author 马弦
 * @date 2017年10月23日 下午2:49
 * HttpClient工具类
 */
public class HttpUtil {

    private static Logger logger = Logger.getLogger(HttpUtil.class);


    /**
     * get请求
     * @return
     */
    public static String doGet(String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());

                return strResult;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 通过代理发送get请求
     * @return
     */
    public static String ProxydoGet(String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            RequestConfig config = getRequestConfig();
            HttpHost proxy = new HttpHost(config.getProxy().getHostName(), config.getProxy().getPort());
            System.out.println("代理ip地址是"+config.getProxy().getHostName()+",代理端口为"+config.getProxy().getPort());
            client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity(),"gb2312");

                return strResult;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * post请求(用于key-value格式的参数)
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map params){

        BufferedReader in = null;
        try {
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();

            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));

            //设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));

                //System.out.println(name +"-"+value);
            }
            request.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if(code == 200){    //请求成功
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(),"utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();

                return sb.toString();
            }
            else{   //
                System.out.println("状态码：" + code);
                return null;
            }
        }
        catch(Exception e){
            e.printStackTrace();

            return null;
        }
    }

    /**
     * post请求（用于请求json格式的参数）
     * @param url
     * @param params
     * @return
     */
    @Test
    public static String doPost(String url, String params) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept-Encoding","gzip");
        httpPost.setHeader("User-Agent","Dalvik/2.1.0 (Linux; U; Android 8.0.0; LLD-AL00 Build/HONORLLD-AL00)");
        httpPost.setHeader("Connection","Keep-Alive");
        httpPost.setHeader("Host","brandapi.quandashi.com");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            else{
                logger.error("请求返回:"+state+"("+url+")");
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //共享的代理对象
    static  HttpHost proxy = null;

    public static  RequestConfig getRequestConfig(){
        if (proxy==null){
            String getProxyUrl = "http://piping.mogumiao.com/proxy/api/get_ip_al?appKey=492fb0fc865646cba9b6798b4093f7ff&count=1&expiryDate=0&format=1&newLine=2";
            String result = HttpUtil.doGet(getProxyUrl);
            ProxyKV proxyKV = parseObject(result, ProxyObj.class).getMsg().get(0);
            //IP代理
            proxy = new HttpHost(proxyKV.getIp(),proxyKV.getPort(),null);
        }
        //设置IP代理
        RequestConfig requestConfig=RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .setProxy(proxy)
                .build();
        return  requestConfig;
    }

    /**
     * 权大师 请求（用于请求json格式的参数）
     * @param url
     * @param params
     * @return
     */
    public static String doQuanDaShiH5DetailPost(String url, String params,String dateId) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();


        HttpPost httpPost = new HttpPost(url);// 创建httpPost

        //设置代理
        RequestConfig config = getRequestConfig();
        HttpHost proxy = new HttpHost(config.getProxy().getHostName(), config.getProxy().getPort());
        System.out.println("代理ip地址是"+config.getProxy().getHostName()+",代理端口为"+config.getProxy().getPort());
        httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);


        httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpPost.setHeader("Accept-Encoding","gzip, deflate");
        httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
        httpPost.setHeader("Content-Type","486");
        httpPost.setHeader("Connection","Keep-Alive");
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
//        httpPost.setHeader("Proxy-Connection","Keep-Alive");
        httpPost.setHeader("Host","39.107.156.86:8888");
        httpPost.setHeader("Origin","http://h5.quandashi.com");

        httpPost.setHeader("Referer","http://h5.quandashi.com/search/searchDetail.html?id="+dateId);
//        httpPost.setHeader("Referer","http://h5.quandashi.com/search/search.html?search_text=100001&pageSize=10&page=0&name=name");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Mobile Safari/537.36");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            else{
                logger.error("请求返回:"+state+"("+url+")");
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 权大师 列表页
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String doQuanDaShiH5ListPost(String url, String params,String key) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        //设置IP代理
        //设置代理
        RequestConfig config = getRequestConfig();
        HttpHost proxy = new HttpHost(config.getProxy().getHostName(), config.getProxy().getPort());
        System.out.println("代理ip地址是"+config.getProxy().getHostName()+",代理端口为"+config.getProxy().getPort());
        httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);


        httpPost.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
        httpPost.setHeader("Accept-Encoding","gzip, deflate");
        httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");

        httpPost.setHeader("Content-Type","application/json;charset=UTF-8");
        httpPost.setHeader("Host","39.107.156.86:8888");
        httpPost.setHeader("Origin","http://h5.quandashi.com");
        httpPost.setHeader("Proxy-Connection","keep-alive");
        httpPost.setHeader("Referer","http://h5.quandashi.com/search/search.html?search_text="+key+"+&pageSize=10&page=0&name=name");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Mobile Safari/537.36");


        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            else{
                logger.error("请求返回:"+state+"("+url+")");
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}