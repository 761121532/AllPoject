import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class controller {

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("").get();
    }
}
