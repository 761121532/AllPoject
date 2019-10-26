package com.example.demo.serviceImpl;

import com.example.demo.dao.NewMapper;
import com.example.demo.entity.Goods;
import com.example.demo.entity.NewMessage;
import com.example.demo.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 在下萝莉控
 */
@Service
public class NewServiceImpl implements NewService {
    @Autowired
    NewMapper newMapper;

    /**
     * 查询所有新闻
     * @return 所有新闻的list集合
     */
    @Override
    public Goods list() {
        List<NewMessage> newMessages=newMapper.findAllNews();
        Goods<NewMessage> goods = new Goods();
        goods.setStatus(0);
        goods.setMessage(newMessages);
        return goods;
    }
}
