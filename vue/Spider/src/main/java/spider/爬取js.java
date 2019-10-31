package spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class 爬取js {
    public static void main(String[] args) {
        Document document = Jsoup.parse("https://www.jsdaima.com/js/demo/3313.html");
        Element e=document.body();
        System.out.println(e);
    }
}
