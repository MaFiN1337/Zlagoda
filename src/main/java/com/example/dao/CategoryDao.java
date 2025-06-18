package com.example.dao;

import com.example.entity.Category;

import java.util.List;
public interface CategoryDao extends GenericDao<Category, Long>, AutoCloseable {
    List<Category> searchCategoriesByName(String categoryName);

    List<Category> searchCategoriesByNameAndSort(String categoryName);

    List<Category> searchCategoriesWithAllProductsInStore_product();

    void close();
}
