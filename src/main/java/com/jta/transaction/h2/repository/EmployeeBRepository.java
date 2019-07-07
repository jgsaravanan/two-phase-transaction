package com.jta.transaction.h2.repository;

import com.jta.transaction.h2.entity.EmployeeB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeBRepository extends JpaRepository<EmployeeB,Integer> {
}
