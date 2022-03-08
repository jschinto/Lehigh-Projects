import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Tagging class takes the result from the file parser and tags each file with
 * the terms associated with it
 * 
 * @author Romeo - 33%
 * @author Matt - 33%
 * @author jake - 33%
 * @version 2017/01/5
 * 
 * 
 */
public class Tagging {
    private HashMap<String, HashMap<String, Double>> hm;
    private RSSFeedParser parser1;
    private RSSFeedParser parser2;

    /**
     * Constructor parses the file and sends the terms to get TFIDF calculated
     * O(n^2)
     * 
     * @param url
     *            file url
     * 
     * 
     */
    public Tagging(File url) {
        FilesParser fp = new FilesParser();
        final File folder = url;
        try {
            fp.parse(folder);
        }
        catch (Exception e1) {
            // do nothing
        }

        hm = new HashMap<String, HashMap<String, Double>>();
        HTMLparser hp1 = new HTMLparser();
        HTMLparser hp2 = new HTMLparser();
        parser1 = new RSSFeedParser("http://feeds.foxnews.com/foxnews/latest");
        parser2 = new RSSFeedParser("http://feeds.slate.com/slate");
        Feed feed1 = parser1.readFeed();
        Feed feed2 = parser2.readFeed();
        for (FeedMessage message : feed1.getMessages()) {
            try {
                hp1.parse(message.getLink());
            }
            catch (Exception e) {
                // do nothing
            }
        }
        for (FeedMessage message : feed2.getMessages()) {
            try {
                hp2.parse(message.getLink());
            }
            catch (Exception e) {
                // do nothing
            }
        }

        HashMap<String, ArrayList<String>> hm1 = hp1.getContent();
        HashMap<String, ArrayList<String>> hm2 = hp2.getContent();
        HashMap<String, ArrayList<String>> hm3 = fp.getContent();

        for (String key : hm2.keySet()) {
            if (!hm1.containsKey(key)) {
                hm1.put(key, hm2.get(key));
            }
        }
        for (String key : hm3.keySet()) {
            if (!hm1.containsKey(key)) {
                hm1.put(key, hm3.get(key));
            }
        }

        TFIDF(hm1);
    }

    /**
     * Parses and the files and assign it to the fileHm Get each term and call
     * the TFIDF from the TFIDF class and push the article, the name of the
     * file, and the TFIDF value to tags. Gets the TFIDF and stores it in a
     * HashMap if TFIDF is greater than zero O(n^2)
     * 
     * @param term
     *            list of terms
     * 
     * 
     */
    public void TFIDF(HashMap<String, ArrayList<String>> term) {
        for (String file : term.keySet()) {
            for (String keyword : term.get(file)) {
                if (!hm.containsKey(keyword)) {
                    TFIDF tfidf = new TFIDF(keyword);
                    tfidf.tf(term.get(file), keyword);
                    tfidf.idf(term, keyword);
                    tfidf.tfidf();
                    if (tfidf.getTfidf() > 0.001) {
                        HashMap<String, Double> value = new HashMap<String, Double>();
                        value.put(file, tfidf.getTfidf());
                        hm.put(keyword, value);
                        System.out.println(keyword + " - " + tfidf.getTfidf()
                                + " - " + file);
                    }
                }
                else if (!hm.get(keyword).containsKey(file)) {
                    TFIDF tfidf = new TFIDF(keyword);
                    tfidf.tf(term.get(file), keyword);
                    tfidf.idf(term, keyword);
                    tfidf.tfidf();
                    if (tfidf.getTfidf() > 0.001) {
                        hm.get(keyword).put(file, tfidf.getTfidf());
                        System.out.println(keyword + " - " + tfidf.getTfidf()
                                + " - " + file);
                    }
                }
            }
        }
    }

    /**
     * returns the hashmap O(1)
     * 
     * @return hm
     */
    public HashMap<String, HashMap<String, Double>> getTags() {
        return hm;
    }
}