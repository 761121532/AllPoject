package spider;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;
import java.net.ConnectException;

import java.net.SocketTimeoutException;


public class HuoQuFile {

    public static void main(String[] args) {
        showFile();
        //showYouLian();

    }
    private static void showFile() {
        String hhh = null;
       while (true) {
            String pathnName = "F:\\Downloads\\风俗.txt";
            File fileName = new File(pathnName);
            try {
                InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                line = bufferedReader.readLine();
                while (line != null) {
                    line = bufferedReader.readLine();
                    String str = "http://";
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append(str).append(line);
                    System.out.println("stringBuffer:" + stringBuffer);
                    hhh=String.valueOf(stringBuffer);
                    Document document;
                    try {
                      document = Jsoup.connect(hhh).timeout(6000).userAgent("Mozilla/5.0 (compatible; Baiduspider/2.0;+http://www.baidu.com/search/spider.html） Chrome/52.0.2743.116 Safari/537.36")
                                .get();
                    }catch (Exception e){
                        continue;
                    }
                    Elements element = document.select("a").nextAll();
                    Element[] elements = element.next().toArray(new Element[0]);
                    for (Element EE : elements) {
                        String string = EE.attr("href");
                        System.out.println("踩你妈：" + string);
                    }
                }
            } catch (SocketTimeoutException e){
                System.out.println("报错的域名++++++++++++++++++++++"+hhh);
               e.printStackTrace();
            } catch (ConnectException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            continue;
        }

    }
//   public static void showYouLian() {
//
//            String url = null;
//
//            System.out.println("showFile:"+url);
//        try {
//            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; Baiduspider/2.0;+http://www.baidu.com/search/spider.html） Chrome/52.0.2743.116 Safari/537.36")
//                    .get();
//            Elements element = document.select("a").nextAll();
//            Element[] elements = element.next().toArray(new Element[0]);
//            for (Element ee : elements) {
//                //System.out.println("快点呀："+ee);
//                java.lang.String string = ee.attr("href");
//                System.out.println("踩你妈："+string);
//
////                FileUtils.write(new File("D:/YouLian/Urls.txt"), string.toString(), true);
////                FileUtils.write(new File("D:/YouLian/Urls.txt"),"\r\n",true);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
