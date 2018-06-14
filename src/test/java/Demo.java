import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cxyhome.webmagic.domain.Patent.Instruction;
import com.cxyhome.webmagic.thread.TrademarkDownloader;
import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

//        String s = "secondKeyWord=%E5%90%8D%E7%A7%B0%2B%E6%91%98%E8%A6%81%2B%E4%B8%BB%E6%9D%83%E9%A1%B9&secondkeyWordVal=%E6%91%A9%E6%89%98%E8%BD%A6&secondSearchType=NOT&express2=&express=+(%E5%90%8D%E7%A7%B0%2C%E6%91%98%E8%A6%81%2C%E4%B8%BB%E6%9D%83%E9%A1%B9+%2B%3D++(+%E6%91%A9%E6%89%98%E8%BD%A6+)+)+&isFamily=&categoryIndex=&selectedCategory=&patentLib=&patentType=patent2&order=&pdbt=&attribute-node:patent_cache-flag=false&attribute-node:patent_start-row=1&attribute-node:patent_page-row=10&attribute-node:patent_sort-column=ano&attribute-node:patent_page=1";
//
//        String encoded = "(  (名称=百度) OR  (申请人=百度) OR  (摘要=百度) OR  (发明人=百度) ) ";
//        System.out.println(URLEncoder.encode(encoded));


//        String zhulu = "申请号： CN201010271917.7 公开(公告)号： CN102151062A 申请日： 20100830 公开(公告)日： 20110817 申请人： 谭湘 发明人： 谭湘 申请人地址： 314000 浙江省嘉兴市南湖区吉水路运南社区文运里45幢505室 申请人区域代码： CN330402 专利权人： 无 洛迦诺分类： 无 IPC： A47J27/00;A47J36/00 CPC： 无 优先权： 无 专利代理机构： 无 代理人： 无 审查员： 无 国际申请： 无 国际公开（公告）： 无 进入国家日期： 无 分案申请： 无";
//        String[] strings = zhulu.split("\\s");
//        for (String s:strings) {
//            System.out.println(s);
//        }


//        String pid = "PIDCNA02011081700000000102151114620AV4P015746";
//         String s1 = pid.substring(7, 11);
//        String s2 = pid.substring(11, 15);
//        String s3 = pid.substring(15, 29);
//        String s4 = pid.substring(29, 45);
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s3);
//        System.out.println(s4);


//      String content =   "<html xmlns:w=\"urn:schemas-microsoft-com:office:text\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">    \n" +
//                "<p style=\"line-height:125%;mso-char-indent-count:2.0;background:#FFFFFF;vertical-align:middle\">\n" +
//                "\n" +
//                "<span style=\"font-size:12.0pt;font-family:微软雅黑;mso-ascii-font-family:\"Times New Roman\";mso-hansi-font-family:\"Times New Roman\"\">本发明公告了一种百度锅，属于锅具技术领域，它由锅口、提手、进水口、进水管、水箱、锅底、内锅底组成，其特征在于锅底包围内锅底，锅底上侧边留有进水口的进水管，内锅底上留有提手，使用时从进水口内通过进水管向锅底包围内锅底的容器内注水至水淹满内锅底，然后把锅底放在炉子上加热至水沸腾后，从锅口向锅内放入烹调油与鸡蛋即可炒熟，其有益效果是设计简单，成本低廉，温度容易控制，实用性较大。\n" +
//                "</span>\n" +
//                "\n" +
//                "</p>                        \n" +
//                "<img align=\"middle\" width=\"661\" height=\"570.886909\" src=\"201010271917.GIF\">        <p style=\"text-indent:21.0pt;background:#FFFFFD\"></p>    \n" +
//                "</html>";
//
//        Document parse = Jsoup.parse(content);
////        Elements elements = parse.getElementsByClass("qwb_box_tab");
//        Elements elements = parse.getElementsByTag("span");
//        for (org.jsoup.nodes.Element link : elements) {
//            //将著录信息写入
//            System.out.println(link.text());
//
//        }

