import student.*;

// -------------------------------------------------------------------------
/**
 * Unit tests for the {@link SimpleCalculator} class.
 * 
 * @author jake
 * @version 2017.01.29
 */
public class SimpleCalculatorTest extends student.TestCase {
    // ~ Instance/static fields ...............................................
    private SimpleCalculator c;
    // ~ Constructor ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new SimpleCalculatorTest object.
     */
    public SimpleCalculatorTest() {
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
        c = new SimpleCalculator(4, 2, '+');
    }

    // ----------------------------------------------------------
    /* Insert your own test methods here */
    /**
     * test getOperator for correct construction
     */
    public void testGetOperator() {
        assertEquals('+', c.getOperator());
    }

    /**
     * test getOperand1 for correct construction
     */
    public void testGetOperand1() {
        assertEquals(4, c.getOperand1(), 0.01);
    }

    /**
     * test getOperand2 for correct construction
     */
    public void testGetOperand2() {
        assertEquals(2, c.getOperand2(), 0.01);
    }

    /**
     * test setOperator for correct construction
     */
    public void testSetOperator() {
        c.setOperator('-');
        assertEquals('-', c.getOperator());
    }

    /**
     * test setOperand1 for correct construction
     */
    public void testSetOperand1() {
        c.setOperand1(3);
        assertEquals(3, c.getOperand1(), 0.01);
    }

    /**
     * test setOperand2 for correct construction
     */
    public void testSetOperand2() {
        c.setOperand2(6);
        assertEquals(6, c.getOperand2(), 0.01);
    }

    /**
     * test computeOperation method in SimpleCalculator
     */
    public void testComputeOperation() {
        assertEquals(6, c.computeOperation(), 0.01);
        c.setOperator('-');
        assertEquals(2, c.computeOperation(), 0.01);
        c.setOperator('*');
        assertEquals(8, c.computeOperation(), 0.01);
        c.setOperator('/');
        assertEquals(2, c.computeOperation(), 0.01);
    }
}