package com.example.dao;

import com.example.dto.Customer_cardDiscountDto;
import com.example.entity.Customer_card;

import java.math.BigDecimal;
import java.util.List;

public interface Customer_cardDao extends GenericDao<Customer_card, String>, AutoCloseable {

    List<Customer_card> searchCustomer_cardBySurname(String surname);

    List<Customer_card> searchCustomer_cardByPercentSortedBySurname(BigDecimal percent);

    List<Customer_cardDiscountDto> searchCustomer_cardDiscountForEachCategory(Customer_card customer_card);

    void close();
}
