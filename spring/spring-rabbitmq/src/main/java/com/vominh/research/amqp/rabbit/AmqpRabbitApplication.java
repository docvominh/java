package com.vominh.research.amqp.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class AmqpRabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmqpRabbitApplication.class, args);
    }

    private static final boolean NON_DURABLE = false;
    private static final String MY_QUEUE_NAME = "myQueue";

    @Bean
    public ApplicationRunner runner(RabbitTemplate template) {
        return args -> {

            for (int i = 0; i < 1000; i++) {
                template.convertAndSend("myQueue", "Hello, " + i + " world! địt");
                Thread.sleep(300);
            }

        };
    }

    @Bean
    public Queue myQueue() {
        return new Queue(MY_QUEUE_NAME, NON_DURABLE);
    }

    @RabbitListener(queues = MY_QUEUE_NAME)
    public void listen(Message message) {
        System.out.println("Message read from myQueue : " + new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
