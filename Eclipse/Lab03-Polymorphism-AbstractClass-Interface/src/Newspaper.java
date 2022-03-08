/**
 * @author jake
 * @version 2017.02.13
 */
public abstract class Newspaper implements BundledSubscription {
    private int idNumber;
    private String title;
    private int numberCopies;
    private double price;

    /**
     * @param inIdNumber
     *            sets idNumber
     * @param inTitle
     *            sets title
     * @param inNumberCopies
     *            sets numberCopies
     * @param inPrice
     *            sets price
     */
    public Newspaper(int inIdNumber, String inTitle, int inNumberCopies,
            double inPrice) {
        idNumber = inIdNumber;
        title = inTitle;
        numberCopies = inNumberCopies;
        price = inPrice;
    }

    /**
     * @return monthly cost of newspaper
     */
    public abstract double monthlyCost();

    /**
     * @param inBundledSubscription
     *            gets other BundledSubscription
     * @return title and otherTitle subscriptions are bundled
     */
    @Override
    public String bundledWith(BundledSubscription inBundledSubscription) {
        return this.getTitle() + " and " + inBundledSubscription.getTitle()
                + " subscriptions are bundled.";
    }

    // ----------------------------------------------------------
    /**
     * @return the id_number of the Newspaper
     */
    public int getIdNumber() {
        return idNumber;
    }

    // ----------------------------------------------------------
    /**
     * @return the title of the Newspaper
     */
    public String getTitle() {
        return title;
    }

    // ----------------------------------------------------------
    /**
     * @return the number_copies of the Newspaper
     */
    public int getNumberCopies() {
        return numberCopies;
    }

    // ----------------------------------------------------------
    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }
}
