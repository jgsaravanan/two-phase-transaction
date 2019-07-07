package com.jta.transaction.service;

import com.jta.transaction.h2.entity.EmployeeB;
import com.jta.transaction.oracle.entity.EmployeeA;

public interface EmployeeTransactionService {

    void addEmployee(EmployeeA employeeA, EmployeeB employeeB) throws Exception;

}
