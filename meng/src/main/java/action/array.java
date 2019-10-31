package action;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class array {
    public static void main(String[] args) throws Exception{
        //使用webClient的构造方法指定以何种浏览器打开网页，此处为以firefox打开,也可以不指定
        WebClient webClient=new WebClient(BrowserVersion.FIREFOX_17);

        //使用代理IP，这是为了应对网站有反爬系统的情况
        //WebClient webClient2=new WebClient(BrowserVersion.FIREFOX_52,"202.106.16.36",3128);

        //HTMLClient对css和js的支持不好，所以将其关闭
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setTimeout(30000);

        HtmlPage page=webClient.getPage("https://ios.lcltwz.com/7758ht.php");

        HtmlInput input3=(HtmlInput) page.getElementByName("pass");
        input3.setValueAttribute("1107778");
        DomNodeList<DomElement> input4= page.getElementsByTagName("input");
        HtmlInput button3=(HtmlInput) input4.get(1);
        HtmlPage page2=button3.click();
        //上面的代码都是登陆

        //获得页面的input
        DomNodeList<DomElement> input=page2.getElementsByTagName("input");
        for(DomElement e: input){
            System.out.println(e.asText());
        }
    }
}
