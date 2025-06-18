package com.example.dao;

import com.example.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao extends GenericDao<Employee, String>, AutoCloseable{

    List<Employee> searchAllCashiersSortedBySurname();

    Optional<Employee> searchEmployeeBySurname(String surname);
    void close();
}
