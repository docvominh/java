package com.vominh.example.jooq;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class GenerateTest {
    public static void main(String[] args) throws SQLException {
        Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver("org.postgresql.Driver")
                        .withUrl("jdbc:postgresql://localhost:5432/java_jdbc")
                        .withUser("postgres")
                        .withPassword("123456"))
                .withGenerator(new Generator()
                        .withName(MyGenerator.class.getCanonicalName())
                        .withDatabase(new Database()
                                .withName("org.jooq.meta.postgres.PostgresDatabase")
                                .withIncludes(".*")
                                .withInputSchema("public")
                        )
                .withTarget(new Target()
                        .withPackageName("com.vominh.example.jooq.generate")
                        .withDirectory("D:\\github\\database\\jooq\\src\\main\\java")));
        try {
            GenerationTool.generate(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
