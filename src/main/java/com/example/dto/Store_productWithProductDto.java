package com.example.dto;

import com.example.entity.IBuilder;

import java.math.BigDecimal;

public class Store_productWithProductDto {

    private String characteristics;
    private BigDecimal selling_price;
    private Long products_number;
    private String product_name;

    public Store_productWithProductDto() { }

    public static class Builder implements IBuilder<Store_productWithProductDto> {

        private final Store_productWithProductDto storeProductWithProductDto = new Store_productWithProductDto();

        public Store_productWithProductDto.Builder setCharacteristics(String characteristics) {
            storeProductWithProductDto.characteristics = characteristics;
            return this;
        }

        public Store_productWithProductDto.Builder setSellingPrice(BigDecimal selling_price) {
            storeProductWithProductDto.selling_price = selling_price;
            return this;
        }

        public Store_productWithProductDto.Builder setProductsNumber(Long products_number) {
            storeProductWithProductDto.products_number = products_number;
            return this;
        }

        public Store_productWithProductDto.Builder setProductName(String product_name) {
            storeProductWithProductDto.product_name = product_name;
            return this;
        }
        @Override
        public Store_productWithProductDto build() {
            return storeProductWithProductDto;
        }

    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public BigDecimal getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(BigDecimal selling_price) {
        this.selling_price = selling_price;
    }

    public Long getProducts_number() {
        return products_number;
    }

    public void setProducts_number(Long products_number) {
        this.products_number = products_number;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
