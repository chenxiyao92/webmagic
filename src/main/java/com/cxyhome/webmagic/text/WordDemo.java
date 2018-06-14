package com.cxyhome.webmagic.text;

import com.alibaba.fastjson.JSONObject;
import com.cxyhome.webmagic.domain.TradeClassify;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordDemo {
    public static void main(String[] args) throws IOException {
        getTxt();
    }

    private static void getTxt() throws IOException {
        List<String> lines=new ArrayList<String>();
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\trade.txt"),"UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("第")){
                String[] strs = line.split("\\s");
                strs.toString();
                System.out.println(line);
            }
            lines.add(line);
        }
        br.close();
    }

    /**
     * 读取json数据
     */
    public static List<TradeClassify> getJsonArrat(String path) throws IOException {
        File file=new File(path);
        String content= FileUtils.readFileToString(file,"UTF-8");
        int i = 1;
        ArrayList<TradeClassify> list = new ArrayList<>();
        List<String> strings = JSONObject.parseArray(content, String.class);
        for (String s:strings) {
            list.add(new TradeClassify(i,s));
            i++;
        }
        return list;
    }


    /**
     * 读取json数据String数据
     */
    public static List<String> getJson(String path) throws IOException {
        File file=new File(path);
        String content= FileUtils.readFileToString(file,"UTF-8");
        return JSONObject.parseArray(content, String.class);
    }



}
