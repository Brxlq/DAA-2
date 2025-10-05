package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {

    @Test
    public void testInsertAndExtract() {
        MinHeap h = new MinHeap(10);
        h.insert(5);
        h.insert(3);
        h.insert(8);
        h.insert(1);

        assertEquals(1, h.extractMin());
        assertEquals(3, h.extractMin());
        assertEquals(5, h.extractMin());
        assertEquals(8, h.extractMin());
    }

    @Test
    public void testExtractFromEmpty() {
        MinHeap h = new MinHeap(2);
        assertThrows(RuntimeException.class, h::extractMin);
    }

    @Test
    public void testDecreaseKey() {
        MinHeap h = new MinHeap(new int[]{10,20,30}, true);
        h.decreaseKey(2, 2); // make 30 -> 2
        assertEquals(2, h.extractMin());
    }
}
