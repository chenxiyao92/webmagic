package com.cxyhome.webmagic.patent;

import com.alibaba.fastjson.JSON;
import com.cxyhome.webmagic.domain.Patent.Instruction;
import com.cxyhome.webmagic.domain.Patent.InstructionPic;
import com.cxyhome.webmagic.domain.Patent.LawStatus;
import com.cxyhome.webmagic.domain.Patent.PatentInfo;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SooIpList {


    public static void main(String[] args) throws DocumentException {
        //设置heads配置
        String pid = getPID("CN201520925715.8");
        if (pid==null){
            return;
        }
        PatentInfo info = getZhuLuInfo(pid);
        info =getZhaiYao(info,pid);
        info =getQuanLi(info,pid);
        info =getShuoMing(info,pid);
        info =getLawdetail(info,pid);
        String jsonString = JSON.toJSONString(info);
        System.out.println(jsonString);
    }

    private static PatentInfo getLawdetail(PatentInfo info, String pid) {
        SimpleDateFormat sdf3 = new SimpleDateFormat();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String url = "http://www.so.iptrm.com/app/lawdetail?isNewWindow=yes&pno_pbdt=" + info.getPublicationNumber() + "&" +
                "pid_pbdt=" + pid + "&pno=" + info.getPublicationNumber() + "&patentLib=&patentType=patent2&select-key%3Apd=" + sdf.format(info.getPublicationDate()) + "&select-key%3Apns=" + info.getPublicationNumber() + "&pid=" + pid;
        Map<String, String> maps = setHead();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            httpGet.setHeader(entry.getKey(), entry.getValue());
        }
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应数据 讲string转换为xml
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //转换为文本　解析页面
                Document parse = Jsoup.parse(content);
                Elements qwb_box_tabx = parse.getElementsByClass("qwb_box_tabx");
                for (org.jsoup.nodes.Element link : qwb_box_tabx) {
                    //日期
                    Elements spans = link.getElementsByTag("span");
                    //法律状态
                    Elements tds = link.select("td[width$=\"23%\"]").select("td:nth-child(2)");
                    //法律状态信息
                    Elements s3 = link.select("td[width$=\"54%\"]");
                    List<LawStatus> lawStatus = new ArrayList<>(spans.size());
                    for (int i = 0; i <spans.size() ; i++) {
                        try {
                            LawStatus status = new LawStatus(sdf2.parse(spans.eq(i).text()), tds.eq(i).text(), s3.eq(i).text());
                            status.setShowLawStatusPublicationDate(sdf3.format(status.getLawStatusPublicationDate()));
                            lawStatus.add(status);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    info.setLawStates(lawStatus);
                }
            }
                return  info;
            }
         catch (IOException e) {
            e.printStackTrace();
        }
        return null;




    }

    private static PatentInfo getShuoMing(PatentInfo info, String pid)  {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //            http://123.138.111.182:8804/html/PID/CNA0/2011/0817/00000000102151/114620AV4P015746/CLA/CLA_ZH.html
        String url = "http://123.138.111.182:8804/html/PID/"+pid.substring(3, 7)+"/"+pid.substring(7, 11)+"/"+pid.substring(11, 15)+"/"+pid.substring(15, 29)+"/"+pid.substring(29, 45)+"/DES/DES_ZH.html";
        Map<String, String> maps = setHead();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            httpGet.setHeader(entry.getKey(), entry.getValue());
        }
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应数据 讲string转换为xml
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");

                //转换为文本　解析页面
                String text = null;
                StringBuilder builder = new StringBuilder();
                Document parse = Jsoup.parse(content);
                Elements elements = parse.getElementsByTag("span");
                for (org.jsoup.nodes.Element link : elements) {
                    text= builder.append(link.text()).toString();
                }
                Instruction instruction1 = ToPatentInstruction(text);
                instruction1.setImgs(info.getInstruction().getImgs());
                info.setInstruction(instruction1);
                return  info;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**　
     *   将字符串网页解析成为说明
     * 　发明内容和实用新型内容 互斥
     *   附图说明不一定有
     *   页面结构内容是 技术领域 背景技术 (发明内容 实用新型内容) (附图说明) 具体实施方式
     * @param text
     *  TODO: 外观设计未完善
     * @return
     */
    private static Instruction ToPatentInstruction(String text) {
        String result = text.replaceAll("\\[\\d{4}\\]", "");
        int technicalFieldStart = result.indexOf("技术领域");
        int technicalBackgroundStart = result.indexOf("背景技术");
        int inventionContentStart = result.indexOf("发明内容");
        int utilityInventionContentStart = result.indexOf("实用新型内容");
        int drawingsShowStart = result.indexOf("附图说明");
        int specificImplementationStart = result.indexOf("具体实施方式");
        String title = result.substring(0,technicalFieldStart );
        String technicalField = result.substring(technicalFieldStart+4, technicalBackgroundStart);
        String technicalBackground;
        String utilityInventionContent = null;
        String inventionContent = null;
        String drawingsShow  = null;
        //附图说明存在
        if (drawingsShowStart!=-1){
            if (inventionContentStart==-1){
                //实用新型内容
                technicalBackground =  result.substring(technicalBackgroundStart + 4, utilityInventionContentStart);
                utilityInventionContent   = result.substring(utilityInventionContentStart + 6, drawingsShowStart);
                drawingsShow   =  result.substring(drawingsShowStart+4,specificImplementationStart);
            }else {
                //发明内容
                technicalBackground =  result.substring(technicalBackgroundStart + 4, inventionContentStart);
                inventionContent =   result.substring(inventionContentStart+4,drawingsShowStart);
                drawingsShow   =  result.substring(drawingsShowStart+4,specificImplementationStart);
            }
        }else {
            //附图说明不存在
            if (inventionContentStart==-1){
                //如果是实用新型内容 并且 附图说明存在
                utilityInventionContent = result.substring(utilityInventionContentStart + 6, specificImplementationStart);
                technicalBackground =  result.substring(technicalBackgroundStart + 4, utilityInventionContentStart);
            }else {
                //如果 是发明内容 并且 附图说明存在
                 inventionContent =  result.substring(inventionContentStart+4,specificImplementationStart);
                technicalBackground =  result.substring(technicalBackgroundStart + 4, inventionContentStart);
            }
        }
        String specificImplementation = result.substring(specificImplementationStart+4,result.length()-1);
        Instruction instruction = new Instruction();
        instruction.setTitle(title);
        instruction.setTechnicalField(technicalField);
        instruction.setTechnicalBackground(technicalBackground);
        instruction.setInventionContent(inventionContent);
        instruction.setUtilityInventionContent(utilityInventionContent);
        instruction.setDrawingsShow(drawingsShow);
        instruction.setSpecificImplementation(specificImplementation);
        return instruction;
    }


    /**
     * 权利多行
     * @param info
     * @param pid
     * @return
     */
    private static PatentInfo getQuanLi(PatentInfo info, String pid) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //                           http://123.138.111.182:8804/html/PID/CNA0/2011/0817/00000000102151/114620AV4P015746/CLA/CLA_ZH.html
        String url = "http://123.138.111.182:8804/html/PID/"+pid.substring(3, 7)+"/"+pid.substring(7, 11)+"/"+pid.substring(11, 15)+"/"+pid.substring(15, 29)+"/"+pid.substring(29, 45)+"/CLA/CLA_ZH.html";
        Map<String, String> maps = setHead();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            httpGet.setHeader(entry.getKey(), entry.getValue());
        }
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应数据 讲string转换为xml
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");

                //转换为文本　解析页面
                Document parse = Jsoup.parse(content);
                Elements elements = parse.getElementsByTag("span");
                StringBuilder builder = new StringBuilder();
                for (org.jsoup.nodes.Element link : elements) {

                    //将权利存入info
                    builder.append(link.text());
                }
                 info.setRight(ToPatentRight(builder.toString()));
                return info;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 权利
     * @param
     * @return
     */
    private static String ToPatentRight(String str) {
        return str.replaceAll("\\d{4}\\s\\.", "");
    }

    private static PatentInfo getZhaiYao(PatentInfo info, String pid) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//                    http://123.138.111.182:8804/html/PID/CNU0/2017/0915/00000000206496/119L0R441V014098/ABS/ABS_ZH.html
        String url = "http://123.138.111.182:8804/html/PID/"+pid.substring(3, 7) +"/"+pid.substring(7, 11)+"/"+pid.substring(11, 15)+"/"+pid.substring(15, 29)+"/"+pid.substring(29, 45)+"/ABS/ABS_ZH.html";
        Map<String, String> maps = setHead();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            httpGet.setHeader(entry.getKey(), entry.getValue());
        }
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应数据 讲string转换为xml
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");

                //转换为文本　解析页面
                Document parse = Jsoup.parse(content);
                Elements elements = parse.getElementsByTag("span");
                for (org.jsoup.nodes.Element link : elements) {
                    //将摘要存入info
                    info.setSummary(link.text());
                }
                return info;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static PatentInfo getZhuLuInfo(String pid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        PatentInfo patentInfo = new PatentInfo();

//      url中未进行_sessionID  拼接   String _sessionID = "&_sessionID=b6XMPBzZN7MrmStW";
        String url = "http://www.so.iptrm.com/app/patentdetail?isNewWindow=yes&pid="+pid+"&patentType=patent2&patentLib=";

        Map<String, String> maps = setHead();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            httpGet.setHeader(entry.getKey(), entry.getValue());
        }
        try {

            CloseableHttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应数据 讲string转换为xml
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");

                //转换为文本　解析页面
                Document parse = Jsoup.parse(content);
                //获取说明图片
                org.jsoup.nodes.Element imgList = parse.getElementById("imgList");
                Elements imgs = imgList.getElementsByTag("dd");
                //保存说明书图片
                List<InstructionPic> list = new ArrayList();
                Instruction instruction = new Instruction();
                for (org.jsoup.nodes.Element e : imgs) {
                    String u = e.getElementsByTag("img").attr("src");
                    String name = e.getElementsByTag("div").text();
                    list.add(new InstructionPic(name, u));
                }
                instruction.setImgs(list);
                patentInfo.setInstruction(instruction);

                Elements elements = parse.getElementsByClass("qwb_box_tab");
                for (org.jsoup.nodes.Element link : elements) {
                    //将著录信息写入
                    String[] split = link.text().split("\\s");
                    //申请号
                    patentInfo.setApplicationNumber(split[1]);
                    //公开号
                    patentInfo.setPublicationNumber(split[3]);
                    try {
                    //申请日
                        patentInfo.setApplicationDate(sdf.parse(split[5]));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        // 公开日
                        patentInfo.setPublicationDate(sdf.parse(split[7]));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //申请人
                    patentInfo.setApplicant(split[9]);
                    // TODO: 著录项目信息封装未完成
                    patentInfo.setInventor(split[11]);
                    patentInfo.setPostalcode(split[13]);
                    patentInfo.setApplicantAddress(split[14]);
                    patentInfo.setApplicationAreaCode(split[16]);
                    patentInfo.setAssignee(split[18]);
                    patentInfo.setLocarnoClassification(split[20]);
                    patentInfo.setIpc(split[22]);
                    patentInfo.setCpc(split[24]);
                    patentInfo.setPriorityNumber(split[26]);
                    patentInfo.setAgency(split[28]);
                    patentInfo.setAgenyCode(split[29]);
                    patentInfo.setAgent(split[31]);
                    //sooip中 审查员 国际申请 国际公开 进入国家日期 分案申请未封装
                    return patentInfo;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getPID(String PID) throws DocumentException {
        Map<String, String> maps = setHead();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.so.iptrm.com/txnPatentData01.ajax");
//        httpPost.setConfig(HttpUtil.getRequestConfig());
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }
        // 构造一个form表单式的实体
        StringEntity formEntity = null;
        try {
            //这里填写申请号
            formEntity = new StringEntity(fillParameters(PID));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 将请求实体设置到httpPost对象中
        httpPost.setEntity(formEntity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应数据 讲string转换为xml
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                // 将xml格式字符串转化为DOM对象
                org.dom4j.Document document = DocumentHelper.parseText(content);
                // 获取根结点对象
                Element rootElement = document.getRootElement();
                // 循环根节点，获取其子节点
                for (Iterator iter = rootElement.elementIterator(); iter.hasNext(); ) {
                    Element element = (Element) iter.next(); // 获取标签对象
                    // 循环第一层节点，获取其子节点
                    for (Iterator iterInner = element.elementIterator(); iterInner
                            .hasNext(); ) {
                        // 获取标签对象
                        Element elementOption = (Element) iterInner.next();
                        // 获取该标签对象的名称
                        String tagName = elementOption.getName();
                        // 获取该标签对象的内容
                        String tagContent = elementOption.getTextTrim();
                        if ("PID".equals(tagName)) {
//                            System.out.println("标签名=" + tagName + ",url=" + tagContent);
                            return tagContent;
                        }
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Map<String, String> setHead() {
        //查询商标
        HashMap<String, String> maps = new HashMap<>();
        maps.put("Accept", "*/*");
        maps.put("Accept-Encoding", "gzip, deflate");
        maps.put("Accept-Language", "zh-CN,zh;q=0.9");
        maps.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        //其中cookie用户验证用户信息;
        maps.put("Cookie", "Hm_lvt_c30cfe64c111172dcd13abe3d7532080=1527910296,1528074267,1528090573,1528181420;" +
                "Hm_lpvt_c30cfe64c111172dcd13abe3d7532080=1528184217; " +
                "JSESSIONID=5EB35CB9361D2D7408176C487AF1191B");
        maps.put("Host", "www.so.iptrm.com");
        maps.put("Origin", "http://www.so.iptrm.com");
        maps.put("Proxy-Connection", "keep-alive");
        maps.put("Referer", "http://www.so.iptrm.com/app/patentlist");
        maps.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");
        maps.put("X-Requested-With", "XMLHttpRequest");
        return maps;

    }


    /**
     *
     * @param express 为申请号
     * @return
     */
   public static String fillParameters(String express) {
       String parameters = "secondKeyWord=%E7%94%B3%E8%AF%B7%E5%8F%B7" + "&" +
               "secondkeyWordVal="+ express + "&" +
               "secondSearchType=NOT" + "&" +
               "express2=" + "&" +
               "express=" + URLEncoder.encode("(申请号 =  ( "+express+"% ) )") + "&" +
               "isFamily=" + "&" +
               "categoryIndex=&selectedCategory=" + "&" +
               "patentLib=" + "&" +
               "patentType=patent2" + "&" +
               "order=" + "&" +
               "pdbt=" + "&" +
               "attribute-node:patent_cache-flag=false" + "&" +
               "attribute-node:patent_start-row=1" + "&" +
               "attribute-node:patent_page-row=10" + "&" +
               "attribute-node:patent_sort-column=ano" + "&" +
               "attribute-node:patent_page=1";
        return parameters;
    }

}

