package com.example.dto;

import com.example.entity.IBuilder;

import java.math.BigDecimal;

public class CheckSumDto {

    private BigDecimal sum;

    public CheckSumDto(){ }

    public static class Builder implements IBuilder<CheckSumDto> {

        private final CheckSumDto checkSumDto = new CheckSumDto();

        public CheckSumDto.Builder setSum(BigDecimal sum) {
            checkSumDto.sum = sum;
            return this;
        }

        @Override
        public CheckSumDto build() {
            return checkSumDto;
        }

    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
