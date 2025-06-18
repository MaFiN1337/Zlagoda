package com.example.service;

import com.example.dao.CategoryDao;
import com.example.dao.CheckDao;
import com.example.dao.DaoFactory;
import com.example.entity.Category;
import com.example.entity.Check;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CheckService {
    private static final Logger LOGGER = LogManager.getLogger(CheckService.class);

    static final String GET_ALL_CHECKS = "Get all checks";
    static final String GET_CHECK_BY_ID = "Get check by id: %s";
    static final String CREATE_CHECK = "Create check: %s";
    static final String UPDATE_CHECK = "Update check: %s";
    static final String DELETE_CHECK = "Delete check: %s";
    static final String SEARCH_CATEGORIES_BY_NAME = "Search categories by name: %s";
    static final String SEARCH_CATEGORIES_BY_NAME_AND_SORT = "Search categories by name and sort: %s";
    static final String SEARCH_CATEGORIES_WITH_ALL_PRODUCTS_IN_STORE_PRODUCT = "Search categories with all products being present in store_product";

    private final DaoFactory daoFactory;

    CheckService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final CheckService INSTANCE = new CheckService(DaoFactory.getDaoFactory());
    }

    public static CheckService getInstance() {
        return CheckService.Holder.INSTANCE;
    }

    public List<Check> getAllChecks() {
        LOGGER.info(GET_ALL_CHECKS);
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.getAll();
        }
    }

    public Optional<Check> getCheckById(String checkNumber) {
        LOGGER.info(String.format(GET_CHECK_BY_ID, checkNumber));
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.getById(checkNumber);
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
}
