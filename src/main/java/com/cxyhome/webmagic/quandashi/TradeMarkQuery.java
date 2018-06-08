package com.cxyhome.webmagic.quandashi;

import com.alibaba.fastjson.JSON;
import com.cxyhome.webmagic.domain.Info;
import com.cxyhome.webmagic.domain.quandashi.Data;
import com.cxyhome.webmagic.domain.quandashi.FlowList;
import com.cxyhome.webmagic.domain.quandashi.GoodsList;
import com.cxyhome.webmagic.thread.PicDownloaderr;
import com.cxyhome.webmagic.util.PicUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeMarkQuery {

    public static void main(String[] args) throws IOException {
        //传入参数
        List<String> list = new ArrayList<>();
        list.add("1487745");
        list.add("1685748");
        list.add("1727717");
        list.add("1727392");
        list.add("1949644");

        list.add("233216");
        list.add("509529");
        list.add("1690331");
        list.add("602285");
        list.add("1698158");

        list.add("1715737");
        list.add("1729527");
        list.add("1625564");
        list.add("12263754");
        list.add("G870340");

        list.add("508561");
        list.add("513438");
        list.add("508609");
        list.add("1452869");
        list.add("340295");


        list.add("G726661");
        list.add("1449103");
        list.add("559642");
        list.add("1161361");
        list.add("590418");

        list.add("G580499");
        list.add("692862");
        list.add("504413");
        list.add("1685451");
        list.add("883373");

        list.add("1116324");
        list.add("1724883");
        list.add("827783");
        list.add("1448310");
        list.add("1569345");

        list.add("G961038");
        list.add("212879");
        list.add("921085");
        list.add("1167673");
        list.add("G717913");

        list.add("235549");
        list.add("1553798");
        list.add("G973864");
        list.add("648325");
        list.add("648617");

        list.add("557368");
        list.add("1577280");
        list.add("1402472");
        list.add("1486605");
        list.add("1556009");

        list.add("1434337");
        list.add("561901");
        list.add("563504");
        list.add("563494");
        list.add("1697525");

        list.add("1716967");
        list.add("1709644");
        list.add("1007494");
        list.add("1044872");
        list.add("989598");


//        list.add("8834788");
//        list.add("10334769");
//        list.add("5781979");
//        list.add("7269167");
//        list.add("7386891");
//
//        list.add("14203959");
//        list.add("10803195");
//        list.add("14241254");
//        list.add("7076403");
//        list.add("3453425");
//
//        list.add("14841057");
//        list.add("7518117");
//        list.add("9968784");
//        list.add("13306792");
//        list.add("7305811");
//
//        list.add("11761728");
//        list.add("11761811");
//        list.add("5582148");
//        list.add("12913868");
//        list.add("3744138");
//
//
//        list.add("10010262");
//        list.add("10322513");
//        list.add("10322512");
//        list.add("10322514");
//        list.add("8350734");
//
//        list.add("7282397");
//        list.add("7892619");
//        list.add("5645138");
//        list.add("6883655");
//        list.add("13511679");
//
//        list.add("11080706");
//        list.add("13100293");
//        list.add("10413330");
//        list.add("14841411");
//        list.add("14841423");
//
//        list.add("4254937");
//        list.add("9367381");
//        list.add("11710675");
//        list.add("13020767");
//        list.add("13020773");
//
//        list.add("12471133");
//        list.add("10010260");
//        list.add("10891085");
//        list.add("5264196");
//        list.add("5264199");
//
//        list.add("7676235");
//        list.add("7215615");
//        list.add("7215620");
//        list.add("7215621");
//        list.add("7215622");
//
//        list.add("7215616");
//        list.add("7215617");
//        list.add("7215619");
//        list.add("7215623");
//        list.add("7215624");
//
//        list.add("9318802");
//        list.add("13058948");
//        list.add("5047580");
//        list.add("14480870");
//        list.add("14048138");

        arrQuery(list);
    }

    /**
     * 同时下载url图片
     * @param list
     * @throws IOException
     */
    public static void arrQuery(List<String> list) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy--MM-dd");
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
