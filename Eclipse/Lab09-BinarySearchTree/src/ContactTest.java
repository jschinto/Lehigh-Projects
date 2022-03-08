import student.TestCase;

/**
 * @author jake
 * @version 2017.04.22
 */
public class ContactTest extends TestCase {
    private Contact con1;
    private Contact con2;
    private Contact con3;
    private Contact con4;
    private Contact con5;

    /**
     * first to run
     */
    public void setUp() {
        con1 = new Contact("Jake", "Schinto", "1234567");
        con2 = new Contact("Jake", "Schinto", "1234567");
        con3 = new Contact("Jak", "Schinto", "1234567");
        con4 = new Contact("Jake", "Schint", "1234567");
        con5 = new Contact("Jake", "Schinto", "1234568");
    }

    /**
     * tests compareTo
     */
    public void testCompareTo() {
        assertEquals(con1.compareTo(con2), 0);
        assertTrue(con1.compareTo(con3) > 0);
        assertTrue(con1.compareTo(con4) > 0);
        assertTrue(con1.compareTo(con5) < 0);
    }
}
