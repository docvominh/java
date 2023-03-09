package com.vominh.java.algorithm.dynamic;

import java.util.Arrays;

public class Knapsack {
    public static void main(String[] args) {
        int weight[] = new int[]{1, 2, 3};
        int value[] = new int[]{15, 6, 12};

        int maxWeight = 5;
        int itemCount = weight.length;
        knapsack(weight, value, itemCount, maxWeight);
    }

    static void knapsack(int[] weight, int[] value, int itemCount, int maxWeight) {
        if (itemCount <= 0 || maxWeight <= 0) {
            return;
        }

        int[][] dpTable = new int[itemCount + 1][maxWeight + 1];


        // Row
        for (int i = 0; i <= itemCount; i++) {
            //Column
            for (int j = 0; j <= maxWeight; j++) {

                if (i == 2 && j == 4) {
                    System.out.printf("%s %s%n", weight[i - 1], j);
                    System.out.printf("%s%n", dpTable[i - 1][j]);
                }

                if (i == 0 || j == 0) {
                    dpTable[i][j] = 0;
                } else if (weight[i - 1] > j) {
                    dpTable[i][j] = dpTable[i - 1][j];
                } else {
                    dpTable[i][j] = Math.max(dpTable[i - 1][j],
                            dpTable[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
                if (i == 2 && j == 4) {
                    System.out.printf("%s%n", dpTable[i][j]);
                }

            }
        }

        System.out.println(Arrays.deepToString(dpTable).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }
}
