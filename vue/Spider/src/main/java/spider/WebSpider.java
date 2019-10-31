package spider;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

        /**
        * 搜索域名下面的所有html，然后获取html的tile名
        */
public class WebSpider extends ArrayRandom {
    //总线程条数
    private static int MAX_THREAD = 50;
    //等待爬取的url
    private static volatile Queue<String> queue = new LinkedBlockingQueue<>();
    //等待写入txt文件队列
    private static volatile Queue<String> queue2 = new LinkedBlockingQueue<>();

    public static void main(String[] orgs) {
        int[] arr = ArrayRandom.random(100000, 2000000000);
        for (int value : arr) {
            String urls = "http://www.gytyjs.com/";
            StringBuffer buffer = new StringBuffer();
            StringBuffer stringBuffer = buffer.append(urls).append(value + ".html");
            queue.add(String.valueOf(stringBuffer));
        }
        for (int i = 0; i < MAX_THREAD; i++) {
            new WebSpider().new MyThread().start();
        }
    }

    private static void getArticlec() {
        String url = queue.poll();
        Document doc = null;
        try {
            doc = Jsoup.connect(url)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("Accept-Encoding", "gzip, deflate, sdch")
                    .header("Accept-Language", "zh-CN,zh;q=0.8")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36")
                    .userAgent("Mozilla/5.0 (compatible; Baiduspider/2.0;+http://www.baidu.com/search/spider.html)")
                    .timeout(30000)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert doc != null;
        String string2=doc.title();
        System.out.println("当前线程为：" +url+" "+ Thread.currentThread() + "标题为：" + string2);
            try {
                File file=new File("D:/WorkSpeas/IdeaCaiJiFile/FileName.txt");
                synchronized (file){
                    FileUtils.write(file, "\n"+string2, true);
               }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            do {
                synchronized (queue.poll()) {
                    getArticlec();
                }
            } while (queue.size() > 0);

        }

    }
}
//}