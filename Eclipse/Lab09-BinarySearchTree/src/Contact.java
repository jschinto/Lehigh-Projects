/**
 * @author jake
 * @version 2017.04.22
 */
public class Contact implements Comparable<Contact> {
    private String first;
    private String last;
    private String phone;

    /**
     * @param inFirst
     *            sets first
     * @param inLast
     *            sets last
     * @param inPhone
     *            sets phone
     */
    public Contact(String inFirst, String inLast, String inPhone) {
        first = inFirst;
        last = inLast;
        phone = inPhone;
    }

    @Override
    public int compareTo(Contact o) {
        if (!first.equals(o.getFirst())) {
            return first.compareTo(o.getFirst());
        }
        else if (!last.equals(o.getLast())) {
            return last.compareTo(o.getLast());
        }
        else if (!phone.equals(o.getPhone())) {
            return phone.compareTo(o.getPhone());
        }
        return 0;
    }

    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
}
