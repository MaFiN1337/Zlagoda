package com.example.dto;

import com.example.entity.IBuilder;

import java.math.BigDecimal;

public class Customer_cardDiscountDto {

    private String cardNumber;
    private String custSurname;
    private BigDecimal discount;


    public static class Builder implements IBuilder<Customer_cardDiscountDto> {

        private final Customer_cardDiscountDto customerCardDiscountDto = new Customer_cardDiscountDto();

        public Customer_cardDiscountDto.Builder setCategoryNumber(String categoryNumber) {
            customerCardDiscountDto.cardNumber = categoryNumber;
            return this;
        }

        public Customer_cardDiscountDto.Builder setCategoryName(String categoryName) {
            customerCardDiscountDto.custSurname = categoryName;
            return this;
        }

        public Customer_cardDiscountDto.Builder setTaxAmount(BigDecimal discount) {
            customerCardDiscountDto.discount = discount;
            return this;
        }
        @Override
        public Customer_cardDiscountDto build() {
            return customerCardDiscountDto;
        }

    }
    public String getCustSurname() {
        return custSurname;
    }

    public void setCustSurname(String custSurname) {
        this.custSurname = custSurname;
    }

    public BigDecimal getTaxAmount() {
        return discount;
    }

    public void setTaxAmount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Customer_cardDiscountDto() { }
}
