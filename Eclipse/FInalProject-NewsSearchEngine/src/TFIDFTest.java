import student.TestCase;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Romeo - 33%
 * @author Matt - 33%
 * @author jake - 33%
 * @version 2017.05.01
 */
public class TFIDFTest extends TestCase {

    private TFIDF test;
    private ArrayList<String> terms;
    private HashMap<String, ArrayList<String>> terms1;
    private String term;

    /**
     * empty constructor
     */
    public TFIDFTest() {
        // empty constructor
    }

    /**
     * set up method
     */
    public void setUp() {
        term = "HI";
        test = new TFIDF(term);
        terms = new ArrayList<String>();
        terms.add("HI");
        terms.add("HI");
        terms.add("BYE");
        terms.add("HI");
        terms.add("LOL");
        terms1 = new HashMap<String, ArrayList<String>>();
        terms1.put("B", terms);
        terms1.put("BY", new ArrayList<String>());
        terms1.put("BYE", new ArrayList<String>());
        terms1.put("BYEE", terms);

    }

    /**
     * tests count method
     */
    public void testCount() {
        assertEquals(3, test.Count(terms, term));
    }

    /**
     * test tf method O(n)
     */

    public void testTF() {
        test.Count(terms, term);
        test.tf(terms, term);
        assertEquals(0.6, test.getTf(), 0.0001);
    }

    /**
     * test idf method O(n)
     */

    public void testIDF() {
        test.Count(terms, term);
        test.idf(terms1, term);
        assertEquals(Math.log10(2.0), test.getIdf(), 0.0001);
    }

    /**
     * test tfidf method O(1)
     */

    public void testTFIDF() {
        test.Count(terms, term);
        test.tf(terms, term);
        test.idf(terms1, term);
        test.tfidf();
        ;
        assertEquals(0.6 * Math.log10(2.0), test.getTfidf(), 0.0001);
    }
}
