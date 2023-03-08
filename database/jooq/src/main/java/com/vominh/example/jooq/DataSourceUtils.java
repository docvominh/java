package com.vominh.example.jooq;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DataSourceUtils {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java_jdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public static DataSource get() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(DB_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);


        return dataSource;
    }
}
