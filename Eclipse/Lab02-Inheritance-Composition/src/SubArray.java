
/**
 * @author jake
 * @version 2017.02.03
 */
public class SubArray extends SuperArray {
    private int addCount;

    /**
     * @param arr
     *            calls super and sets addCount to 0
     */
    public SubArray(Object[] arr) {
        super(arr);
        addCount = 0;
    }

    /**
     * no arg constructor
     */
    public SubArray() {
        super();
        addCount = 0;
    }

    /**
     * @return the addCount
     */
    public int getAddCount() {
        return addCount;
    }

    /**
     * @param entry
     *            adds to addCount and then calls the super
     */
    public void add(Object entry) {
        addCount++;
        super.add(entry);
    }

    /**
     * @param c
     *            adds length to addCount and then calls super
     */
    public void addAll(Object[] c) {
        addCount += c.length;
        super.addAll(c);
    }
}
