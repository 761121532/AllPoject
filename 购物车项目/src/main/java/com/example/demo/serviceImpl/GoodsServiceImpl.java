package com.example.demo.serviceImpl;


import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 在下萝莉控
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    Goods goods;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ImagesMapper imagesMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    HumImagesMapper humImagesMapper;


    @Override
    public Goods findAll() {
        List<HomeMessage> messages = goodsMapper.findAllpictrue();
        goods.setStatus(0);
        goods.setMessage(messages);
        return goods;
    }

    @Override
    public Goods findAllCategory() {
        List<Category> list = categoryMapper.findAllCategory();
        goods.setStatus(0);
        goods.setMessage(list);
        return goods;
    }

    @Override
    public Goods findAllImages(String a) {
        a = a.substring(15);
        int Category_id = Integer.parseInt(a);
        if (Category_id == 0) {
            List<images> list = imagesMapper.findAllImages();
            goods.setStatus(0);
            goods.setMessage(list);
            return goods;
        } else {
            List<images> list = imagesMapper.findCategoryImages(Category_id);
            goods.setStatus(0);
            goods.setMessage(list);
            return goods;
        }
    }

    @Override
    public Goods findAllGoodsList(String s) {
        if (s != null) {
            int a = Integer.parseInt(s);
            a = (a - 1) * 10;
            List<goodsList> list = goodsMapper.findAllGoodsList(a);
            goods.setStatus(0);
            goods.setMessage(list);
        }
        return goods;
    }

    @Override
    public Goods findCategoryImage(String a) {
        a = a.substring(18);
        int Category_id = Integer.parseInt(a);
        List<images> list = imagesMapper.findCategoryImage(Category_id);
        goods.setStatus(0);
        goods.setMessage(list);
        return goods;
    }

    @Override
    public Goods findComment(String path,String agrs) {
        path = path.substring(17);
        int a = Integer.parseInt(path);
        int b = Integer.parseInt(agrs);
        b = (b-1)*10;
        List<comment> list =commentMapper.findComment(a,b);
        goods.setStatus(0);
        goods.setMessage(list);
        return goods;
    }

    @Override
    public Boolean insertComment(String image_id, String content) {
        image_id = image_id.substring(17);
        int a = Integer.parseInt(image_id);

        comment comment =new comment();
        comment.setContent(content);
        comment.setImages_id(a);
        comment.setUser_name("匿名游客");

        return commentMapper.insertComment(comment);
    }

    @Override
    public Goods getthumimages(String src) {
        src = src.substring(19);
        int a = Integer.parseInt(src);
        List<humimages> list = humImagesMapper.getthumimages(a);
        goods.setStatus(0);
        goods.setMessage(list);
        return goods;
    }

    @Override
    public Goods findGoodsById(String src) {
        src = src.substring(19);
        int a = Integer.parseInt(src);
        List<goodsList> list = goodsMapper.findGoodsById(a);
        goods.setStatus(0);
        goods.setMessage(list);
        return goods;
    }

    @Override
    public Goods findDesc(String src) {
        src = src.substring(19);
        int a = Integer.parseInt(src);
        List<goodsList> list = goodsMapper.findDesc(a);
        goods.setStatus(0);
        goods.setMessage(list);
        return goods;
    }
}
