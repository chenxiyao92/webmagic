package com.cxyhome.webmagic.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cxyhome.webmagic.dataobject.Items;
import com.cxyhome.webmagic.dataobject.JsonRootBean;
import com.cxyhome.webmagic.dataobject.ProxyKV;
import com.cxyhome.webmagic.dataobject.ProxyObj;
import com.cxyhome.webmagic.domain.Info;
import com.cxyhome.webmagic.domain.quandashi.Data;
import com.cxyhome.webmagic.domain.quandashi.FlowList;
import com.cxyhome.webmagic.domain.quandashi.GoodsList;
import com.cxyhome.webmagic.domain.quandashi.QuanDaShiTrademarkInfo;
import com.cxyhome.webmagic.util.HttpUtil;
import com.cxyhome.webmagic.util.PicUtil;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.alibaba.fastjson.JSON.parseObject;

public class TrademarkDownloader extends Thread {

    Logger logger = LoggerFactory.getLogger(TrademarkDownloader.class);

    public List<String> list;

    private SimpleDateFormat sdf;

    /**
     * 详情页浏览器参数配置
     */
    private CloseableHttpClient detailHttpClient;

    /**
     *
     */
    private CloseableHttpClient listHttpClient;

    /**
     * 目标对象集合
     */
    private List<Info> objs;

    @Override
    public void run() {
        logger.info(getName()+"线程启动");
        long startDate = new Date().getTime();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        //目标对象集合
        objs = new ArrayList<>();
        //图片url的集合
        Map<String, String> urlMap = new HashMap<>();
        //申请号id结合
        for (String id : list) {
            //查询同申请号的多个商标
            List<String> ids = queryByKeyword(id);
            if (ids==null || ids.size()==0) {
                continue;
            }
            //权大师INFO对象
            for (String s:ids) {
                Data data = queryById(s).getData();
                if (data!=null){
                    continue;
                }
                //将权大师Data对象 封装为 自身数据库对象
                Info info = toInfo(data);
                if (info != null) {
                    urlMap.put(info.getImgAddr(), info.getLocalImageAddr());
                    objs.add(info);
                }
            }
        }
        //将目标对象保存到数据库
        String result = JSON.toJSONString(objs);

        //开启一个线程下载权大师的图片
        logger.info("待下载的图片有"+urlMap.size()+"张"+urlMap.toString());
        if (urlMap.size()!=0){
            new PicDownloader(urlMap).start();
        }
        //打印出结果
       logger.info(result);
        long endDate = new Date().getTime();
        logger.info(getName()+"线程结束,用时"+ (endDate-startDate)/1000+"秒");
    }
    //共享的代理对象
    HttpHost proxy = null;
    RequestConfig config = null;
    /**
     * 根据权大师的内容部id进行查询
     *
     * @param id
     * @return
     */
    public QuanDaShiTrademarkInfo queryById(String id) {
        JSONObject param = new JSONObject();
        param.put("appKey", "quandashi2151283371");
        param.put("executor", id);
        param.put("format", "json");
        param.put("geetestChallenge", "");
        param.put("geetestSeccode", "");
        param.put("geetestUniqueCode", "");
        param.put("geetestValidate", "");
        param.put("ip", "");
        param.put("method", "brandSearchDetailById");
        //列表页请求参数
//        param.put("method", "brandSearch");
        param.put("oldVersion", "old");
        param.put("partnerId", "");
        param.put("sign", "");
        param.put("signMethod", "md5");
        param.put("simplify", "false");
        param.put("targetAppKey", "");
        param.put("timestamp", "");
        param.put("v", "1.0");
        HashMap<String, Object> map = new HashMap<>();
        //详情页参数
        //查询的参数进行了加密 a3dcb4d229de6fde0db5686dee47145d
        map.put("id", id);
        map.put("dataId", id);
        param.put("map", map);

        HttpPost httpPost = new HttpPost("http://39.107.156.86:8888/api");// 创建httpPost
        if (detailHttpClient == null) {
            detailHttpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
        }
        httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpPost.setHeader("Content-Type", "486");
        httpPost.setHeader("Connection", "Keep-Alive");
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setHeader("Host", "39.107.156.86:8888");
        httpPost.setHeader("Origin", "http://h5.quandashi.com");
        httpPost.setHeader("Referer", "http://h5.quandashi.com/search/searchDetail.html?id=" + id);
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Mobile Safari/537.36");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(param.toJSONString(), charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            //这边可能会报错
            response = detailHttpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //详情页json解析
               return JSONObject.parseObject(EntityUtils.toString(response.getEntity()), QuanDaShiTrademarkInfo.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //重新获取代理ip和client对象
            listHttpClient = HttpClients.custom().setDefaultRequestConfig(getRequestConfig()).build();
        }finally {
            String result = null;
            if (response == null ) {
                result = "失败";
            }else {
                result = "成功";
            }
            //针对请求的失败的未进行操作
            logger.info("申请号" + id + "数据获取"+result);
        }
        return null;
    }

