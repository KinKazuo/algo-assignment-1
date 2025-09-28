package com.algo.assignment;

public class Util {

    public static void swap(int[] a, int i, int j) {
        if (a == null || i == j) return;
        if (i < 0 || j < 0 || i >= a.length || j >= a.length) return;
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static int partition(int[] a, int lo, int hi) {
        if (lo >= hi) return lo;
        int pivot = a[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            Metrics.incComparisons();
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, hi);
        return i + 1;
    }

    public static void shuffle(int[] a) {
        if (a == null || a.length <= 1) return;
        for (int i = a.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            swap(a, i, j);
        }
    }

    public static boolean isValid(int[] a, int lo, int hi) {
        return a != null && lo >= 0 && hi < a.length && lo <= hi;
    }
}