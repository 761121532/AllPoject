package com.example.demo.dao;


import com.example.demo.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 在下萝莉控
 */
@Mapper
@Repository
public interface CategoryMapper {
    /**
     * 查找所有的商品分类的方法
     * @return 返回所有的商品分类信息
     */
    List<Category> findAllCategory();

}
