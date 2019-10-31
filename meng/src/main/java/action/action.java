package action;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class action {
    private final static ExecutorService executor = Executors.newFixedThreadPool(10);//启用多线程

    public static void main(String[] args) throws IOException {
        Map<String, String> datas = new HashMap<String, String>();
        datas.put("pass", "1107778");
        Connection con2 = Jsoup.connect("https://web.czjunbo.com/7758ht.php ");
        //maxBodySize(0) 用来设置 jsoup 爬取的网页大小 0 为没有大小限制
        Connection.Response login = con2.ignoreContentType(true).maxBodySize(0).method(Connection.Method.POST).data(datas).execute();

//        StringBuffer sr= sendPost("https://ios.jiningrcw.cn/7758ht.php", "pass=1107778");
        Document document = Jsoup.parse(login.body());
        //System.out.println(document);
        Elements document1 = document.select("input");
        Elements ele = document.select("a");

        Element[] elements = iter(document1);//迭代出来
        Element[] eleme = iter(ele);

        int i = elements.length;
        int a = i / 4;
        String[] strings = new String[a];  //目标站
        String[] pingpai = new String[a];  //品牌
        String[] beian   = new String[a];  //备案
        int number1 = 0;
        for (int j = 0; j < i; j++) {
            if (j == 2 || (j - 2) % 5 == 0) {
                strings[number1] = "http://" + elements[j].val();
                number1++;
            }
        }
        number1 = 0;
        for (int ee = 0; ee < eleme.length - 1; ee++) {
            if ((ee - 4) % 14 == 0) {
                pingpai[number1] = eleme[ee].attr("href");
                number1++;
            }
        }
        number1 = 0;
        for (int ee = 0; ee < eleme.length - 1; ee++) {
            if ((ee - 7) % 14 == 0) {
                beian[number1] = eleme[ee].attr("href");
                number1++;
            }
        }
        for (int ss = 0; ss < strings.length; ss++) {
            HashSet<String> set = new HashSet<String>();
            StringBuffer sb = new StringBuffer();
            final int j = ss;                  //关键是这一句代码，将 ss 转化为 j，这样j 还是final类型的参与线程
            executor.execute( ()->throd(pingpai,j,sb,strings,beian,set));
        }
        executor.shutdown();
        func();
    }

    /**
     * 迭代器的方法
     * @param elementss 传入一个elements 的对象
     * @return 被迭代的elements 的所有单个元素
     */
    private static Element[] iter(Elements elementss) {
        Iterator it = elementss.iterator();
        Element[] elements = new Element[elementss.size()];
        int i = 0;
        while (it.hasNext()) {
            Element element = (Element) it.next();
            elements[i] = element;
            i++;
        }
        return elements;
    }

    private static void throd(String[] pingpai, int ss, StringBuffer sb, String[] strings,String[] beian, HashSet<String> set) {
        String str;
        try {
            Document document3 = Jsoup.connect(pingpai[ss]).timeout(15000).get();
            String title = document3.select("title").first().toString();
            Document document4 = Jsoup.connect(beian[ss]).timeout(15000).get();
            int aaa=document4.getElementsByClass("Tool-MainWrap").select("div").size();
            if (aaa!=16){
                sb = new StringBuffer("有备案");
                sb.append("\n");
            } else if (!title.equals("<title>\n" +
                    "商户收录_百度口碑\n" +
                    "</title>")) {
                //System.out.println(title);
                sb = new StringBuffer("有品牌");
                sb.append("\n");
            } else {
                Document document2 = Jsoup.connect(strings[ss])
                        .userAgent("Mozilla/5.0 (compatible; Baiduspider/2.0;+http://www.baidu.com/search/spider.html） Chrome/52.0.2743.116 Safari/537.36")
                        .timeout(60000)
                        .get();
                str = Jsoup.clean(document2.body().toString(), new Whitelist());
                //System.out.println(string);
                String[] reg = {"([0-9]+){4,13}", "\\w+@\\w+\\.([comnet]{1,3})", "(\\w){5,30}(\\.[comnet]{1,3})?\\.([comnet]{1,3})"};
                for (int ii = 0; ii < reg.length; ii++) {
                    Pattern pattern = Pattern.compile(reg[ii]);
                    Matcher matcher = pattern.matcher(str);
                    while (matcher.find()) {
                        String s = matcher.group();//得到匹配的结果
                        String number = "";
                        switch (ii) {
                            case 0: {
                                if (s.length() >= 4) number = s.substring(0, 4);
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
                if (sb.toString().equals("")) {
                    sb = new StringBuffer("没有联系方式");
                }
                sb.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            sb = new StringBuffer("有毛病");
            sb.append("\n");
        }
        File file = new File("C:\\Users\\Administrator\\Desktop\\优化带联系方式.txt");
        synchronized (file) {
            try {
                FileUtils.write(file, ss+ "  " +strings[ss]+"  "+ sb, "utf-8", true);
                System.out.println(ss);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void func(){
        File file = new File("/\\www\\优化带联系方式.txt");
        try {
            List<String> list= FileUtils.readLines(file,"utf-8");
            int[] numbers=new int[list.size()];
            HashMap<Integer , String> map = new HashMap<Integer , String>();
            int p=0;
            for (String s : list){
                String[] a=s.split(" ");
                int number=Integer.parseInt(a[0]);
                numbers[p]=number;
                map.put(number,a[4]);
                p++;
            }
            for(int  i=0; i<numbers.length; i++){//表示n次排序过程。
                for(int j=1; j<numbers.length-i; j++){
                    if(numbers[j-1] > numbers[j]){//前面的数字大于后面的数字就交换
                        //交换a[j-1]和a[j]
                        int temp;
                        temp = numbers[j-1];
                        numbers[j-1] = numbers[j];
                        numbers[j]=temp;
                    }
                }
            }
            for (int i=0;i<numbers.length;i++){
                for (Map.Entry<Integer, String> Entry : map.entrySet()) {
                    if (Entry.getKey()==numbers[i]){
                        FileUtils.write(new File("C:\\Users\\Administrator\\Desktop\\优化排序好了的.txt"),Entry.getValue()+"\n","utf-8",true);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        //古老的流的方式做爬虫
//    private static StringBuffer sendPost(String url, String param) {
//        PrintWriter out = null;
//        BufferedReader in = null;
//        StringBuffer result = new StringBuffer("");
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            URLConnection conn = realUrl.openConnection();
//            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
//            // 发送请求参数
//            out.print(param);
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result.append(line);
//            }
//        } catch (Exception e) {
//            System.out.println("发送 POST 请求出现异常！"+e);
//            e.printStackTrace();
//        }
//        //使用finally块来关闭输出流、输入流
//        finally{
//            try{
//                if(out!=null){
//                    out.close();
//                }
//                if(in!=null){
//                    in.close();
//                }
//            }
//            catch(IOException ex){
//                ex.printStackTrace();
//            }
//        }
//        return result;
//    }
}
