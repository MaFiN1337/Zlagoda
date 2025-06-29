package com.example.service;

import com.example.dao.CategoryDao;
import com.example.dao.DaoFactory;
import com.example.entity.Category;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CategoryService {
    private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);

    static final String GET_ALL_CATEGORIES = "Get all categories";
    static final String GET_CATEGORY_BY_ID = "Get category by id: %d";
    static final String CREATE_CATEGORY = "Create category: %s";
    static final String UPDATE_CATEGORY = "Update category: %d";
    static final String DELETE_CATEGORY = "Delete category: %d";
    static final String SEARCH_CATEGORIES_BY_NAME = "Search categories by name: %s";
    static final String SEARCH_CATEGORIES_BY_NAME_AND_SORT = "Search categories by name and sort: %s";
    static final String SEARCH_CATEGORIES_WITH_ALL_PRODUCTS_IN_STORE_PRODUCT = "Search categories with all products being present in store_product";

    private final DaoFactory daoFactory;

    CategoryService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final CategoryService INSTANCE = new CategoryService(DaoFactory.getDaoFactory());
    }

    public static CategoryService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Category> getAllCategories() {
        LOGGER.info(GET_ALL_CATEGORIES);
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()) {
            return categoryDao.getAll();
        }
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        LOGGER.info(String.format(GET_CATEGORY_BY_ID, categoryId));
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()) {
            return categoryDao.getById(categoryId);
        }
    }

    public void createCategory(Category category) {
        LOGGER.info(String.format(CREATE_CATEGORY, category.getName()));
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()) {
            categoryDao.create(category);
        }
    }

    public void updateCategory(Category category) {
        LOGGER.info(String.format(UPDATE_CATEGORY, category.getId()));
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()) {
            categoryDao.update(category);
        }
    }

    public void deleteCategory(Long categoryId) {
        LOGGER.info(String.format(DELETE_CATEGORY, categoryId));
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()) {
            categoryDao.delete(categoryId);
        }
    }

    public List<Category> searchCategoriesByName(String categoryName) {
        LOGGER.info(String.format(SEARCH_CATEGORIES_BY_NAME, categoryName));
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()) {
            return categoryDao.searchCategoriesByName(categoryName);
        }
    }

    public List<Category> searchCategoriesByNameAndSort(String categoryName){
        LOGGER.info(String.format(SEARCH_CATEGORIES_BY_NAME_AND_SORT, categoryName));
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()){
            return categoryDao.searchCategoriesByNameAndSort(categoryName);
        }
    }

    public List<Category> searchCategoriesWithAllProductsInStore_product(){
        LOGGER.info(SEARCH_CATEGORIES_WITH_ALL_PRODUCTS_IN_STORE_PRODUCT);
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()){
            return categoryDao.searchCategoriesWithAllProductsInStore_product();
        }
    }
}
