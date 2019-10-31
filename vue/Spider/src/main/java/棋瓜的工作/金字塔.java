package 棋瓜的工作;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class 金字塔 {
    public static void main(String[] args) throws IOException {
        String str =FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\半自动做料\\jcwz.html"),"");
        Document document = Jsoup.parse(str);
        System.out.println(document);

    }
}