//        String content =   "<html xmlns:w=\"urn:schemas-microsoft-com:office:text\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
//                "<div class=\"Section1\">\n" +
//                "  \n" +
//                "    \n" +
//                "<a name=\"c0001\">\n" +
//                "<p class=\"\" style=\"line-height:125%;background:#FFFFFF;vertical-align:middle\">\n" +
//                "<span style=\"font-size:12.0pt;font-family:微软雅黑;mso-ascii-font-family:\"Times New Roman\";mso-hansi-font-family:\"Times New Roman\"\"><b style=\"mso-bidi-font-weight:    normal\">0001\n" +
//                "\t\t\t\t\t\t\t.\n" +
//                "\t\t\t\t\t\t</b>1.一种百度锅，由锅口(1)、提手(2)、进水口(3)、进水管(4)、水箱(5)、锅底(6)、内锅底(7)组成，其特征在于锅底(6)包围内锅底(7)，锅底(6)上侧边留有进水口(3)的进水管(4)，内锅底(7)上留有提手(2)。</span>\n" +
//                "</p>\n" +
//                "</a>\n" +
//                "    \n" +
//                "  \n" +
//                "\n" +
//                "</div>\n" +
//                "</html>\n";
//
//        Document parse = Jsoup.parse(content);
//        Elements elements = parse.getElementsByTag("span");
//        for (org.jsoup.nodes.Element link : elements) {
//            //将著录信息写入
//            System.out.println(link.text());
//
//        }



//        Instruction instruction = new Instruction();
//        StringBuilder builder = new StringBuilder();
//        Document parse = Jsoup.parse(content);
//        Elements elements = parse.getElementsByTag("p");
//        String s = null;
//        for (org.jsoup.nodes.Element e : elements) {
//            if (e.getElementsByTag("span")!=null){
//               s= builder.append(e.text()).toString();
////                    if (!e.text().startsWith("[")){
////                        instruction.setTitle(e.text());
////                    }else {
////                        String fixString = e.text().replaceAll("\\[\\d{4}\\]\\s", "");
////                        fixString.startsWith()
////                    }
//
//
//
//            }
//        }

        //去处[0001]等行号
//        String result = s.replaceAll("\\[\\d{4}\\]", "");
//        int titleEnd = result.indexOf("技术领域");
//        int technicalFieldEnd = result.indexOf("背景技术");
//        int technicalBackgroundEnd = result.indexOf("发明内容");
//        int inventionContentEnd = result.indexOf("附图说明");
//        int drawingsShowEnd = result.indexOf("具体实施方式");
//        int specificImplementationEnd = result.length();
//
//        String r1 = result.substring(0,titleEnd );
//        String r2 = result.substring(titleEnd+4, technicalFieldEnd);
//        String r3 = result.substring(technicalFieldEnd+4, technicalBackgroundEnd);
//        String r4 = result.substring(technicalBackgroundEnd+4, inventionContentEnd);
//        String r5 = result.substring(inventionContentEnd+4, drawingsShowEnd);
//        String r6 = result.substring(drawingsShowEnd+6, specificImplementationEnd);

