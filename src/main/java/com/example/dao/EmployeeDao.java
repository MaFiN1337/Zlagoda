package com.example.dao;

import com.example.dto.EmployeeTaxSummaryDto;
import com.example.entity.Employee;

import java.util.List;

public interface EmployeeDao extends GenericDao<Employee, String>, AutoCloseable{

    List<Employee> searchAllCashiersSortedBySurname();

    List<Employee> searchEmployeeBySurname(String surname);

    List<EmployeeTaxSummaryDto> searchEmployeeSumOfVatForEachCategory(Employee employee);

    List<Employee> searchEmployeesSellingAllCategoriesOfProducts();
    void close();
}
