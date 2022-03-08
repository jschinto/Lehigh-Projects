/**
 * @author jake
 * @version 2017.02.12
 */
public abstract class Pets implements Motion {
    private String name;
    private String breed;
    private int age;

    /**
     * @param inName
     *            sets name
     * @param inBreed
     *            sets breed
     * @param inAge
     *            sets age
     */
    public Pets(String inName, String inBreed, int inAge) {
        setName(inName);
        setBreed(inBreed);
        setAge(inAge);
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

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age
     *            the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
}