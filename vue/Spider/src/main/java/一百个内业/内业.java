package 一百个内业;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;

public class 内业 {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\一百个内业");
        String[]  nameList=file.list();
        System.out.println(nameList);
        //Document document = Jsoup.parse("");
    }
}
