package com.vominh.java.core.thread.task;

import java.util.List;

public class Persist implements Runnable {

    private List<String> names;

    public Persist(List<String> names) {
        this.names = names;
    }

    @Override
    public void run() {
        System.out.println("Persist data");
        names.forEach(name -> System.out.println(name));
    }
}
