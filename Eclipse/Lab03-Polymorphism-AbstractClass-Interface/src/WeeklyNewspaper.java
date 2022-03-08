/**
 * @author jake
 * @version 2017.02.13
 */
public class WeeklyNewspaper extends Newspaper {
    /**
     * @param idNumber
     *            sets id_number
     * @param title
     *            sets title
     * @param numberCopies
     *            sets number_copies
     * @param price
     *            sets price
     */
    public WeeklyNewspaper(int idNumber, String title, int numberCopies,
            double price) {
        super(idNumber, title, numberCopies, price);
    }

    /**
     * @return 4*price*number_copies
     */
    @Override
    public double monthlyCost() {
        return 4 * this.getPrice() * this.getNumberCopies();
    }

    /**
     * @param otherNewspaper
     *            gets other WeeklyNewspaper
     * @return otherTitle subscription is bundled with title
     */
    public String bundledWith(WeeklyNewspaper otherNewspaper) {
        return otherNewspaper.getTitle() + " subscription is bundled with "
                + this.getTitle() + ".";
    }
}
