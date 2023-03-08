package com.vominh.exmple.jdbc;

import com.vominh.exmple.jdbc.utils.DriverManagerUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverManagerTest {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManagerUtils.createConnection();
        try {
            deleteAll(connection);
            connection.commit();
            insert(connection);
            connection.commit();
            select(connection);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Roll back transaction");
            connection.rollback();
        }
    }

    static void deleteAll(Connection connection) {
        int rowCount = 0;
        try (Statement statement = connection.createStatement()) {
            String DELETE = "DELETE FROM java_jdbc.public.device";
            rowCount = statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Deleted: " + rowCount);
    }

    static void insert(Connection connection) {
        int rowCount = 0;
        try (Statement statement = connection.createStatement()) {
            String INSERT1 = "INSERT INTO java_jdbc.public.device (id, serial, name) VALUES (1,9999,'Dell xps 13')";
            String INSERT2 = "INSERT INTO java_jdbc.public.device (id, serial, name) VALUES (2,8888,'Dell xps 15')";
            rowCount += statement.executeUpdate(INSERT1);
            rowCount += statement.executeUpdate(INSERT2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int x = 1 / 0;
        System.out.println("Inserted: " + rowCount);
    }

    static void select(Connection connection) throws SQLException {
        ResultSet rs = null;
        try (Statement statement = connection.createStatement()) {
            String SELECT = "SELECT * FROM java_jdbc.public.device";
            rs = statement.executeQuery(SELECT);
            if (rs != null) {

//                if (rs.last()) {
//                    System.out.println("Selected " + rs.getRow());
//                    rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
//                }

                while (rs.next()) {
                    System.out.println(String.format("%s %s %s", rs.getInt(1), rs.getInt(2), rs.getString(3)));
                }
            } else {
                System.out.println("Rs null");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        int x = 1 / 0;

    }


}
