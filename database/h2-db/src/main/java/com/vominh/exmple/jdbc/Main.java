package com.vominh.exmple.jdbc;

import org.h2.tools.DeleteDbFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_FILE_URL = "jdbc:h2:~/testdbfile";
    static final String DB_MEMORY_URL = "jdbc:h2:mem:testdb";

    public static void main(String[] args) throws ClassNotFoundException {
        DeleteDbFiles.execute("~", "test", true);

        Class.forName(JDBC_DRIVER);
        try (Connection conn = DriverManager.getConnection(DB_FILE_URL); Statement stat = conn.createStatement()) {
            stat.execute("create table test(id int primary key, name varchar(255))");
            stat.execute("insert into test values(1, 'Hello')");
            stat.execute("insert into test values(2, 'Bonjour')");

            try (ResultSet rs = stat.executeQuery("select * from test")) {
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
