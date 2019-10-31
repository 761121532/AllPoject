package spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class 提取a标签 {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("http://www.464748.com").get();
        Elements elements=document.select("a");
    }
}
