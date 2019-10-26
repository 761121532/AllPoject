package com.example.demo.dao;


import com.example.demo.entity.comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 在下萝莉控
 */
@Mapper
@Repository
public interface CommentMapper {
    /**
     * 查找所有的商品分类的方法
     * @return 返回所有的商品分类信息
     */
    List<comment> findComment(int a , int b);

    /**
     *
     * @param comment 写入数据库的值
     * @return  是否成功写入数据库
     */
    Boolean insertComment(comment comment);

}
