package 结巴;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 结巴 {
    public static void main(String[] args) {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<SegToken> process = segmenter.process("今天早上，出门的的时候，天气很好", JiebaSegmenter.SegMode.INDEX);
        for (SegToken token:process) {
            //分词的结果
            System.out.println(token.word);
        }


        }

    private static StringBuffer changePingPai(int i, DomNodeList<DomElement> input, String string) throws IOException {
        //声明变量  用来存数据
        HashSet<String> set = new HashSet<String>();
        StringBuffer sb=new StringBuffer("");
        Document document2 = Jsoup.connect(string)
                .userAgent("Mozilla/5.0 (compatible; MSIE 10.0;Eindows NT 6.2;gimeex;gana;Trident/6.0)")
                .timeout(100000)
                .get();
        String str = Jsoup.clean(document2.body().toString(),new Whitelist());

        return new StringBuffer("");
    }
}

