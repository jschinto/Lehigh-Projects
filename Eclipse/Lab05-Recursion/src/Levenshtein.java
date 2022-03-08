import java.util.HashMap;

//-------------------------------------------------------------------------
/**
 * @author jake
 * @version 2017.03.03
 */
public class Levenshtein {
    private String string1;
    private String string2;
    private HashMap<String, Integer> map;

    /**
     * @param inString1
     *            sets string1
     * @param inString2
     *            sets string2
     */
    public Levenshtein(String inString1, String inString2) {
        string1 = inString1;
        string2 = inString2;
        map = new HashMap<String, Integer>();
    }

    /**
     * 
     * @param start1
     *            start index of string1
     * @param length1
     *            length of string1
     * @param start2
     *            start index of string2
     * @param length2
     *            length of string2
     * @return minimum distance
     */
    private int distance(int start1, int length1, int start2, int length2) {
        String key = start1 + "," + length1 + "," + start2 + "," + length2;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        else if (length1 == 0 || length2 == 0) {
            return length1 + length2;
        }
        else {
            int delete = distance(start1 + 1, length1 - 1, start2, length2) + 1;
            int insert = distance(start1, length1, start2 + 1, length2 - 1) + 1;
            int change = distance(start1 + 1, length1 - 1, start2 + 1,
                    length2 - 1)
                    + ((string1.charAt(start1) != string2.charAt(start2) ? 1
                            : 0));
            map.put(key, Math.min(delete, Math.min(insert, change)));
            return Math.min(delete, Math.min(insert, change));
        }
    }

    /**
     * @return distance(0,length1,0,length2)
     */
    public int distance() {
        return distance(0, string1.length(), 0, string2.length());
    }
}
