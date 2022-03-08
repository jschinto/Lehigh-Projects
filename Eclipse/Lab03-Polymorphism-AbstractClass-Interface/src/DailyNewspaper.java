/**
 * @author jake
 * @version 2017.02.13
 */
public class DailyNewspaper extends Newspaper

{
    /**
     * @param idNumber
     *            sets idNumber
     * @param title
     *            sets title
     * @param numberCopies
     *            sets numberCopies
     * @param price
     *            sets price
     */
    public DailyNewspaper(int idNumber, String title, int numberCopies,
            double price) {
        super(idNumber, title, numberCopies, price);
    }

    /**
     * @return 30*price*number_copies
     */
    @Override
    public double monthlyCost() {
        return 30 * this.getPrice() * this.getNumberCopies();
    }

}
