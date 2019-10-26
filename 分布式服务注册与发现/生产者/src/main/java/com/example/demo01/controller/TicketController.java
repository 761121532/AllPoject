package com.example.demo01.controller;

import com.example.demo01.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    @Autowired
    TicketService ticketService;

    @LoadBalanced
    @GetMapping("/ticket")
    public String getTicket(){
        System.out.println("在下萝莉控2222");
        return ticketService.getTicket();
    }
}
