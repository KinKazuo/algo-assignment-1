package com.algo.assignment;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class DeterministicSelectTest {

    private static final int TRIALS = 100;
    private static final Random RAND = new Random(42);

    @Test
    void testSmallArrays() {
        int[] a = {3, 1, 4, 1, 5};
        assertEquals(1, DeterministicSelect.select(a.clone(), 0)); // min
        assertEquals(1, DeterministicSelect.select(a.clone(), 1));
        assertEquals(3, DeterministicSelect.select(a.clone(), 2));
        assertEquals(4, DeterministicSelect.select(a.clone(), 3));
        assertEquals(5, DeterministicSelect.select(a.clone(), 4)); // max
    }

    @Test
    void testRandomTrials() {
        for (int size = 1; size <= 1000; size = Math.min(size * 3, 1000)) {
            for (int t = 0; t < TRIALS / 10; t++) {
                int[] a = RAND.ints(size, -1_000_000, 1_000_000).toArray();
                int k = RAND.nextInt(size);
                int[] copy = a.clone();
                Arrays.sort(copy);
                int expected = copy[k];
                int actual = DeterministicSelect.select(a, k);
                assertEquals(expected, actual, "Failed at size=" + size + ", k=" + k);
            }
        }
    }

    @Test
    void testEdgeCases() {
        int[] a = {42};
        assertEquals(42, DeterministicSelect.select(a, 0));

        int[] b = {5, 5, 5, 5};
        for (int k = 0; k < 4; k++) {
            assertEquals(5, DeterministicSelect.select(b.clone(), k));
        }
    }

    @Test
    void testLargeArray() {
        int n = 10_000;
        int[] a = new Random(123).ints(n, 0, n).toArray();
        int k = n / 2;
        int[] copy = a.clone();
        Arrays.sort(copy);
        int expected = copy[k];
        int actual = DeterministicSelect.select(a, k);
        assertEquals(expected, actual);
    }
}