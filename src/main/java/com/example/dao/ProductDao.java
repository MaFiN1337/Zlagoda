package com.example.dao;

import com.example.entity.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product, Long>, AutoCloseable {
    List<Product> searchProductByName(String name);

    List<Product> searchProductByNameAndSorted(String name);

    List<Product> searchProductByCategoryAndSortedByName(String categoryName);

    void close();
}
