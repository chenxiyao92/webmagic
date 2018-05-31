package com.cxyhome.webmagic.util;

import com.cxyhome.webmagic.dao.Transfer;
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
import java.util.List;

import static com.cxyhome.webmagic.util.HttpUtil.getRequestConfig;

/**
 * 图片下载类
 */
public class PicUtil {

    static Logger logger = LoggerFactory.getLogger(PicUtil.class);

    public static void main(String[] args) throws IOException {
        getWsjsPicUrl();

    }

    /**
     * 下载商标局的url的方法
     * @param urls 传入的urls
     *                                                    4f6a5dd4/c77de3a8/5474990e/543c7ec7/logo_middle.jpg
     *  https://tm-images.oss-cn-beijing.aliyuncs.com/jpg/c8e720de/d5d19506/fb501b6e/3646c399/logo_middle.jpg
     */
    public static void getQuanDaShiPicUrl(List<String> urls) {
        String baseUrl = "https://tm-images.oss-cn-beijing.aliyuncs.com/jpg/";
        RequestConfig config = getRequestConfig();
        String baseFile = "D:\\images\\QDS\\";
        //创建文件夹和文件
        for (String url:urls) {
            //截取出真正的url
            String realPath = url.substring(23);
            //目标file文件
            File file = new File(baseFile, realPath);
            if (!file.getParentFile().exists()){
                //创建父文件夹
                file.getParentFile().mkdirs();
            }
            //如果目标文件存在 跳过本次循环
            if (file.exists()){
                continue;
            }else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    logger.error("创建文件夹失败,目标文件位置"+ realPath);
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
            HttpGet httpGet = new HttpGet(baseUrl+realPath);// 创建httpPost
            //设置代理
           logger.info("代理ip地址是" + config.getProxy().getHostName() + ",代理端口为" + config.getProxy().getPort());
            CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(config).build();

            //针对QDS网站设置head与cookie信息
            setQDSClientParameter(httpGet);
            CloseableHttpResponse response ;
            try {
                //执行请求  可能会发生代理失效的情况
                response = httpclient.execute(httpGet);
                //判断返回状 200

                if (!checkState(baseUrl+realPath, response)) {
                    //如果返回状态为错误 则应该等一会再次发送发送请求 TODO:
                    logger.info("请求失败:");
                    return;
                }
            }catch (Exception e){
                CloseableHttpResponse closeableHttpResponse;
                //如果发生代理失败的情况 重新获取代理,并且请求
                try {
                    CloseableHttpClient client = getNewProxy();
                    closeableHttpResponse = client.execute(httpGet);
                    //TODO:
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (!checkState(urlList, closeableHttpResponse)) {
                    logger.error("图片获取失败="+urlList);
                    return;
                }
            }
            //通过response获取输入流
            InputStream inputStream = response.getEntity().getContent();
            byte[] b = new byte[1024*8];
            int len = 0;
            //  一次读取一个字节数组
            while ((len = inputStream.read(b)) != -1) {
                fileOutputStream.write(b, 0, len);
            }
            //关闭IO流
            inputStream.close();
            fileOutputStream.close();






        }






    }

    private static void setQDSClientParameter(HttpGet httpGet) {
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpGet.setHeader("Cache-Control", "max-age=0");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Host", "tm-images.oss-cn-beijing.aliyuncs.com");
        httpGet.setHeader("If-Modified-Since", "Wed, 13 Jul 2016 12:22:26 GMT");
        httpGet.setHeader("If-None-Match", "2EE83FE33D5AE26453755311DA9C60EF");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");
    }


    //链接url下载图片
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
        System.out.println("代理ip地址是" + config.getProxy().getHostName() + ",代理端口为" + config.getProxy().getPort());
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(config).build();
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpGet.setHeader("Content-Type", "486");
        httpGet.setHeader("Connection", "Keep-Alive");
        httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Mobile Safari/537.36");
        httpGet.setHeader("Host", "wswj.saic.gov.cn:8080");

        String charSet = "UTF-8";
        CloseableHttpResponse response ;
        try {
            //执行请求
             response = httpclient.execute(httpGet);
             //判断返回状 200
            if (!checkState(urlList, response)) {
                return;
            }
        }catch (Exception e){
            //如果发生代理失败的情况 重新获取代理,并且请求
            response = getNewProxy(httpGet);
            if (!checkState(urlList, response)) {
                logger.error("图片获取失败="+urlList);
                return;
            }
        }
        //通过response获取输入流
        InputStream inputStream = response.getEntity().getContent();
        byte[] b = new byte[1024*8];
        int len = 0;
        //  一次读取一个字节数组
        while ((len = inputStream.read(b)) != -1) {
            fileOutputStream.write(b, 0, len);
        }
        //关闭IO流
        inputStream.close();
        fileOutputStream.close();
    }

    /**
     * 批量获取国家商标局的url的方法
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
     * @param urlList
     * @param response
     * @return
     */
    private static boolean checkState(String urlList, CloseableHttpResponse response) {
        StatusLine status = response.getStatusLine();
        int state = status.getStatusCode();
        //如果状态正常
        if (state == HttpStatus.SC_OK) {
            logger.info("状态请求正常");
            return true;
        }
        else{
            logger.error("状态请求失败:请求返回:"+state+"请求url"+urlList);
            return false;
        }
    }

    /**
     * 重新获取代理IP地址
     * @param
     * @return
     * @throws IOException
     */
    private static CloseableHttpClient getNewProxy() throws IOException {
        logger.error("代理IP挂了..重新获取");
        //如果连接超时 获取IP创建连接
        RequestConfig newconfig = getRequestConfig();
        //返回一个更换ProxyIP的浏览器
        return   HttpClients.custom().setDefaultRequestConfig(newconfig).build();;
    }
}
