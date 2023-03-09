package com.vominh.java.core.thread.parallel;

public class ThreadUtil {


    public static void showThreadInfo(Thread thread) {
        System.out.println(String.format("Thread ID/Name/Group: %s/%s/%s", thread.getId(), thread.getName(), thread.getThreadGroup().getName()));
    }
}
