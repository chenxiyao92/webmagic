package com.cxyhome.webmagic.util;

import com.cxyhome.webmagic.dao.Transfer;
import com.cxyhome.webmagic.thread.PicDownloader;
import org.apache.commons.codec.digest.DigestUtils;
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
import java.util.*;

import static com.cxyhome.webmagic.util.HttpUtil.getRequestConfig;

/**
 * 图片下载类
 */
public class PicUtil {

    static Logger logger = LoggerFactory.getLogger(PicUtil.class);

    static CloseableHttpClient httpClient;

    public static void main(String[] args) throws IOException {

        ArrayList<String> list = new ArrayList<String>();
        list.add("63a4c25e/a061eae3/44c22709/c29b8a8b/logo_middle.jpg");
//        getQuanDaShiPicUrl(list);





    }

    /**
     * 下载权大师的url的方法
     *
     * @param map 传入的urls
     *             4f6a5dd4/c77de3a8/5474990e/543c7ec7/logo_middle.jpg
     *             https://tm-images.oss-cn-beijing.aliyuncs.com/jpg/c8e720de/d5d19506/fb501b6e/3646c399/logo_middle.jpg
     *             https://tm-images.oss-cn-beijing.aliyuncs.com/jpg/63a4c25e/a061eae3/44c22709/c29b8a8b/logo_original.jpg
     */
    public static void getQuanDaShiPicUrl(Map<String,String> map) {
        //拼接访问链接
        String baseUrl = "https://tm-images.oss-cn-beijing.aliyuncs.com/jpg/";
        RequestConfig config = getRequestConfig();
        String baseFile = "D:\\images\\";
        //创建文件夹和文件
        HashMap<String, String> retryUrlMap = new HashMap<>();
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
                    logger.error("创建文件夹失败,目标文件位置" + entry.getKey());
                    e.printStackTrace();
                }
            }
            //调用httpclient 发送请求
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                logger.info("文本未找到");
                e.printStackTrace();
            }
            //请求
            HttpGet httpGet = new HttpGet(baseUrl + entry.getKey());// 创建httpPost
            //设置代理
            logger.info("代理ip地址是" + config.getProxy().getHostName() + ",代理端口为" + config.getProxy().getPort());
            httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
            //针对QDS网站设置head与cookie信息
            setQDSClientParameter(httpGet);
            CloseableHttpResponse response = null;
            try {
                //执行请求  可能会发生代理失效的情况
                response = httpClient.execute(httpGet);
            } catch (Exception e) {
                //如果发生代理失败的情况 重新获取代理,将url加入url队列
                CloseableHttpResponse closeableHttpResponse;
                try {
                    //重新获取代理替换原来代理
                    httpClient = getNewProxy();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
        new PicDownloader(retryUrlMap).start();
    }

    private static void setQDSClientParameter(HttpGet httpGet) {

//        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
//        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
//        httpGet.setHeader("Cache-Control", "max-age=0");
//        httpGet.setHeader("Connection", "keep-alive");
//        httpGet.setHeader("Host", "tm-images.oss-cn-beijing.aliyuncs.com");
//        httpGet.setHeader("If-Modified-Since", "Tue, 12 Jul 2016 03:15:05 GMT");
//        httpGet.setHeader("If-None-Match", "2EE83FE33D5AE26453755311DA9C60EF");
//        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
//        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");

    }


    /**
     * TODO: 待优化传入为 list
     *
     * @param urlList
     * @param config
     * @throws IOException
     */
    //链接url下载图片商标局图片
    private static void downloadWjssPicture(String urlList, RequestConfig config) throws IOException {
//     源地址   http://wswj.saic.gov.cn:8080/images/TID/201611/822/C5203643B8E4662A1BD3D7125CB49627/02/ORI.JPG
//     目标地址 D:\images/TID/201611/822/C5203643B8E4662A1BD3D7125CB49627/02/ORI.JPG
        File parentPath = new File("D:\\images");
        String subUrl = urlList.substring(36);
        File file = new File(parentPath, subUrl);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        } else {
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        HttpGet httpGet = new HttpGet(urlList);// 创建httpPost
        //设置代理
        logger.info("代理ip地址是" + config.getProxy().getHostName() + ",代理端口为" + config.getProxy().getPort());
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(config).build();
        //设置商标局浏览器参数
        setWsjsClientParameter(httpGet);
        CloseableHttpResponse response = null;
        try {
            //执行请求 如果获取代理失败
            response = httpclient.execute(httpGet);
        } catch (Exception e) {
            //如果发生代理失败的情况 重新获取代理,
            httpclient = getNewProxy();
        }
        //判断返回状 200
        if (!checkState(urlList, response)) {
            return;
        }
        //通过response获取输入流
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
    }

    private static void setWsjsClientParameter(HttpGet httpGet) {
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpGet.setHeader("Content-Type", "486");
        httpGet.setHeader("Connection", "Keep-Alive");
        httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Mobile Safari/537.36");
        httpGet.setHeader("Host", "wswj.saic.gov.cn:8080");
    }

    /**
     * 批量获取国家商标局的url的方法
     *
     * @throws IOException
     */
    private static void getWsjsPicUrl() throws IOException {
        RequestConfig config = getRequestConfig();
//        String url = "http://wswj.saic.gov.cn:8080/images/TID/201611/966/093A959B9EC49287E3CFB4CBA7624B87/02/ORI.JPG";
        List<String> picUrls = Transfer.getPicUrl();
        for (String url : picUrls) {
            try {
                downloadWjssPicture(url, config);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检查返回数据的状态
     *
     * @param urlList
     * @param response
     * @return
     */
    private static boolean checkState(String urlList, CloseableHttpResponse response) {
        StatusLine status = response.getStatusLine();
        int state = status.getStatusCode();
        //如果状态正常
        if (state == HttpStatus.SC_OK) {
            logger.info(urlList+"状态请求正常");
            return true;
        } else {
            logger.error("状态请求失败:请求返回:" + state + "请求url" + urlList);
            return false;
        }
    }

    /**
     * 重新获取代理IP地址
     *
     * @param
     * @return
     * @throws IOException
     */
    private static CloseableHttpClient getNewProxy() throws IOException {
        logger.error("代理IP挂了..重新获取");
        //如果连接超时 获取IP创建连接
        RequestConfig newconfig = getRequestConfig();
        //返回一个更换ProxyIP的浏览器
        return HttpClients.custom().setDefaultRequestConfig(newconfig).build();
    }


    /**
     * 存入访问路径 生成访问路径和存储路径的map
     * @return
     */
    public static Map<String, String> getPicMap(List<String> list,String name) {


        HashMap<String, String> map = new HashMap<>();
        //根据时间随机生成的图片存储路径  name + 年 + 月 +日 + 时间戳
       String storeUrl = name+"/"+DateUtil.getYear()+"/"+DateUtil.getMonth()+"/"+DateUtil.getDay()+"/"+new Date().getTime();
        for (String url : list) {
            //根据url获取图片格式
            String suffix = url.substring(url.indexOf("."));
            // TODO 根据访问路径生成 存储路径map
            map.put(url,storeUrl+"."+suffix);
        }
        return map;
    }


    /**
     * 3571bde9/44aeb4d0/4a57f666/0f57b35b/logo_middle.jpg
     * @param imgAddr
     * @param name
     * @return
     */
    public static String makeLocalImgAddr(String imgAddr, String name) {
//        String storeUrl = name+"/"+DateUtil.getYear()+"/"+DateUtil.getMonth()+"/"+DateUtil.getDay()+"/"+DigestUtils.md5Hex(imgAddr);
        String storeUrl = DigestUtils.md5Hex(imgAddr);
        String suffix = imgAddr.substring(imgAddr.indexOf("."));
         return    storeUrl+suffix;
    }
}