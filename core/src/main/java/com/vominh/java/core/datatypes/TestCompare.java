package com.vominh.java.core.datatypes;

public class TestCompare {

    public static void main(String[] args) {
        System.out.println("a".compareTo("b"));
        System.out.println("a".compareTo("c"));

        Integer x = 1, y = 2, z = 3;
        Long a = 1L, b = 2L, c = 3L;
        Double v1 = 1.2, v2 = 1.5, v3 = 10.5;

        System.out.println(a.compareTo(b));
        System.out.println(a.compareTo(c));

        System.out.println(x.compareTo(y));
        System.out.println(x.compareTo(z));

        System.out.println(v1.compareTo(v2));
        System.out.println(v1.compareTo(v3));
    }
}
