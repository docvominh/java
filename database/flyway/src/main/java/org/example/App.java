package org.example;

import org.flywaydb.core.Flyway;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/flyway_test", "postgres", "123456").load();

        // Start the migration
        flyway.migrate();
    }
}
