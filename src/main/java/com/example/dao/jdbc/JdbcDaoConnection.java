package com.example.dao.jdbc;

import com.example.dao.DaoConnection;
import com.example.exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoConnection implements DaoConnection {


    private static final Logger LOGGER = LogManager.getLogger(JdbcDaoConnection.class);

    private Connection connection;

    private boolean inTransaction = false;

    public JdbcDaoConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void begin() {
        try {
            connection.setAutoCommit(false);
            inTransaction = true;
            LOGGER.info("Transaction has began");
        } catch (SQLException e) {
            LOGGER.error("JdbcDaoConnection begin error", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            inTransaction = false;
            LOGGER.info("Transaction is committed");
        } catch (SQLException e) {
            LOGGER.error("JdbcDaoConnection commit error", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            inTransaction = false;
            LOGGER.info("Transaction is rollbacked");
        } catch (SQLException e) {
            LOGGER.error("JdbcDaoConnection rollback error", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void close() {
        if (inTransaction) {
            rollback();
        }
        try {
            connection.close();
            LOGGER.info("Transaction is closed");
        } catch (SQLException e) {
            LOGGER.error("JdbcDaoConnection close error", e);
            throw new ServerException(e);
        }
    }

}
