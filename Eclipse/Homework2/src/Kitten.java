/**
 * @author jake
 * @version 2017.02.05
 */
public class Kitten extends Pets {
    /**
     * @param inName
     * @param inBreed
     *            calls super
     */
    public Kitten(String inName, String inBreed) {
        super(inName, inBreed);
    }

    /**
     * @return Name + action
     */
    public String Move() {
        return super.Move() + " Runs";
    }
}
