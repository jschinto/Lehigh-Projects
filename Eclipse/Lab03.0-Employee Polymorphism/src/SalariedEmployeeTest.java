import student.TestCase;

//-------------------------------------------------------------------------
/**
 * @author jake
 * @version 2017.02.10
 */
public class SalariedEmployeeTest extends TestCase
{
    //~ Instance/static fields ...............................................

    // will initialize in your setUp method.
    SalariedEmployee emp;

    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        emp = new SalariedEmployee("Jake", 5.01);
    }

    /**
     * test weeklyPay
     */
    public void testWeeklyPay(){
        assertEquals(5.01,emp.weeklyPay(),0.001);
    }
    /**
     * test meetWith
     */
    public void testMeetWith(){
        assertEquals("Chris is joining Jake in a conference",emp.meetWith(new SalariedEmployee("Chris",4)));
    }
}
