
/**
 * @author jake
 * @version 2017.02.05
 */
public class Pets {
    private String name;
    private String breed;
    private int age;

    /**
     * @param inName
     *            sets name
     * @param inBreed
     *            sets breed age set to 0
     */
    public Pets(String inName, String inBreed) {
        setName(inName);
        setBreed(inBreed);
        age = 0;
    }

    /**
     * @return name of pet
     */
    public String Move() {
        return this.getName();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * @param breed
     *            the breed to set
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }
}