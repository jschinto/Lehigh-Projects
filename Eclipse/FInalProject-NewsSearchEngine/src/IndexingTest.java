import java.util.HashMap;
import student.TestCase;

/**
 * @author Romeo - 33%
 * @author Matt - 33%
 * @author jake - 33%
 * @version 2017.04.30
 */
public class IndexingTest extends TestCase {
    private Indexing index;
    private Tagging tags;
    private HashMap<String, HashMap<String, Double>> hm;
    private HashMap<String, Double> tfidf;

    /**
     * runs before the tests
     * 
     */
    public void setUp() {
        tfidf = new HashMap<String, Double>();
        hm = new HashMap<String, HashMap<String, Double>>();
        tfidf.put("1", 2d);
        tfidf.put("2", 1d);
        final HashMap<String, Double> tfidf1 = tfidf;
        hm.put("Apple", tfidf1);
        tfidf.clear();
        tfidf.put("3", 3d);
        tfidf.put("2", 1d);
        final HashMap<String, Double> tfidf2 = tfidf;
        hm.put("Java", tfidf2);
        tfidf.clear();
        tfidf.put("1", 4d);
        final HashMap<String, Double> tfidf3 = tfidf;
        hm.put("App", tfidf3);
        tfidf.put("3", 3d);
        tfidf.put("2", 1d);
        final HashMap<String, Double> tfidf4 = tfidf;
        hm.put("Jav", tfidf4);
        tfidf.clear();
        tfidf.put("1", 4d);
        final HashMap<String, Double> tfidf5 = tfidf;
        hm.put("Appl", tfidf5);
        index = new Indexing(hm);
        tags = new Tagging(null);
    }

    /**
     * tests createIndex() worst case: O(n^2) average case: O(n*log(n))
     */
    public void testCreateIndex() {
        index.createIndex();
        assertNotNull(index.getTree());
        assertNotNull(index.getTree().find(new Term("App", null)));
    }

    /**
     * tests toInOrderIndex O(n)
     */
    public void testToInOrderIndex() {
        index.createIndex();
        assertEquals("App, Appl, Apple, Jav, Java", index.toInOrderIndex());
        hm.clear();
        index = new Indexing(hm);
        index.createIndex();
        assertEquals("", index.toInOrderIndex());
        hm = tags.getTags();
        index = new Indexing(hm);
        index.createIndex();
        assertNotNull(index.toInOrderIndex());
    }

    /**
     * tests search() average: O(log(n)) worst: O(n)
     */
    public void testSearch() {
        index.createIndex();
        assertTrue(index.search("Apple").contains("1"));
        assertFalse(index.search("Apple").contains("3"));
        assertFalse(index.search("Jav").contains("2"));
        hm = tags.getTags();
        index = new Indexing(hm);
        index.createIndex();
        assertNotNull(
                index.search((String) tags.getTags().keySet().toArray()[0]));
    }
}
