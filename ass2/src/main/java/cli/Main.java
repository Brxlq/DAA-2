package cli;

import algorithms.MinHeap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demo MinHeap:");
        MinHeap min = new MinHeap(10);
        min.insert(5);
        min.insert(3);
        min.insert(8);
        min.insert(1);
        System.out.println(min);
        System.out.println("extractMin=" + min.extractMin());
        System.out.println("after: " + min);
        System.out.println("MinHeap tracker: " + min.getTracker());
    }
}
