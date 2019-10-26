package com.example.demo.entity;

import java.util.Date;

public class humimages {
    private int id;
    private String src;
    private String images_id;
    private Date add_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getImages_id() {
        return images_id;
    }

    public void setImages_id(String images_id) {
        this.images_id = images_id;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }
}
