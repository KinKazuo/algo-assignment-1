package com.algo.assignment;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public static void sort(int[] a) {
        if (a == null || a.length <= 1) return;
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int lo, int hi) {
        while (lo < hi) {
            Metrics.enter();

            int randomIndex = ThreadLocalRandom.current().nextInt(lo, hi + 1);
            Util.swap(a, lo, randomIndex);

            int lt = lo, gt = hi;
            int pivot = a[lo];
            int i = lo + 1;
            while (i <= gt) {
                Metrics.incComparisons();
                if (a[i] < pivot) {
                    Util.swap(a, lt++, i++);
                } else if (a[i] > pivot) {
                    Util.swap(a, i, gt--);
                } else {
                    i++;
                }
            }

            int leftSize = lt - lo;
            int rightSize = hi - gt;

            if (leftSize < rightSize) {
                sort(a, lo, lt - 1);
                lo = gt + 1; // iterate on right
            } else {
                sort(a, gt + 1, hi);
                hi = lt - 1; // iterate on left
            }

            Metrics.exit();
        }
    }

       }
}