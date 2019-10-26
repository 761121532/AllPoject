package com.example.demo.entity;


import java.util.Date;

/**
 * Auto-generated: 2019-10-08 14:52:41
 *  评论系统的实体类
 * @author 在下萝莉控
 */
public class comment {

    private int id;
    private String user_name;
    private Date add_time;
    private String content;
    private int images_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImages_id() {
        return images_id;
    }

    public void setImages_id(int images_id) {
        this.images_id = images_id;
    }
}