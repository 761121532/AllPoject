package com.example.demo.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.apache.http.HttpEntity;
public class main {
    public static void main(String[] args) throws MalformedURLException {
        String str="http://www.scjiale.com/";
        String getStr=DomPareUtil.getHttpContent(str);
        String postStr=DomPareUtil.postHttpContent(str,new HashMap<String, String>());
        //看有没有这个文件  有的话就不管   没有就创建一个文件夹
        String strPath = "E:\\a\\aa";
        File file = new File(strPath);
        if(!file.exists()){
            file.mkdirs();
        }
        //将字符串输出成一个文件
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(strPath+"\\index.html");
            fos.write(postStr.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(postStr);
    }
}
