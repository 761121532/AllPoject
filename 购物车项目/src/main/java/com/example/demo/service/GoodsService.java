package com.example.demo.service;

import com.example.demo.entity.Goods;

/**
 * @author 在下萝莉控
 */
public interface GoodsService {
    /**
     *
     * @return  所有图片信息
     */
    Goods findAll();


    /**
     *
     * @return 所有分类的信息
     */
    Goods findAllCategory();

    /**
     *
     * @return 图片信息
     */
    Goods findAllImages(String a);

    /**
     * @return 所有商品信息
     */
    Goods findAllGoodsList(String s) ;


    /**
     *@return 指定id商品信息
     */
    Goods findCategoryImage(String s) ;

    /**
     *
     * @param path 路由参数
     * @return  所有评论信息
     */
    Goods findComment(String path,String agrs) ;


    /**
     *
     * @return 是否成功写入数据库
     */
    Boolean insertComment(String image_id ,String content);

    /**
     *
     * @param src 路由中传入的参数
     * @return 包含所有对应分类小图片的对象
     */
    Goods getthumimages(String src);

    /**
     *
     * @param src 路由中传入的参数
     * @return 对应id 的商品内容
     */
    Goods findGoodsById(String src);

    /**
     *
     * @param src 路由中传入的参数
     * @return  商品的详细信息
     */
    Goods findDesc(String src);


}
