package com.example.demo.entity;

import java.util.Date;

/**
 * Auto-generated: 2019-10-06 15:38:27
 *
 * @author 在下萝莉控
 */
public class NewMessage {

    private int New_id;
    private String New_title;
    private Date create_time;
    private String New_zhaiyao;
    private int New_click;
    private String New_img_url;

    public int getNew_id() {
        return New_id;
    }

    public void setNew_id(int new_id) {
        New_id = new_id;
    }

    public String getNew_title() {
        return New_title;
    }

    public void setNew_title(String new_title) {
        New_title = new_title;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getNew_zhaiyao() {
        return New_zhaiyao;
    }

    public void setNew_zhaiyao(String new_zhaiyao) {
        New_zhaiyao = new_zhaiyao;
    }

    public int getNew_click() {
        return New_click;
    }

    public void setNew_click(int new_click) {
        New_click = new_click;
    }

    public String getNew_img_url() {
        return New_img_url;
    }

    public void setNew_img_url(String new_img_url) {
        New_img_url = new_img_url;
    }
}
