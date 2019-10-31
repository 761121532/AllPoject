import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {
    private static String urll = "http://www.baidu.com/s?wd=";
    public static int number=0;


    public static void main(String[] args) {

        String[] citys={  "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "海南", "四川", "贵州", "云南",
                "陕西", "甘肃", "青海","兰州", "西安", "郑州", "济南", "沈阳", "石家庄", "昆明", "南宁", "杭州",
                "宁波", "南通", "海口", "太原", "武汉", "长沙", "贵阳", "太原", "桂林", "银川", "南京", "广州", "深圳",
                "福州", "合肥", "南昌", "深圳", "东莞", "苏州", "青岛", "烟台", "泉州", "无锡"};

        String[] industry={"空调","床上用品","油漆","空气净化器","水果","刺绣",
                "小程序","烟花","园艺","菠菜","外挂","奶粉","喷涂","工艺品","石墨","月嫂","鸟类","天文",
                "MBA","乒乓球","橡胶","厨具","新材料","典当","化学品","稀土","陶艺","餐饮设计","外贸服务"
                ,"激光配件","风俗" ,"订货系统"};
        int c=1;
        String name="";
        for (int i=0;i<citys.length;i++){
            for (int j=0;j<industry.length;j++){
                name=citys[i]+" "+industry[j]+" 公司";
                getResult(600,name,number,c);
            }
        }

    }

    private static void getNews(String url,String question,int number,int c) {
        String industrys[]=question.split(" ");
        String industry=industrys[1];
        try {
            Document doc = Jsoup.connect(url).get();//搜出来的网页转换为dom对象
            Element element = doc.getElementById("content_left");//获得dom对象 对应name的dom元素
            int oo=c+10 ;
            for (; c < oo; c++) {
                Element result = element.getElementById(String.valueOf(c));
                if (result==null){
                    continue;
                }
                Elements add = result.select("a");

                String attr = add.first().attr("href");
                String aa=attr.substring(0,7);
                if (aa.equals("http://")){

                    String str=getRealUrlFromBaiduUrl(attr);

                    String regex="^((ht|f)tps?):\\/\\/[\\w\\-]+(\\.[\\w\\-]+)+[/.com]?$";
                    // String regex="^[http;//www.]|[https://www.][A-Za-z0-9_]+[.com]|[.cn]|[.net]|[.org]$";
                    Pattern p=Pattern.compile(regex);
                    Matcher m=p.matcher(str);
                    if (m.matches()) {
                        String name=add.first().text();
                        //System.out.println(name);
//                    FileUtils.write(new File("D:/爬虫/"+question+".txt"),name,true);
//                    FileUtils.write(new File("D:/爬虫/"+question+".txt"),"\r\n",true);
                        //System.out.println(attr);
//                    String str=getRealUrlFromBaiduUrl(attr);

                        String cc=update(str);
                        if (cc!=""){
                            //System.out.println(str);
                            FileUtils.write(new File("/www/Spider/"+industry+".txt"),cc,true);
                            FileUtils.write(new File("/www/Spider/"+industry+".txt"),"\r\n",true);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //拼接百度网址
    private static void getResult(int num, String question,int number,int c) {
        int rest=num/10;

        for (int i=0;i<rest;i++){
            String url = "";
            c=i*10;
            try {
                url = urll + URLEncoder.encode(question, "utf-8") + "&pn=" + (i*10)+"&oq="+URLEncoder.encode(question, "utf-8")+"&tn=92509258_hao_pg&ie=utf-8&ch=5&usm=1";//转换为西欧字符

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            getNews(url,question,number,c);
            number++;
            System.out.println("--------------------- " + number + " ---------------------");
            System.out.println();
        }
    }

    //转成真实url
    public static String getRealUrlFromBaiduUrl(String url) {
        Connection.Response res = null;
        int itimeout = 60000;
        try {
            res = Jsoup.connect(url).timeout(itimeout).method(Connection.Method.GET).followRedirects(false).execute();
            return res.header("Location");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String update(String str){
        String bb=str.substring(0,7);
        String cc=str.replace(bb,"");
        char charcc=cc.charAt(0);
        String stringcc=String.valueOf(charcc);
        //去除开头的/
        if(stringcc=="/"){
            cc=cc.replace("/","");
        }
        String noturl=cc.substring(0,3);
        //判断是不是www开头的
        if(!noturl.equals("www")){
            return "";
        }
        return cc.replace("/","");
    }




}
