package com.cxyhome.webmagic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * PhantomJs是一个基于webkit内核的无头浏览器，即没有UI界面，即它就是一个浏览器，只是其内的点击、翻页等人为相关操作需要程序设计实现;
 * 因为爬虫如果每次爬取都调用一次谷歌浏览器来实现操作,在性能上会有一定影响,而且连续开启十几个浏览器简直是内存噩梦,
 * 因此选用phantomJs来替换chromeDriver
 * PhantomJs在本地开发时候还好，如果要部署到服务器，就必须下载linux版本的PhantomJs,相比window操作繁琐
 * @author zhuangj
 * @date 2017/11/14
 */
public class TestPhantomJsDriver {


    public static PhantomJSDriver getPhantomJSDriver(){
        //设置必要参数
        DesiredCapabilities dcaps = new DesiredCapabilities();
        //ssl证书支持
        dcaps.setCapability("acceptSslCerts", true);
        //截屏支持
        dcaps.setCapability("takesScreenshot", false);
        //css搜索支持
        dcaps.setCapability("cssSelectorsEnabled", true);
        //js支持
        dcaps.setJavascriptEnabled(true);
        //驱动支持
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"D:\\software\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");

        PhantomJSDriver driver = new PhantomJSDriver(dcaps);
        return  driver;
    }

    public static void main(String[] args) {
        WebDriver driver=getPhantomJSDriver();
        driver.get("https://www.taobao.com");
        System.out.println(" Page title is: " +driver.getTitle());
        // 通过 id 找到 input 的 DOM
        WebElement element =driver.findElement(By.id("q"));

        element.sendKeys("java从入门到放弃");
        // 提交 input 所在的 form
        element.submit();

        new WebDriverWait(driver, 10).until(
                new ExpectedCondition() {
                    public Object apply(Object input) {
                        return ((WebDriver)input).getTitle().toLowerCase().startsWith("java从入门到放弃");
                    }
                });

        System.out.println("本地测试出url为="+driver.getCurrentUrl());
    }
}