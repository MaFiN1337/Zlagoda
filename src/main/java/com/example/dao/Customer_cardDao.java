package com.example.dao;

import com.example.entity.Customer_card;

import java.util.List;

public interface Customer_cardDao extends GenericDao<Customer_card, String>, AutoCloseable {

    List<Customer_card> searchCustomer_cardBySurname(String surname);

    List<Customer_card> searchCustomer_cardSortedBySurname();

    List<Customer_card> searchCustomer_cardByPercentSortedBySurname(int percent);

    void close();
}
