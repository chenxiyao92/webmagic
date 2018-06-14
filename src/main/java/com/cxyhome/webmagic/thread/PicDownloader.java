package com.cxyhome.webmagic.thread;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.cxyhome.webmagic.util.HttpUtil.getRequestConfig;

public class PicDownloader extends Thread {

    Logger logger = LoggerFactory.getLogger(PicDownloader.class);

     Map<String,String> map;

    /**
     * 图片下载浏览器
     */
    CloseableHttpClient httpClient;


    /**
     *  通过线程类传入参数下载图片
     */
    public void run() {

        //拼接访问链接
        String baseUrl = "https://tm-images.oss-cn-beijing.aliyuncs.com/jpg/";

        //创建文件夹和文件
        //重试url列表
        HashMap<String, String> retryUrlMap = new HashMap<>();

        String baseFile = "D:\\images\\";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //目标file文件
            File file = new File(baseFile, entry.getValue());
            if (!file.getParentFile().exists()) {
                //创建父文件夹
                file.getParentFile().mkdirs();
            }
            //如果目标文件存在 跳过本次循环
            if (file.exists()) {
                continue;
            } else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    logger.warn("创建文件夹失败,目标文件位置" + entry.getKey());
                    e.printStackTrace();
                }
            }
            //调用httpclient 发送请求
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                logger.warn("文本未找到");
                e.printStackTrace();
            }
            //请求
            HttpGet httpGet = new HttpGet(baseUrl + entry.getKey());// 创建httpPost
            //设置代理
            if (httpClient == null){
                httpClient = HttpClients.custom().setDefaultRequestConfig(getRequestConfig()).build();
            }
            CloseableHttpResponse response = null;
            try {
                //执行请求  可能会发生代理失效的情况
                response = httpClient.execute(httpGet);
            } catch (Exception e) {
                //如果发生代理失败的情况 重新获取代理,将url加入url队列
                httpClient =  HttpClients.custom().setDefaultRequestConfig(getRequestConfig()).build();
                retryUrlMap.put(map.get(entry.getKey()), entry.getValue());
//              如果出错 返回为null 应该重新获得
                continue;
            }

            //判断请求返回状态 200
            if (!checkState(baseUrl + entry.getKey(), response)) {
                //如果返回状态为错误 则将url加入url中再次发送
                retryUrlMap.put(map.get(entry.getKey()), entry.getValue());
                logger.info(baseUrl + entry.getKey() + "返回状态出错,下载图片失败");
                // 跳过本次
                continue;
            }

            //通过response获取输入流
            try {
                InputStream inputStream = response.getEntity().getContent();
                byte[] b = new byte[1024 * 8];
                int len = 0;
                //  一次读取一个字节数组
                while ((len = inputStream.read(b)) != -1) {
                    fileOutputStream.write(b, 0, len);
                }
                //关闭IO流
                inputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将下载失败的重新下载一遍
        if (retryUrlMap.size()!=0){
          new PicDownloader(retryUrlMap).start();
        }
    }


    /**
     * 重新获取代理IP地址
     *
     * @param
     * @return
     * @throws IOException
     */
    private  CloseableHttpClient getNewProxy() throws IOException {
        logger.error("代理IP挂了..重新获取");
        //如果连接超时 获取IP创建连接
        RequestConfig newconfig = getRequestConfig();
        //返回一个更换ProxyIP的浏览器
        return HttpClients.custom().setDefaultRequestConfig(newconfig).build();
    }

   public PicDownloader(Map<String,String> urls){
        this.map = urls;
    }

    /**
     * 检查返回数据的状态
     *
     * @param urlList
     * @param response
     * @return
     */
    private boolean checkState(String urlList, CloseableHttpResponse response) {
        int state = response.getStatusLine().getStatusCode();
        //如果状态正常
        if (state == HttpStatus.SC_OK) {
            logger.info(urlList+"状态请求正常");
            return true;
        } else {
            logger.error("状态请求失败:请求返回:" + state + "请求url" + urlList);
            return false;
        }
    }

}
