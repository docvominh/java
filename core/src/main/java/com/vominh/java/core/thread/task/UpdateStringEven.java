package com.vominh.java.core.thread.task;

public class UpdateStringEven implements Runnable {

    private StringBuffer value;

    public UpdateStringEven(StringBuffer value) {
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 1; i < 15; i++) {
            if (i % 2 == 0) {
                value.append(",");
                value.append(i);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(value);
    }
}
