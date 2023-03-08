package com.vominh.example.string.regex;

public class Test {
    public static void main(String[] args) {
        System.out.println(String.valueOf("").matches("\\d*"));
        System.out.println(String.valueOf("order.product.abc").startsWith("order.product"));
        System.out.println(String.valueOf("order.product.abc").matches("^order.product"));
    }
}
