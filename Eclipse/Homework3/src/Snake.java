/**
 * @author jake
 * @version 2017.02.12
 */
public class Snake extends Pets {
    /**
     * @param inName
     *            sets name
     * @param inBreed
     *            sets breed
     * @param inAge
     *            sets age
     */
    public Snake(String inName, String inBreed, int inAge) {
        super(inName, inBreed, inAge);
    }

    /**
     * @return name Slithers
     */
    @Override
    public String Move() {
        return this.getName() + " Slithers";
    }
}