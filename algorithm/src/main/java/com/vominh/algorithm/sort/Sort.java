package com.vominh.algorithm.sort;

import java.util.Arrays;


// https://www.geeksforgeeks.org/top-10-algorithms-in-interview-questions
public class Sort {

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] array = {9, 1, 4, 2, 7, 8, 3, 0};
        sort.bubbleSort(array);
        sort.insertionSort(array);
        sort.quickSort(array);
    }

    public void bubbleSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(array));
    }

    public void insertionSort(int[] array) {
        int key;
        int j;
        for (int i = 1; i < array.length; i++) {
            key = array[i];
            j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;

        }

        System.out.println(Arrays.toString(array));
    }

    public void quickSort(int[] array) {

    }

}
