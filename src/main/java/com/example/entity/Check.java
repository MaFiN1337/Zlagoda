package com.example.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Check {
    private String number;
    private LocalDateTime print_date;
    private BigDecimal sum_total;
    private BigDecimal vat;

    public Check() {

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
        builder.append("Check [number=").append(number).append(", print date=").append(print_date)
                .append(", sum total=").append(sum_total).append(", vat=").append(vat).append("]");
        return builder.toString();
    }
}


