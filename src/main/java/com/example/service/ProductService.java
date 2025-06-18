package com.example.service;

import com.example.dao.DaoFactory;
import com.example.dao.ProductDao;
import com.example.entity.Product;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private static final Logger LOGGER = LogManager.getLogger(ProductService.class);

    static final String GET_ALL_PRODUCTS = "Get all products";
    static final String GET_PRODUCTS_BY_ID = "Get products by id: %d";
    static final String CREATE_PRODUCT = "Create product: %s";
    static final String UPDATE_PRODUCT = "Update product: %d";
    static final String DELETE_PRODUCT = "Delete product: %d";
    static final String SEARCH_PRODUCTS_BY_NAME = "Search products by name: %s";
    static final String SEARCH_PRODUCTS_BY_NAME_AND_SORT = "Search products by name and sort: %s";
    static final String SEARCH_PRODUCTS_BY_CATEGORY_SORTED_BY_NAME = "Search products by category and sort by name: %s";

    private final DaoFactory daoFactory;

    ProductService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final ProductService INSTANCE = new ProductService(DaoFactory.getDaoFactory());
    }

    public static ProductService getInstance() {
        return ProductService.Holder.INSTANCE;
    }

    public List<Product> getAllProducts() {
        LOGGER.info(GET_ALL_PRODUCTS);
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.getAll();
        }
    }

    public Optional<Product> getProductById(Long productId) {
        LOGGER.info(String.format(GET_PRODUCTS_BY_ID, productId));
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.getById(productId);
        }
    }

    public void createProduct(Product product) {
        LOGGER.info(String.format(CREATE_PRODUCT, product.getName()));
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.create(product);
        }
    }

    public void updateProduct(Product product) {
        LOGGER.info(String.format(UPDATE_PRODUCT, product.getId()));
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.update(product);
        }
    }

    public void deleteProduct(Long productId) {
        LOGGER.info(String.format(DELETE_PRODUCT, productId));
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.delete(productId);
        }
    }

    public List<Product> searchProductsByName(String productName) {
        LOGGER.info(String.format(SEARCH_PRODUCTS_BY_NAME, productName));
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.searchProductByName(productName);
        }
    }

    public List<Product> searchProductsByNameAndSort(String productName) {
        LOGGER.info(String.format(SEARCH_PRODUCTS_BY_NAME_AND_SORT, productName));
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.searchProductByNameAndSorted(productName);
        }
    }

    public List<Product> searchProductsByCategoryAndSortByName(String categoryName) {
        LOGGER.info(String.format(SEARCH_PRODUCTS_BY_CATEGORY_SORTED_BY_NAME, categoryName));
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.searchProductByCategoryAndSortedByName(categoryName);
        }
    }

}
