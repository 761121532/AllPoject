package com.example.demo.entity;

import java.util.Date;

/**
 * Auto-generated: 2019-10-07 19:25:46
 *
 * @author 在下萝莉控
 */
public class goodsList {

    private int id;
    private String title;
    private Date add_time;
    private String zhaiyao;
    private int click;
    private String img_url;
    private int sell_price;
    private int market_price;
    private int stock_quantity;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }
    public Date getAdd_time() {
        return add_time;
    }

    public void setZhaiyao(String zhaiyao) {
        this.zhaiyao = zhaiyao;
    }
    public String getZhaiyao() {
        return zhaiyao;
    }

    public void setClick(int click) {
        this.click = click;
    }
    public int getClick() {
        return click;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
    public String getImg_url() {
        return img_url;
    }

    public void setSell_price(int sell_price) {
        this.sell_price = sell_price;
    }
    public int getSell_price() {
        return sell_price;
    }

    public void setMarket_price(int market_price) {
        this.market_price = market_price;
    }
    public int getMarket_price() {
        return market_price;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }
    public int getStock_quantity() {
        return stock_quantity;
    }

}