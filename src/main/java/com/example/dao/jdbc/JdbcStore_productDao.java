//package com.example.dao.jdbc;
//
//import com.example.dao.Store_productDao;
//import com.example.entity.Category;
//import com.example.entity.Product;
//import com.example.exception.ServerException;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class JdbcStore_productDao implements Store_productDao {
//    private static final Logger LOGGER = LogManager.getLogger(JdbcCategoryDao.class);
//
//    private static final String GET_ALL = "SELECT * FROM `Product` ORDER BY product_name";
//    private static final String GET_BY_ID = "SELECT * FROM `Product` WHERE id_product=?";
//    private static final String CREATE = "INSERT INTO `Product` (product_name) VALUES (?)";
//    private static final String UPDATE = "UPDATE `Product` SET product_name=? WHERE id_product=?";
//    private static final String DELETE = "DELETE FROM `Product` WHERE id_product=?";
//    private static final String SEARCH_PRODUCT_BY_NAME = "SELECT * FROM `Product` WHERE LOWER(product_name) LIKE CONCAT('%', LOWER(?), '%')";
//    private static final String SEARCH_PRODUCT_BY_NAME_AND_SORTED = "SELECT * FROM `Product` WHERE LOWER(product_name) LIKE CONCAT('%', LOWER(?), '%') ORDER BY product_name";
//    private static final String SEARCH_PRODUCT_BY_CATEGORY_AND_SORTED_BY_NAME = "SELECT p.* FROM Product p JOIN Category c ON p.category_number = c.category_number WHERE LOWER(c.category_name) LIKE CONCAT('%', LOWER(?), '%') ORDER BY p.product_name";
//
//    private static final String ID = "UPC";
//    private static final String PRODUCT = "id_product";
//    private static final String UPC_PROM = "UPC_prom";
//    private static final String SELLING_PRICE = "selling_price";
//    private static final String PRODUCTS_NUMBER = "products_number";
//    private static final String PROMO_PRODUCT = "promotional_product";
//
//    private Connection connection;
//    private final boolean connectionShouldBeClosed;
//
//    public JdbcProductDao(Connection connection) {
//        this.connection = connection;
//        connectionShouldBeClosed = false;
//    }
//
//    public JdbcProductDao(Connection connection, boolean connectionShouldBeClosed) {
//        this.connection = connection;
//        this.connectionShouldBeClosed = connectionShouldBeClosed;
//    }
//
//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }
//
//    @Override
//    public List<Product> getAll() {
//        List<Product> categories = new ArrayList<>();
//
//        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
//            while (resultSet.next()) {
//                categories.add(extractStore_ProductFromResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            LOGGER.error("JdbcProductDao getAll SQL exception", e);
//            throw new ServerException(e);
//        }
//        return categories;
//    }
//
//    @Override
//    public Optional<Product> getById(Long id) {
//        Optional<Product> category = Optional.empty();
//        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
//            query.setLong(1, id);
//            ResultSet resultSet = query.executeQuery();
//            while (resultSet.next()) {
//                category = Optional.of(extractStore_ProductFromResultSet(resultSet));
//            }
//
//        } catch (SQLException e) {
//            LOGGER.error("JdbcProductDao getById SQL exception: " + id, e);
//            throw new ServerException(e);
//        }
//        return category;
//    }
//
//    @Override
//    public void create(Product product) {
//        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
//            query.setString(1, product.getName());
//            query.executeUpdate();
//
//            ResultSet keys = query.getGeneratedKeys();
//            if (keys.next()) {
//                product.setId(keys.getLong(1));
//            }
//        } catch (SQLException e) {
//            LOGGER.error("JdbcProductDao create SQL exception", e);
//            throw new ServerException(e);
//        }
//    }
//
//    @Override
//    public void update(Product product) {
//        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
//            query.setString(1, product.getName());
//            query.setLong(2, product.getId());
//            query.executeUpdate();
//        } catch (SQLException e) {
//            LOGGER.error("JdbcProductDao update SQL exception: " + product.getId(), e);
//            throw new ServerException(e);
//        }
//    }
//
//    @Override
//    public void delete(Long id) {
//        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
//            query.setLong(1, id);
//            query.executeUpdate();
//        } catch (SQLException e) {
//            LOGGER.error("JdbcProductDao delete SQL exception: " + id, e);
//            throw new ServerException(e);
//        }
//    }
//
//    @Override
//    public List<Product> searchProductByName(String productName) {
//        List<Product> products = new ArrayList<>();
//
//        try (PreparedStatement query = connection.prepareStatement(SEARCH_PRODUCT_BY_NAME)) {
//            query.setString(1, productName);
//            ResultSet resultSet = query.executeQuery();
//            while (resultSet.next()) {
//                products.add(extractStore_ProductFromResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            LOGGER.error("JdbcProductDao searchProductByName SQL exception: " + productName, e);
//            throw new ServerException(e);
//        }
//        return products;
//    }
//
//    @Override
//    public List<Product> searchProductByCategoryAndSortedByName(Category category) {
//        List<Product> products = new ArrayList<>();
//
//        try (PreparedStatement query = connection.prepareStatement(SEARCH_PRODUCT_BY_CATEGORY_AND_SORTED_BY_NAME)) {
//            query.setString(1, category.getName());
//            ResultSet resultSet = query.executeQuery();
//            while (resultSet.next()) {
//                products.add(extractStore_ProductFromResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            LOGGER.error("JdbcProductDao searchProductByName SQL exception: " + category.getName(), e);
//            throw new ServerException(e);
//        }
//        return products;
//    }
//
//    @Override
//    public List<Product> searchProductByNameAndSorted(String name){
//        List<Product> products = new ArrayList<>();
//
//        try (PreparedStatement query = connection.prepareStatement(SEARCH_PRODUCT_BY_NAME_AND_SORTED)) {
//            query.setString(1, name);
//            ResultSet resultSet = query.executeQuery();
//            while (resultSet.next()) {
//                products.add(extractStore_ProductFromResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            LOGGER.error("JdbcProductDao searchProductByName SQL exception: " + name, e);
//            throw new ServerException(e);
//        }
//        return products;
//    }
//
//    @Override
//    public void close() {
//        if (connectionShouldBeClosed) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                LOGGER.error("JdbcProductDao Connection can't be closed", e);
//                throw new ServerException(e);
//            }
//        }
//    }
//
//    protected static Product extractStore_ProductFromResultSet(ResultSet resultSet) throws SQLException {
//        return new Product.Builder().setId(resultSet.getLong(ID)).setName(resultSet.getString(NAME)).build();
//    }
//
//}
