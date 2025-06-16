package com.example.dao;

import com.example.entity.Employee;

import java.util.List;

public interface EmployeeDao extends GenericDao<Employee, String>, AutoCloseable{

    Employee searchEmployeeByID(String id);
    List<Employee> searchAllEmployeesSortedBySurname();

    List<Employee> searchAllCashiersSortedBySurname();

    Employee searchEmployeePhoneAndAddressBySurname(String surname);
    void close();
}
