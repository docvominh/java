package com.vominh.example.spring.couchbase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCouchbaseRepositories(basePackages = {"com.vominh.research.couchbase.repository"})
public class CouchBase extends AbstractCouchbaseConfiguration {

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList("couchbase://127.0.0.1");
    }

    @Override
    public String getBucketName() {
        return "beer-sample";
    }

    @Override
    protected String getUsername() {
        return "admin";
    }

    @Override
    protected String getBucketPassword() {
        return "abcde12345";
    }

    @Bean
    public NaiveAuditorAware testAuditorAware() {
        return new NaiveAuditorAware();
    }
}
