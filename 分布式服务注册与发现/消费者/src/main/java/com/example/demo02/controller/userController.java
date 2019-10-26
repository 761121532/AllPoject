package com.example.demo02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class userController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/buy")
    public String buyTicket(String name){
        String s =restTemplate.getForObject("http://PRIVODER-TICKET/ticket",String.class);
        return name+"就是"+s;
    }
}
