package cs2114;

import student.TestCase;

public class Lab08DequeTest extends TestCase {
    private Lab08Deque<String> deque;

    public void setUp() {
        deque = new Lab08Deque<String>();
    }

    public void testSize() {
        assertEquals(0, deque.size());
    }

    public void testenqueueAtFront() {
        deque.enqueueAtFront("Hello");
        assertEquals(1, deque.size());
        assertEquals("Hello", deque.frontItem());
        deque.enqueueAtFront("Hola");
        assertEquals(2, deque.size());
        assertEquals("Hola", deque.frontItem());
    }

    public void testenqueueAtRear() {
        deque.enqueueAtRear("Hello");
        assertEquals(1, deque.size());
        assertEquals("Hello", deque.rearItem());
        deque.enqueueAtRear("Hola");
        assertEquals(2, deque.size());
        assertEquals("Hola", deque.rearItem());
    }

    public void testdequeueAtRear1() {
        Exception ex = null;
        try {
            deque.dequeueAtRear();
        }
        catch (Exception e) {
            ex = e;
        }
        assertTrue(ex instanceof IllegalStateException);
    }

    public void testdequeAtRear() {
        deque.enqueueAtRear("Hello");
        deque.enqueueAtRear("Hola");
        assertEquals("Hola", deque.dequeueAtRear());
        assertEquals(1, deque.size());
        assertEquals("Hello", deque.rearItem());
    }

    public void testdequeAtFront() {
        deque.enqueueAtFront("Hello");
        deque.enqueueAtFront("Hola");
        assertEquals("Hola", deque.dequeueAtFront());
        assertEquals(1, deque.size());
        assertEquals("Hello", deque.frontItem());
    }

    public void testdequeueAtFront1() {
        Exception ex = null;
        try {
            deque.dequeueAtFront();
        }
        catch (Exception e) {
            ex = e;
        }
        assertTrue(ex instanceof IllegalStateException);
    }

    public void testClear() {
        deque.enqueueAtFront("Hello");
        deque.enqueueAtFront("Hola");
        deque.clear();
        assertEquals(0, deque.size());
    }

    public void testToString() {
        deque.enqueueAtRear("Hello");
        deque.enqueueAtRear("Hola");
        deque.enqueueAtRear("Bonjour");
        assertEquals("[Hello, Hola, Bonjour]", deque.toString());
    }
}
