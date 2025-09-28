package com.algo.assignment;

import java.util.Arrays;

public class DeterministicSelect {

    /**
     * Returns the k-th smallest element (0-indexed) in the array.
     * @param a input array (will be modified)
     * @param k index of order statistic (0 <= k < a.length)
     * @return k-th smallest element
     */
    public static int select(int[] a, int k) {
        if (a == null || k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Invalid k: " + k);
        }
        return select(a, 0, a.length - 1, k);
    }

    private static int select(int[] a, int lo, int hi, int k) {
        if (lo == hi) return a[lo];

        Metrics.enter();

        int pivotIndex = medianOfMedians(a, lo, hi);
        pivotIndex = partition(a, lo, hi, pivotIndex);
        if (k == pivotIndex) {
            Metrics.exit();
            return a[k];
        } else if (k < pivotIndex) {
            // Recurse left
            int result = select(a, lo, pivotIndex - 1, k);
            Metrics.exit();
            return result;
        } else {
            // Recurse right
            int result = select(a, pivotIndex + 1, hi, k);
            Metrics.exit();
            return result;
        }
    }

    private static int partition(int[] a, int lo, int hi, int pi) {
        Util.swap(a, pi, hi); // move pivot to end
        int pivot = a[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            Metrics.incComparisons();
            if (a[j] <= pivot) {
                i++;
                Util.swap(a, i, j);
            }
        }
        Util.swap(a, i + 1, hi);
        return i + 1;
    }

    private static int medianOfMedians(int[] a, int lo, int hi) {
        int n = hi - lo + 1;
        if (n <= 5) {
            // Sort small group and return median
            insertionSort(a, lo, hi);
            return lo + n / 2;
        }

        int numGroups = (n + 4) / 5; // ceil(n/5)
        for (int i = 0; i < numGroups; i++) {
            int groupLo = lo + i * 5;
            int groupHi = Math.min(groupLo + 4, hi);
            insertionSort(a, groupLo, groupHi);
            // Move median to front (lo + i)
            Util.swap(a, lo + i, groupLo + (groupHi - groupLo) / 2);
        }

        return select(a, lo, lo + numGroups - 1, lo + numGroups / 2);
    }

    private static void insertionSort(int[] a, int lo, int hi) {
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