    /**
     * 根据关键词查询出列表
     *
     * @param key
     * @return
     */
    public List<String> queryByKeyword(String key) {
        //定义post请求参数
        JSONObject param = new JSONObject();
        param.put("appKey", "quandashi2151283371");
        param.put("executor", "5a32564d76707a34576e377a4a6a64536f4c737133673d3d");
        param.put("format", "json");
        param.put("geetestChallenge", "");
        param.put("geetestSeccode", "");
        param.put("geetestUniqueCode", "");
        param.put("geetestValidate", "");
        param.put("ip", "");
        param.put("method", "brandSearch");
        param.put("oldVersion", "old");
        param.put("partnerId", "false");
        param.put("signMethod", "md5");
        param.put("targetAppKey", "");
        param.put("timestamp", "");
        param.put("v", "1.0");
        HashMap<String, Object> map = new HashMap<>();
        map.put("facet", "");
        map.put("facetField", "create_year,process3_code,type_code");
        map.put("field", "name");
        map.put("page", 0);
        map.put("pageSize", 15);
        map.put("q", key);
        map.put("style", "");
        //列表页参数
        param.put("map", map);

        // 创建httpPost
        HttpPost httpPost = new HttpPost("http://39.107.156.86:8888/api");
        //设置IP代理
        if (config == null) {
            config = getRequestConfig();
        }
        logger.info("代理ip地址是" + config.getProxy().getHostName() + ",代理端口为" + config.getProxy().getPort());
        //实例化CloseableHttpClient对象
        if (listHttpClient == null) {
            listHttpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
        }
        httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setHeader("Host", "39.107.156.86:8888");
        httpPost.setHeader("Origin", "http://h5.quandashi.com");
        httpPost.setHeader("Proxy-Connection", "keep-alive");
        httpPost.setHeader("Referer", "http://h5.quandashi.com/search/search.html?search_text=" + key + "+&pageSize=10&page=0&name=name");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Mobile Safari/537.36");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(param.toJSONString(), charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            //可能会出现连接超时情况
            response = listHttpClient.execute(httpPost);
            int state = response.getStatusLine().getStatusCode();
            if (state == HttpStatus.SC_OK) {
                JsonRootBean jsonRootBean = JSONObject.parseObject(EntityUtils.toString(response.getEntity()), JsonRootBean.class);
                //取出对象第一个对象
                List<Items> items = jsonRootBean.getData().getItems();
                List<String> list = new ArrayList<>();
                for (Items i:items) {
                    list.add(i.getId());
                }
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //重新获取代理ip和client对象
            listHttpClient = HttpClients.custom().setDefaultRequestConfig(getRequestConfig()).build();
        } finally {
            String result = null;
            if (response == null ) {
                result = "失败";
            }else {
                result = "成功";
            }
            //针对请求的失败的未进行操作
            logger.info("申请号" + key + "获取权大师列表数据"+result);
        }
        return null;
    }

    public RequestConfig getRequestConfig() {
        if (proxy == null) {
            String getProxyUrl = "http://piping.mogumiao.com/proxy/api/get_ip_al?appKey=492fb0fc865646cba9b6798b4093f7ff&count=1&expiryDate=0&format=1&newLine=2";
            String result = HttpUtil.doGet(getProxyUrl);
            ProxyKV  proxyKV = parseObject(result, ProxyObj.class).getMsg().get(0);
            //IP代理
            proxy = new HttpHost(proxyKV.getIp(), proxyKV.getPort(), null);
        }
        //设置IP代理
        config = RequestConfig.custom()
                //设置传输超时时间 秒
                .setConnectTimeout(30000)
                //设置请求时间
                .setSocketTimeout(30000)
                .setProxy(proxy)
                .build();
        return config;
    }

