import java.util.EmptyStackException;

import student.TestCase;

//-------------------------------------------------------------------------
/**
 * Tests for the {@link ArrayListStack} class.
 *
 * @author jake
 * @version 2017.03.28
 */
public class ArrayListStackTest extends TestCase {
    // ~ Instance/static variables .............................................

    private ArrayListStack<String> stack;

    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates a brand new, empty stack for each test method.
     */
    public void setUp() {
        stack = new ArrayListStack<String>();
    }

    /**
     * tests top method
     */
    public void testTop() {
        nullCheck("top");
        stack.push("Hello");
        assertEquals("Hello", stack.top());
    }

    /**
     * @param op
     *            operation to test
     */
    public void nullCheck(String op) {
        Exception thrown = null;
        try {
            if (op.equals("top")) {
                stack.top();
            }
            else {
                stack.pop();
            }
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyStackException);
    }

    /**
     * tests push method
     */
    public void testPush() {
        stack.push("Hello");
        assertEquals("Hello", stack.top());
        stack.push("World");
        assertEquals("World", stack.top());
    }

    /**
     * tests pop method
     */
    public void testPop() {
        stack.push("Hello");
        assertEquals("Hello", stack.top());
        stack.pop();
        nullCheck("pop");
    }

    /**
     * tests size method
     */
    public void testSize() {
        assertEquals(0, stack.size());
        stack.push("Hello");
        assertEquals(1, stack.size());
        stack.push("World");
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
        stack.pop();
        assertEquals(0, stack.size());
    }

    /**
     * tests isEmpty method
     */
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push("Hello");
        assertFalse(stack.isEmpty());
        stack.push("World");
        assertFalse(stack.isEmpty());
        stack.pop();
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}
