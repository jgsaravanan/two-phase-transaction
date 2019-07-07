package com.jta.transaction.controller;

import com.jta.transaction.h2.entity.Orders;
import com.jta.transaction.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("create-order")
    public String createOrder(@RequestBody Orders orders){
        orderService.createOrder(orders);
        return "Success";
    }

}
