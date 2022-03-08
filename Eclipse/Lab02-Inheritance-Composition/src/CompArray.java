/**
 * @author jake
 * @version 2017.02.03
 */
public class CompArray {
    private SuperArray sa;
    private int addCount;

    /**
     * constructor
     */
    public CompArray() {
        SuperArray sar = new SuperArray();
        this.sa = sar;
        this.addCount = 0;
    }

    /**
     * @param sua
     *            sets sa
     */
    public CompArray(SuperArray sua) {
        sa = sua;
    }

    /**
     * @param o
     *            adds to count and adds
     */
    public void add(Object o) {
        // your code here
        addCount++;
        sa.add(o);
    }

    /**
     * @param c
     *            adds to count and adds all
     */
    public void addAll(Object[] c) {
        // your code here
        addCount += c.length;
        sa.addAll(c);
    }

    /**
     * @return addCount
     */
    public int getAddCount() {
        return this.addCount;
    }
}
