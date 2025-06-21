package com.example.dao.jdbc;

import com.example.dao.Customer_cardDao;
import com.example.dto.Customer_cardDiscountDto;
import com.example.entity.Customer_card;
import com.example.exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCustomer_cardDao implements Customer_cardDao {
    private static final Logger LOGGER = LogManager.getLogger(JdbcCategoryDao.class);

    private static final String GET_ALL = "SELECT * FROM `Customer_card` ORDER BY cust_surname";
    private static final String GET_BY_ID = "SELECT * FROM `Customer_card` WHERE card_number=?";
    private static final String CREATE = "INSERT INTO `Customer_card` " +
            "(cust_surname, cust_name, cust_patronymic, phone_number, city, street, zip_code, percent) VALUES (?, ?, ?, ? ,?, ? ,?, ?)";
    private static final String UPDATE = "UPDATE `Customer_card` SET " +
            "cust_surname=?, cust_name=?, cust_patronymic=?, phone_number=?, city=?, street=?, zip_code=?, percent=? " +
            "WHERE card_number=?";
    private static final String DELETE = "DELETE FROM `Customer_card` WHERE card_number=?";
    private static final String SEARCH_CUSTOMER_CARD_BY_SURNAME = "SELECT * FROM `Customer_card` WHERE LOWER(cust_surname) LIKE CONCAT('%', LOWER(?), '%')";
    private static final String SEARCH_CUSTOMER_CARD_BY_PERCENT_SORTED_BY_SURNAME =
            "SELECT * FROM Customer_Card" +
            "WHERE percent = ?" +
            "ORDER BY cust_surname";
    private static final String SEARCH_CUSTOMER_CARD_DISCOUNT_FOR_EACH_CATEGORY =
            "SELECT c.category_number," +
            "    c.category_name," +
            "    COALESCE(SUM(sa.selling_price / (100 - cc.percent) * cc.percent), 0) AS discount" +
            "FROM Category c" +
            "         LEFT JOIN Product p ON c.category_number = p.category_number" +
            "         LEFT JOIN Store_product sp ON p.id_product = sp.id_product" +
            "         LEFT JOIN Sale sa ON sp.UPC = sa.UPC" +
            "         LEFT JOIN Check_table ct ON sa.check_number = ct.check_number" +
            "         LEFT JOIN Customer_Card cc ON ct.card_number = cc.card_number AND cc.card_number = ?" +
            "GROUP BY c.category_number, c.category_name" +
            "ORDER BY c.category_number";


    private static final String ID = "card_number";
    private static final String CUST_SURNAME = "cust_surname";
    private static final String CUST_NAME = "cust_name";
    private static final String CUST_PATRONYMIC = "cust_patronymic";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String ZIP_CODE = "zip_code";
    private static final String PERCENT = "percent";
    private static final String CATEGORY_NAME = "category_name";
    private static final String DISCOUNT = "discount";
    private static final String CATEGORY_NUMBER = "category_number";


    private Connection connection;
    private final boolean connectionShouldBeClosed;

    public JdbcCustomer_cardDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcCustomer_cardDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Customer_card> getAll() {
        List<Customer_card> customer_cards = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                customer_cards.add(extractCustomer_cardFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCustomer_cardDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return customer_cards;
    }

    @Override
    public Optional<Customer_card> getById(String id) {
        Optional<Customer_card> customer_card = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customer_card = Optional.of(extractCustomer_cardFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcCustomer_cardDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return customer_card;
    }

    @Override
    public void create(Customer_card customer_card) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, customer_card.getSurname());
            query.setString(2,  customer_card.getName());
            query.setString(3,  customer_card.getPatronymic());
            query.setString(4,  customer_card.getPhoneNumber());
            query.setString(5,  customer_card.getCity());
            query.setString(6,  customer_card.getStreet());
            query.setString(7,  customer_card.getZip_code());
            query.setLong(8,  customer_card.getPercent());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                customer_card.setNumber(keys.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCustomer_cardDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Customer_card customer_card) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, customer_card.getSurname());
            query.setString(2,  customer_card.getName());
            query.setString(3,  customer_card.getPatronymic());
            query.setString(4,  customer_card.getNumber());
            query.setString(5,  customer_card.getCity());
            query.setString(6,  customer_card.getStreet());
            query.setString(7,  customer_card.getZip_code());
            query.setLong(8,  customer_card.getPercent());
            query.setString(9,  customer_card.getNumber());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcCustomer_cardDao update SQL exception: " + customer_card.getNumber(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(String number) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setString(1, number);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcCustomer_cardDao delete SQL exception: " + number, e);
            throw new ServerException(e);
        }
    }

    @Override
    public List<Customer_card> searchCustomer_cardBySurname(String surname) {
        List<Customer_card> customer_cards = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SEARCH_CUSTOMER_CARD_BY_SURNAME)) {
            query.setString(1, surname);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customer_cards.add(extractCustomer_cardFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCustomer_cardDao searchCustomer_cardBySurname SQL exception: " + surname, e);
            throw new ServerException(e);
        }
        return customer_cards;
    }

    @Override
    public List<Customer_card> searchCustomer_cardByPercentSortedBySurname(BigDecimal percent) {
        List<Customer_card> customer_cards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(SEARCH_CUSTOMER_CARD_BY_PERCENT_SORTED_BY_SURNAME)) {
            query.setBigDecimal(1, percent);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customer_cards.add(extractCustomer_cardFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCustomer_cardDao searchCustomer_cardByPercentSortedBySurname SQL exception: " + percent, e);
            throw new ServerException(e);
        }
        return customer_cards;
    }

    @Override
    public List<Customer_cardDiscountDto> searchCustomer_cardDiscountForEachCategory(Customer_card customer_card){
        List<Customer_cardDiscountDto> customerCardDiscountDtos = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SEARCH_CUSTOMER_CARD_DISCOUNT_FOR_EACH_CATEGORY)) {
            query.setString(1, customer_card.getNumber());
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCardDiscountDtos.add(extractCustomer_cardDiscountInCategoriesResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCustomer_cardDao searchCustomer_cardDiscountForEachCategory SQL exception: " + customer_card, e);
            throw new ServerException(e);
        }

        return customerCardDiscountDtos;
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

    protected static Customer_card extractCustomer_cardFromResultSet(ResultSet resultSet) throws SQLException {
        return new Customer_card.Builder().setName(resultSet.getString(CUST_NAME))
                .setSurname(resultSet.getString(CUST_SURNAME)).setPatronymic(resultSet.getString(CUST_PATRONYMIC))
                .setNumber(resultSet.getString(ID)).setPhone(resultSet.getString(PHONE_NUMBER))
                .setCity(resultSet.getString(CITY)).setStreet(resultSet.getString(STREET))
                .setZip_code(resultSet.getString(ZIP_CODE)).setPercent(resultSet.getLong(PERCENT))
                .build();
    }

    protected static Customer_cardDiscountDto extractCustomer_cardDiscountInCategoriesResultSet(ResultSet resultSet) throws SQLException {
        return new Customer_cardDiscountDto.Builder().setCategoryName(resultSet.getString(CATEGORY_NAME))
                .setTaxAmount(resultSet.getBigDecimal(DISCOUNT))
                .setCategoryNumber(resultSet.getString(CATEGORY_NUMBER)).build();
    }

}
