package com.example.dao;

import com.example.dto.Store_productWithProductDto;
import com.example.entity.Store_product;

import java.util.List;
import java.util.Optional;

public interface Store_productDao extends GenericDao<Store_product, String>, AutoCloseable{

    Optional<Store_productWithProductDto> searchStoreProductDetailsByUPC(String upc);

    List<Store_product> searchStore_productSortedByProductsNum();

    List<Store_product> searchStore_productSortedByName();

    List<Store_product> searchPromotionalStore_productSortedByName();

    List<Store_product> searchPromotionalStore_productSortedByProductsNum();

    List<Store_product> searchNonPromotionalStore_productSortedByName();

    List<Store_product> searchNonPromotionalStore_productSortedByProductsNum();

    void close();

}
