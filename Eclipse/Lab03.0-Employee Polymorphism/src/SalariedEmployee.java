// -------------------------------------------------------------------------
/**
 * @author jake
 * @version 2017.02.10
 */
public class SalariedEmployee extends Employee {

    // ~ Instance/static fields ................................................

    /**
     * @param name
     *            sets name
     * @param payRate
     *            sets payRate
     */
    public SalariedEmployee(String name, double payRate) {
        super(name, payRate);
    }

    /**
     * @return payRate
     */
    public double weeklyPay() {
        return payRate;
    }

    /**
     * @param otherParticipant
     * @return otherParticipant is joining this in a conference
     */
    public String meetWith(SalariedEmployee otherParticipant) {
        return otherParticipant.getName() + " is joining " + this.getName()
                + " in a conference";
    }
}