package com.example.demo.config;

import com.example.demo.entity.Goods;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoodsBean {

    @Bean
    public Goods setGoods(){
        return new Goods();
    }
}
