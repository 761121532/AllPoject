package spider;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;


public class neiye {
    public static final String PATH = "C:\\Users\\Administrator\\Desktop\\新建文件夹\\";

    public static void main(String[] args) throws IOException {
        File file = new File(PATH);
        String[] list = file.list();
        for (int o = 0; o < list.length; o++) {
            if (!"css".equals(list[o])) {
                String str = list[o].substring(list[o].length() - 5);

                if (!"files".equals(str)) {

                    String html = FileUtils.readFileToString(new File(PATH + "\\" + list[o]), "utf-8");
                    Document document = Jsoup.parse(html);

                    document.select("title").html("<标题关键词集合>");

                    document.select("script").remove();

                    Elements a = document.select("a");
                    a.attr("href", "/news/<蜘蛛池_动态随机字符><蜘蛛池_随机端口>.html");
                    a.attr("alt", "<蜘蛛池_随机关键词>");
                    a.attr("title", "<蜘蛛池_随机关键词>");


                    String[] keyWords = {"首页", "公司简介", "企业文化", "核心服务", "乐士商城", "联系我们", "扶商团队",
                            "联系我们", "联系我们", "行业资讯", "岱银报", "环保公示"};
                    for (Element element : a) {
                        var chang = true;
                        for (String keyWord : keyWords) {
                            if (keyWord.equals(element.html())) {
                                chang = false;
                                break;
                            }
                        }
                        if (chang) {
                            element.text("【<蜘蛛池_随机关键词><蜘蛛池_动态随机字符>】");
                        }
                    }

                    Elements image = document.select("img");
                    for (Element e : image) {
                        e.attr("src", "<蜘蛛池_随机图片>");
                        e.attr("alt","<蜘蛛池_拼音关键词1>");
                    }
                    String htmlName = "neiye" + o;
                    System.out.println(document);
                    FileUtils.write(new File(PATH + htmlName + ".html"), document.toString(), "utf-8", false);
                }
            }
        }
    }
}
