package com.algo.assignment;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void edgeCases() {
        int[] a = {}; QuickSort.sort(a); assertArrayEquals(new int[]{}, a);
        int[] b = {42}; QuickSort.sort(b); assertArrayEquals(new int[]{42}, b);
    }

    @Test
    void sortedAndReversed() {
        int[] a = {1,2,3,4,5}; QuickSort.sort(a); assertArrayEquals(new int[]{1,2,3,4,5}, a);
        int[] b = {5,4,3,2,1}; QuickSort.sort(b); assertArrayEquals(new int[]{1,2,3,4,5}, b);
    }

    @Test
    void duplicates() {
        int[] a = {3,1,4,1,5,9,2,6,5,3};
        int[] expected = {1,1,2,3,3,4,5,5,6,9};
        QuickSort.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    void recursionDepthBounded() {
        int n = 50_000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i; // worst-case without randomization

        Metrics.reset();
        QuickSort.sort(a);

        int maxAllowed = (int) (2 * Math.ceil(Math.log(n) / Math.log(2)) + 10);
        assertTrue(Metrics.maxRecursionDepth <= maxAllowed,
            "Depth=" + Metrics.maxRecursionDepth + " > allowed=" + maxAllowed);
    }
}