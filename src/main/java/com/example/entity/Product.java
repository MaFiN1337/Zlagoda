package com.example.entity;

import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private String characteristics;
    private Category category;

    public Product() {

    }

    public static class Builder implements IBuilder<Product> {

        private Product product = new Product();

        public Product.Builder setId(int id) {
            product.id = id;
            return this;
        }

        public Product.Builder setName(String name) {
            product.name = name;
            return this;
        }

        public Product.Builder setCharacteristics(String characteristics) {
            product.characteristics = characteristics;
            return this;
        }

        public Product.Builder setCategory(Category category) {
            product.category = category;
            return this;
        }

        @Override
        public Product build() {
            return product;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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

        Product other = (Product) obj;

        return (!Objects.equals(name, other.name));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Product [id=").append(id).append(", name=").append(name)
                .append(", characteristics=").append(characteristics).append("]");
        return builder.toString();
    }
}

