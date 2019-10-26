package com.example.demo02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient    //开启发现服务功能
@SpringBootApplication
public class Demo02Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo02Application.class, args);
    }


    /**
     *  RestTemplate 可以发送 http请求
     */
    @LoadBalanced    //使用负载均衡机制
    @Bean            //交给容器管理
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
