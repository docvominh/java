package com.vominh.java.core.thread.task;

public class UpdateStringOdd implements Runnable {

    private StringBuffer value;

    public UpdateStringOdd(StringBuffer value) {
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 1; i < 20; i++) {
            if (i % 2 != 0) {
                value.append(",");
                value.append(i);
            }
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(value);
    }
}
