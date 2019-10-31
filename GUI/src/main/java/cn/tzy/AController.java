package cn.tzy;


import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AController {
    //保存上一个页面的路径
    private String path;

    private String begin;

    private String end;

    private String TYPE;

    private String[] strings;

    private LinkedList<String> JC = new LinkedList<>();

    private ArrayList<String> UrlList;

    private String[] res;

    @FXML
    private TextArea action;

    @FXML
    private TextArea too;

    @FXML
    private TextArea three;

    public AController() {
        this.path = StageManager.count.get("path");
        this.begin = StageManager.count.get("begin");
        this.end = StageManager.count.get("end");
        this.TYPE = Type(StageManager.count.get("path"));
        this.strings = new String[1200];
    }

    //开始处理的按钮的点击事件
    public void Button1() {
        try {
            test(path, TYPE, begin, end, three);
        } catch (Exception e) {
            e.printStackTrace();
            alert("不知道为什么爆了个错");
        }
    }

    //一键处理的按钮
    public void Button2() {
        too.setText("");
        String[] test = three.getText().split("\n");
        for (String i : test) {
            new Thread(() -> {
                try {
                    Jsoup.connect("http://" + i).userAgent("Mozilla/5.0 (compatible; MSIE 10.0;Eindows NT 6.2;gimeex;gana;Trident/6.0)").get();
                } catch (IOException e) {
                    e.printStackTrace();
                    too.appendText(i + "\n");
                }
            }).start();
        }
        //保存的是错了的网址
        String[] strings =new String[JC.size()];
        JC.toArray(strings);
        //需要跑的网址
        List<String> resultList = new ArrayList<>(test.length);
        Collections.addAll(resultList, test);

        AController aController = this;
        try {
            Thread thread = new Thread(() -> inspect(aController,resultList, strings, too));
            thread.start();
            while (true) {
                if (thread.isAlive()) {
                    alert("处理完毕");
                    break;
                }
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            alert("不知道为什么，反正就是爆了个错");
        }
    }

    /**
     * @param path  需要访问的网站  构造函数  从上一个页面中获取
     * @param type  网站的类型  m   wap   web 。。。
     * @param begin 从哪里开始
     * @param end   到哪里结束
     * @param three 第三个文本框
     * @throws IOException 不知道为什么
     */
    private void test(String path, String type, String begin, String end, TextArea three) throws IOException, InterruptedException {
        //启用多线程
        final ExecutorService EXECUTOR = Executors.newFixedThreadPool(50);

        HtmlPage page2 = demo.getPage(path);

        DomNodeList<DomElement> element = page2.getElementsByTagName("a");

        LinkedList<String> 检查 = new LinkedList<>();

        int x = 0;
        for (DomElement domElement : element) {
            if ((x) % 14 == 0) {
                String href = domElement.asText();
                检查.add(href);
            }
            x++;
        }

        HashMap<String, Integer> map = getaction(begin, end, 检查);
        int be = map.get("be");
        int en = map.get("en");
        //保存检查的域名
        ArrayList<String> allUrlList = new ArrayList<>();
        int size = element.size() / 14;
        //遍历所有的a标签   并将  左边的检查域名取出来  并且准备拼接需要的字符串
        for (int i = be; i < en; i++) {
            if (i >= size) {
                break;
            }
            allUrlList.add(type + "." + element.get(i * 14).asText());
        }
        //声明一个保存所有   已经拼接好域名的list
        List<String> AllUrl = new ArrayList<>();
        //遍历  所有已经拼接好的  url 再次拼接

        for (String url : allUrlList) {
            AllUrl.add("http://" + url + "/domain2e/index/" + url + ".txt");
        }
        String[] response = new String[AllUrl.size()];
        //打开拼接好的url   并且访问  获得response  保存起来
        for (int i = 0; i < AllUrl.size(); i++) {
            final int j = i;
            EXECUTOR.execute(() -> {
                try {
                    String resout = Jsoup.connect(AllUrl.get(j)).get().body().text();
                    response[j] = "http://" + allUrlList.get(j) + "/" + resout;
                } catch (Exception e1) {
                    System.out.println(AllUrl.get(j));
                    e1.printStackTrace();
                }
            });
        }
        EXECUTOR.shutdown();
        //检查有没有问题的方法
        while (true) {
            if (EXECUTOR.isTerminated()) {
                this.UrlList = allUrlList;
                this.res = response;
                AController aController = this;
                Thread thread = new Thread(() -> inspect(aController,UrlList, res, three));
                thread.start();
                return;
            }
            Thread.sleep(2000);
        }
    }

    //取网站类型的方法
    /**
     * @param PA 访问的网站的域名
     * @return 网站的类型
     */
    private String Type(String PA) {
        String[] all = PA.split("\\.");
        all = all[0].split("//");
        if (!all[1].equals("m") && !all[1].equals("wap") && !all[1].equals("web") && !all[1].equals("ios") && !all[1].equals("anzhuo")) {
            return "www";
        }
        return all[1];
    }

    //弹窗的方法

    /**
     * @param str 需要在窗口打印的值
     */
    private void alert(String str) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, str, new ButtonType("确定", ButtonBar.ButtonData.YES));
        alert.setHeaderText(null);
        alert.setTitle("提示");
        alert.show();
    }

    //获取开始和结束位置的方法

    /**
     * @param begin 从哪里开始的
     * @param end   到哪里结束
     * @param 检查    包含了所有检查的域名的list
     * @return 包含了开始  和结束的int值的  map   方便使用
     * @throws IOException 弹窗 调用失败抛出的异常
     */
    private HashMap<String, Integer> getaction(String begin, String end, List<String> 检查) throws IOException {
        begin = begin.trim();
        end = end.trim();
        HashMap<String, Integer> map = new HashMap<>();
        int be = -1, en = -1;
        try {
            be = Integer.parseInt(begin);
            en = Integer.parseInt(end);
        } catch (Exception e) {
            //找到开始结束位置
            for (int i1 = 0; i1 < 检查.size(); i1++) {
                if (begin.equals(检查.get(i1))) {
                    be = i1;
                }
                if (end.equals(检查.get(i1))) {
                    en = i1;
                }
            }

            //可预计的一些错误的判断处理
            if (begin.equals("")) be = 0;
            if (end.equals("")) en = 1200;
            if (!begin.equals("") && be == -1) alert("你踏马，起始位置有点问题");
            if (!end.equals("") && en == -1) alert("卧槽，结束位置有点问题");
            if (be > en) alert("SBs，开始结束填反了");
        }
        map.put("be", be);
        map.put("en", en);

        return map;
    }

    /**
     *
     * @param aController  保存不同线程中的同一个实例  便于数据交互
     * @param allUrlList    被跑域名的信息
     * @param response      被跑域名的   老大服务器路径
     * @param three         显示到那个文本域
     */
    private void inspect(AController aController ,List<String> allUrlList, String[] response, TextArea three) {
        int y = 0;
        for (int i = 0; i < allUrlList.size(); i++) {
            try {
                //System.out.println(response[i]);
                String string = Jsoup.connect(response[i]).get().title();
                if (string.equals("502 Bad Gateway") || string.length() < 12 || string.equals("IIS 8.5 具體毛病 - 404.0 - Not Found")) {
                    aController.JC.add(response[i]);
                    three.appendText(allUrlList.get(i) + "\n");
                    strings[i] = allUrlList.get(y);
                    y++;
                } else {
                    action.appendText(allUrlList.get(i) + "  " + string + "\n");
                }
            } catch (Exception e1) {
                strings[i] = allUrlList.get(y);
                y++;
                aController.JC.add(response[i]);
                three.appendText(allUrlList.get(i) + "\n");
                e1.printStackTrace();
            }
        }
    }
}
