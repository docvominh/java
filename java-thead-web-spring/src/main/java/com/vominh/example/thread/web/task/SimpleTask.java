package com.vominh.example.thread.web.task;

import java.util.concurrent.Callable;

public class SimpleTask implements Callable<String> {

    @Override
    public String call() {

        return String.format("Current Thread Id: %s, Name: %s, Group: %s",
                Thread.currentThread().getId(),
                Thread.currentThread().getName(),
                Thread.currentThread().getThreadGroup().getName());
    }
}
