import java.io.File;

/**
 * @author Romeo - 33%
 * @author Matt - 33%
 * @author jake - 33%
 * @version 2017.04.30
 */
import java.util.ArrayList;
import java.util.HashMap;

import student.*;

/**
 * @author jake
 * @version 2017.05.09
 */
public class TaggingTest extends TestCase {
    private Tagging test;

    @Override
    public void setUp() throws Exception {
        FilesParser fp = new FilesParser();
        final File folder = new File(
                "/Users/jake/Documents/Eclipse/ExampleTexts");
        fp.parse(folder);

        HashMap<String, ArrayList<String>> hm = fp.getContent();
        test = new Tagging(folder);
    }

    /**
     * test for tfidf method O(n^2)
     */
    public void testTfidf() {
        HashMap<String, Double> t = new HashMap<String, Double>();
        for (String key : test.getTags().keySet()) {
            t = test.getTags().get(key);
            for (String key1 : t.keySet()) {
                assertTrue(t.get(key1) > 0);
                System.out.println(t.get(key1)+ " - " +key+" - "+ key1);
            }
        }
    }
}
