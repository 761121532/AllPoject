package com.example.demo.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class User {
    //主键
    private long id;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //邮箱
    private String email;
    //上级
    private long managerid;
    //创建时间
    private LocalDateTime createTime;


}
