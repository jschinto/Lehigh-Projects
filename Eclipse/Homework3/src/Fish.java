/**
 * @author jake
 * @version 2017.02.12
 */
public class Fish extends Pets {
    /**
     * @param inName
     *            sets name
     * @param inBreed
     *            sets breed
     * @param inAge
     *            sets age
     */
    public Fish(String inName, String inBreed, int inAge) {
        super(inName, inBreed, inAge);
    }

    /**
     * @return name Swims
     */
    @Override
    public String Move() {
        return this.getName() + " Swims";
    }
}