//        String[] a1 = result.split("技术领域");
//        instruction.setTitle(a1[0]);
//
//        String[] a2 = a1[1].split("背景技术");
//        instruction.setTechnicalField(a2[0]);
//
//        String[] a3 = a2[1].split("发明内容");
//        instruction.setTechnicalBackground(a3[0]);
//
//        String[] a4 = a3[1].split("附图说明");
//        instruction.setInventionContent(a2[0]);
//        String[] a5 = a4[1].split("具体实施方式");
//        instruction.setDrawingsShow(a5[0]);
//        instruction.setSpecificImplementation(a5[1]);


    /*    String str ="0001 . 1.一种美容仪器，其特征在于，包括：0001 . 导电膜片，所述导电膜片适于接触人脸；0001 . 导电电极，所述导电电极与所述导电膜片间隔开设置，且所述导电电极适于通过人体与所述导电膜片导接；0001 . 输出接头，所述输出接头上具有第一电极和第二电极，所述第一电极与所述导电膜片相连，所述第二电极与所述导电电极相连，所述输出接头适于与外部终端相连，以使所述美容仪器由所述外部终端驱动工作和/或调控工作。0002 . 2.根据权利要求1所述的美容仪器，其特征在于，进一步包括：控制装置，所述控制装置串接在所述导电膜片与所述导电电极之间且用于调控所述美容仪器工作。0003 . 3.根据权利要求2所述的美容仪器，其特征在于，所述控制装置包括升压元件和/或整流元件。0004 . 4.根据权利要求2所述的美容仪器，其特征在于，所述控制装置设在所述导电电极内。0005 . 5.根据权利要求1所述的美容仪器，其特征在于，所述导电电极为金属片。0006 . 6.根据权利要求1所述的美容仪器，其特征在于，所述导电电极设在所述外部终端上。0007 . 7.根据权利要求1所述的美容仪器，其特征在于，所述第一电极与所述导电膜片通过导线相连，所述导线与所述导电膜片可拆卸地相连。0008 . 8.根据权利要求7所述的美容仪器，其特征在于，所述导线与所述导电膜片通过插拔组件或按扣组件或磁吸组件可拆卸地相连。0009 . 9.根据权利要求1所述的美容仪器，其特征在于，所述输出接头为耳机插头或USB插头。0010 . 10.根据权利要求1所述的美容仪器，其特征在于，所述导电膜片形成为人脸形且适于贴敷整个人脸，或所述导电膜片形成为眼罩形且适于贴敷人眼周围。0011 . 11.根据权利要求1所述的美容仪器，其特征在于，所述导电膜片为柔性材料件。0012 . 12.根据权利要求1所述的美容仪器，其特征在于，所述导电膜片为防水材料件。0013 . 13.根据权利要求1所述的美容仪器，其特征在于，所述导电膜片为导电硅胶材料件或导电纤维材料件。0014 . 14.根据权利要求1所述的美容仪器，其特征在于，进一步包括：0014 . 加热膜片，所述加热膜片贴设在所述导电膜片的远离人脸的一侧以用于加热所述导电 膜片以调节人脸温度。0015 . 15.根据权利要求1所述的美容仪器，其特征在于，进一步包括：0015 . 加固膜片，所述加固膜片的形状可调节且贴设在所述导电膜片的远离人脸的一侧以用于调整所述导电膜片的形状。0016 . 16.一种美容系统，其特征在于，包括如权利要求1-15中任一项所述的美容仪器和外部终端，所述美容仪器的所述输出接头与所述外部终端相连，以使所述美容仪器由所述外部终端驱动工作和/或调控工作。0017 . 17.根据权利要求16所述的美容系统，其特征在于，所述外部终端与所述美容仪器通信以调控所述美容仪器工作。0018 . 18.根据权利要求17所述的美容系统，其特征在于，所述外部终端记录用户的使用参数和使用时间，且根据所述使用参数和所述使用时间对所述美容仪器的工作电流强度进行控制。0019 . 19.根据权利要求17所述的美容系统，其特征在于，所述外部终端获取所述导电膜片上面膜的类型信息，且根据所述面膜的类型信息对所述美容仪器的工作电流强度进行控制。";
        System.out.println(str.replaceAll("\\d{4}\\s\\.", ""));*/

//        Document parse = Jsoup.parse(s);
//        Elements qwb_box_tabx = parse.getElementsByClass("qwb_box_tabx");
//        for (org.jsoup.nodes.Element link : qwb_box_tabx) {
//            //日期
//            Elements span = link.getElementsByTag("span");
//            Elements eq = span.eq(0);
//
//            Elements tds = link.select("td[width$=\"23%\"]").select("td:nth-child(2)");
//            Elements s2 = link.select("td[width$=\"54%\"]");
//
//        }

//        ArrayList<Object> objects = new ArrayList<>(1);
//
//        System.out.println(objects.size());

//        String s1 = DigestUtils.md5Hex("3571bde9/44aeb4d0/4a57f666/0f57b35b/logo_middle.jpg");
//        System.out.println(s1);




        List<String> list = new ArrayList<>();
        list.add("15713572");
        new TrademarkDownloader(list).start();
//        for (int i = 100; i <= 1000 ; i++) {
//            if (i % 100 !=0){
//                list.add(15713572 + i + "");
//            }else {
//                list.add(15713572 + i + "");
//                //每满100个开一个线程
//                new TrademarkDownloader(list).start();
//                list.clear();
//            }
//        }





    }


    }







