package com.vominh.spring.cloud.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;

import java.io.ByteArrayInputStream;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableBinding(Processor.class)
public class StreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamApplication.class, args);
    }



    @StreamListener(Processor.INPUT)
    public void getMessage(Message<byte[]> in) {
        ByteArrayInputStream bisPayload = new ByteArrayInputStream(in.getPayload())
    }
}
