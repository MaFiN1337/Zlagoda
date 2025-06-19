package com.example.dao.jdbc;

import com.example.dao.Store_productDao;
import com.example.dto.Store_productWithProductDto;
import com.example.entity.Store_product;
import com.example.exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcStore_productDao implements Store_productDao {
    private static final Logger LOGGER = LogManager.getLogger(JdbcStore_productDao.class);

    private static final String GET_ALL = "SELECT sp.*, p.id_product FROM `Store_product` sp" +
            "JOIN `Product` p ON sp.id_product = p.id_product" +
            "ORDER BY product_name";
    private static final String GET_BY_ID = "SELECT sp.*, p.id_product FROM `Store_product` " +
            "JOIN `Product` p ON sp.id_product = p.id_product" +
            "WHERE sp.UPC=?";
    private static final String CREATE =
            "INSERT INTO `Store_product` (id_product, selling_price, products_number, promotional_product) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE `Store_product` SET products_number=?, selling_price=? WHERE UPC=?";
    private static final String DELETE = "DELETE FROM `Store_product` WHERE UPC=?";
    private static final String SEARCH_STORE_PRODUCT_SORTED_BY_PRODUCTS_NUM =
            "SELECT * FROM Store_Product" +
            "ORDER BY products_number";
    private static final String SEARCH_STORE_PRODUCTS_SORTED_BY_NAME =
            "SELECT sp.*, p.product_name FROM Store_Product sp" +
            "JOIN Product p ON sp.id_product = p.id_product" +
            "ORDER BY p.product_name";
    private static final String SEARCH_PROMO_STORE_PRODUCTS_SORTED_BY_PRODUCTS_NUM =
            "SELECT sp.*, p.product_name FROM Store_Product sp" +
            "JOIN Product p ON sp.id_product = p.id_product" +
            "WHERE sp.promotional_product = TRUE" +
            "ORDER BY sp.products_number;";
    private static final String SEARCH_PROMO_STORE_PRODUCTS_SORTED_BY_NAME =
            "SELECT sp.*, p.product_name FROM Store_Product sp" +
            "JOIN Product p ON sp.id_product = p.id_product" +
            "WHERE sp.promotional_product = TRUE" +
            "ORDER BY p.product_name";
    private static final String SEARCH_NON_PROMO_STORE_PRODUCTS_SORTED_BY_PRODUCTS_NUM =
            "SELECT sp.*, p.product_name FROM Store_Product sp" +
            "JOIN Product p ON sp.id_product = p.id_product" +
            "WHERE sp.promotional_product = FALSE OR sp.promotional_product IS NULL" +
            "ORDER BY sp.products_number";
    private static final String SEARCH_NON_PROMO_STORE_PRODUCTS_SORTED_BY_NAME =
            "SELECT sp.*, p.product_name FROM Store_Product sp" +
            "JOIN Product p ON sp.id_product = p.id_product" +
            "WHERE sp.promotional_product = FALSE OR sp.promotional_product IS NULL" +
            "ORDER BY p.product_name";
    private static final String SEARCH_STORE_PRODUCT_DETAILS_BY_UPC =
            "SELECT sp.selling_price, sp.products_number, p.product_name, p.characteristics" +
            "FROM Store_Product sp" +
            "JOIN Product p ON sp.id_product = p.id_product" +
            "WHERE sp.UPC = ?";

    private static final String UPC = "UPC";
    private static final String PRODUCT = "id_product";
    private static final String UPC_PROM = "UPC_prom";
    private static final String SELLING_PRICE = "selling_price";
    private static final String PRODUCTS_NUMBER = "products_number";
    private static final String PROMO_PRODUCT = "promotional_product";
    private static final String PRODUCT_NAME = "product_name";
    private static final String CHARACTERISTICS = "characteristics";

    private Connection connection;
    private final boolean connectionShouldBeClosed;

    public JdbcStore_productDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcStore_productDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Store_product> getAll() {
        List<Store_product> storeProducts = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                storeProducts.add(extractStore_ProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStore_productDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return storeProducts;
    }

    @Override
    public Optional<Store_product> getById(String UPC) {
        Optional<Store_product> storeProduct = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, UPC);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                storeProduct = Optional.of(extractStore_ProductFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcStore_productDao getById SQL exception: " + UPC, e);
            throw new ServerException(e);
        }
        return storeProduct;
    }

    @Override
    public void create(Store_product storeProduct) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            query.setLong(1, storeProduct.getProduct().getId());
            query.setLong(2, storeProduct.getProducts_number());
            query.setBigDecimal(3, storeProduct.getSelling_price());
            query.setBoolean(4, storeProduct.getPromotional_product());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                storeProduct.setUpc(keys.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStore_productDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Store_product storeProduct) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setLong(1, storeProduct.getProducts_number());
            query.setBigDecimal(2, storeProduct.getSelling_price());
            query.setString(3, storeProduct.getUpc());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcStore_productDao update SQL exception: " + storeProduct.getUpc(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(String UPC) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setString(1, UPC);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcStore_productDao delete SQL exception: " + UPC, e);
            throw new ServerException(e);
        }
    }

    @Override
    public List<Store_product> searchStore_productSortedByProductsNum() {
        List<Store_product> storeProducts = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SEARCH_STORE_PRODUCT_SORTED_BY_PRODUCTS_NUM)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                storeProducts.add(extractStore_ProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStore_productDao searchStore_productSortedByProductsNum SQL exception", e);
            throw new ServerException(e);
        }
        return storeProducts;
    }

    @Override
    public List<Store_product> searchStore_productSortedByName() {
        List<Store_product> storeProducts = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(SEARCH_STORE_PRODUCTS_SORTED_BY_NAME)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                storeProducts.add(extractStore_ProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStore_productDao searchStore_productSortedByName SQL exception", e);
            throw new ServerException(e);
        }
        return storeProducts;
    }

    @Override
    public List<Store_product> searchPromotionalStore_productSortedByName(){
        List<Store_product> storeProducts = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(SEARCH_PROMO_STORE_PRODUCTS_SORTED_BY_NAME)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                storeProducts.add(extractStore_ProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStore_productDao searchPromotionalStore_productSortedByName SQL exception", e);
            throw new ServerException(e);
        }
        return storeProducts;
    }

    @Override
    public List<Store_product> searchPromotionalStore_productSortedByProductsNum(){
        List<Store_product> storeProducts = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(SEARCH_PROMO_STORE_PRODUCTS_SORTED_BY_PRODUCTS_NUM)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                storeProducts.add(extractStore_ProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStore_productDao searchPromotionalStore_productSortedByProductsNum SQL exception", e);
            throw new ServerException(e);
        }
        return storeProducts;
    }

    @Override
    public List<Store_product> searchNonPromotionalStore_productSortedByName(){
        List<Store_product> storeProducts = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(SEARCH_NON_PROMO_STORE_PRODUCTS_SORTED_BY_NAME)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                storeProducts.add(extractStore_ProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStore_productDao searchNonPromotionalStore_productSortedByName SQL exception", e);
            throw new ServerException(e);
        }
        return storeProducts;
    }

    @Override
    public List<Store_product> searchNonPromotionalStore_productSortedByProductsNum(){
        List<Store_product> storeProducts = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(SEARCH_NON_PROMO_STORE_PRODUCTS_SORTED_BY_PRODUCTS_NUM)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                storeProducts.add(extractStore_ProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStore_productDao searchNonPromotionalStore_productSortedByProductsNum SQL exception", e);
            throw new ServerException(e);
        }
        return storeProducts;
    }

    @Override
    public Optional<Store_productWithProductDto> searchStoreProductDetailsByUPC(String UPC){
        Optional<Store_productWithProductDto> storeProductWithProductDto = Optional.empty();

        try (PreparedStatement query = connection.prepareStatement(SEARCH_STORE_PRODUCT_DETAILS_BY_UPC)) {
            query.setString(1, UPC);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                storeProductWithProductDto = Optional.of(extractStore_productDetailsFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStore_productDao searchStoreProductDetailsByUPC SQL exception: " + UPC, e);
            throw new ServerException(e);
        }
        return storeProductWithProductDto;
    }
    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcStore_productDao Connection can't be closed", e);
                throw new ServerException(e);
            }
        }
    }

    protected static Store_product extractStore_ProductFromResultSet(ResultSet resultSet) throws SQLException {
        return new Store_product.Builder().setUpc(resultSet.getString(UPC))
                .setProducts_number(resultSet.getLong(PRODUCTS_NUMBER))
                .setSelling_price(resultSet.getBigDecimal(SELLING_PRICE))
                .setUpc_prom(null)
                .setPromotional_product(resultSet.getBoolean(PROMO_PRODUCT))
                .setProduct(JdbcProductDao.extractProductFromResultSet(resultSet)).build();
    }

    protected static Store_productWithProductDto extractStore_productDetailsFromResultSet(ResultSet resultSet) throws SQLException {
        return new Store_productWithProductDto.Builder().setProductsNumber(resultSet.getLong(PRODUCTS_NUMBER))
                .setSellingPrice(resultSet.getBigDecimal(SELLING_PRICE)).setProductName(resultSet.getString(PRODUCT_NAME))
                .setCharacteristics(resultSet.getString(CHARACTERISTICS)).build();
    }
}
