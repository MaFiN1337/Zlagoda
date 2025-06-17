package com.example.dao;


import com.example.exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DaoFactory {
    private static final Logger LOGGER = LogManager.getLogger(DaoFactory.class);

    public static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";

    private static DaoFactory daoFactory;

    public abstract DaoConnection getConnection();

    public abstract EmployeeDao createUserDao();
    public abstract EmployeeDao createUserDao(DaoConnection connection);

    public abstract CategoryDao createCategoryDao();
    public abstract CategoryDao createCategoryDao(DaoConnection connection);

    public abstract ProductDao createProductDao();
    public abstract ProductDao createProductDao(DaoConnection connection);

    public abstract Store_productDao createStore_productDao();
    public abstract Store_productDao createStore_productDao(DaoConnection connection);

    public abstract CheckDao createCheckDao();
    public abstract CheckDao createCheckDao(DaoConnection connection);

    public abstract Customer_cardDao createCustomer_cardDao();
    public abstract Customer_cardDao createCustomer_cardDao(DaoConnection connection);

    public static DaoFactory getDaoFactory() {
        if (daoFactory == null) {
            try {
                InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
                Properties dbProps = new Properties();
                dbProps.load(inputStream);
                String factoryClass = dbProps.getProperty(DB_FACTORY_CLASS);
                daoFactory = (DaoFactory) Class.forName(factoryClass).newInstance();

            } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                LOGGER.error("Can't load inputStream db config file to properties object", e);
                throw new ServerException(e);
            }
        }
        return daoFactory;
    }
}
