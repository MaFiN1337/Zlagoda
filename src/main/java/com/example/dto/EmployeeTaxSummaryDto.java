package com.example.dto;

import com.example.entity.IBuilder;

import java.math.BigDecimal;

public class EmployeeTaxSummaryDto {

    private BigDecimal categoryNumber;
    private String categoryName;
    private BigDecimal taxAmount;


    public static class Builder implements IBuilder<EmployeeTaxSummaryDto> {

        private final EmployeeTaxSummaryDto taxSummaryDto = new EmployeeTaxSummaryDto();

        public EmployeeTaxSummaryDto.Builder setCategoryNumber(BigDecimal categoryNumber) {
            taxSummaryDto.categoryNumber = categoryNumber;
            return this;
        }

        public EmployeeTaxSummaryDto.Builder setCategoryName(String categoryName) {
            taxSummaryDto.categoryName = categoryName;
            return this;
        }

        public EmployeeTaxSummaryDto.Builder setTaxAmount(BigDecimal taxAmount) {
            taxSummaryDto.taxAmount = taxAmount;
            return this;
        }
        @Override
        public EmployeeTaxSummaryDto build() {
            return taxSummaryDto;
        }

    }
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(BigDecimal categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public EmployeeTaxSummaryDto() { }
}
