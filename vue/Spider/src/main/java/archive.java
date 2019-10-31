import net.sf.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class archive {
    public static void main(String[] args) throws IOException {

        String[] domain={"www.fzzmsoft.com","www.wdgj.com","www.sintoyu.com","www.fjshuanghuan.com"};
        for (int i=0;i<domain.length;i++){
            String interfice="http://web.archive.org/__wb/sparkline?url="+domain[i]+"&collection=web&output=json";
            Connection.Response res = Jsoup.connect(interfice).timeout(10000).ignoreContentType(true).execute();
            JSONObject jsonObject = JSONObject.fromObject(res.body());
            JSONObject a = jsonObject.getJSONObject("years");
            for (Iterator it = a.keys(); it.hasNext(); ) {
                Object s = it.next();
               var urlll="http://web.archive.org/__wb/calendarcaptures?url="+domain[i]+"&selected_year="+s+"";
                Connection.Response ress = Jsoup.connect(urlll).timeout(10000).ignoreContentType(true).execute();
                String str=ress.body();
                List<String> strs = new ArrayList<String>();
                Pattern p = Pattern.compile("[0-9]{14}");
                Matcher m = p.matcher(str);
                while(m.find()) {
                    strs.add(m.group());
                }
                for (int j = 0; j <strs.size() ; j++) {
                    try {
                        Document document= Jsoup.connect("http://web.archive.org/web/"+strs.get(j)+"/"+domain[i]).get();
                        String strss=strs.get(j).toString().substring(0,8);
                        System.out.println(strss.substring(0,4)+"年"+strss.substring(4,6)+"月"+strss.substring(6,8)+"日"+document.select("title"));
                        TimeUnit.SECONDS.sleep(1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
            System.out.println();
        }
    }

}
