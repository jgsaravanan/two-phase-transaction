package com.jta.transaction.service;

import com.jta.transaction.h2.entity.Orders;

public interface OrderService {
    void createOrder(Orders orders);
}
