import student.TestCase;

/**
 * @author jake
 * @version 2017.04.19
 */
public class BinaryTreeTest extends TestCase {
    private BinaryTree<String> tree1;
    private BinaryTree<String> tree2;
    private BinaryTree<String> tree3;
    private BinaryTree<String> tree4;
    private BinaryTree<String> tree5;
    private BinaryTree<String> tree6;
    private BinaryTree<String> tree7;

    /**
     * runs before tests
     */
    public void setUp() {
        tree1 = new BinaryTree<String>("Hello");
        tree2 = new BinaryTree<String>("World");
        tree3 = new BinaryTree<String>("Worl", tree1, tree2);
        tree4 = new BinaryTree<String>("Worlde");
        tree5 = new BinaryTree<String>("Wol", tree3, tree4);
        tree6 = new BinaryTree<String>("Wole", null, tree5);
        tree7 = new BinaryTree<String>("Wolee", tree6, null);
    }

    /**
     * test toPreOrderString()
     */
    public void testToPreOrderString() {
        assertEquals("(Wol (Worl (Hello) (World)) (Worlde))",
                tree5.toPreOrderString());
        assertEquals("(Wole (Wol (Worl (Hello) (World)) (Worlde)))",
                tree6.toPreOrderString());
        assertEquals("(Wolee (Wole (Wol (Worl (Hello) (World)) (Worlde))))",
                tree7.toPreOrderString());
    }

    /**
     * test toInOrderString()
     */
    public void testToInOrderString() {
        assertEquals("(((Hello) Worl (World)) Wol (Worlde))",
                tree5.toInOrderString());
        assertEquals("(Wole (((Hello) Worl (World)) Wol (Worlde)))",
                tree6.toInOrderString());
        assertEquals("((Wole (((Hello) Worl (World)) Wol (Worlde))) Wolee)",
                tree7.toInOrderString());
    }

    /**
     * test toPostOrderString()
     */
    public void testToPostOrderString() {
        assertEquals("(((Hello) (World) Worl) (Worlde) Wol)",
                tree5.toPostOrderString());
        assertEquals("((((Hello) (World) Worl) (Worlde) Wol) Wole)",
                tree6.toPostOrderString());
        assertEquals("(((((Hello) (World) Worl) (Worlde) Wol) Wole) Wolee)",
                tree7.toPostOrderString());
    }

    /**
     * test Size()
     */
    public void testSize() {
        assertEquals(1, tree1.size());
        assertEquals(1, tree2.size());
        assertEquals(3, tree3.size());
        assertEquals(1, tree4.size());
        assertEquals(5, tree5.size());
        assertEquals(6, tree6.size());
        assertEquals(7, tree7.size());
    }

    /**
     * test clone()
     */
    public void testClone() {
        tree6 = tree5.clone();
        assertEquals("(Wol (Worl (Hello) (World)) (Worlde))",
                tree6.toPreOrderString());
    }

    /**
     * test height()
     */
    public void testHeight() {
        assertEquals(1, tree1.height());
        assertEquals(1, tree2.height());
        assertEquals(2, tree3.height());
        assertEquals(1, tree4.height());
        assertEquals(3, tree5.height());
        assertEquals(4, tree6.height());
        assertEquals(5, tree7.height());
    }
}
