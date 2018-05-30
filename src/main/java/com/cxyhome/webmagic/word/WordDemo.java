package com.cxyhome.webmagic.word;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordDemo {
    public static void main(String[] args) throws IOException {

//        FileReader fr = new FileReader("C:\\Users\\Administrator\\Desktop\\trade.docx");
        List<String> lines=new ArrayList<String>();
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\trade2.docx"),"UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            lines.add(line);
        }
        br.close();

    }
}
