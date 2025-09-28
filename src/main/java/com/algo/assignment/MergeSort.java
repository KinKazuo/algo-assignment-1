package com.algo.assignment;

public class MergeSort {
    private static final int CUTOFF = 10;

    public static void sort(int[] array) {
        if (array == null || array.length <= 1) return;
        int[] buffer = new int[array.length];
        Metrics.incAllocations();
        sort(array, buffer, 0, array.length - 1);
    }

    private static void sort(int[] a, int[] buf, int left, int right) {
        if (left >= right) return;
        Metrics.enter();

        if (right - left + 1 <= CUTOFF) {
            InsertionSort.sort(a, left, right);
            Metrics.exit();
            return;
        }

        int mid = left + (right - left) / 2;
        sort(a, buf, left, mid);
        sort(a, buf, mid + 1, right);
        merge(a, buf, left, mid, right);
        Metrics.exit();
    }

    private static void merge(int[] a, int[] buf, int left, int mid, int right) {
        for (int i = left; i <= mid; i++) buf[i] = a[i];
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            Metrics.incComparisons();
            if (buf[i] <= a[j]) a[k++] = buf[i++];
            else a[k++] = a[j++];
        }
        while (i <= mid) a[k++] = buf[i++];
    }
}