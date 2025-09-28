package com.algo.assignment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    @Test void empty() {
        int[] a = {}; MergeSort.sort(a); assertArrayEquals(new int[]{}, a);
    }
    @Test void single() {
        int[] a = {5}; MergeSort.sort(a); assertArrayEquals(new int[]{5}, a);
    }
    @Test void reversed() {
        int[] a = {3,2,1}; MergeSort.sort(a); assertArrayEquals(new int[]{1,2,3}, a);
    }
    @Test void duplicates() {
        int[] a = {2,1,2}; MergeSort.sort(a); assertArrayEquals(new int[]{1,2,2}, a);
    }
}