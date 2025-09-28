package com.algo.assignment;

public class Metrics {
    public static int comparisons = 0;
    public static int allocations = 0;
    public static int maxRecursionDepth = 0;
    public static int currentDepth = 0;

    public static void reset() {
        comparisons = 0;
        allocations = 0;
        maxRecursionDepth = 0;
        currentDepth = 0;
    }

    public static void incComparisons() {
        comparisons++;
    }

    public static void incAllocations() {
        allocations++;
    }

    public static void enter() {
        currentDepth++;
        if (currentDepth > maxRecursionDepth) {
            maxRecursionDepth = currentDepth;
        }
    }

    public static void exit() {
        currentDepth--;
    }
}