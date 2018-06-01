package com.cxyhome.webmagic.thread;

import com.cxyhome.webmagic.util.PicUtil;

import java.util.Map;

public class PicDownloaderr extends Thread {

     Map<String,String> urls;

    /**
     *  通过线程类传入参数下载图片
     */
    public void run() {
        PicUtil.getQuanDaShiPicUrl(urls);
    }


   public PicDownloaderr(Map<String,String> urls){
        this.urls = urls;
    }

}
