package com.cxyhome.webmagic.quandashi;

import com.alibaba.fastjson.JSONObject;
import com.cxyhome.webmagic.dataobject.JsonRootBean;
import com.cxyhome.webmagic.util.HttpUtil;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuanDaShiH5List {

    static Pattern pattern = Pattern.compile("id:");




    public static String queryByKeyword(String key) {
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
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("facet", "");
        map.put("facetField", "create_year,process3_code,type_code");
        map.put("field", "name");
        map.put("page", 0);
        map.put("pageSize", 15);

        map.put("q", key);
        map.put("style", "");

        //列表页参数
        param.put("map",map);
        try {
            //详情页json解析
            String date = HttpUtil.doQuanDaShiH5ListPost("http://39.107.156.86:8888/api",param.toJSONString(),key);
            System.out.println(date);
            JsonRootBean jsonRootBean = JSONObject.parseObject(date, JsonRootBean.class);
            return jsonRootBean.getData().getItems().get(0).getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
