package com.jta.transaction.request.data;

import com.jta.transaction.h2.entity.EmployeeB;
import com.jta.transaction.oracle.entity.EmployeeA;
import lombok.Data;

@Data
public class EmployeeRequestData {
    private EmployeeA employeeA;
    private EmployeeB employeeB;
}
