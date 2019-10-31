package spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class guoge {

    public static void main(String[] args) throws IOException {
        String url="https://www.google.com/search?q=" ;//谷歌本身搜索栏
        String urll="安徽+北京pk10&start=20"; //谷歌搜索参数   &start= 这个参数可以改变页码
        String[] KeyWord={"成都","安徽","北京pk10"};//关键词数组
        int PageNumber=0; //爬取页码
        while (true){
            PageNumber+=10;
            Document document= Jsoup.connect(url+KeyWord[2]+"&start="+PageNumber).get();
            System.out.println(document.select(".iUh30").html());
        }
    }


}
