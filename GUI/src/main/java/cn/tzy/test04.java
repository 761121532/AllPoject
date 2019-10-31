package cn.tzy;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import javafx.scene.control.TextArea;
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

import static cn.tzy.Controller.alert;


public class test04 {

    public void test(String s, TextArea text, String begin, String end) throws IOException {
        begin = begin.trim();
        end = end.trim();

        //启用多线程
        final ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);

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

        //获得页面的input   和a 标签
        DomNodeList<DomElement> input=page2.getElementsByTagName("input");
        DomNodeList<DomElement> a=page2.getElementsByTagName("a");

        //装有所有  需要的 网址
        LinkedList<String> 目标站 = new LinkedList<>();
        LinkedList<String> 品牌 = new LinkedList<>();
        LinkedList<String> 备案 = new LinkedList<>();
        LinkedList<String> 更新 = new LinkedList<>();
        LinkedList<String> 检查 = new LinkedList<>();
        int i=0;
        for (DomElement domElement : input) {
            if ((i-2)%5==0){
                目标站.add("http://"+domElement.asText());
            }
            i++;
        }
        i=0;
        for (DomElement domElement : a) {
            if ((i)%14==0){
                String href=domElement.asText();
                检查.add(href);
            }
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
        int be=-1 , en =-1;

        try {
            be = Integer.parseInt(begin);
            en = Integer.parseInt(end);
        }catch (Exception e){
            //找到开始结束位置
            for(int i1=0;i1<检查.size();i1++){
                if (begin.equals(检查.get(i1))){
                    be =i1;
                }
                if (end.equals(检查.get(i1))){
                    en =i1;
                }
            }

            //可预计的一些错误的判断处理
            if(begin.equals(""))be=0;
            if(end.equals(""))en=1200;
            if (!begin.equals("")&&be==-1) alert("你踏马，起始位置有点问题");
            if (!end.equals("")&&en==-1) alert("卧槽，结束位置有点问题");
            if (be>en) alert("SBs，开始结束填反了");
        }

        //目标站.size()
        for (var j=be;j<en;j++){
            if (j>目标站.size()){
                return;
            }
            final int ss = j;
            EXECUTOR.execute(()->throd(品牌,ss,目标站,备案,input,text));
        }
        EXECUTOR.shutdown();
        webClient.closeAllWindows();
        while (true){
            if (EXECUTOR.isTerminated()){
                alert("已经跑完了");
            }
        }
    }

    private static void throd(LinkedList<String> pingpai, int ss, LinkedList<String> strings,LinkedList<String> beian,DomNodeList<DomElement> input, TextArea text) {

        //字符串拼接用的
        StringBuffer sb ;
        String title;
        try {
            try {
                title = GetTitle(pingpai.get(ss));
            }catch (IOException e){
                title = GetTitle(pingpai.get(ss));
            }

            int aaa = Get备案(beian.get(ss));

            if (!title.equals("商户收录_百度口碑")){
                sb = new StringBuffer("有品牌");
                sb.append("\n");
            } else if (aaa!=16) {
                sb = new StringBuffer("有备案");
                sb.append("\n");
            } else {
                sb=changePath(ss,input,strings.get(ss));
            }
        }catch (IOException e){
            sb = new StringBuffer("有毛病\n");
        } catch (Exception e) {
            e.printStackTrace();
            sb = new StringBuffer("有毛病\n");
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
                text.appendText(ss+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //用来替换域名的方法
    private static StringBuffer chinge(int i,DomNodeList<DomElement> input){
        String path = "src/main/resources/筛选过的";
        File file = new File(path);
        String[] list=file.list();
        StringBuffer sb=new StringBuffer();
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
            System.out.println(sb);
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


    //获取品牌title的方法
    private static String GetTitle(String url) throws IOException {
        Document document3 = Jsoup.connect(url).timeout(15000).get();
        return document3.select("title").first().text();
    }

    //获取备案的长度的方法
    private static int Get备案(String 备案) throws IOException {
        Document document4 = Jsoup.connect(备案).timeout(15000).get();
        return  document4.getElementsByClass("Tool-MainWrap").select("div").size();
    }

}