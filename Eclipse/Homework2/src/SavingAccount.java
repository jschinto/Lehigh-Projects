/**
 * @author jake
 * @version 2017.02.05
 */
public class SavingAccount extends BankAccount {
    private double intestRate; // misspelled on question

    // constructor not listed as needed

    /**
     * sets balance to balance + interest
     */
    public void addInterest() {
        // it doesn't say to update, but I assumed it meant to
        this.totalBalance = this.getBalance() + intestRate;
    }
}
