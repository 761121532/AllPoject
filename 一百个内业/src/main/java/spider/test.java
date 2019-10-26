package spider;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class test {
    public static final String PATH = "C:\\Users\\Administrator\\Desktop\\一百个内业\\aaa";
    public static void main(String[] args) throws IOException {
        File file = new File(PATH);
        String[]  list = file.list();
        for (String s : list) {
            String str = s.substring(s.length()-5);
            if (!str.equals("files")){
                String  html =FileUtils.readFileToString(new File(PATH+"\\"+s),"utf-8");
                Document document = Jsoup.parse(html);
                System.out.println(document);
            }
        }
    }
}
