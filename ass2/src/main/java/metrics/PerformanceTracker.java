package metrics;

public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long accesses = 0;

    public void reset() {
        comparisons = 0;
        swaps = 0;
        accesses = 0;
    }

    public void incComparisons() { comparisons++; }
    public void incSwaps() { swaps++; }
    public void incAccesses() { accesses++; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getAccesses() { return accesses; }

    @Override
    public String toString() {
        return "Comparisons=" + comparisons + ", Swaps=" + swaps + ", Accesses=" + accesses;
    }
}
