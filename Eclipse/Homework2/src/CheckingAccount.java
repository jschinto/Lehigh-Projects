/**
 * @author jake
 * @version 2017.02.05
 */
public class CheckingAccount extends BankAccount {
    private double fee;

    // constructor not listed as needed

    /**
     * sets balance to balance - fee
     */
    public void deductFee() {
        this.totalBalance = this.getBalance() - fee;
    }
}
