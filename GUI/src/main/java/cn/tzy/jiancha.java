package cn.tzy;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class jiancha {
    public static void main(String[] args) throws IOException {
        jianchawz("http://wap.gytzg.com/7758ht.php","wap");
    }

    public static void jianchawz(String s,String type) throws IOException {
        //使用webClient的构造方法指定以何种浏览器打开网页，此处为以firefox打开,也可以不指定
        WebClient webClient=new WebClient(BrowserVersion.FIREFOX_17);

        //HTMLClient对css和js的支持不好，所以将其关闭
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        //设置超时时间
        webClient.getOptions().setTimeout(30000);
        //设置访问域名   就是优化后台
        HtmlPage page=webClient.getPage(s);

        HtmlInput input3=(HtmlInput) page.getElementByName("pass");
        input3.setValueAttribute("1107778");
        DomNodeList<DomElement> input4= page.getElementsByTagName("input");
        HtmlInput button3=(HtmlInput) input4.get(1);
        HtmlPage page2=button3.click();
        //上面的代码都是登陆

        DomNodeList<DomElement> element =page2.getElementsByTagName("a");
        List<String> allUrlList = new ArrayList<>();
        //遍历所有的a标签   并将  左边的检查域名取出来  并且准备拼接需要的字符串
        for(int i =0 ; i< element.size() ; i++){
            if (i%14 == 0){
                allUrlList.add(type+"."+element.get(i).asText());
            }
        }

        List<String> AllUrl = new ArrayList<>();
        //遍历  所有已经拼接好的  url 再次拼接    http://wap.szgxy.com/domain2e/index/wap.szgxy.com.txt
        for (String url : allUrlList){
            AllUrl.add("http://"+url+"/domain2e/index/"+url+".txt") ;
        }
        List<String> response = new ArrayList<>();
        //打开拼接好的url   并且访问  获得response  保存起来
        for(int i =0 ; i<AllUrl.size() ; i++){
            try {
                String resout = Jsoup.connect(AllUrl.get(i)).get().body().text();
                response.add("http://"+allUrlList.get(i)+"/"+resout);
            }catch (Exception e1){
                System.out.println(AllUrl.get(i));
                e1.printStackTrace();
            }
        }
        //打开看有没有问题
        for (String str : response){
            try {
                String string = Jsoup.connect(str).get().title();
                System.out.println(string);
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }
}
