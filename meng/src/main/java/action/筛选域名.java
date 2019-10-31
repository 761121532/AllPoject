package action;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 筛选域名 {
    private final static ExecutorService executor = Executors.newFixedThreadPool(10);//启用多线程

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Administrator\\Desktop\\临时文件夹";
        File file = new File(path);
        String[] list = file.list();
        for (int j = 0; j < list.length; j++) {
            String s = list[j];
            File file1 = new File(path + "\\" + s);
            List<String> list1 = FileUtils.readLines(file1, "utf-8");
            System.out.println(list1.get(1));
            for (int i = 0; i < list1.size(); i++) {
                String s1 = list1.get(i);
                boolean sw = false;
                try {
                    Document document = Jsoup.connect("http://" + s1).timeout(5000).get();
                    sw = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (sw) {
                    try {
                        FileUtils.writeStringToFile(new File("C:\\Users\\Administrator\\Desktop" + "\\" + "筛选过的" + "\\" + s), s1 + "\n", "utf-8", true);
                    } catch (Exception ignored) {
                    }
                }
                System.out.println(s1);
            }
        }

//    private static void get(String s1, String path, String s) {
//        boolean sw= false;
//        try {
//            Document document = Jsoup.connect("http://"+s1).timeout(15000).get();
//            sw=true;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        if (sw){
//            try {
//                FileUtils.writeStringToFile(new File("C:\\Users\\Administrator\\Desktop"+"\\"+"筛选过的"+"\\"+s),s1+"\n","utf-8",true);
//            }catch (Exception ignored){
//            }
//        }
//    }
    }
}