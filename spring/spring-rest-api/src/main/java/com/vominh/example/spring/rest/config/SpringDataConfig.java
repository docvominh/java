package com.vominh.example.spring.rest.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.vominh.example.spring.rest.repository"})
@EntityScan(basePackages = {"com.vominh.example.spring.rest.entity"})
public class SpringDataConfig {

}
