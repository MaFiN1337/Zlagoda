package com.example.service;

import com.example.dao.DaoFactory;
import com.example.dao.Store_productDao;
import com.example.dto.Store_productWithProductDto;
import com.example.entity.Store_product;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class Store_productService {
    private static final Logger LOGGER = LogManager.getLogger(Store_productService.class);

    static final String GET_ALL_STORE_PRODUCTS = "Get all store products";
    static final String GET_STORE_PRODUCT_BY_ID = "Get store product by id: %s";
    static final String CREATE_STORE_PRODUCT = "Create store product: %s";
    static final String UPDATE_STORE_PRODUCT = "Update store product: %s";
    static final String DELETE_STORE_PRODUCT = "Delete store product: %s";
    static final String SEARCH_STORE_PRODUCTS_SORTED_BY_PRODUCTS_NUM = "Search store products sorted by products number";
    static final String SEARCH_STORE_PRODUCTS_SORTED_BY_NAME = "Search store products sorted by product name";
    static final String SEARCH_PROMO_STORE_PRODUCTS_SORTED_BY_PRODUCTS_NUM = "Search promotional store products sorted by products number";
    static final String SEARCH_PROMO_STORE_PRODUCTS_SORTED_BY_NAME = "Search promotional store products sorted by products name";
    static final String SEARCH_NON_PROMO_STORE_PRODUCTS_SORTED_BY_PRODUCTS_NUM = "Search non-promotional store products sorted by products number";
    static final String SEARCH_NON_PROMO_STORE_PRODUCTS_SORTED_BY_NAME = "Search non-promotional store products sorted by products name";
    static final String SEARCH_STORE_PRODUCT_DETAILS_BY_UPC = "Search store product details by UPC: %s ";

    private final DaoFactory daoFactory;

    Store_productService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final Store_productService INSTANCE = new Store_productService(DaoFactory.getDaoFactory());
    }

    public static Store_productService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Store_product> getAllStore_products() {
        LOGGER.info(GET_ALL_STORE_PRODUCTS);
        try (Store_productDao storeProductDao = daoFactory.createStore_productDao()) {
            return storeProductDao.getAll();
        }
    }

    public Optional<Store_product> getStore_productById(String UPC) {
        LOGGER.info(String.format(GET_STORE_PRODUCT_BY_ID, UPC));
        try (Store_productDao storeProductDao = daoFactory.createStore_productDao()) {
            return storeProductDao.getById(UPC);
        }
    }

    public void createStore_product(Store_product storeProduct) {
        LOGGER.info(String.format(CREATE_STORE_PRODUCT, storeProduct.getUpc()));
        try (Store_productDao storeProductDao = daoFactory.createStore_productDao()) {
            storeProductDao.create(storeProduct);
        }
    }

    public void updateStore_product(Store_product storeProduct) {
        LOGGER.info(String.format(UPDATE_STORE_PRODUCT, storeProduct.getUpc()));
        try (Store_productDao storeProductDao = daoFactory.createStore_productDao()) {
            storeProductDao.update(storeProduct);
        }
    }

    public void deleteStore_product(String store_productNumber) {
        LOGGER.info(String.format(DELETE_STORE_PRODUCT, store_productNumber));
        try (Store_productDao storeProductDao = daoFactory.createStore_productDao()) {
            storeProductDao.delete(store_productNumber);
        }
    }

    public List<Store_product> searchStore_productSortedByProductsNum(){
        LOGGER.info(SEARCH_STORE_PRODUCTS_SORTED_BY_PRODUCTS_NUM);
        try (Store_productDao storeProductDao = daoFactory.createStore_productDao()) {
            return storeProductDao.searchStore_productSortedByProductsNum();
        }
    }
    public List<Store_product> searchStore_productSortedByName(){
        LOGGER.info(SEARCH_STORE_PRODUCTS_SORTED_BY_NAME);
        try (Store_productDao storeProductDao = daoFactory.createStore_productDao()) {
            return storeProductDao.searchStore_productSortedByName();
        }
    }
    public List<Store_product> searchPromotionalStore_productSortedByName(){
        LOGGER.info(SEARCH_PROMO_STORE_PRODUCTS_SORTED_BY_NAME);
        try (Store_productDao storeProductDao = daoFactory.createStore_productDao()) {
            return storeProductDao.searchPromotionalStore_productSortedByName();
        }
    }
    public List<Store_product> searchPromotionalStore_productSortedByProductsNum(){
        LOGGER.info(SEARCH_PROMO_STORE_PRODUCTS_SORTED_BY_PRODUCTS_NUM);
        try (Store_productDao storeProductDao = daoFactory.createStore_productDao()) {
            return storeProductDao.searchPromotionalStore_productSortedByProductsNum();
        }
    }
    public List<Store_product> searchNonPromotionalStore_productSortedByName(){
        LOGGER.info(SEARCH_NON_PROMO_STORE_PRODUCTS_SORTED_BY_NAME);
        try (Store_productDao storeProductDao = daoFactory.createStore_productDao()) {
            return storeProductDao.searchNonPromotionalStore_productSortedByName();
        }
    }
    public List<Store_product> searchNonPromotionalStore_productSortedByProductsNum(){
        LOGGER.info(SEARCH_NON_PROMO_STORE_PRODUCTS_SORTED_BY_PRODUCTS_NUM);
        try (Store_productDao storeProductDao = daoFactory.createStore_productDao()) {
            return storeProductDao.searchNonPromotionalStore_productSortedByProductsNum();
        }
    }
    public Optional<Store_productWithProductDto> searchStoreProductDetailsByUPC(String upc){
        LOGGER.info(String.format(SEARCH_STORE_PRODUCT_DETAILS_BY_UPC, upc));
        try (Store_productDao storeProductDao = daoFactory.createStore_productDao()) {
            return storeProductDao.searchStoreProductDetailsByUPC(upc);
        }
    }
}
