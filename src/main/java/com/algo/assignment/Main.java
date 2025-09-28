package com.algo.assignment;

import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        if (args.length < 4) {
            System.err.println("Usage: --algo <name> --size <n> [--seed <s>]");
            System.exit(1);
        }

        String algo = null;
        int size = -1;
        long seed = System.currentTimeMillis();

        for (int i = 0; i < args.length; i++) {
            if ("--algo".equals(args[i])) {
                algo = args[++i];
            } else if ("--size".equals(args[i])) {
                size = Integer.parseInt(args[++i]);
            } else if ("--seed".equals(args[i])) {
                seed = Long.parseLong(args[++i]);
            }
        }

        if (algo == null || size <= 0) {
            System.err.println("Invalid arguments");
            System.exit(1);
        }

        Random rand = new Random(seed);
        int[] original = rand.ints(size, 0, size).toArray();

        Metrics.reset();
        long start = System.nanoTime();

        switch (algo.toLowerCase()) {
            case "mergesort":
                MergeSort.sort(original.clone());
                break;
            case "quicksort":
                QuickSort.sort(original.clone());
                break;
            case "select":
                DeterministicSelect.select(original.clone(), size / 2);
                break;
            case "closest":
                Point[] points = new Point[size];
                for (int i = 0; i < size; i++) {
                    points[i] = new Point(rand.nextDouble() * size, rand.nextDouble() * size);
                }
                ClosestPair.closest(points);
                break;
            default:
                System.err.println("Unknown algorithm: " + algo);
                System.exit(1);
        }

        long timeNs = System.nanoTime() - start;
        double timeMs = timeNs / 1_000_000.0;

        System.out.printf("%s,%d,%.3f,%d,%d,%d%n",
            algo, size, timeMs,
            Metrics.comparisons,
            Metrics.allocations,
            Metrics.maxRecursionDepth
        );
    }
}