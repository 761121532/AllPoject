package cn.tzy;

public class 分词 {
    public static void test(){
        String[] end_standard = {"有限公司", "有责任限公司", "集团", "事务所", "研究所"};
        String[] find_key_word = {"版权所有", "Copyright", "©"};
        String reg="([\u4e00-\u9fa5()（）a-zA-Z]+?({0}))";
    }
}
