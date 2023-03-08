package com.vominh.exmple.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HikariDataSourceTest {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java_jdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws SQLException, InterruptedException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(20);

        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", 250);
        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);

        DataSource dataSource = new HikariDataSource(config);
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        select(connection);

        Thread.sleep(10000);
    }

    static void select(Connection connection) throws SQLException {
        ResultSet rs = null;
        try (Statement statement = connection.createStatement()) {
            String SELECT = "SELECT * FROM java_jdbc.public.device";
            rs = statement.executeQuery(SELECT);

            if (rs != null) {
                while (rs.next()) {
                    System.out.println(String.format("%s %s %s", rs.getInt(1), rs.getInt(2), rs.getString(3)));
                }
            } else {
                System.out.println("Rs null");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
