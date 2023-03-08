package com.vominh.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RomanNumberUtils {
    public int characterToValue(String romanNumber) {
        Map<Character, Integer> converter = new HashMap<>();
        converter.put('I', 1);
        converter.put('V', 5);
        converter.put('X', 10);
        converter.put('L', 50);
        converter.put('C', 100);
        converter.put('D', 500);
        converter.put('M', 1000);

        int sum = 0;

        // XXVIII
        // IX -1 + 10
        // XI -> 10 + 1
        // XIV -> 10 + (- 1) + 4

        if (romanNumber == null || romanNumber.isEmpty()) {
            throw new IllegalArgumentException("Input not valid");
        }

        int currentValue;

        for (int i = 0; i < romanNumber.length(); i++) {
            currentValue = converter.getOrDefault(romanNumber.charAt(i), -1);

            if (currentValue == -1) throw new IllegalArgumentException("Input not valid");

            if (i == romanNumber.length() - 1) {
                sum += currentValue;
                break;
            }

            int nextValue = converter.getOrDefault(romanNumber.charAt(i + 1), -1);
            if (currentValue < nextValue) {
                sum -= currentValue;
            } else {
                sum += currentValue;
            }
        }


        return sum;
    }

    // 118 -> CXVIII
    // 100 + 10 + 8 -> C X VIII
    public String valueToCharacter(int num) {

        Map<Integer, String> converter = new HashMap<>();
        converter.put(1, "I");
        converter.put(4, "IV");
        converter.put(5, "V");
        converter.put(9, "IX");
        converter.put(10, "X");
        converter.put(40, "XL");
        converter.put(50, "L");
        converter.put(90, "XC");
        converter.put(100, "C");
        converter.put(400, "CD");
        converter.put(500, "D");
        converter.put(900, "CM");
        converter.put(1000, "M");
        String result = "";

        Integer[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        System.out.println(result + "/" + num);
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] <= num) {
                result += converter.get(nums[i]);
                num -= nums[i];
                System.out.println(result + "/" + num);
            }
        }

        return result;

    }

}
