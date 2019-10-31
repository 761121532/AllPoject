package spider;

import org.apache.commons.io.FileUtils;

import java.io.*;
public class HuoQuTxT {
    static String basePath = "E:\\测试";

    public static void main(String[] args) throws IOException {
        findFile();
    }

    public static void findFile() throws IOException {
        File dir=new File(basePath);
        File[] dirFiles = dir.listFiles();
        for (int i=0;i<dirFiles.length;i++){//遍历所有的txt文件的名字
            String temp=dirFiles[i].toString();//将所有名字转成字符串
            String str = FileUtils.readFileToString(new File(temp), "GBK"); //去读取文件
            StringBuffer stringBuffer=new StringBuffer(str); //StringBuffer改写起来比String  消耗小的多
            String txtName=temp.replace(basePath+"\\","").replace(".txt","");//活得文件的名字

            int txtLength=stringBuffer.length();//笨办法 直接获取文章的长度  用来随机生成插入位置
            for (int j=0;j<5;j++){
                int index= (int) Math.floor(Math.random()*txtLength);//随机生成一个数字，它是小于文件长度的
                stringBuffer.insert(index,txtName);//向随机位置插入文件名字
            }
            String txt=new String(stringBuffer);

            String[] txts=txt.split("\r\n");
            StringBuffer oo=new StringBuffer();
            for (int j=0;j<txts.length;j++){
                oo.append("<p>"+txts[j]+"</p>");
            }
            String hh=new String(oo);
            hh=hh.replace("</p>","</p>\n");
            FileUtils.write(new File(basePath+"\\"+txtName+".txt"),hh,"GBK",false);
        }
    }

    public static String readFileContent(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        while (br.ready()) {
            sb.append(br.readLine());
        }
        System.out.println(sb.toString());
        return sb.toString();
    }


    public static void writeFileContent(File file, String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.flush();
        fw.close();
    }


}




