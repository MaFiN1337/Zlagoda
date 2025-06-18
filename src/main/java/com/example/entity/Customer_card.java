package com.example.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Customer_card {
    private String number;         // PK
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private String city;
    private String street;
    private String zip_code;
    private BigDecimal percent;


    public Customer_card() {

    }

    public static class Builder implements IBuilder<Customer_card> {

        private Customer_card customer_card = new Customer_card();

        public Customer_card.Builder setNumber(String number) {
            customer_card.number = number;
            return this;
        }

        public Customer_card.Builder setName(String name) {
            customer_card.name = name;
            return this;
        }

        public Customer_card.Builder setSurname(String surname) {
            customer_card.surname = surname;
            return this;
        }

        public Customer_card.Builder setPatronymic(String patronymic) {
            customer_card.patronymic = patronymic;
            return this;
        }

        public Customer_card.Builder setPhone(String phone) {
            customer_card.phone = phone;
            return this;
        }


        public Customer_card.Builder setCity(String city) {
            customer_card.city = city;
            return this;
        }

        public Customer_card.Builder setStreet(String street) {
            customer_card.street = street;
            return this;
        }

        public Customer_card.Builder setZip_code(String zip_code) {
            customer_card.zip_code = zip_code;
            return this;
        }

        public Customer_card.Builder setPercent(BigDecimal percent) {
            customer_card.percent = percent;
            return this;
        }

        @Override
        public Customer_card build() {
            return customer_card;
        }

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((zip_code == null) ? 0 : zip_code.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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

        Customer_card other = (Customer_card) obj;

        if (!Objects.equals(zip_code, other.zip_code)) {
            return false;
        }

        if (!Objects.equals(name, other.name)) {
            return false;
        }

        return (Objects.equals(phone, other.phone));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Customer_card [number=").append(number).append(", name=").append(name).append(", surname=").append(surname)
                .append(", patronymic=").append(patronymic).append(", phone=").append(phone)
                .append(", city=").append(city).append(", street=")
                .append(street).append(", zip_code=").append(zip_code)
                .append(", percent=").append(percent).append("]");
        return builder.toString();
    }
}





