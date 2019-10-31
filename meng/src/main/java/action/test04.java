package action;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test04 {
    public static void main(String[] args) throws Exception {

        //启用多线程
         final  ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);

        //使用webClient的构造方法指定以何种浏览器打开网页，此处为以firefox打开,也可以不指定
        WebClient webClient=new WebClient(BrowserVersion.FIREFOX_17);

        //使用代理IP，这是为了应对网站有反爬系统的情况
        //WebClient webClient2=new WebClient(BrowserVersion.FIREFOX_52,"202.106.16.36",3128);

        //HTMLClient对css和js的支持不好，所以将其关闭
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        //设置超时时间
        webClient.getOptions().setTimeout(30000);
        //设置访问域名   就是优化后台
        HtmlPage page=webClient.getPage("http://wap.lnscy.com/7758ht.php");

        HtmlInput input3=(HtmlInput) page.getElementByName("pass");
        input3.setValueAttribute("1107778");
        DomNodeList<DomElement> input4= page.getElementsByTagName("input");
        HtmlInput button3=(HtmlInput) input4.get(1);
        HtmlPage page2=button3.click();
        //上面的代码都是登陆

        //获得页面的input   和a 标签
        DomNodeList<DomElement> input=page2.getElementsByTagName("input");
        DomNodeList<DomElement> a=page2.getElementsByTagName("a");

        //装有所有  需要的 网址
        LinkedList<String> 目标站 = new LinkedList<>();
        LinkedList<String> 品牌 = new LinkedList<>();
        LinkedList<String> 备案 = new LinkedList<>();
        LinkedList<String> 更新 = new LinkedList<>();
        int i=0;
        for (DomElement domElement : input) {
            if ((i-2)%5==0){
                目标站.add("http://"+domElement.asText());
            }
            i++;
        }
        i=0;
        for (DomElement domElement : a) {
            if ((i-4)%14==0){
                String href=domElement.getAttribute("href");
                品牌.add(href);
            }
            if ((i-7)%14==0){
                String href=domElement.getAttribute("href");
                备案.add(href);
            }
            if ((i-1)%14==0){
                String href=domElement.getAttribute("href");
                更新.add(href);
            }
            i++;
        }
        //目标站.size()
        for (var j=0;j<目标站.size();j++){
            final int ss = j;
            EXECUTOR.execute(()->throd(品牌,ss,目标站,备案,input,a));
        }
        EXECUTOR.shutdown();
        webClient.closeAllWindows();
    }

    private static void throd(LinkedList<String> pingpai, int ss, LinkedList<String> strings,LinkedList<String> beian,DomNodeList<DomElement> input,DomNodeList<DomElement> a) {

        //字符串拼接用的
        StringBuffer sb = new StringBuffer();
        String str;
        String title="";
        try {
            try {
                Document document3 = Jsoup.connect(pingpai.get(ss)).timeout(15000).get();
                title = document3.select("title").first().toString();
            }catch (Exception e){
                Document document3 = Jsoup.connect(pingpai.get(ss)).timeout(15000).get();
                title = document3.select("title").first().toString();
            }
            Document document4 = Jsoup.connect(beian.get(ss)).timeout(15000).get();
            int aaa=document4.getElementsByClass("Tool-MainWrap").select("div").size();

            if (!title.equals("<title>\n" +
                    "商户收录_百度口碑\n" +
                    "</title>")){
                sb = new StringBuffer("有品牌");
                sb.append("\n");
            } else if (aaa!=16) {
                sb = new StringBuffer("有备案");
                sb.append("\n");
            } else {
                sb=changePath(ss,input,strings.get(ss));
            }
        } catch (Exception e) {
            e.printStackTrace();
            sb = new StringBuffer("有毛病");
            sb.append("\n");
        }
        int i=(ss+1)*5;

        if ("有毛病\n".equals(sb.toString())){
            sb=chinge(i,input);
        }

        //将  生成出来的 sb 字符串 提交到后台当中
        HtmlInput input5=(HtmlInput)input.get(i-2);
        //当input框内没有内容时 才写入内容
        if (input5.asText().equals("")){
            input5.setValueAttribute(sb.toString());
        }
        //点d2
        HtmlInput button4=(HtmlInput)input.get(i-1);
        try {
            button4.click();
        }catch (Exception e){
            e.printStackTrace();
        }
        //在桌面生成一个文件
        File file = new File("C:\\Users\\Administrator\\Desktop\\优化新方法带联系方式.txt");
        synchronized (file) {
            try {
                FileUtils.write(file, ss+ "  " +strings.get(ss)+"  "+ sb, "utf-8", true);
                System.out.println(ss);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //用来替换域名的方法
    private static StringBuffer chinge(int i,DomNodeList<DomElement> input){
        String path = "C:\\Users\\Administrator\\Desktop\\临时文件夹\\筛选过的";
        File file = new File(path);
        String[] list=file.list();
        StringBuffer sb=new StringBuffer("");
        int a = (int)Math.floor(Math.random()* list.length);
            try {
                List<String> stringList=FileUtils.readLines(new File(path+"\\"+list[a]),"utf-8");
                int b = (int)Math.floor(Math.random()* stringList.size());

                String www=stringList.get(b);
                System.out.println(www);

                HtmlInput input5=(HtmlInput)input.get(i-3);
                input5.setValueAttribute(www);

                try {
                    sb=changePath(i,input,"http://"+www);
                }catch (Exception e){
                    chinge(i,input);
                }

                HtmlInput input6=(HtmlInput)input.get(i-2);
                input6.setValueAttribute(sb.toString());
                HtmlInput button5=(HtmlInput)input.get(i-1);

                button5.click();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  sb;
    }

        private static StringBuffer changePath(int i,DomNodeList<DomElement> input, String string) throws IOException {
        //声明变量  用来存数据
        HashSet<String> set = new HashSet<String>();
        StringBuffer sb=new StringBuffer("");

            Document document2 = Jsoup.connect(string)
                    .userAgent("Mozilla/5.0 (compatible; MSIE 10.0;Eindows NT 6.2;gimeex;gana;Trident/6.0)")
                    .timeout(100000)
                    .get();
            String str = Jsoup.clean(document2.body().toString(),new Whitelist());
            String[] reg = {"([0-9]+){4,13}", "\\w+@\\w+\\.([comnet]{1,3})", "(\\w){5,30}(\\.[comnet]{1,3})?\\.([comnet]{1,3})"};
            for (int ii = 0; ii < reg.length; ii++) {
                Pattern pattern = Pattern.compile(reg[ii]);
                Matcher matcher = pattern.matcher(str);
                while (matcher.find()) {
                    //得到匹配的结果
                    String s = matcher.group();
                    String number = "";
                    switch (ii) {
                        case 0: {
                            if (s.length() >= 4) {number = s.substring(0, 4);}
                            break;
                        }
                        case 1: {
                            if (!s.equals("")) {
                                String[] splic = s.split("@");
                                number = splic[0].length() >= 4 ? splic[0] : splic[1];
                                number = number.substring(0, 4);
                            }
                            break;
                        }
                        case 2: {
                            if (!s.equals("")) {
                                String[] splic1 = s.split("/.");
                                number = splic1[0].length() >= 4 ? splic1[0] : splic1[1];
                                number = number.substring(0, 4);
                            }
                            break;
                        }
                        default:{
                        }
                    }
                    set.add(number);
                }
            }
            for (String o : set) {
                if (o.equals("2016") || o.equals("2017") || o.equals("2018") || o.equals("2019") || o.equals("2020")) {
                    o = "";
                } else {
                    o = o + "|";
                }
                sb.append(o);
            }
            if ("".equals(sb.toString())) {
                //sb=new StringBuffer("有毛病");
                sb=chinge(i,input);
            }else {
                sb.delete(sb.lastIndexOf("|"),sb.lastIndexOf("|")+1);
            }
            sb.append("\n");
        return sb;
    }
}
