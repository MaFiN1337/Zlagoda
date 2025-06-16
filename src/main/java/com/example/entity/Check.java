package com.example.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Check {
    private String number;
    private LocalDateTime print_date;
    private BigDecimal sum_total;
    private BigDecimal vat;
    private Employee employee;
    private Customer_card customer_card;

    private List<Store_product> storeProducts = new ArrayList<>();

    public Check() {

    }

    public Store_product addStoreProduct(Store_product storeProduct){
        storeProducts.add(storeProduct);
        return storeProduct;
    }

    public static class Builder implements IBuilder<Check> {

        private Check check = new Check();

        public Check.Builder setNumber(String number) {
            check.number = number;
            return this;
        }

        public Check.Builder setPrint_date(LocalDateTime print_date) {
            check.print_date = print_date;
            return this;
        }

        public Check.Builder setSum_total(BigDecimal sum_total) {
            check.sum_total = sum_total;
            return this;
        }

        public Check.Builder setVat(BigDecimal vat) {
            check.vat = vat;
            return this;
        }

        public Check.Builder setEmployee(Employee employee) {
            check.employee = employee;
            return this;
        }

        public Check.Builder setCustomer_card(Customer_card customer_card) {
            check.customer_card = customer_card;
            return this;
        }

        public Check.Builder setStoreProducts(List<Store_product> storeProducts){
            check.storeProducts = new ArrayList<>();
            check.storeProducts.addAll(storeProducts);
            return this;
        }

        @Override
        public Check build() {
            return check;
        }

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String id) {
        this.number = number;
    }

    public LocalDateTime getPrint_date() {
        return print_date;
    }

    public void setPrint_date(LocalDateTime print_date) {
        this.print_date = print_date;
    }

    public BigDecimal getSum_total() {
        return sum_total;
    }

    public void setSum_total(BigDecimal sum_total) {
        this.sum_total = sum_total;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer_card getCustomer_card() {
        return customer_card;
    }

    public void setCustomer_card(Customer_card customer_card) {
        this.customer_card = customer_card;
    }

    public List<Store_product> getStoreProducts() {
        return storeProducts;
    }

    public void setStoreProducts(List<Store_product> storeProducts) {
        this.storeProducts = new ArrayList<>();
        this.storeProducts.addAll(storeProducts);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        Check other = (Check) obj;

        return (Objects.equals(number, other.number));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Check [number=").append(number).append(", print_date=").append(print_date)
                .append(", sum_total=").append(sum_total).append(", vat=").append(vat)
                .append(", employee=").append(employee).append(", customer_card=").append(customer_card)
                .append(", store_products=").append(storeProducts).append("]");
        return builder.toString();
    }
}


