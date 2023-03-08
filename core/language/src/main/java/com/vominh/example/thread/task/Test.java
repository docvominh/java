package com.vominh.example.thread.task;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        Persist task1 = new Persist(Arrays.asList("Minh, Nguyen, Khang"));
        SendEmail task2 = new SendEmail("minh@gmail.com", "Hi Minh");

        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(task1);
//        executorService.execute(task2);
//
//        executorService.shutdown();


//        String x = new String("");
        StringBuilder stringBuilder1 = new StringBuilder("Test");
        StringBuilder stringBuilder2 = new StringBuilder("Test");
        StringBuffer stringBuffer = new StringBuffer("Test");

        UpdateStringOdd task3 = new UpdateStringOdd(stringBuffer);
        UpdateStringEven task4 = new UpdateStringEven(stringBuffer);

        executorService.execute(task3);
        executorService.execute(task4);
        executorService.shutdown();


        System.out.println(Thread.currentThread().getName() + " end");

    }
}
