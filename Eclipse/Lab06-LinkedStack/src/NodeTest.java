import student.TestCase;

//-------------------------------------------------------------------------
/**
 * Tests for the {@link Node} class.
 *
 * @author jake
 * @version 2017.04.04
 */
public class NodeTest extends TestCase {
    // ~ Fields ................................................................

    // these.
    private Node<String> node1;
    private Node<String> node2;
    private Node<String> node3;

    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Create some new nodes for each test method.
     */
    public void setUp() {
        node1 = new Node<String>("node1");
        node2 = new Node<String>("node2");
        node3 = new Node<String>("node3");
    }

    /**
     * test join method
     */
    public void testJoin() {
        assertNull(node1.next());
        assertNull(node2.previous());
        node1.join(null);
        node1.join(node2);
        assertEquals(node2, node1.next());
        assertEquals(node1, node2.previous());
        ifexception(node1);
        node1.split();
        node2.join(node3);
        ifexception(node1);
    }

    /**
     * @param inNode
     *            node to check
     */
    public void ifexception(Node<String> inNode) {
        Exception thrown = null;
        try {
            inNode.join(node3);
            assertEquals(inNode.next(), node3);
            inNode.split();
        }
        catch (Exception e) {
            thrown = e;
            assertTrue(thrown instanceof IllegalStateException);
            assertEquals("next node already exists", thrown.getMessage());
        }
        thrown = null;
        try {
            inNode.join(null);
            assertNull(inNode.next());
        }
        catch (Exception e) {
            thrown = e;
            assertTrue(thrown instanceof IllegalStateException);
            assertEquals("next node already exists", thrown.getMessage());
        }
    }

    /**
     * test split method
     */
    public void testSplit() {
        assertNull(node1.split());
        assertNull(node1.next());
        assertNull(node2.previous());
        node1.join(node2);
        assertEquals(node2, node1.split());
        assertNull(node1.next());
        assertNull(node2.previous());
        ifexception(node1);
    }
}
