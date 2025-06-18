package com.example.dto;

import com.example.entity.IBuilder;

import java.math.BigDecimal;

public class Customer_cardDiscountDto {

    private BigDecimal categoryNumber;
    private String categoryName;
    private BigDecimal discount;


    public static class Builder implements IBuilder<Customer_cardDiscountDto> {

        private final Customer_cardDiscountDto customerCardDiscountDto = new Customer_cardDiscountDto();

        public Customer_cardDiscountDto.Builder setCategoryNumber(BigDecimal categoryNumber) {
            customerCardDiscountDto.categoryNumber = categoryNumber;
            return this;
        }

        public Customer_cardDiscountDto.Builder setCategoryName(String categoryName) {
            customerCardDiscountDto.categoryName = categoryName;
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
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getTaxAmount() {
        return discount;
    }

    public void setTaxAmount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(BigDecimal categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public Customer_cardDiscountDto() { }
}
