package com.example.demo01.service;

import org.springframework.stereotype.Service;

@Service
public class TicketService {
    public String getTicket(){
        System.out.println("8001");
        return "我套死你猴子";
    }
}