    //将Data对象封装为Info对象
    private Info toInfo(Data data) {
        //如果查询出的数据为空 返回为空
        if (data == null) {
            return null;
        }
        //如果查询出的数据为空,则跳过
        if (data ==null){
            return null;
        }
        //准备封装数据
        Info info = new Info();
        if (data.getBrand().getLogoUrl() != null) {
            info.setImgAddr(data.getBrand().getLogoUrl());
        } else {
            info.setImgAddr(null);
        }
        info.setMark(data.getBrand().getName());
//            info.setClassificationInfo("1101");
//            info.setRegistrationNumber(data.getBrand().getRegisterIssue());
//            info.setFilingDate(data.getBrand().getRegisterDate());
        //国际分类号
        info.setInternationalClasses(Integer.parseInt(data.getBrand().getTypeCode()));
        //注册号
        info.setSerialNumber(data.getBrand().getDataId());
        //申请人名称
        info.setApplicantName(data.getBrand().getApplicant());
//            info.setApplicantNameEn(data.getBrand().getEnApplicant());
//            info.setApplicantAddress(data.getBrand().getAdress());
//            info.setApplicantAddressEn(data.getBrand().getEnAdress());
//            info.setFirstPublicationNumber(Integer.parseInt(data.getBrand().getNoticeIssue()));

        //初审公告日 noticeDate : "2013-06-13"
        info.setPublishedOppositionDate(data.getBrand().getNoticeDate());
        if (data.getBrand().getNoticeDate() != null) {
            info.setShowPublishedOppositionDate(sdf.format(data.getBrand().getNoticeDate()));
        } else {
            info.setShowPublishedOppositionDate("暂无");
        }
//            info.setRegistrationPublicationNumber(Integer.parseInt(data.getBrand().getRegisterIssue()));

        //申请日期 v-createDate : "2012-05-25"
        info.setRegistrationDate(data.getBrand().getRegisterDate());
        if (data.getBrand().getRegisterDate() != null) {
            info.setShowRegistrationDate(sdf.format(data.getBrand().getRegisterDate()));
        } else {
            info.setShowRegistrationDate("暂无");
        }
//            info.setIsMultipleOwners("无");
//            info.setMultipleOwners("");
//            info.setRegisterType("一般");

        //注册公告日 privateStartDate : "2013-09-14"
        info.setPossessionTermStart(data.getBrand().getPrivateStartDate());
        if (data.getBrand().getPrivateStartDate() != null) {
            info.setShowPossessionTermStart(sdf.format(data.getBrand().getPrivateStartDate()));
        } else {
            info.setShowPossessionTermStart("暂无");
        }
//            info.setPossessionTermEnd(data.getBrand().getPrivateEndDate());
//            info.setMarkType("颜色商标");
//            info.setInternationalRegistrationDate(new Date());
//            info.setLateSpecifiedDate(new Date());
//            info.setPriorityDate("无优先权");
        info.setCorrespondent(data.getBrand().getAgency());
//            info.setBrandStatus(JSON.toJSONString(data.getNoticeList()));

        //权大师的流程对象
        List<FlowList> flowList = data.getFlowList();
        if (flowList!=null && flowList.size()>0){
            for (FlowList f : flowList) {
                if (f.getLastTime()!=null){
                    f.setShowLastTime(sdf.format(f.getLastTime()));
                }
                f.setLastTime(null);
                f.setCode(null);
            }
        }
        //对流程对象进行日期倒叙
        ArrayList<FlowList> returnFlowList = new ArrayList<>();
        for (int i = flowList.size()-1;i>=0;i--){
            returnFlowList.add(flowList.get(i));
        }
        info.setFlowLists(returnFlowList);

        List<GoodsList> goodsList = data.getGoodsList();
        for (GoodsList g : goodsList) {
            g.setDataId(null);
            g.setFlag(null);
            g.setIsdelete(null);
            g.setRepairCode(null);
        }
        info.setGoodsLists(goodsList);
        //取出url 生成本地url
//            urls.add(info.getImgAddr());
        //生成本地访问的url
        info.setLocalImageAddr(PicUtil.makeLocalImgAddr(info.getImgAddr(), "quandashi"));
        return info;
    }


    public TrademarkDownloader(List<String> list) {
        this.list = list;
    }





}
