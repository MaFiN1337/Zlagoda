package com.example.dao.jdbc;

import com.example.dao.CategoryDao;
import com.example.entity.Category;
import com.example.exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCategoryDao implements CategoryDao {
    private static final Logger LOGGER = LogManager.getLogger(JdbcCategoryDao.class);

    private static final String GET_ALL = "SELECT * FROM `Category` ORDER BY category_name";
    private static final String GET_BY_ID = "SELECT * FROM `Category` WHERE category_number=?";
    private static final String CREATE = "INSERT INTO `Category` (category_name) VALUES (?)";
    private static final String UPDATE = "UPDATE `Category` SET category_name=? WHERE category_number=?";
    private static final String DELETE = "DELETE FROM `Category` WHERE category_number=?";
    private static final String SEARCH_CATEGORY_BY_NAME = "SELECT * FROM `Category` WHERE LOWER(category_name) LIKE CONCAT('%', LOWER(?), '%')";
    private static final String SEARCH_CATEGORY_BY_NAME_AND_SORT = "SELECT * FROM `Category` WHERE LOWER(category_name) LIKE CONCAT('%', LOWER(?), '%') ORDER BY category_name";
    private static final String SEARCH_CATEGORIES_WITH_ALL_PRODUCTS_IN_STORE_PRODUCT =
            "SELECT c.* FROM Category c WHERE NOT EXISTS (   " +
                    "SELECT * FROM Product p WHERE p.category_number = c.category_number  " +
                    "AND NOT EXISTS (       " +
                            "SELECT * FROM Store_product sp WHERE sp.id_product = p.id_product ))";

    private static final String ID = "category_number";
    private static final String NAME = "category_name";

    private Connection connection;
    private final boolean connectionShouldBeClosed;

    public JdbcCategoryDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcCategoryDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                categories.add(extractCategoryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return categories;
    }

    @Override
    public Optional<Category> getById(Long id) {
        Optional<Category> category = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                category = Optional.of(extractCategoryFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return category;
    }

    @Override
    public void create(Category category) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, category.getName());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                category.setId(keys.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Category category) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, category.getName());
            query.setLong(2, category.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao update SQL exception: " + category.getId(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setLong(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao delete SQL exception: " + id, e);
            throw new ServerException(e);
        }
    }

    @Override
    public List<Category> searchCategoriesByName(String categoryName) {
        List<Category> categories = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(SEARCH_CATEGORY_BY_NAME)) {
            query.setString(1, categoryName);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                categories.add(extractCategoryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao searchCategoryByName SQL exception: " + categoryName, e);
            throw new ServerException(e);
        }
        return categories;
    }

    @Override
    public List<Category> searchCategoriesByNameAndSort(String categoryName) {
        List<Category> categories = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(SEARCH_CATEGORY_BY_NAME_AND_SORT)) {
            query.setString(1, categoryName);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                categories.add(extractCategoryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao searchCategoryByName SQL exception: " + categoryName, e);
            throw new ServerException(e);
        }
        return categories;
    }

    @Override
    public List<Category> searchCategoriesWithAllProductsInStore_product(){
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SEARCH_CATEGORIES_WITH_ALL_PRODUCTS_IN_STORE_PRODUCT)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                categories.add(extractCategoryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao searchCategoriesWithAllProductsInStore_product SQL exception", e);
            throw new ServerException(e);
        }
        return categories;
    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcCategoryDao Connection can't be closed", e);
                throw new ServerException(e);
            }
        }
    }

    protected static Category extractCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        return new Category.Builder().setId(resultSet.getLong(ID)).setName(resultSet.getString(NAME)).build();
    }

}
