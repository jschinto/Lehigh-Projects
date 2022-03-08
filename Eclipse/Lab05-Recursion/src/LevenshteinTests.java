import student.TestCase;

// -------------------------------------------------------------------------
/**
 * @author jake
 * @version 2017.03.03
 */
public class LevenshteinTests extends TestCase {
    private Levenshtein lev1;
    private Levenshtein lev2;
    private Levenshtein lev3;
    private Levenshtein lev4;
    private Levenshtein lev5;
    private Levenshtein lev6;
    private Levenshtein lev7;
    private Levenshtein lev8;
    private Levenshtein lev9;

    /**
     * setUp method runs before tests
     */
    public void setUp() {
        lev1 = new Levenshtein("", "a");
        lev2 = new Levenshtein("a", "");
        lev3 = new Levenshtein("", "");
        lev4 = new Levenshtein("clap", "cram");
        lev5 = new Levenshtein("mitt", "smitten");
        lev6 = new Levenshtein("start", "cart");
        lev7 = new Levenshtein("love", "love");
        lev8 = new Levenshtein("a", "b");
        lev9 = new Levenshtein("this is a long sentence",
                "this sentence is long");
    }

    // ----------------------------------------------------------
    /**
     * Test the distance method.
     */
    public void testDistance() {
        assertEquals(1, lev1.distance());
        assertEquals(1, lev2.distance());
        assertEquals(0, lev3.distance());
        assertEquals(2, lev4.distance());
        assertEquals(3, lev5.distance());
        assertEquals(2, lev6.distance());
        assertEquals(0, lev7.distance());
        assertEquals(1, lev8.distance());
        assertEquals(15, lev9.distance());
    }
}
