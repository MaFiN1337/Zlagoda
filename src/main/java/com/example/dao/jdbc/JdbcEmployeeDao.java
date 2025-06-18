package com.example.dao.jdbc;

import com.example.dao.EmployeeDao;
import com.example.dto.EmployeeTaxSummaryDto;
import com.example.entity.Employee;
import com.example.entity.Role;
import com.example.exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcEmployeeDao implements EmployeeDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcEmployeeDao.class);

    private static final String GET_ALL = "SELECT * FROM `employee` ORDER BY empl_surname";
    private static final String GET_BY_ID = "SELECT * FROM `employee` WHERE id_employee=?";
    private static final String CREATE = "INSERT INTO `employee`"
            + " (empl_name, empl_surname, empl_patronymic, salary, phone_number, empl_role, date_of_birth, date_of_start, city, street, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE `employee`"
            + " SET empl_name=?, empl_surname=?, empl_patronymic=?, salary=?, phone_number=?, phone_number=?, empl_role=?, date_of_birth=?, date_of_start=?, city=?, street=?, zip_code=?" + " WHERE id_employee=? ";
    private static final String DELETE = "DELETE FROM `employee` WHERE id_employee=?";
    private static final String SEARCH_EMPLOYEES_BY_SURNAME = "SELECT * FROM `employee` WHERE LOWER(empl_surname) LIKE CONCAT('%', LOWER(?), '%')";
    private static final String SEARCH_EMPLOYEE_CASHIERS_SORTED_BY_SURNAME = "SELECT * FROM `Employee` WHERE empl_role = 'cashier'"
            + "ORDER BY empl_surname";
    private static final String SEARCH_EMPLOYEE_SUM_OF_VATS_FOR_EACH_CATEGORY =
            "SELECT c.category_number, c.category_name,SUM(s.selling_price) * 0.2 AS total_tax" +
            "FROM Employee e" +
            "         INNER JOIN Check_table ct ON e.id_employee = ct.id_employee" +
            "         INNER JOIN Sale s ON ct.check_number = s.check_number" +
            "         INNER JOIN Store_product sp ON s.UPC = sp.UPC" +
            "         INNER JOIN Product p ON sp.id_product = p.id_product" +
            "         INNER JOIN Category c ON p.category_number = c.category_number" +
            "WHERE e.id_employee = ?" +
            "GROUP BY c.category_number, c.category_name";

    private static final String ID = "id_employee";
    private static final String NAME = "empl_name";
    private static final String SURNAME = "empl_surname";
    private static final String PATRONYMIC = "empl_patronymic";
    private static final String SALARY = "salary";
    private static final String PHONE = "phone_number";
    private static final String ROLE = "empl_role";
    private static final String DATE_OF_BIRTH = "date_of_birth";
    private static final String DATE_OF_START = "date_of_start";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String ZIP_CODE = "zip_code";
    private static final String CATEGORY_NUMBER = "category_number";
    private static final String CATEGORY_NAME = "category_name";
    private static final String TAX_AMOUNT = "tax_amount";


    private Connection connection;
    private final boolean connectionShouldBeClosed;

    public JdbcEmployeeDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcEmployeeDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                employees.add(extractEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcEmployeeDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return employees;
    }

    @Override
    public Optional<Employee> getById(String id) {
        Optional<Employee> employee = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                employee = Optional.of(extractEmployeeFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcEmployeeDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return employee;
    }

    @Override
    public void create(Employee employee) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, employee.getName());
            query.setString(2, employee.getSurname());
            query.setString(3, employee.getPatronymic());
            query.setBigDecimal(4, employee.getSalary());
            query.setString(5, employee.getPhone());
            query.setString(6, employee.getRole().getValue());
            query.setTimestamp(7, Timestamp.valueOf(employee.getDate_of_birth().atStartOfDay()));
            query.setTimestamp(8, Timestamp.valueOf(employee.getDate_of_start().atStartOfDay()));
            query.setString(9, employee.getCity());
            query.setString(10, employee.getStreet());
            query.setString(11, employee.getZip_code());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                employee.setId(keys.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcEmployeeDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Employee employee) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, employee.getName());
            query.setString(2, employee.getSurname());
            query.setString(3, employee.getPatronymic());
            query.setBigDecimal(4, employee.getSalary());
            query.setString(5, employee.getPhone());
            query.setString(6, employee.getRole().getValue());
            query.setDate(7, Date.valueOf(employee.getDate_of_birth()));
            query.setDate(8, Date.valueOf(employee.getDate_of_start()));
            query.setString(9, employee.getCity());
            query.setString(10, employee.getStreet());
            query.setString(11, employee.getZip_code());
            query.setString(12, employee.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcEmployeeDao update SQL exception: " + employee.getId(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setString(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcEmployeeDao delete SQL exception: " + id, e);
            throw new ServerException(e);
        }
    }

    @Override
    public Optional<Employee> searchEmployeeBySurname(String surname) {
        Optional<Employee> employee = Optional.empty();

        try (PreparedStatement query = connection.prepareStatement(SEARCH_EMPLOYEES_BY_SURNAME)) {
            query.setString(1, surname);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                employee = Optional.of(extractEmployeeFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcEmployeeDao searchEmployeeBySurname SQL exception: " + surname, e);
            throw new ServerException(e);
        }
        return employee;
    }

    @Override
    public List<Employee> searchAllCashiersSortedBySurname() {
        List<Employee> employees = new ArrayList<>();
        try (
                Statement query = connection.createStatement();
                ResultSet resultSet = query.executeQuery(SEARCH_EMPLOYEE_CASHIERS_SORTED_BY_SURNAME)) {
            while (resultSet.next()) {
                employees.add(extractEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcEmployeeDao searchAllCashiersSortedBySurname SQL exception", e);
            throw new ServerException(e);
        }
        return employees;
    }

    @Override
    public List<EmployeeTaxSummaryDto> searchEmployeeSumOfVatForEachCategory(Employee employee){
        List<EmployeeTaxSummaryDto> taxSummaryDto = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(SEARCH_EMPLOYEE_SUM_OF_VATS_FOR_EACH_CATEGORY)) {
            query.setString(1, employee.getId());
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                taxSummaryDto.add(extractEmployeeTaxSummaryDto(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcEmployeeDao searchEmployeeSumOfVatForEachCategory SQL exception: " + employee, e);
            throw new ServerException(e);
        }

        return taxSummaryDto;
    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcEmployeeDao Connection can't be closed", e);
                throw new ServerException(e);
            }
        }
    }

    protected static Employee extractEmployeeFromResultSet(ResultSet resultSet) throws SQLException {

        return new Employee.Builder().setId(resultSet.getString(ID)).setName(resultSet.getString(NAME))
                .setSurname(resultSet.getString(SURNAME)).setPatronymic(resultSet.getString(PATRONYMIC)).setSalary(resultSet.getBigDecimal(SALARY))
                .setPhone(resultSet.getString(PHONE)).setDate_of_birth(resultSet.getDate(DATE_OF_BIRTH).toLocalDate())
                .setRole(Role.forValue(resultSet.getString(ROLE))).setDate_of_start(resultSet.getDate(DATE_OF_START).toLocalDate())
                .setCity(resultSet.getString(CITY)).setStreet(resultSet.getString(STREET))
                .setZip_code(resultSet.getString(ZIP_CODE)).build();
    }

    protected static EmployeeTaxSummaryDto extractEmployeeTaxSummaryDto(ResultSet resultSet) throws SQLException {

        return new EmployeeTaxSummaryDto.Builder().setCategoryName(resultSet.getString(CATEGORY_NAME))
                .setTaxAmount(resultSet.getBigDecimal(TAX_AMOUNT))
                .setCategoryNumber(resultSet.getBigDecimal(CATEGORY_NUMBER)).build();
    }

}
