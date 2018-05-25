package com.cxyhome.webmagic;


import com.cxyhome.webmagic.util.HttpUtil;

public class HttpClentDemo {

    public static void main(String[] args) {

        String param = "" +
                "{\n" +
                "  \"method\":\"brandSearchDetailById\",\n" +
                "  \"v\":\"1.0\",\n" +
                "  \"executor\":\"5a32564d76707a34576e377a4a6a64536f4c737133673d3d\",\n" +
                "  \"sign\":\"B3A3D916508195A90C6DE159810158E0\",\n" +     //根据签名进行匹配
                                //6A5CD6407DE6546ADAA0C14458ECCB5D
                                //B3A3D916508195A90C6DE159810158E0
                             // 8c51b119e997a77c62b4a7a72a4c6c13    TODO:// 需要破解他的md5 加密
                //            0ebe860bc7a780735a59e5836acecc4b
                "  \"appKey\":\"quandashi4524472038\",\n" +
                "  \"partnerId\":\"1000\",\n" +
                "  \"map\":{\n" +
                "    \"dataId\":\"2000008\",\n" +                       //请求参数
                "    \"id\":\"492b74587a67394a304358527431426a4473796537413d3d\"\n" +
                "  },\n" +
                "  \"signMethod\":\"md5\",\n" +
                "  \"timestamp\":1527240662254\n" +                     //更改
                "}"
                ;


        String ss = "{\n" +
                "  \"method\":\"brandSearchDetailById\",\n" +
                "  \"v\":\"1.0\",\n" +
                "  \"executor\":\"5a32564d76707a34576e377a4a6a64536f4c737133673d3d\",\n" +
                "  \"sign\":\"47bce5c74f589f4867dbd57e9ca9f808\",\n" +
                "  \"appKey\":\"quandashi4524472038\",\n" +
                "  \"partnerId\":\"1000\",\n" +
                "  \"map\":{\n" +
                "    \"dataId\":\"105001\",\n" +
                "    \"id\":\"376465455647413751522b6a334a6e56516d505553673d3d\"\n" +
                "  },\n" +
                "  \"signMethod\":\"md5\",\n" +
                "  \"timestamp\":1527248808319\n" +
                "}";

        try {
            String s = HttpUtil.doPost("http://brandapi.quandashi.com/brand/api", ss);
            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
