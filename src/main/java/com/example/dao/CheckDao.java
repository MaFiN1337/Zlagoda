package com.example.dao;

import com.example.dto.CheckSumDto;
import com.example.entity.Check;
import com.example.entity.Store_product;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CheckDao extends GenericDao<Check, String>, AutoCloseable{

    Optional<Check> searchCheckByNumber(String number);

    List<Check> searchChecksByEmployeeId(String id);

    List<Check> searchChecksByEmployeeSurname(String surname);

    List<Check> searchChecksByEmployeeIdPerPeriod(String id, LocalDate fromDate, LocalDate toDate);

    List<Check> searchChecksByEmployeeSurnamePerPeriod(String surname, LocalDate fromDate, LocalDate toDate);

    List<Check> searchChecksPerPeriod(LocalDate fromDate, LocalDate toDate);

    Optional<CheckSumDto> searchSumOfChecksByEmployeeSurnamePerPeriod(String surname, LocalDate fromDate, LocalDate toDate);

    Optional<CheckSumDto> searchSumOfChecksPerPeriod(LocalDate fromDate, LocalDate toDate);

    Optional<CheckSumDto> searchAmountOfStoreProductsPerPeriod(Store_product storeProduct, LocalDate fromDate, LocalDate toDate);

    void close();
}
