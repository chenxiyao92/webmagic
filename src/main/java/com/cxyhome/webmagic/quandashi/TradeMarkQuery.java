package com.cxyhome.webmagic.quandashi;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TradeMarkQuery {

    public static void main(String[] args) throws IOException {

        //传入参数
//        String dateId = QuanDaShiH5List.queryByKeyword("1325487");
//        String result = QuanDaShiH5Detail.queryById(dateId);

        int number = 1325487 ;

        FileWriter fw = new FileWriter("D:/datasource.txt");

        for (int i = 0; i < 100; i++) {

            String dateId = QuanDaShiH5List.queryByKeyword(number+i+"");
            String result = QuanDaShiH5Detail.queryById(dateId);
//            String result = "hahah";
//            System.out.println("取出数据"+result);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS ");
            Date date = new Date();
            String s = sdf.format(date) + result + "\n";
            fw.write(s,0,s.length());
            if (i % 10 ==0){
                fw.flush();
            }

        }

    }



}
