package com.vominh.example.thread.web;

import com.vominh.example.thread.web.task.SimpleTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootApplication
@RestController
public class JavaWebSpringApplication {

    private static final Logger log = LoggerFactory.getLogger(JavaWebSpringApplication.class);

    public static void main(String[] args) {

        String threadInfo = String.format("Current Thread Id: %s, Name: %s, Group: %s",
                Thread.currentThread().getId(),
                Thread.currentThread().getName(),
                Thread.currentThread().getThreadGroup().getName());

        log.info(threadInfo);
        SpringApplication.run(JavaWebSpringApplication.class, args);
    }

    @GetMapping("/info")
    @ResponseBody
    public ResponseEntity getAppThreadInfo() throws InterruptedException, ExecutionException {

        String threadInfo = String.format("Current Thread Id: %s, Name: %s, Group: %s",
                Thread.currentThread().getId(),
                Thread.currentThread().getName(),
                Thread.currentThread().getThreadGroup().getName());

        SimpleTask task = new SimpleTask();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> result = executorService.submit(task);
        threadInfo += "<br/>";
        threadInfo += result.get();


        List<String> strings = new ArrayList<>();
        for (long i = 1; i < 20000000; i++) {
            String x = "abcdef" + i;
            strings.add(x);
        }

        return ResponseEntity.ok(threadInfo);
    }


}
