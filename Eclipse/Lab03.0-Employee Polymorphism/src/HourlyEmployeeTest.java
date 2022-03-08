import student.TestCase;

//-------------------------------------------------------------------------
/**
 * @author jake
 * @version 2017.02.10
 */
public class HourlyEmployeeTest extends TestCase {
    // ~ Instance/static fields ...............................................
    HourlyEmployee emp;
    // will initialize in your setUp method.

    // ~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture. Called before every test case method.
     */
    public void setUp() {
        emp = new HourlyEmployee("Fred Ziffle", 1.01);
    }

    /**
     * test getters
     */
    public void testGetName() {
        assertEquals("Fred Ziffle", emp.getName());
        assertEquals(1.01, emp.getPayRate(), 0.001);
    }

    /**
     * test weeklyPay
     */
    public void testWeeklyPay() {
        assertEquals(40.40, emp.weeklyPay(), 0.001);
    }

    /**
     * test meetWith
     */
    public void testMeetWith() {
        assertEquals("Fred Ziffle is meeting with Chris",
                emp.meetWith(new HourlyEmployee("Chris", 5)));
    }
}
