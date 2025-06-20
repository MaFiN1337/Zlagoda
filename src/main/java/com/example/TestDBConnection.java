package com.example;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class TestDBConnection {

    public static void main(String[] args) {
        System.out.println("=== Тест підключення до БД Zlagoda ===\n");

        // Тест 1: Підключення через properties файл
        testConnectionFromProperties();

        // Тест 2: Пряме підключення
        testDirectConnection();

        // Тест 3: Показати структуру БД
        showDatabaseStructure();

        // Тест 4: Тестові дані (якщо є таблиці)
        testSampleData();
    }

    private static void testConnectionFromProperties() {
        System.out.println("--- Тест 1: Підключення через dbProperties ---");

        try (InputStream input = TestDBConnection.class.getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (input == null) {
                System.out.println("❌ Файл dbProperties не знайдено в classpath");
                return;
            }

            Properties props = new Properties();
            props.load(input);

            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            System.out.println("URL: " + url);
            System.out.println("User: " + user);

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                System.out.println("✅ Підключення через properties успішне!");
                System.out.println("Database: " + conn.getCatalog());
                System.out.println("Driver: " + conn.getMetaData().getDriverName());
            }

        } catch (Exception e) {
            System.out.println("❌ Помилка підключення через properties: " + e.getMessage());
        }
        System.out.println();
    }

    private static void testDirectConnection() {
        System.out.println("--- Тест 2: Пряме підключення ---");

        String url = "jdbc:mysql://localhost:3306/zlagoda?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
        String user = "root";
        String password = "MaFiN22813376611";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                System.out.println("✅ Пряме підключення успішне!");

                // Перевіряємо версію MySQL
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT VERSION()")) {

                    if (rs.next()) {
                        System.out.println("MySQL версія: " + rs.getString(1));
                    }
                }

                // Перевіряємо кодування
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SHOW VARIABLES LIKE 'character_set_database'")) {

                    if (rs.next()) {
                        System.out.println("Кодування БД: " + rs.getString(2));
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Помилка прямого підключення: " + e.getMessage());
        }
        System.out.println();
    }

    private static void showDatabaseStructure() {
        System.out.println("--- Тест 3: Структура БД ---");

        String url = "jdbc:mysql://localhost:3306/zlagoda?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
        String user = "root";
        String password = "MaFiN22813376611";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            DatabaseMetaData metaData = conn.getMetaData();

            // Показуємо всі таблиці
            System.out.println("Таблиці в БД:");
            try (ResultSet tables = metaData.getTables("zlagoda", null, "%", new String[]{"TABLE"})) {
                boolean hasTablesFound = false;
                while (tables.next()) {
                    hasTablesFound = true;
                    String tableName = tables.getString("TABLE_NAME");
                    System.out.println("  📋 " + tableName);
                }

                if (!hasTablesFound) {
                    System.out.println("  ⚠️ Таблиць не знайдено");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Помилка отримання структури БД: " + e.getMessage());
        }
        System.out.println();
    }

    private static void testSampleData() {
        System.out.println("--- Тест 4: Тестові дані ---");

        String url = "jdbc:mysql://localhost:3306/zlagoda?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
        String user = "root";
        String password = "MaFiN22813376611";

        // Список можливих таблиць для тестування
        String[] possibleTables = {"category", "categories", "product", "products",
                "employee", "employees", "customer", "customers"};

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            for (String tableName : possibleTables) {
                try (Statement stmt = conn.createStatement()) {

                    // Спробуємо підрахувати записи в таблиці
                    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        System.out.println("📊 Таблиця '" + tableName + "': " + count + " записів");

                        // Якщо є записи, покажемо перші 3
                        if (count > 0) {
                            try (ResultSet dataRs = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT 3")) {
                                ResultSetMetaData metaData = dataRs.getMetaData();
                                int columnCount = metaData.getColumnCount();

                                System.out.println("   Перші записи:");
                                while (dataRs.next()) {
                                    StringBuilder row = new StringBuilder("   ");
                                    for (int i = 1; i <= columnCount; i++) {
                                        if (i > 1) row.append(" | ");
                                        row.append(metaData.getColumnName(i)).append(": ");
                                        row.append(dataRs.getString(i));
                                    }
                                    System.out.println(row.toString());
                                }
                            }
                        }
                    }

                } catch (SQLException e) {
                    // Таблиця не існує, пропускаємо
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Помилка тестування даних: " + e.getMessage());
        }

        System.out.println("\n=== Тест завершено ===");
    }
}
