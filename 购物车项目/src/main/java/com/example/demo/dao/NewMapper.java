package com.example.demo.dao;

import com.example.demo.entity.NewMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 在下萝莉控
 */
@Mapper
@Repository
public interface NewMapper {
    /**
     * 查找所有的图片的方法
     * @return 返回所有的图片信息
     */
    List<NewMessage> findAllNews();

}
