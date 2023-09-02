package priorityqueuetest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import priorityqueue.PriorityQueue;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    private PriorityQueue<Integer> priorityQueue;

    @BeforeEach
    public void setUp() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        priorityQueue = new PriorityQueue<>(comparator);
    }

    @Test
    void testEmptyQueue() {
        assertTrue(priorityQueue.empty());
    }

    @Test
    void testNotEmptyQueue() {
        priorityQueue.push(10);
        assertFalse(priorityQueue.empty());
    }

    @Test
    void testPushEmptyQueueOneEl() {
        priorityQueue.push(3);
        assertEquals(1, priorityQueue.getSize());
    }

    @Test
    void testPushEmptyQueueTwoEl() {
        priorityQueue.push(3);
        priorityQueue.push(10);
        assertEquals(2, priorityQueue.getSize());
    }

    @Test
    void testTopAfterPush() {
        priorityQueue.push(10);
        assertEquals(10, priorityQueue.top());
        priorityQueue.push(3);
        assertEquals(3, priorityQueue.top());
        priorityQueue.push(15);
        assertEquals(3, priorityQueue.top());
    }

    @Test
    void testSequencePushTopPop() {
        assertTrue(priorityQueue.empty());
        priorityQueue.push(3);
        assertEquals(3, priorityQueue.top());
        priorityQueue.push(0);
        assertEquals(0, priorityQueue.top());
        priorityQueue.push(1);
        assertEquals(0, priorityQueue.top());
        priorityQueue.pop();
        assertEquals(1, priorityQueue.top());
        priorityQueue.pop();
        assertEquals(3, priorityQueue.top());
        priorityQueue.pop();
        assertTrue(priorityQueue.empty());
    }

    @Test
    void testContainsElementInQueue() {
        priorityQueue.push(1);
        priorityQueue.push(3);
        assertTrue(priorityQueue.contains(3));
    }

    @Test
    void testContainsElementNotInQueue() {
        priorityQueue.push(1);
        priorityQueue.push(3);
        assertFalse(priorityQueue.contains(2));
    }

    @Test
    void testOrderAfterPush() {
        priorityQueue.push(5);
        priorityQueue.push(3);
        priorityQueue.push(4);
        priorityQueue.push(1);
        priorityQueue.push(0);

        Integer[] expectedOrder = {0, 1, 3, 4, 5};
        for (Integer expectedValue : expectedOrder) {
            assertEquals(expectedValue, priorityQueue.top());
            priorityQueue.pop();
        }
    }

    @Test
    void testRemoveElementInQueue() {
        priorityQueue.push(1);
        priorityQueue.push(2);
        assertTrue(priorityQueue.remove(2));
        assertFalse(priorityQueue.contains(2));
        assertTrue(priorityQueue.contains(1));
    }

    @Test
    void testRemoveElementNotInQueue() {
        priorityQueue.push(1);
        priorityQueue.push(2);
        assertFalse(priorityQueue.remove(3));
        assertTrue(priorityQueue.contains(1));
        assertTrue(priorityQueue.contains(2));
    }

    @Test
    void testOrderAfterRemove() {
        priorityQueue.push(5);
        priorityQueue.push(3);
        priorityQueue.push(4);
        priorityQueue.push(1);
        priorityQueue.push(0);

        priorityQueue.remove(0);
        Integer[] expectedOrder = {1, 3, 4, 5};
        for (Integer expectedValue : expectedOrder) {
            assertEquals(expectedValue, priorityQueue.top());
            priorityQueue.pop();
        }
    }

    @Test
    void testChangePriority() {
        priorityQueue.push(5);
        priorityQueue.push(3);
        priorityQueue.push(4);
        priorityQueue.push(1);
        priorityQueue.push(0);

        assertFalse(priorityQueue.changePriority(7, 1));
        assertEquals(0, priorityQueue.top());
        assertTrue(priorityQueue.changePriority(0, 7));
        Integer[] expectedOrder = {1, 3, 4, 5, 7};
        for (Integer expectedValue : expectedOrder) {
            assertEquals(expectedValue, priorityQueue.top());
            priorityQueue.pop();
        }
    }

}