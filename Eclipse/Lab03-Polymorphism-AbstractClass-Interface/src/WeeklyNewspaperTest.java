import student.TestCase;

// -------------------------------------------------------------------------
/**
 * @author jake
 * @version 2017.02.13
 */
public class WeeklyNewspaperTest extends TestCase {
    // ----------------------------------------------------------

    // ~ Fields ................................................................
    private WeeklyNewspaper wn;
    // will initialize in your setUp method.

    // ~ Methods ...............................................................
    /**
     * Create a new WeeklyNewspaperTest object.
     */
    public WeeklyNewspaperTest() {
        // Empty
    }

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture. Called before every test case method.
     */
    public void setUp() {
        wn = new WeeklyNewspaper(12, "paper", 5, 1.99);
    }

    /**
     * test monthlyCost
     */
    public void testMonthlyCost() {
        assertEquals(4 * 5 * 1.99, wn.monthlyCost(), 0.001);
    }

    /**
     * test bundledWith(WeeklyNewspaper)
     */
    public void testBundledWith() {
        assertEquals("paper2 subscription is bundled with paper.",
                wn.bundledWith(new WeeklyNewspaper(13, "paper2", 6, 3.50)));
    }
}
