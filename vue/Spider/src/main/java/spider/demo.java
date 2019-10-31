package spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class demo {

    public static void main(String[] args) throws IOException, InterruptedException {
        trustEveryone();
        var number=1;
        for (;number<70;number++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            var url="https://search.cctv.com/search.php?qtext=篮球新闻&sort=relevance&type=web&vtime=&datepid=1&channel=&page="+number;
            var document=new Document("");
            try {
                 document= Jsoup.connect(url)
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        .header("Accept-Encoding", "gzip, deflate, sdch")
                        .header("Accept-Language", "zh-CN,zh;q=0.8")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36")
                        .userAgent("Mozilla/5.0 (compatible; Baiduspider/2.0;+http://www.baidu.com/search/spider.html)")
                        .timeout(30000)
                        .get();

                for (var i=1;i<10;i++){
                    var id="web_content_"+i;
                    String s= document.getElementById(id).text();
                    System.out.println(s);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(number);
       }
    }
    //让自己信任所有站点   就可以抓取https的网页  直接调用即可
    public static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[] { new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            } }, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
