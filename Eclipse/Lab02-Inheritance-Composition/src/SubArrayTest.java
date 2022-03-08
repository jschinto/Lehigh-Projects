/**
 * @author jake
 * @version 2017.02.03
 */
public class SubArrayTest extends student.TestCase
{

    private SubArray sub;
    private String[] b = { "1", "2", "3", "4" };

    /**
     * sets up the test
     */
    public void setUp()
    {
        String[] a = { "a", "b", "c", "d" };
        sub = new SubArray(a);
        sub = new SubArray();
    }

    // ----------------------------------------------------------
    /**
     * Test {@link SubArray#addAll(Object[])}.
     */
    public void testAddAll()
    {
        //The original size of SuA is 4
        assertEquals(0, sub.getSize());
        String test = "testString";
        sub.add(test);
        //After adding the string test, the size should be 5
        assertEquals(1, sub.getSize());
        assertEquals(1, sub.getAddCount());
        sub.addAll(b);
        assertEquals(5, sub.getSize());
        assertEquals(9, sub.getAddCount());
    }

}
