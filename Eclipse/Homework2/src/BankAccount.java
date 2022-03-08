/**
 * @author jake
 * @version 2017.02.05
 */
public class BankAccount {
    private int accountNumber;
    protected double totalBalance;

    // constructor not listed as needed

    /**
     * @param amount
     *            takes and returns nothing
     */
    public void deposit(double amount) {
        totalBalance = amount + totalBalance;
        // not listed in question but i assumed it does this
        // returns nothing
    }

    /**
     * @param amount
     *            takes and returns nothing
     */
    public void withdraw(double amount) {
        totalBalance = totalBalance - amount;
        // not listed in question but i assumed it does this
        // returns nothing
    }

    /**
     * @return totalBalance
     */
    public double getBalance() {
        return totalBalance;
    }
}
