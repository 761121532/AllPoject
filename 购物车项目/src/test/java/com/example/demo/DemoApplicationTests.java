package com.example.demo;

import com.example.demo.dao.GoodsMapper;
import com.example.demo.entity.HomeMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    GoodsMapper goodsMapper;
    @Test
    public void contextLoads() {
        List<HomeMessage> message= goodsMapper.findAllpictrue();
        System.out.println(message.get(0).getHomepage_image());
    }

}
