package com.imooc.demo.dao;

import javax.annotation.Resource;

import com.imooc.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;



@Repository
public class UserDao {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource(name="stringRedisTemplate")//key和value都是String的情况下调用的方法
    ValueOperations<String, String> valOpsStr;

    public void save(String key,String value){
        valOpsStr.set(key, value);
    }

    public String getByKey(String key){
        String value = valOpsStr.get(key);
        return value;
    }


    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    @Resource(name="redisTemplate")//key是字符串value是object的情况下调用的方法
    ValueOperations<Object, Object> valOps;

    public void save(User user){
        valOps.set(user.getId(), user);
    }

    public User getById(String id){
        User user = (User) valOps.get(id);
        return user;
    }

}
