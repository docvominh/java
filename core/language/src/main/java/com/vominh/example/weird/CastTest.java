package com.vominh.example.weird;

public class CastTest {
    public static void main(String[] args) {
        Object value = null;
        Long newValue = (Long) value;
        System.out.println(newValue != null);

        newValue = Long.valueOf(null);
        System.out.println(newValue != null);
    }
}
