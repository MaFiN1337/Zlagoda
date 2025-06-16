package com.example.dao;

import com.example.entity.Check;

import java.time.LocalDate;
import java.util.List;

public interface CheckDao extends GenericDao<Check, String>, AutoCloseable{


    Check searchCheckByNumber(String number);

    List<Check> searchChecksByEmployeeId(String id);

    List<Check> searchChecksByEmployeeSurname(String surname);

    List<Check> searchChecksByEmployeeIdPerPeriod(String id, LocalDate fromDate, LocalDate toDate);

    List<Check> searchChecksByEmployeeSurnamePerPeriod(String surname, LocalDate fromDate, LocalDate toDate);

    List<Check> searchChecksPerPeriod(LocalDate fromDate, LocalDate toDate);

    Long searchSumOfChecksByEmployeeSurnamePerPeriod(String surname, LocalDate fromDate, LocalDate toDate);

    Long searchSumOfChecksPerPeriod(LocalDate fromDate, LocalDate toDate);



}
