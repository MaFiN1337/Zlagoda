package com.example.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Store_product {
    private String upc;
    private BigDecimal selling_price;
    private Long products_number;
    private boolean promotional_product;
    private Store_product upc_prom;
    private Product product;

    public Store_product() { }

    public static class Builder implements IBuilder<Store_product> {

        private final Store_product store_product = new Store_product();

        public Store_product.Builder setUpc(String upc) {
            store_product.upc = upc;
            return this;
        }

        public Store_product.Builder setUpc_prom(Store_product upc_prom) {
            store_product.upc_prom = upc_prom;
            return this;
        }

        public Store_product.Builder setSelling_price(BigDecimal selling_price) {
            store_product.selling_price = selling_price;
            return this;
        }

        public Store_product.Builder setProducts_number(Long products_number) {
            store_product.products_number = products_number;
            return this;
        }

        public Store_product.Builder setPromotional_product(boolean promotional_product) {
            store_product.promotional_product = promotional_product;
            return this;
        }

        public Store_product.Builder setProduct(Product product) {
            store_product.product = product;
            return this;
        }

        @Override
        public Store_product build() {
            return store_product;
        }
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Store_product getUpc_prom() {
        return upc_prom;
    }

    public void setUpc_prom(Store_product upc_prom) {
        this.upc_prom = upc_prom;
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

    public boolean getPromotional_product() {
        return promotional_product;
    }

    public void setPromotional_product(boolean promotional_product) {
        this.promotional_product = promotional_product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((upc == null) ? 0 : upc.hashCode());
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

        Store_product other = (Store_product) obj;

        return (Objects.equals(upc, other.upc));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Store_Product [upc=").append(upc).append(", upc_prom=").append(upc_prom)
                .append(", selling_price=").append(selling_price)
                .append(", products_number=").append(products_number).append(", promotional_product=").append(promotional_product).append("]");
        return builder.toString();
    }
}