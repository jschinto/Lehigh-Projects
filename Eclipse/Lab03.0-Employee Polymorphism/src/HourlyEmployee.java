// -------------------------------------------------------------------------
/**
 * @author jake
 * @version 2017.02.10
 */
public class HourlyEmployee extends Employee {
    /**
     * @param name
     *            sets name
     * @param payRate
     *            sets payRate
     */
    public HourlyEmployee(String name, double payRate) {
        super(name, payRate);
    }

    /**
     * @return 40*payRate
     */
    @Override
    public double weeklyPay() {
        return 40 * payRate;
    }
}