package com.vominh.example.thread.parallel.thread;

import com.vominh.example.thread.parallel.Person;
import com.vominh.example.thread.parallel.ThreadUtil;

import java.util.List;

public class ThreadExample extends Thread {

    private final List<Person> persons;

    public ThreadExample(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public void run() {
        ThreadUtil.showThreadInfo(Thread.currentThread());
        for (Person p : this.persons) {
            System.out.println(p.toString());
        }
    }
}
