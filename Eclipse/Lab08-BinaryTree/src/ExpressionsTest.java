import student.TestCase;

/**
 * @author jake
 * @version 2017.05.08
 */
public class ExpressionsTest extends TestCase {
    private String inOrder;
    private String preOrder;
    private String postOrder;

    /**
     * runs before tests
     */
    public void setUp() {
        inOrder = "(((a) - (b)) * (((c) + (d)) / (e)))";
        preOrder = "(* (- (a) (b)) (/ (+ (c) (d)) (e)))";
        postOrder = "(((a) (b) -) (((c) (d) +) (e) /) *)";
    }

    /**
     * test main()
     */
    public void testMain() {
        new Expressions();
        Expressions.main(new String[1]);
        assertTrue(systemOut().getHistory().contains(inOrder));
        assertTrue(systemOut().getHistory().contains(preOrder));
        assertTrue(systemOut().getHistory().contains(postOrder));
    }
}
