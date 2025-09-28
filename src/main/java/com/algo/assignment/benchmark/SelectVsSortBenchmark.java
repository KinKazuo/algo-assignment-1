package com.algo.assignment.benchmark;

import com.algo.assignment.DeterministicSelect;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(java.util.concurrent.TimeUnit.MILLISECONDS)
public class SelectVsSortBenchmark {

    private int[] array;
    private int k;

    @Param({"1000", "5000", "10000", "50000"})
    private int size;

    @Setup
    public void setup() {
        array = ThreadLocalRandom.current().ints(size, 0, size).toArray();
        k = size / 2; // median
    }

    @Benchmark
    public void deterministicSelect(Blackhole bh) {
        int[] copy = array.clone();
        int result = DeterministicSelect.select(copy, k);
        bh.consume(result);
    }

    @Benchmark
    public void sortAndIndex(Blackhole bh) {
        int[] copy = array.clone();
        Arrays.sort(copy);
        int result = copy[k];
        bh.consume(result);
    }
}