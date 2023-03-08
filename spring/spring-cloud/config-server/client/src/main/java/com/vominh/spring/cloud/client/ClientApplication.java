package com.vominh.spring.cloud.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Value("${owner}")
    private String owner;

    @GetMapping(value = "/owner", produces = MediaType.TEXT_PLAIN_VALUE)
    public String showOwner() {
        return String.format("This one belong to %s", this.owner);
    }

}
