package com.example.demo.dao;

import com.example.demo.entity.HomeMessage;
import com.example.demo.entity.goodsList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 在下萝莉控
 */
@Mapper
@Repository
public interface GoodsMapper {
    /**
     * 查找所有的图片的方法
     * @return 返回所有的图片信息
     */
    List<HomeMessage> findAllpictrue();

    List<goodsList> findAllGoodsList(Integer s);

    List<goodsList> findGoodsById(Integer a );

    List<goodsList> findDesc(Integer a);

}
