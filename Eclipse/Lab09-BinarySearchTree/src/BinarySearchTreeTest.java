import student.TestCase;

/**
 * @author jake
 * @version 2017.04.21
 */
public class BinarySearchTreeTest extends TestCase {
    private BinarySearchTree<String> bst1;
    private BinarySearchTree<String> bst2;
    private BinarySearchTree<Contact> bst3;
    private Contact con1;
    private Contact con3;
    private Contact con4;
    private Contact con5;

    /**
     * runs before tests
     */
    public void setUp() {
        bst1 = new BinarySearchTree<String>();
        bst2 = new BinarySearchTree<String>();
        bst3 = new BinarySearchTree<Contact>();
        con1 = new Contact("Jake", "Schinto", "1234567");
        con3 = new Contact("Jak", "Schinto", "1234567");
        con4 = new Contact("Jake", "Schint", "1234567");
        con5 = new Contact("Jake", "Schinto", "1234568");
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
        bst3.insert(con1);
        bst3.insert(con3);
        bst3.insert(con4);
        bst3.insert(con5);
        assertEquals(con1, bst3.find(con1));
        assertEquals(con3, bst3.find(con3));
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
        assertTrue(bst3.isEmpty());
        bst3.insert(con1);
        bst3.insert(con3);
        bst3.insert(con4);
        bst3.insert(con5);
        assertEquals(con1, bst3.find(con1));
        assertEquals(con3, bst3.find(con3));
        bst3.makeEmpty();
        assertTrue(bst3.isEmpty());
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
        bst3.insert(con1);
        bst3.insert(con3);
        bst3.insert(con4);
        bst3.insert(con5);
        bst3.remove(con4);
        assertEquals(con1, bst3.find(con1));
        assertEquals(con3, bst3.find(con3));
        assertNull(bst3.find(con4));
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
        bst3.insert(con1);
        bst3.insert(con3);
        bst3.insert(con4);
        bst3.insert(con5);
        assertEquals(con1, bst3.find(con1));
        assertEquals(con3, bst3.find(con3));
        assertEquals(bst3.findMin(), con3);
        assertEquals(bst3.findMax(), con5);
    }
}
