import student.*;

// -------------------------------------------------------------------------
/**
 * Unit tests for the {@link Computer} class.
 * 
 * @author jake
 * @version 2017.01.27
 */
public class ComputerTest extends student.TestCase {
    // ~ Instance/static fields ...............................................
    private Computer c;
    // ~ Constructor ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new ComputerTest object.
     */
    public ComputerTest() {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }

    // ~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture. Called before every test case method.
     */
    public void setUp() {
        /* Insert your own setup code here */
        c = new Computer("Intel 4", 2, 200);
    }

    // ----------------------------------------------------------
    /* Insert your own test methods here */
    /**
     * this is the test for processor of Computer class
     */
    public void testSetProcessor() {
        c.setProcessor("Intel 2017");
        assertEquals("Intel 2017", c.getProcessor());
    }

    /**
     * this is the test for numCores of Computer class
     */
    public void testSetNumCores() {
        c.setNumCores(3);
        assertEquals(3, c.getNumCores());
    }

    /**
     * this is the test for processorSpeed of Computer class
     */
    public void testSetProcessorSpeed() {
        c.setProcessorSpeed(100);
        assertEquals(100d, c.getProcessorSpeed(), 0.001);
    }

    /**
     * this is the test for computePower of Computer class
     */
    public void testComputePower() {
        c.setNumCores(3);
        c.setProcessorSpeed(100);
        assertEquals(300d, c.computePower(), 0.001);
    }

    /**
     * this is the test for toString of Computer class
     */
    public void testToString() {
        assertEquals("Intel 4, 2 cores at 200.0GHz", c.toString());
        c.setNumCores(1);
        assertEquals("Intel 4, 1 core at 200.0GHz", c.toString());
    }
}
