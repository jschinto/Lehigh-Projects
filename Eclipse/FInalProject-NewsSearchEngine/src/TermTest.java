import java.util.ArrayList;

import student.TestCase;

/**
 * @author Romeo - 33%
 * @author Matt - 33%
 * @author jake - 33%
 * @version 2017.04.30
 */
public class TermTest extends TestCase {
    private Term a;
    private Term b;
    private ArrayList<String> files;

    /**
     * runs before each test
     */
    public void setUp() {
        files = new ArrayList<String>();
        files.add("1");
        files.add("3");
        a = new Term("java", files);
        b = new Term("javaa", null);
    }

    /**
     * tests getters and setters
     */
    public void testGettersSetters() {
        assertEquals("java", a.getTerm());
        assertEquals(files, a.getFiles());
        assertEquals("javaa", b.getTerm());
        assertNull(b.getFiles());
        a.setTerm("ja");
        b.setFiles(files);
        assertEquals("ja", a.getTerm());
        assertEquals(files, b.getFiles());
    }

    /**
     * test compareTo()
     */
    public void testCompareTo() {
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        b.setTerm("java");
        assertEquals(0, a.compareTo(b));
    }

    /**
     * test equals()
     */
    public void testEquals() {
        assertFalse(a.equals(b));
        b.setTerm("java");
        assertTrue(a.equals(b));
    }

    /**
     * test toString()
     */
    public void testToString() {
        assertEquals("java", a.toString());
        assertEquals("javaa", b.toString());
    }
}
