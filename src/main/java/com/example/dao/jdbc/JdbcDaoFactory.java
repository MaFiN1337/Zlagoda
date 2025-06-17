package com.example.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.example.dao.CategoryDao;
import com.example.dao.DaoConnection;
import com.example.dao.DaoFactory;
import com.example.dao.ProductDao;
import com.example.dao.Store_productDao;
import com.example.dao.EmployeeDao;
import com.example.dao.CheckDao;
import com.example.dao.Customer_cardDao;
import com.example.exception.ServerException;

public class JdbcDaoFactory extends DaoFactory {

    private static final Logger LOGGER = LogManager.getLogger(JdbcDaoFactory.class);

    private DataSource dataSource;

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/zlagoda");

        } catch (Exception e) {
            LOGGER.error("Can't load pool connection from Initial Context", e);
            throw new ServerException(e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            LOGGER.error("Can't get DB connection to the data source", e);
            throw new ServerException(e);
        }
    }

    @Override
    public EmployeeDao createUserDao() {
        try {
            return new JdbcEmployeeDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcUserDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public EmployeeDao createUserDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcEmployeeDao(sqlConnection);
    }

    @Override
    public CategoryDao createCategoryDao() {
        try {
            return new JdbcCategoryDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcCategoryDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public CategoryDao createCategoryDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcCategoryDao(sqlConnection);
    }

    @Override
    public ProductDao createProductDao() {
        try {
            return new JdbcProductDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcDishDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public ProductDao createProductDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcProductDao(sqlConnection);
    }

    @Override
    public Store_productDao createStore_productDao() {
        try {
            return new JdbcStore_productDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcOrderDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public Store_productDao createStore_productDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcStore_productDao(sqlConnection);
    }

    @Override
    public Customer_cardDao createCustomer_cardDao() {
        try {
            return new JdbcCustomer_cardDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcOrderDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public Customer_cardDao createCustomer_cardDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcCustomer_cardDao(sqlConnection);
    }

    @Override
    public CheckDao createCheckDao() {
        try {
            return new JdbcCheckDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcOrderDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public CheckDao createCheckDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcCheckDao(sqlConnection);
    }
}
