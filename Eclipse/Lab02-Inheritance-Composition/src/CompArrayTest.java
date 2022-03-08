/**
 * @author jake
 * @version 2017.02.03
 */
public class CompArrayTest extends student.TestCase
{

    private CompArray sub;
    private String[] b = { "1", "2", "3", "4" };

    /**
     * sets up the test
     */
    public void setUp()
    {
        sub = new CompArray(new SuperArray());
        sub = new CompArray();
    }

    // ----------------------------------------------------------
    /**
     * Test {@link CompArray#addAll(Object[])}.
     */
    public void testAddAll()
    {
        //The original size of SuA is 4
        assertEquals(0, sub.getAddCount());
        String test = "testString";
        sub.add(test);
        //After adding the string test, the size should be 5
        assertEquals(1, sub.getAddCount());
        sub.addAll(b);
        assertEquals(5, sub.getAddCount());
    }

}
