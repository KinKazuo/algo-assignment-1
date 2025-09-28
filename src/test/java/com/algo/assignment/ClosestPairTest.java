package com.algo.assignment;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {

    @Test
    void testSmallCases() {
        Point[] p1 = {new Point(0, 0), new Point(1, 1)};
        assertEquals(Math.sqrt(2), ClosestPair.closest(p1), 1e-9);

        Point[] p2 = {new Point(0,0), new Point(3,4), new Point(1,1)};
        assertEquals(Math.sqrt(2), ClosestPair.closest(p2), 1e-9);
    }

    @Test
    void testAgainstBruteForce() {
        Random rand = new Random(42);
        for (int n = 2; n <= 200; n += 10) {
            for (int t = 0; t < 20; t++) {
                Point[] points = new Point[n];
                for (int i = 0; i < n; i++) {
                    points[i] = new Point(rand.nextDouble() * 100, rand.nextDouble() * 100);
                }
                double fast = ClosestPair.closest(points.clone());
                double brute = bruteForce(points);
                assertEquals(brute, fast, 1e-9, "Mismatch at n=" + n);
            }
        }
    }

    private static double bruteForce(Point[] points) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double d = Point.dist(points[i], points[j]);
                if (d < min) min = d;
            }
        }
        return min;
    }
}