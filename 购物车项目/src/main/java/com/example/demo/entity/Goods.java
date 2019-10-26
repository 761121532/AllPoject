package com.example.demo.entity;

import java.util.List;

/**
 * Auto-generated: 2019-10-05 21:31:30
 *
 * @author 在下萝莉控
 */
public class Goods<T> {

    private int status;
    private List<T> message;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<T> getMessage() {
        return message;
    }

    public void setMessage(List<T> message) {
        this.message = message;
    }


}