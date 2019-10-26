package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@SuppressWarnings("serial")   //压制警告的东西  让编译器忽略警告
@Data                         // get  和set方法
@AllArgsConstructor           //全部参数的构造方法  全参构造
@NoArgsConstructor            //没有参数的构造方法  空参构造
@Accessors(chain = true)      //链式风格访问
public class aaa implements Serializable {
    String content;
}
