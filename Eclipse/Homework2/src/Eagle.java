/**
 * @author jake
 * @version 2017.02.05
 */
public class Eagle extends Pets {
    /**
     * @param inName
     * @param inBreed
     *            calls super
     */
    public Eagle(String inName, String inBreed) {
        super(inName, inBreed);
    }

    /**
     * @return Name + action
     */
    public String Move() {
        return super.Move() + " Flies";
    }
}
