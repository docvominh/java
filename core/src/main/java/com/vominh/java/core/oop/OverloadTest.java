package com.vominh.java.core.oop;

public class OverloadTest {
    public static void main(String[] args) {
        byte x = 5;
        show(x);

    }

    private static void show(long value) {
        System.out.println("long: " + value);
    }

    private static void show(int value) {
        System.out.println("int: " + value);
    }

    private static void show(short value) {
        System.out.println("short: " + value);
    }
}
