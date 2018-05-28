package com.cxyhome.webmagic.quandashi;

import com.alibaba.fastjson.JSONObject;
import com.cxyhome.webmagic.util.HttpUtil;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuanDaShiH5Detail {






    public static String queryById(String id) {

        JSONObject param = new JSONObject();
        param.put("appKey","quandashi2151283371");
        param.put("executor",id);
        param.put("format","json");
        param.put("geetestChallenge", "");
        param.put("geetestSeccode", "");
        param.put("geetestUniqueCode", "");
        param.put("geetestValidate", "");
        param.put("ip", "");
        param.put("method","brandSearchDetailById");
        //列表页请求参数
//        param.put("method", "brandSearch");
        param.put("oldVersion","old");
        param.put("partnerId","");
        param.put("sign","");
        param.put("signMethod","md5");
        param.put("simplify", "false");
        param.put("targetAppKey", "");
        param.put("timestamp", "");
        param.put("v","1.0");
        HashMap<String, Object> map = new HashMap<String, Object>();
        //详情页参数
        //查询的参数进行了加密 a3dcb4d229de6fde0db5686dee47145d
        map.put("id",id);
        map.put("dataId",id);
        param.put("map",map);
        try {
            //详情页json解析
            String date = HttpUtil.doQuanDaShiH5DetailPost("http://39.107.156.86:8888/api",param.toJSONString(),id);
            return  date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
