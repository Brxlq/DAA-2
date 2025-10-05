package algorithms;

import java.util.Arrays;

import metrics.PerformanceTracker;

public class MinHeap {
    private int[] heap;
    private int size;
    private PerformanceTracker tracker;

    public MinHeap(int capacity) {
        this.heap = new int[Math.max(1, capacity)];
        this.size = 0;
        this.tracker = new PerformanceTracker();
    }

    public MinHeap(int[] data, boolean build) {
        this.heap = Arrays.copyOf(data, Math.max(1, data.length));
        this.size = data.length;
        this.tracker = new PerformanceTracker();
        if (build) buildHeap();
    }

    public PerformanceTracker getTracker() { return tracker; }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= heap.length) return;
        int newCap = Math.max(heap.length * 2, minCapacity);
        heap = Arrays.copyOf(heap, newCap);
    }

    private void swap(int i, int j) {
        tracker.incSwaps();
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
        tracker.incAccesses();
        tracker.incAccesses();
    }

    public void insert(int key) {
        ensureCapacity(size + 1);
        heap[size] = key;
        tracker.incAccesses();
        int i = size++;
        heapifyUp(i);
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int p = parent(i);
            tracker.incComparisons();
            tracker.incAccesses();
            tracker.incAccesses();
            if (heap[p] <= heap[i]) break;
            swap(i, p);
            i = p;
        }
    }

    public int extractMin() {
        if (size == 0) throw new RuntimeException("Heap underflow");
        int root = heap[0];
        tracker.incAccesses();
        heap[0] = heap[size - 1];
        tracker.incAccesses();
        size--;
        if (size > 0) heapifyDown(0);
        return root;
    }

    private void heapifyDown(int i) {
        while (true) {
            int l = left(i), r = right(i);
            int smallest = i;
            if (l < size) {
                tracker.incComparisons();
                tracker.incAccesses();
                tracker.incAccesses();
                if (heap[l] < heap[smallest]) smallest = l;
            }
            if (r < size) {
                tracker.incComparisons();
                tracker.incAccesses();
                tracker.incAccesses();
                if (heap[r] < heap[smallest]) smallest = r;
            }
            if (smallest == i) break;
            swap(i, smallest);
            i = smallest;
        }
    }

    public void decreaseKey(int i, int newValue) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
        if (newValue > heap[i]) throw new IllegalArgumentException("newValue is larger than current");
        heap[i] = newValue;
        tracker.incAccesses();
        heapifyUp(i);
    }

    public MinHeap merge(MinHeap other) {
        int[] combined = Arrays.copyOf(heap, size + other.size);
        System.arraycopy(other.heap, 0, combined, size, other.size);
        MinHeap merged = new MinHeap(combined, true);
        return merged;
    }

    private void buildHeap() {
        for (int i = parent(size - 1); i >= 0; i--) heapifyDown(i);
    }

    public int size() { return size; }

    public int[] toArray() { return Arrays.copyOf(heap, size); }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(heap, size));
    }
}
