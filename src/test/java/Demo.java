import org.apache.http.client.methods.HttpGet;

import java.net.URLEncoder;

public class Demo {
    public static void main(String[] args) {

        String s = "secondKeyWord=%E5%90%8D%E7%A7%B0%2B%E6%91%98%E8%A6%81%2B%E4%B8%BB%E6%9D%83%E9%A1%B9&secondkeyWordVal=%E6%91%A9%E6%89%98%E8%BD%A6&secondSearchType=NOT&express2=&express=+(%E5%90%8D%E7%A7%B0%2C%E6%91%98%E8%A6%81%2C%E4%B8%BB%E6%9D%83%E9%A1%B9+%2B%3D++(+%E6%91%A9%E6%89%98%E8%BD%A6+)+)+&isFamily=&categoryIndex=&selectedCategory=&patentLib=&patentType=patent2&order=&pdbt=&attribute-node:patent_cache-flag=false&attribute-node:patent_start-row=1&attribute-node:patent_page-row=10&attribute-node:patent_sort-column=ano&attribute-node:patent_page=1";

        String encoded = "(  (名称=百度) OR  (申请人=百度) OR  (摘要=百度) OR  (发明人=百度) ) ";
        System.out.println(URLEncoder.encode(encoded));
    }

}
