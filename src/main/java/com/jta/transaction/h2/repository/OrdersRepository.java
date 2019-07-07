package com.jta.transaction.h2.repository;

import com.jta.transaction.h2.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
