package com.algo.assignment;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    private static final Comparator<Point> BY_X = Comparator.comparingDouble(p -> p.x);
    private static final Comparator<Point> BY_Y = Comparator.comparingDouble(p -> p.y);

    public static double closest(Point[] points) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("Need at least 2 points");
        }
        Point[] px = points.clone();
        Point[] py = points.clone();
        Arrays.sort(px, BY_X);
        Arrays.sort(py, BY_Y);
        return closest(px, py, 0, points.length - 1);
    }

    private static double closest(Point[] px, Point[] py, int lo, int hi) {
        int n = hi - lo + 1;
        if (n <= 3) {
            return bruteForce(px, lo, hi);
        }

        Metrics.enter();

        int mid = lo + (hi - lo) / 2;
        Point midPoint = px[mid];

        // Split py into left and right by x-coordinate
        Point[] pyl = new Point[mid - lo + 1];
        Point[] pyr = new Point[hi - mid];
        int li = 0, ri = 0;
        for (Point p : py) {
            if (p.x <= midPoint.x && li < pyl.length) {
                pyl[li++] = p;
            } else if (ri < pyr.length) {
                pyr[ri++] = p;
            }
        }

        double dl = closest(px, pyl, lo, mid);
        double dr = closest(px, pyr, mid + 1, hi);
        double d = Math.min(dl, dr);

        // Build strip
        Point[] strip = new Point[py.length];
        int si = 0;
        for (Point p : py) {
            if (Math.abs(p.x - midPoint.x) < d) {
                strip[si++] = p;
            }
        }

        // Check up to 7 neighbors
        for (int i = 0; i < si; i++) {
            for (int j = i + 1; j < si && (strip[j].y - strip[i].y) < d; j++) {
                Metrics.incComparisons();
                double dist = Point.dist(strip[i], strip[j]);
                if (dist < d) d = dist;
            }
        }

        Metrics.exit();
        return d;
    }

    private static double bruteForce(Point[] points, int lo, int hi) {
        double min = Double.MAX_VALUE;
        for (int i = lo; i <= hi; i++) {
            for (int j = i + 1; j <= hi; j++) {
                Metrics.incComparisons();
                double dist = Point.dist(points[i], points[j]);
                if (dist < min) min = dist;
            }
        }
        return min;
    }
}