import student.TestCase;

// -------------------------------------------------------------------------
/**
 * @author jake
 * @version 2017.02.13
 */
public class DailyNewspaperTest extends TestCase {
    // ----------------------------------------------------------

    // ~ Fields ................................................................
    private DailyNewspaper dn;
    // will initialize in your setUp method.

    // ~ Methods ...............................................................
    /**
     * Create a new DailyNewspaperTest object.
     */
    public DailyNewspaperTest() {
        // Empty
    }

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture. Called before every test case method.
     */
    public void setUp() {
        dn = new DailyNewspaper(69, "Title", 32, 1.50);
    }

    /**
     * test the getters
     */
    public void testGetters() {
        assertEquals(69, dn.getIdNumber());
        assertEquals("Title", dn.getTitle());
        assertEquals(32, dn.getNumberCopies());
        assertEquals(1.50, dn.getPrice(), 0.001);
    }

    /**
     * test monthlyCost()
     */
    public void testMonthlyCost() {
        assertEquals(1.50 * 32 * 30, dn.monthlyCost(), 0.001);
    }

    /**
     * test bundledWith()
     */
    public void testBundledWith() {
        assertEquals("Title and Other Title subscriptions are bundled.",
                dn.bundledWith(new DailyNewspaper(70, "Other Title", 40, 2)));
    }
}
