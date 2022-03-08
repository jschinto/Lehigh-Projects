
/**
 * @author jake
 * @version 2017.01.29
 */
public class Person {
    private int age;
    private String name;

    /**
     * @param inName
     *            sets name
     * @param inAge
     *            sets age
     */
    public Person(String inName, int inAge) {
        age = inAge;
        name = inName;
    }

    /**
     * no-arg constructor with default Jake and 18
     */
    public Person() {
        age = 18;
        name = "Jake";
    }

    /**
     * speak method prints name
     */
    public void speak() {
        System.out.println(this.getName());
    }

    /**
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age
     *            sets new age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            sets new name
     */
    public void setName(String name) {
        this.name = name;
    }
}
