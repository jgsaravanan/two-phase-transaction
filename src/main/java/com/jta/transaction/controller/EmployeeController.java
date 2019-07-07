package com.jta.transaction.controller;

import com.jta.transaction.request.data.EmployeeRequestData;
import com.jta.transaction.service.EmployeeTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("employee")
public class EmployeeController {

    @Autowired
    private EmployeeTransactionService employeeTransactionService;

    @PostMapping("addEmployee")
    public String addEmployee(@RequestBody EmployeeRequestData employeeRequestData) throws Exception{
        employeeTransactionService.addEmployee(employeeRequestData.getEmployeeA(),employeeRequestData.getEmployeeB());
        return "Success";
    }

}
