import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class 优化 {
    public static void main(String[] args) throws IOException {
        Document document= Jsoup.connect("http://tai10.520373.com/7758ht.php?pass=1107778\n").get();
        String elements =document.select("a").html();
        String[]  strings=elements.split("更新\n百\n@V\n品牌\n360\nsogou\n备案\nchinaZ\nGoogle\n爱\n目标站\n设标题\n删除");
        for (int i=0;i<strings.length;i++) {
            String URL =strings[i].replace("\n","");
            if (strings[i].equals(""))continue;
            try {
                Document doc = Jsoup.connect("http://www."+URL).header("User-Agent","Mozilla/5.0 (compatible; MSIE 10.0;Eindows NT 6.2;gimeex;gana;Trident/6.0)").timeout(3000).get();
                String titel=doc.title();
                if(!titel.equals("")){
                    System.out.println("url  "+strings[i]+"  title  "+titel);
                }else {
                    FileUtils.write(new File("D:\\新建文件夹 (2)\\优化\\优化.txt"),URL,"utf-8",false);
                }
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
