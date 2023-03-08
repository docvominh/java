package com.vominh.example.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
//        System.out.println(String.valueOf("").matches("\\d*"));

        Pattern pattern = Pattern.compile("(.*)Minh(.*)");
        Matcher matcher = pattern.matcher("NGUYENMinhKhang");
        System.out.println(matcher.matches());
    }
}
