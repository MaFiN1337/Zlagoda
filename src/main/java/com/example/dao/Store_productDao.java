package com.example.dao;

import com.example.entity.Product;
import com.example.entity.Store_product;

import java.util.List;
import java.util.Optional;

public interface Store_productDao extends GenericDao<Store_product, String>, AutoCloseable{

    Optional<Product> searchProductDetailsByUPC(String upc);

    Optional<Store_product> searchStoreProductWithProductByUPC(String upc);

    List<Store_product> searchStore_productSortedByProductsNum();

    List<Store_product> searchStore_productSortedByName();

    List<Store_product> searchPromotionalStore_productSortedByName();

    List<Store_product> searchPromotionalStore_productSortedByProductsNum();

    List<Store_product> searchNonPromotionalStore_productSortedByName();

    List<Store_product> searchNonPromotionalStore_productSortedByProductsNum();

    Optional<Store_product> searchByUPC(String UPC);



}
