package com.cxyhome.webmagic.quandashi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cxyhome.webmagic.domain.Info;
import com.cxyhome.webmagic.domain.quandashi.Data;
import com.cxyhome.webmagic.domain.quandashi.FlowList;
import com.cxyhome.webmagic.domain.quandashi.GoodsList;
import com.cxyhome.webmagic.thread.PicDownloaderr;
import com.cxyhome.webmagic.util.PicUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TradeMarkQuery {

    public static void main(String[] args) throws IOException {

        arrQuery(getJson("C:\\Users\\Administrator\\Desktop\\ids.json"));
    }


    /**
     * 读取json数据
     */
    public static List<String> getJson(String path) throws IOException {
        File file=new File(path);
        String content= FileUtils.readFileToString(file,"UTF-8");
          return JSONObject.parseArray(content, String.class);
    }




    /**
     * 同时下载url图片
     * @param list
     * @throws IOException
     */
    public static void arrQuery(List<String> list) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> urls = new ArrayList<String>();
        ArrayList objs = new ArrayList();
        Map<String, String> urlMap = new HashMap<>();
        for (String id : list) {
            String dateId = QuanDaShiH5List.queryByKeyword(id);
            //权大师INFO对象
            Data data = QuanDaShiH5Detail.queryById(dateId).getData();
            if (data ==null){
                continue;
            }
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
            info.setInternationalClassesZh("厨房用器具");
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
                info.setShowPublishedOppositionDate(null);
            }
//            info.setRegistrationPublicationNumber(Integer.parseInt(data.getBrand().getRegisterIssue()));
            //申请日期 v-createDate : "2012-05-25"
            info.setRegistrationDate(data.getBrand().getRegisterDate());
            if (data.getBrand().getRegisterDate() != null) {
                info.setShowRegistrationDate(sdf.format(data.getBrand().getRegisterDate()));
            } else {
                info.setShowRegistrationDate(null);
            }
//            info.setIsMultipleOwners("无");
//            info.setMultipleOwners("");
//            info.setRegisterType("一般");

            //注册公告日 privateStartDate : "2013-09-14"
            info.setPossessionTermStart(data.getBrand().getPrivateStartDate());
            if (data.getBrand().getPrivateStartDate() != null) {
                info.setShowPossessionTermStart(sdf.format(data.getBrand().getPrivateStartDate()));
            } else {
                info.setShowPossessionTermStart(null);
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
            info.setFlowLists(flowList);
            List<GoodsList> goodsList = data.getGoodsList();
            for (GoodsList g:goodsList) {
                g.setDataId(null);
                g.setFlag(null);
                g.setIsdelete(null);
                g.setRepairCode(null);
            }
            info.setGoodsLists(goodsList);
            //取出url 生成本地url
//            urls.add(info.getImgAddr());
            //生成本地访问的url
            info.setLocalImageAddr(PicUtil.makeLocalImgAddr(info.getImgAddr(),"quandashi"));
            //生成urlMap
            urlMap.put(info.getImgAddr(), info.getLocalImageAddr());
            objs.add(info);
        }
        //开启一个线程下载权大师的图片
        new PicDownloaderr(urlMap).start();
        String result = JSON.toJSONString(objs);
        System.out.println(result);
    }


}
