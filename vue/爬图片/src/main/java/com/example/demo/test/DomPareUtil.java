package com.example.demo.test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yangxiaohui
 * @Date: Create by 2018-10-31 12:23
 * @Description: 基于Apache的HttpClient实现的网络访问工具类
 */
public class DomPareUtil {
    public static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    /**
     * <pre>
     *     发起GET请求 获取网络内容 并转成字符串进行返回（字符串：后面无论是解析dom或者结果是json字符串 都比较方便进行转换）
     * <pre>
     * @author Yangxiaohui
     * @date 2018-10-31 12:28
     * @param url   请求地址
     * @return
     */
    public static String getHttpContent(String url){
        StringBuffer result = new StringBuffer();
        HttpGet httpGet = new HttpGet(url);
        try{
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStreamReader reader = new InputStreamReader(entity.getContent(),"utf-8");
            char [] charbufer;
            while (0<reader.read(charbufer=new char[10])){
                result.append(charbufer);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            httpGet.releaseConnection();
        }

        httpGet.releaseConnection();
        return result.toString();
    }
    /**
     * <pre>
     *     发起POST请求 获取网络内容 并转成字符串进行返回（字符串：后面无论是解析dom或者结果是json字符串 都比较方便进行转换）
     * <pre>
     * @author Yangxiaohui
     * @date 2018-10-31 12:31
     * @param url   请求地址
     * @param data  请求参数内容
     * @return
     */
    public static String postHttpContent(String url, Map<String,String> data){
        StringBuffer sb = new StringBuffer();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
        //解决中文乱码问题
        httpPost.addHeader("Content-type","application/x-www-form-urlencoded; charset=utf-8");
        if(null != data) {
            httpPost.setEntity(new StringEntity(data.toString(), Charset.forName("UTF-8")));
            for (String key : data.keySet()) {
                valuePairs.add(new BasicNameValuePair(key, data.get(key)));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            BufferedInputStream bis = new BufferedInputStream(httpEntity.getContent());
            byte [] buffer;
            while (0<bis.read(buffer=new byte[128])){
                sb.append(new String(buffer,"utf-8"));
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
        return sb.toString();
    }
}