package com.vominh.exmple.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerUtils {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java_jdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public static Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
