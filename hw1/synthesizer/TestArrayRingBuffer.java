package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testEnqueueDequeue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(1);
        assertEquals(arb.dequeue(), (Integer) 1);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        assertEquals(arb.dequeue(), (Integer) 1);
        assertEquals(arb.dequeue(), (Integer) 2);
    }

    @Test
    public void testFullBuffer() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }
        assertTrue(arb.isFull());
        for (int i = 0; i < 10; i++) {
            assertEquals(arb.dequeue(), (Integer) i);
        }
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }
        int j = 0;
        for (int i : arb) {
            assertEquals(i, j);
            j += 1;
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
