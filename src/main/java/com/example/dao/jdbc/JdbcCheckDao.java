package com.example.dao.jdbc;

import com.example.dao.CheckDao;
import com.example.entity.Category;
import com.example.entity.Check;
import com.example.entity.Store_product;
import com.example.exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCheckDao implements CheckDao {
    private static final Logger LOGGER = LogManager.getLogger(JdbcCheckDao.class);

    private static final String GET_ALL = "SELECT ct.*, empl_name, empl_surname, empl_patronymic, salary, e.phone_number, empl_role, date_of_birth, date_of_start, e.city, e.street, e.zip_code, cust_surname, cust_name, cust_patronymic, cc.phone_number, cc.city, cc.street, cc.zip_code, percent" +
            "FROM `Check_table` ct" +
            "INNER JOIN `Employee` e ON e.id_employee = ct.id_employee" +
            "INNER JOIN `Customer_card` cc ON cc.card_number = ct.card_number" +
            "ORDER BY check_number";
    private static final String GET_BY_ID = "SELECT ct.*, empl_name, empl_surname, empl_patronymic, salary, e.phone_number, empl_role, date_of_birth, date_of_start, e.city, e.street, e.zip_code, cust_surname, cust_name, cust_patronymic, cc.phone_number, cc.city, cc.street, cc.zip_code, percent " +
            "FROM `Check_table` " +
            "INNER JOIN `Employee` e ON e.id_employee = ct.id_employee" +
            "INNER JOIN `Customer_card` cc ON cc.card_number = ct.card_number" +
            "WHERE check_number=?";
    private static final String CREATE = "INSERT INTO `Check_table` (check_number, print_date, sum_total, vat, id_employee, card_number) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE `Check_table` SET print_date=? WHERE check_number=?";
    private static final String DELETE = "DELETE FROM `Check_table` WHERE check_number=?";
    private static final String SEARCH_CATEGORY_BY_NAME = "SELECT * FROM `Category` WHERE LOWER(category_name) LIKE CONCAT('%', LOWER(?), '%')";
    private static final String SEARCH_CATEGORY_BY_NAME_AND_SORT = "SELECT * FROM `Category` WHERE LOWER(category_name) LIKE CONCAT('%', LOWER(?), '%') ORDER BY category_name";

    private static final String ID = "check_number";
    private static final String PRINT_DATE = "print_date";
    private static final String SUM_TOTAL = "sum_total";
    private static final String VAT = "vat";
    private static final String ID_EMPLOYEE = "id_employee";
    private static final String CARD_NUMBER = "card_number";

    private Connection connection;
    private final boolean connectionShouldBeClosed;

    public JdbcCheckDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcCheckDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Check> getAll() {
        List<Check> checks = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                checks.add(extractCheckFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCheckDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return checks;
    }

    @Override
    public Optional<Check> getById(String number) {
        Optional<Check> check = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, number);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                check = Optional.of(extractCheckFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcCheckDao getById SQL exception: " + number, e);
            throw new ServerException(e);
        }
        return check;
    }

    @Override
    public void create(Check check) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, check.getNumber());
            query.setTimestamp(2, Timestamp.valueOf(check.getPrint_date()));
            query.setBigDecimal(3, check.getSum_total());
            query.setBigDecimal(4, check.getVat());
            query.setString(5, check.getEmployee().getId());
            query.setString(6, check.getCustomer_card().getNumber());
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                check.setNumber(keys.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCheckDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Check check) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(2, check.getNumber());
            query.setTimestamp(1, Timestamp.valueOf(check.getPrint_date()));
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcCheckDao update SQL exception: " + check.getNumber(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(String number) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setString(1, number);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcCheckDao delete SQL exception: " + number, e);
            throw new ServerException(e);
        }
    }

    @Override
    public Optional<Check> searchCheckByNumber(String number){

    }

    @Override
    public List<Check> searchChecksByEmployeeId(String id){

    }

    @Override
    public List<Check> searchChecksByEmployeeSurname(String surname){

    }

    @Override
    public List<Check> searchChecksByEmployeeIdPerPeriod(String id, LocalDate fromDate, LocalDate toDate){

    }

    @Override
    public List<Check> searchChecksByEmployeeSurnamePerPeriod(String surname, LocalDate fromDate, LocalDate toDate){

    }

    @Override
    public List<Check> searchChecksPerPeriod(LocalDate fromDate, LocalDate toDate){

    }

    @Override
    public BigDecimal searchSumOfChecksByEmployeeSurnamePerPeriod(String surname, LocalDate fromDate, LocalDate toDate)

    @Override
    public BigDecimal searchSumOfChecksPerPeriod(LocalDate fromDate, LocalDate toDate){

    }

    @Override
    public BigDecimal searchAmountOfStoreProductsPerPeriod(Store_product storeProduct, LocalDate fromDate, LocalDate toDate){

    }

    protected static Check extractCheckFromResultSet(ResultSet resultSet) throws SQLException {
        return new Check.Builder().setNumber(resultSet.getString(ID)).setPrint_date(resultSet.getTimestamp(PRINT_DATE).toLocalDateTime())
                .setSum_total(resultSet.getBigDecimal(SUM_TOTAL)).setVat(resultSet.getBigDecimal(VAT))
                .setCustomer_card(JdbcCustomer_cardDao.extractCustomer_cardFromResultSet(resultSet))
                .setEmployee(JdbcEmployeeDao.extractEmployeeFromResultSet(resultSet)).build();
    }

}
