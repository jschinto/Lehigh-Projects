
/**
 * @author jake
 * @version 2017.01.29
 */
public class Kitten {
    private String name;
    private String owner;
    private int age;

    /**
     * @param inName
     *            sets name
     * @param inOwner
     *            sets owner age is set to 0
     */
    public Kitten(String inName, String inOwner) {
        setName(inName);
        setOwner(inOwner);
        age = 0;
    }

    /**
     * increments age by 1
     */
    public void haveBirthday() {
        age++;
    }

    /**
     * @return name is age and belongs to owner
     */
    public String toString() {
        return name + " is " + age + " and belongs to " + owner;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            sets name to (input) the Feline
     */
    public void setName(String name) {
        this.name = name + " the Feline";
    }

    /**
     * @return owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner
     *            sets the owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return age
     */
    public int getAge() {
        return age;
    }
}
