// -------------------------------------------------------------------------
/**
 * @author jake
 * @version 2017.02.10
 */
public abstract class Employee implements MeetingParticipant {
    // ~ Instance/static fields ................................................

    protected String name; // The employee's name.
    protected double payRate; // The employee's pay rate.

    // ~ Constructor ...........................................................
    /**
     * @param name
     *            sets name
     * @param payRate
     *            sets payRate
     */
    public Employee(String name, double payRate) {
        this.name = name;
        this.payRate = payRate;
    }

    public String meetWith(MeetingParticipant otherParticipant) {
        return this.getName() + " is meeting with "
                + otherParticipant.getName();
    }

    /**
     * Gets the employee's name.
     * 
     * @return the employee's name
     */
    public String getName() {
        return name;
    }

    // ----------------------------------------------------------
    /**
     * Gets the pay rate.
     * 
     * @return the pay rate
     */
    public double getPayRate() {
        return payRate;
    }

    /**
     * @return weeklyPay
     */
    public abstract double weeklyPay();
}
