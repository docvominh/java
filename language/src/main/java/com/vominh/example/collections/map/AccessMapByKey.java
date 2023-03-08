package com.vominh.example.collections.map;

import java.util.HashMap;
import java.util.Map;

public class AccessMapByKey {
    public static void main(String[] args) {
        Map<String,String> calculation = new HashMap<>();
        calculation.put("M80_Auto", "True");

        calculation.get("M80_Auto");
//        calculation["M80_Auto"];
    }
}
