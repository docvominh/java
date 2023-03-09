package com.vominh.java.algorithm;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        for(int i = 0; i < 26; i++){
//            System.out.println((char) (97 + i));
//        }
//        int nums[] = new int[]{2, 2, 2, 4};
//        System.out.println(singleNumber(nums) + "");

        String s = "0P";
        System.out.println(isPalindrome(s));
    }

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        int temp;
        for (int i = 0; i < nums.length; i++) {
            temp = target - nums[i];
            if (map.containsKey(temp)) {
                return new int[]{i, map.get(temp)};
            }

            map.put(nums[i], i);
        }

        return null;
    }

    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();


        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                characters.add(s.charAt(i));
            }
        }

        if (characters.size() == 0) {
            return true;
        }

        if (characters.size() == 2) {
            return characters.get(0) == characters.get(1);
        }

        Character[] array = characters.toArray(new Character[0]);

        int start = 0;
        for (int end = array.length - 1; end > start; end--) {
            if (array[start] != array[end]) {
                return false;
            }
            start++;
        }
        return true;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Set<Integer> small = new HashSet<>();
        Set<Integer> big = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        if (nums1.length < nums2.length) {
            for (int i : nums1) {
                small.add(i);
            }

            for (int i : nums2) {
                big.add(i);
            }
        } else {
            for (int i : nums2) {
                small.add(i);
            }

            for (int i : nums1) {
                big.add(i);
            }
        }

        for (int num : small) {
            if (big.contains(num)) {
                result.add(num);
            }
        }

        int[] resultArray = new int[result.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }

    public static int singleNumber(int[] nums) {
        List<Integer> integers = new ArrayList<>();
        for (int i : nums) {
            if (!integers.contains(i)) {
                integers.add(i);
            } else {
                integers.remove(Integer.valueOf(i));
            }
        }

        return integers.get(0);
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        return set.size() != nums.length;
    }

    public static int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();

        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(s.charAt(i), 1);
            } else {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return s.indexOf(s.charAt(i));
            }
        }

        return -1;
    }

    public static int reverse(int x) {
        if (x > -10 && x < 10) return x;

        boolean isNegative = x < 0;

        // remove negative
        if (isNegative) {
            x = x * (-1);
        }

        char[] ch = String.valueOf(x).toCharArray();

        int i = 0;
        char temp;

        // Reverse number in string format
        for (int j = ch.length - 1; j > i; j--) {
            temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
            i++;
        }

        String numberString = "";
        for (int p = 0; p < ch.length; p++) {
            if (ch[p] == '0') continue;
            numberString += ch[p];
        }

        if (isNegative) {
            return Integer.parseInt(numberString) * (-1);
        }

        return Integer.parseInt(numberString);

    }
}
