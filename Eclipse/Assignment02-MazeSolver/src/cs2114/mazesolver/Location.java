package cs2114.mazesolver;

/**
 * @author jake
 * @version 2017.04.09
 */
public class Location implements ILocation {
    private int x;
    private int y;

    /**
     * @param xIn
     *            sets x
     * @param yIn
     *            sets y
     */
    public Location(int xIn, int yIn) {
        x = xIn;
        y = yIn;
    }

    /**
     * @return x
     */
    @Override
    public int x() {
        return x;
    }

    /**
     * @return y
     */
    @Override
    public int y() {
        return y;
    }

    /**
     * @return new Location one up
     */
    @Override
    public ILocation north() {
        return new Location(x, y - 1);
    }

    /**
     * @return new Location one down
     */
    @Override
    public ILocation south() {
        return new Location(x, y + 1);
    }

    /**
     * @return new Location one left
     */
    @Override
    public ILocation west() {
        return new Location(x - 1, y);
    }

    /**
     * @return new Location one right
     */
    @Override
    public ILocation east() {
        return new Location(x + 1, y);
    }

    /**
     * @return true if x and y are equal
     */
    @Override
    public boolean equals(Object inObject) {
        if (!(inObject instanceof Location)) {
            return false;
        }
        return (((Location) inObject).x() == this.x()
                && ((Location) inObject).y() == this.y());
    }

    /**
     * @return (x, y)
     */
    @Override
    public String toString() {
        return "(" + this.x() + ", " + this.y() + ")";
    }
}
