package com.vominh.example.thread.parallel.runable;

import com.vominh.example.thread.parallel.ObjectData;
import com.vominh.example.thread.parallel.Person;
import com.vominh.example.thread.parallel.ThreadUtil;

import java.util.List;

/**
 * Since Java 1.0
 * Runnable doesn't return any value or throw exception
 */
public class RunnableExample extends ObjectData implements Runnable {



    public RunnableExample(List<Person> persons) {
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
