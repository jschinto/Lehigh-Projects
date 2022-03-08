
// -------------------------------------------------------------------------
/**
 *  @author Varun
 *  @version Feb 5, 2016
 */
public class SuperArrayTest extends student.TestCase
{

    private SuperArray sua;

    /**
     * sets up for test
     */
    public void setUp()
    {
        String[] a = { "a", "b", "c", "d" };
        sua = new SuperArray(a);
    }

    // ----------------------------------------------------------
    /**
     * Test {@link SuperArray#addAll(Object[])}.
     */
    public void testAddAll()
    {
        //The original size of SuA is 4
        assertEquals(4, sua.getSize());
        String test = "testString";
        sua.add(test);
        //After adding the string test, the size should be 5
        assertEquals(5, sua.getSize());
    }

}
