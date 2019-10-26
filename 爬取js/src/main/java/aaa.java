import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class aaa {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://jsdaima.hu-cheng.net/js/2019/09/3313/").get();
        System.out.println(document);
    }
}
