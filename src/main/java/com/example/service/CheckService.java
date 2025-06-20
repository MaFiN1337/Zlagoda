package com.example.service;

import com.example.dao.CheckDao;
import com.example.dao.DaoFactory;
import com.example.dto.CheckSumDto;
import com.example.entity.Check;
import com.example.entity.Store_product;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CheckService {
    private static final Logger LOGGER = LogManager.getLogger(CheckService.class);

    static final String GET_ALL_CHECKS = "Get all checks";
    static final String GET_CHECK_BY_ID = "Get check by id: %s";
    static final String CREATE_CHECK = "Create check: %s";
    static final String UPDATE_CHECK = "Update check: %s";
    static final String DELETE_CHECK = "Delete check: %s";
    static final String SEARCH_CHECK_BY_NUMBER = "Search check by number: %s";
    static final String SEARCH_CHECKS_BY_EMPLOYEE_ID = "Search checks by employee id: %s";
    static final String SEARCH_CHECKS_BY_EMPLOYEE_SURNAME = "Search checks by employee surname: %s";
    static final String SEARCH_CHECKS_BY_EMPLOYEE_ID_PER_PERIOD = "Search checks by employee id per period: %s %s %s";
    static final String SEARCH_CHECKS_BY_EMPLOYEE_SURNAME_PER_PERIOD = "Search checks by employee surname per period: %s %s %s";
    static final String SEARCH_CHECKS_PER_PERIOD = "Search checks per period: %s %s";
    static final String SEARCH_SUM_OF_CHECKS_BY_EMPLOYEE_SURNAME_PER_PERIOD = "Search sum of checks by employee surname per period: %s %s %s";
    static final String SEARCH_SUM_OF_CHECKS_PER_PERIOD = "Search sum of checks per period: %s %s";
    static final String SEARCH_AMOUNT_OF_STORE_PRODUCT_PER_PERIOD = "Search amount of store product per period: %s %s %s";


    private final DaoFactory daoFactory;

    CheckService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final CheckService INSTANCE = new CheckService(DaoFactory.getDaoFactory());
    }

    public static CheckService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Check> getAllChecks() {
        LOGGER.info(GET_ALL_CHECKS);
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.getAll();
        }
    }

    public Optional<Check> getCheckById(String checkNumber) {
        LOGGER.info(String.format(GET_CHECK_BY_ID, checkNumber));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.getById(checkNumber);
        }
    }

    public void createCheck(Check check) {
        LOGGER.info(String.format(CREATE_CHECK, check.getNumber()));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.create(check);
        }
    }

    public void updateCheck(Check check) {
        LOGGER.info(String.format(UPDATE_CHECK, check.getNumber()));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.update(check);
        }
    }

    public void deleteCheck(String checkNumber) {
        LOGGER.info(String.format(DELETE_CHECK, checkNumber));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.delete(checkNumber);
        }
    }

    public Optional<Check> searchCheckByNumber(String number) {
        LOGGER.info(String.format(SEARCH_CHECK_BY_NUMBER, number));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.searchCheckByNumber(number);
        }
    }

    public List<Check> searchChecksByEmployeeId(String id){
        LOGGER.info(String.format(SEARCH_CHECKS_BY_EMPLOYEE_ID, id));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.searchChecksByEmployeeId(id);
        }
    }

    public List<Check> searchChecksByEmployeeSurname(String surname){
        LOGGER.info(String.format(SEARCH_CHECKS_BY_EMPLOYEE_SURNAME, surname));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.searchChecksByEmployeeSurname(surname);
        }
    }

    public List<Check> searchChecksByEmployeeIdPerPeriod(String id, LocalDate fromDate, LocalDate toDate){
        LOGGER.info(String.format(SEARCH_CHECKS_BY_EMPLOYEE_ID_PER_PERIOD, id, fromDate.toString(), toDate.toString()));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.searchChecksByEmployeeIdPerPeriod(id, fromDate, toDate);
        }
    }

    public List<Check> searchChecksByEmployeeSurnamePerPeriod(String surname, LocalDate fromDate, LocalDate toDate){
        LOGGER.info(String.format(SEARCH_CHECKS_BY_EMPLOYEE_SURNAME_PER_PERIOD, surname, fromDate.toString(), toDate.toString()));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.searchChecksByEmployeeSurnamePerPeriod(surname, fromDate, toDate);
        }
    }

    public List<Check> searchChecksPerPeriod(LocalDate fromDate, LocalDate toDate){
        LOGGER.info(String.format(SEARCH_CHECKS_PER_PERIOD, fromDate.toString(), toDate.toString()));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.searchChecksPerPeriod(fromDate, toDate);
        }
    }

    public Optional<CheckSumDto> searchSumOfChecksByEmployeeSurnamePerPeriod(String surname, LocalDate fromDate, LocalDate toDate){
        LOGGER.info(String.format(SEARCH_SUM_OF_CHECKS_BY_EMPLOYEE_SURNAME_PER_PERIOD, surname, fromDate.toString(), toDate.toString()));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.searchSumOfChecksByEmployeeSurnamePerPeriod(surname, fromDate, toDate);
        }
    }

    public Optional<CheckSumDto> searchSumOfChecksPerPeriod(LocalDate fromDate, LocalDate toDate){
        LOGGER.info(String.format(SEARCH_SUM_OF_CHECKS_PER_PERIOD, fromDate.toString(), toDate.toString()));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.searchSumOfChecksPerPeriod(fromDate, toDate);
        }
    }

    public Optional<CheckSumDto> searchAmountOfStoreProductsPerPeriod(Store_product storeProduct, LocalDate fromDate, LocalDate toDate){
        LOGGER.info(String.format(SEARCH_AMOUNT_OF_STORE_PRODUCT_PER_PERIOD, storeProduct.getUpc(), fromDate.toString(), toDate.toString()));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.searchAmountOfStoreProductsPerPeriod(storeProduct, fromDate, toDate);
        }
    }
}
