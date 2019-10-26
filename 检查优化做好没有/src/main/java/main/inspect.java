package main;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;


public class inspect {

    private static final String PATH ="http://wap.tcgppz.com/7758ht.php";

    public static void main(String[] args) {
        //设置启动google浏览器的驱动和位置
        System.setProperty("webdriver.chrome.driver", "E:\\java\\新建文件夹\\chromedriver.exe");

        System.out.println("启动 ...");
        //获取谷歌浏览器驱动
        WebDriver driver=new ChromeDriver();
        //设置默认打开的页面
        driver.get(PATH);
        //设置默认搜索的关键字
        driver.findElement(By.name("pass")).sendKeys("1107778");
        //搜索按钮点击
        List<WebElement> e = driver.findElements(By.tagName("input"));
        e.get(1).click();
        //关闭浏览器
//        driver.close();
    }
}
