package com.example.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Employee {

    private String id;
    private String name;
    private String surname;
    private String patronymic;
    private Role role;
    private BigDecimal salary;
    private LocalDate date_of_birth;
    private LocalDate date_of_start;
    private String phone;
    private String city;
    private String street;
    private String zip_code;


    public Employee() {

    }

    public static class Builder implements IBuilder<Employee> {

        private Employee employee = new Employee();

        public Builder setId(String id) {
            employee.id = id;
            return this;
        }

        public Builder setName(String name) {
            employee.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            employee.surname = surname;
            return this;
        }

        public Builder setPatronymic(String patronymic) {
            employee.patronymic = patronymic;
            return this;
        }

        public Builder setRole(Role role) {
            employee.role = role;
            return this;
        }

        public Builder setSalary(BigDecimal salary) {
            employee.salary = salary;
            return this;
        }

        public Builder setDate_of_birth(LocalDate date_of_birth) {
            employee.date_of_birth = date_of_birth;
            return this;
        }

        public Builder setDate_of_start(LocalDate date_of_start) {
            employee.date_of_start = date_of_start;
            return this;
        }

        public Builder setPhone(String phone) {
            employee.phone = phone;
            return this;
        }


        public Builder setCity(String city) {
            employee.city = city;
            return this;
        }

        public Builder setStreet(String street) {
            employee.street = street;
            return this;
        }

        public Builder setZip_code(String zip_code) {
            employee.zip_code = zip_code;
            return this;
        }

        @Override
        public Employee build() {
            return employee;
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public LocalDate getDate_of_start() {
        return date_of_start;
    }

    public void setDate_of_start(LocalDate date_of_start) {
        this.date_of_start = date_of_start;
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

        Employee other = (Employee) obj;

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
        builder.append("Employee [id=").append(id).append(", name=").append(name).append(", surname=").append(surname)
                .append(", patronymic=").append(patronymic).append(", role=").append(role).append(", salary=").append(salary)
                .append(", date_of_birth=").append(date_of_birth).append(", date_of_start=").append(date_of_start)
                .append(", phone=").append(phone).append(", city=").append(city)
                .append(", street=").append(street).append(", zip_code=").append(zip_code).append("]");
        return builder.toString();
    }
}
