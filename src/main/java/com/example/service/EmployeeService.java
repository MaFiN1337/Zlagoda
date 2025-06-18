package com.example.service;

import com.example.dao.DaoFactory;
import com.example.dao.EmployeeDao;
import com.example.entity.Employee;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);

    static final String GET_ALL_EMPLOYEES = "Get all employees";
    static final String GET_EMPLOYEE_BY_ID = "Get employee by id: %s";
    static final String CREATE_EMPLOYEE = "Create employee: %s";
    static final String UPDATE_EMPLOYEES = "Update employee: %s";
    static final String DELETE_EMPLOYEES = "Delete employee: %s";
    static final String SEARCH_EMPLOYEES_BY_SURNAME = "Search employees by surname: %s";
    static final String SEARCH_EMPLOYEE_CASHIERS_SORTED_BY_SURNAME = "Search employees-cashiers by surname and sort: %s";

    private final DaoFactory daoFactory;

    EmployeeService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final EmployeeService INSTANCE = new EmployeeService(DaoFactory.getDaoFactory());
    }

    public static EmployeeService getInstance() {
        return EmployeeService.Holder.INSTANCE;
    }

    public List<Employee> getAllEmployees() {
        LOGGER.info(GET_ALL_EMPLOYEES);
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            return employeeDao.getAll();
        }
    }

    public Optional<Employee> getEmployeeById(String employeeId) {
        LOGGER.info(String.format(GET_EMPLOYEE_BY_ID, employeeId));
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            return employeeDao.getById(employeeId);
        }
    }

    public void createEmployee(Employee employee) {
        LOGGER.info(String.format(CREATE_EMPLOYEE, employee.getName()));
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            employeeDao.create(employee);
        }
    }

    public void updateProduct(Employee employee) {
        LOGGER.info(String.format(UPDATE_EMPLOYEES, employee.getId()));
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            employeeDao.update(employee);
        }
    }

    public void deleteEmployee(String employeeId) {
        LOGGER.info(String.format(DELETE_EMPLOYEES, employeeId));
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            employeeDao.delete(employeeId);
        }
    }

    public Optional<Employee> searchEmployeesBySurname(String employeeSurname) {
        LOGGER.info(String.format(SEARCH_EMPLOYEES_BY_SURNAME, employeeSurname));
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            return employeeDao.searchEmployeeBySurname(employeeSurname);
        }
    }

    public List<Employee> searchEmployeesCashiersSortedBySurname() {
        LOGGER.info(SEARCH_EMPLOYEE_CASHIERS_SORTED_BY_SURNAME);
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            return employeeDao.searchAllCashiersSortedBySurname();
        }
    }
}
