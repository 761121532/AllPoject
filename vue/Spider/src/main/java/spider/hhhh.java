package spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class hhhh {
    public static void main(String[] args) throws IOException {
        String string = getURLContent("https://api.1314zf.com/qqzl.php?qq=3088854&amp;collection=web&amp;output=json");
        System.out.println(string);
    }
    public static String getURLContent(String urlStr) {
        //请求的url
        URL url = null;
        //请求的输入流
        BufferedReader in = null;
        //输入流的缓冲
        StringBuffer sb = new StringBuffer();
        try{
            url = new URL(urlStr);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "ISO_8859_1") );
            String str = null;
            //一行一行进行读入
            while((str = in.readLine()) != null) {
                sb.append( str );
            }
        } catch (Exception ex) {

        } finally{
            try{
                if(in!=null) {
                    in.close(); //关闭流
                }
            }catch(IOException ex) {

            }
        }
        String result =sb.toString();
        return result;
    }

}

