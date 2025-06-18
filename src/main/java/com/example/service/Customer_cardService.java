package com.example.service;

import com.example.dao.Customer_cardDao;
import com.example.dao.DaoFactory;
import com.example.dto.Customer_cardDiscountDto;
import com.example.entity.Customer_card;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Customer_cardService {
    private static final Logger LOGGER = LogManager.getLogger(Customer_cardService.class);

    static final String GET_ALL_CUSTOMER_CARDS = "Get all customer cards";
    static final String GET_CUSTOMER_CARD_BY_ID = "Get customer card by id: %s";
    static final String CREATE_CUSTOMER_CARD = "Create customer card: %s";
    static final String UPDATE_CUSTOMER_CARD = "Update customer card: %s";
    static final String DELETE_CUSTOMER_CARD = "Delete customer card: %s";
    static final String SEARCH_CUSTOMER_CARD_BY_SURNAME = "Search customer cards by surname and sort: %s";
    static final String SEARCH_CUSTOMER_CARD_BY_PERCENT_SORTED_BY_SURNAME = "Search customer cards by percent and sort by surname: %s";
    static final String SEARCH_CUSTOMER_CARD_DISCOUNT_FOR_EACH_CATEGORY = "Search total amount of discount made per each category by card_number: %s";

    private final DaoFactory daoFactory;

    Customer_cardService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final Customer_cardService INSTANCE = new Customer_cardService(DaoFactory.getDaoFactory());
    }

    public static Customer_cardService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Customer_card> getAllCustomer_cards() {
        LOGGER.info(GET_ALL_CUSTOMER_CARDS);
        try (Customer_cardDao customer_cardDao = daoFactory.createCustomer_cardDao()) {
            return customer_cardDao.getAll();
        }
    }

    public Optional<Customer_card> getCustomer_cardById(String customer_card_id) {
        LOGGER.info(String.format(GET_CUSTOMER_CARD_BY_ID, customer_card_id));
        try (Customer_cardDao customer_cardDao = daoFactory.createCustomer_cardDao()) {
            return customer_cardDao.getById(customer_card_id);
        }
    }

    public void createCustomer_card(Customer_card customer_card) {
        LOGGER.info(String.format(CREATE_CUSTOMER_CARD, customer_card.getNumber()));
        try (Customer_cardDao customerCardDao = daoFactory.createCustomer_cardDao()) {
            customerCardDao.create(customer_card);
        }
    }

    public void updateCustomer_card(Customer_card customerCard) {
        LOGGER.info(String.format(UPDATE_CUSTOMER_CARD, customerCard.getNumber()));
        try (Customer_cardDao customer_cardDao = daoFactory.createCustomer_cardDao()) {
            customer_cardDao.update(customerCard);
        }
    }

    public void deleteCustomer_card(String card_number) {
        LOGGER.info(String.format(DELETE_CUSTOMER_CARD, card_number));
        try (Customer_cardDao customer_cardDao = daoFactory.createCustomer_cardDao()) {
            customer_cardDao.delete(card_number);
        }
    }

    public List<Customer_card> searchCustomer_cardBySurname(String cust_surname) {
        LOGGER.info(String.format(SEARCH_CUSTOMER_CARD_BY_SURNAME, cust_surname));
        try (Customer_cardDao customer_cardDao = daoFactory.createCustomer_cardDao()) {
            return customer_cardDao.searchCustomer_cardBySurname(cust_surname);
        }
    }

    public List<Customer_card> searchCustomer_cardsByPercentSortedBySurname(BigDecimal percent){
        LOGGER.info(String.format(SEARCH_CUSTOMER_CARD_BY_PERCENT_SORTED_BY_SURNAME, percent));
        try (Customer_cardDao customer_cardDao = daoFactory.createCustomer_cardDao()){
            return customer_cardDao.searchCustomer_cardByPercentSortedBySurname(percent);
        }
    }

    public List<Customer_cardDiscountDto> searchCustomer_cardDiscountForEachCategory(Customer_card customer_card){
        LOGGER.info(String.format(SEARCH_CUSTOMER_CARD_DISCOUNT_FOR_EACH_CATEGORY, customer_card.getNumber()));
        try (Customer_cardDao customer_cardDao = daoFactory.createCustomer_cardDao()){
            return customer_cardDao.searchCustomer_cardDiscountForEachCategory(customer_card);
        }
    }
}
