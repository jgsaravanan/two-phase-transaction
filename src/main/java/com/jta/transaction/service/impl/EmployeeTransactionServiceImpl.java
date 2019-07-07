package com.jta.transaction.service.impl;

import com.jta.transaction.h2.entity.EmployeeB;
import com.jta.transaction.h2.repository.EmployeeBRepository;
import com.jta.transaction.oracle.entity.EmployeeA;
import com.jta.transaction.oracle.repository.EmployeeARepository;
import com.jta.transaction.service.EmployeeTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeTransactionServiceImpl implements EmployeeTransactionService {

    @Autowired
    private EmployeeARepository employeeARepository;

    @Autowired
    private EmployeeBRepository employeeBRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addEmployee(EmployeeA employeeA, EmployeeB employeeB) throws Exception{
        employeeARepository.save(employeeA);
        employeeBRepository.save(employeeB);
        //throw new Exception("Not Saved");
    }
}
