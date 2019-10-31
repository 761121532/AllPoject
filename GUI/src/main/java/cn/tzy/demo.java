package cn.tzy;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

public class demo {
    public static HtmlPage getPage(String path) throws IOException {
        //使用webClient的构造方法指定以何种浏览器打开网页，此处为以firefox打开,也可以不指定
        WebClient webClient=new WebClient(BrowserVersion.FIREFOX_17);

        //HTMLClient对css和js的支持不好，所以将其关闭
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        //设置超时时间
        webClient.getOptions().setTimeout(30000);
        //设置访问域名   就是优化后台
        HtmlPage page=webClient.getPage(path);

        HtmlInput input3=(HtmlInput) page.getElementByName("pass");
        input3.setValueAttribute("1107778");
        DomNodeList<DomElement> input4= page.getElementsByTagName("input");
        HtmlInput button3=(HtmlInput) input4.get(1);
        //上面的代码都是登陆
        return button3.click();
    }
}
