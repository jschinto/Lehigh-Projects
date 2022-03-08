import student.TestCase;

/**
 * @author jake
 * @version 2017.04.21
 */
public class BinarySearchTreeTest extends TestCase {
    private BinarySearchTree<String> bst1;
    private BinarySearchTree<String> bst2;

    /**
     * runs before tests
     */
    public void setUp() {
        bst1 = new BinarySearchTree<String>();
        bst2 = new BinarySearchTree<String>();
    }

    /**
     * test insert and find
     */
    public void testInsertFind() {
        bst1.insert("Hello");
        bst1.isEmpty();
        assertEquals("Hello", bst1.find("Hello"));
        bst1.insert("World");
        assertEquals("Hello", bst1.find("Hello"));
        assertEquals("World", bst1.find("World"));
        bst1.insert("a");
        bst1.insert("aa");
        bst1.insert("aaaa");
        bst1.insert("aaa");
        assertEquals("a", bst1.find("a"));
        assertNull(bst2.find("Hello"));
        Exception caught = null;
        try {
            bst1.insert("Hello");
        }
        catch (Exception e) {
            caught = e;
        }
        assertTrue(caught instanceof DuplicateItemException);
    }

    /**
     * tests isEmpty and makeEmpty
     */
    public void testIsEmptyMakeEmpty() {
        assertTrue(bst1.isEmpty());
        bst1.insert("Hello");
        bst1.insert("World");
        assertFalse(bst1.isEmpty());
        bst1.makeEmpty();
        assertTrue(bst1.isEmpty());
    }

    /**
     * test remove
     */
    public void testRemove() {
        assertTrue(bst1.isEmpty());
        bst1.insert("Hello");
        bst1.insert("Hell");
        bst1.insert("World");
        bst1.insert("Helloo");
        bst1.insert("Worl");
        bst1.insert("Worldoo");
        bst1.equals("Worldo");
        bst1.insert("Hellooo");
        bst1.insert("Wor");
        bst1.remove("Hello");
        assertNull(bst1.find("Hello"));
        assertEquals("World", bst1.find("World"));
        bst1.remove("World");
        bst1.remove("Worldoo");
        bst2.insert("Ho");
        bst2.insert("Holl");
        bst2.insert("Hol");
        bst2.remove("Holl");
        bst2.remove("Hol");
        bst2.remove("Ho");
        bst2.insert("Hol");
        bst2.insert("Ho");
        bst2.insert("H");
        bst2.remove("H");
        assertNull(bst1.find("World"));
        Exception caught = null;
        try {
            bst1.remove("Hello");
        }
        catch (Exception e) {
            caught = e;
        }
        assertTrue(caught instanceof ItemNotFoundException);
    }

    /**
     * test findMin, findMax
     */
    public void testFindMinMax() {
        assertNull(bst1.findMax());
        assertNull(bst1.findMin());
        bst1.insert("Hello");
        bst1.insert("He");
        bst1.insert("Hel");
        bst1.insert("H");
        bst1.insert("Hellooo");
        assertEquals(bst1.findMin(), "H");
        assertEquals(bst1.findMax(), "Hellooo");
    }
}
