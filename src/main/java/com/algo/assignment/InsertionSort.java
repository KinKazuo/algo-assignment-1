package com.algo.assignment;

public class InsertionSort {
    public static void sort(int[] a, int lo, int hi) {
        if (a == null || lo >= hi) return;
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }
}