package spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class spider {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("http://youjizz.com").get();
        Element e=document.getElementById("content");
        Elements element=e.getElementsByClass("video-thumb ");
        for (Element ele : element){
            Elements Ta=ele.select("a");
            for (Element element1 : Ta){
                System.out.println(element1.attr("data-clip"));
            }
        }
    }
}
