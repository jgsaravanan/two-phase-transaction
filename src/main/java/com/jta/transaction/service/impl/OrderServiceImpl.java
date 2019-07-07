package com.jta.transaction.service.impl;

import com.jta.transaction.h2.entity.Orders;
import com.jta.transaction.h2.repository.OrdersRepository;
import com.jta.transaction.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    public void createOrder(Orders orders){
        ordersRepository.save(orders);
    }
}
