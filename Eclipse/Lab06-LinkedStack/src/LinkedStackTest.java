import java.util.EmptyStackException;

import student.TestCase;

//-------------------------------------------------------------------------
/**
 * Tests for the {@link LinkedStack} class.
 *
 * @author jake
 * @version 2017.04.04
 */
public class LinkedStackTest extends TestCase {
    // ~ Fields ................................................................

    private LinkedStack<String> stack;

    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates a brand new, empty stack for each test method.
     */
    public void setUp() {
        stack = new LinkedStack<String>();
    }

    /**
     * test push method
     */
    public void testPush() {
        stack.push("Hello");
        assertEquals("Hello", stack.peek());
        stack.push("World");
        assertEquals("World", stack.peek());
    }

    /**
     * test pop method
     */
    public void testPop() {
        ifexception();
        assertTrue(stack.isEmpty());
        stack.push("Hello");
        stack.push("World");
        assertFalse(stack.isEmpty());
        stack.pop();
        assertEquals("Hello", stack.peek());
        stack.pop();
        ifexception();
        assertTrue(stack.isEmpty());
        stack.push("World");
        stack.push("Hello");
        ifexception();
    }

    /**
     * checks if exceptions occur
     */
    public void ifexception() {
        Exception thrown = null;
        try {
            stack.peek();
            assertEquals("Hello", stack.peek());
            stack.pop();
        }
        catch (Exception e) {
            thrown = e;
            assertTrue(thrown instanceof EmptyStackException);
        }
        thrown = null;
        try {
            stack.pop();
        }
        catch (Exception e) {
            thrown = e;
            assertTrue(thrown instanceof EmptyStackException);
        }
    }
}
