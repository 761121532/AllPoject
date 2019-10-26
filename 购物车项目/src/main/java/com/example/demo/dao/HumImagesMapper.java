package com.example.demo.dao;


import com.example.demo.entity.humimages;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 在下萝莉控
 */
@Mapper
@Repository
public interface HumImagesMapper {
    /**
     * 查找对应分类的小图片方法
     * @return 返回所有对应分类的小图片信息
     */
    List<humimages> getthumimages(int a);

}
