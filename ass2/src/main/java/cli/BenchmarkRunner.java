package cli;

import algorithms.MinHeap;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        Random rand = new Random(42);

        try (FileWriter writer = new FileWriter("heap_benchmarks.csv")) {
            writer.write("Algorithm,n,TimeMs\n");
            for (int n : sizes) {
                int[] data = new int[n];
                for (int i = 0; i < n; i++) data[i] = rand.nextInt(1_000_000);

                long start = System.nanoTime();
                MinHeap minHeap = new MinHeap(n);
                for (int x : data) minHeap.insert(x);
                for (int i = 0; i < n; i++) minHeap.extractMin();
                long end = System.nanoTime();
                writer.write("MinHeap," + n + "," + ((end - start) / 1_000_000) + "\n");
                System.out.println("MinHeap n=" + n + " time=" + ((end - start) / 1_000_000) + "ms");
            }
            System.out.println("Benchmark finished: heap_benchmarks.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
