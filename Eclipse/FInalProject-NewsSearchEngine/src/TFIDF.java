import java.util.ArrayList;
import java.util.HashMap;

/**
 * Calculates the TFIDF value of a term in a file
 * 
 * @author Romeo - 33%
 * @author Matt - 33%
 * @author jake - 33%
 * @version 2017.04.30
 * 
 */
public class TFIDF {
    private String term;
    private double tf;
    private double idf;
    private double tfidf;

    /**
     * Constructor sets the term and initial values O(1)
     * 
     * @param term
     *            sets term
     *
     */
    public TFIDF(String term) {
        this.term = term;
        this.tf = 0.0;
        this.idf = 0.0;
        this.tfidf = 0.0;
    }

    /**
     * returns the term O(1)
     * 
     * @return term
     * 
     */
    public String getTerm() {
        return term;
    }

    /**
     * returns the tf O(1)
     * 
     * @return tf
     * 
     */
    public double getTf() {
        return tf;
    }

    /**
     * returns the idf O(1)
     * 
     * @return idf
     * 
     */
    public double getIdf() {
        return idf;
    }

    /**
     * returns the tfidf O(1)
     * 
     * @return tfidf
     * 
     */
    public double getTfidf() {
        return tfidf;
    }

    /**
     * Computes the Term frequency O(n)
     * 
     * @param terms
     *            list of all terms in document
     * @param term
     *            the term to count
     */
    public void tf(ArrayList<String> terms, String term) {
        int count = Count(terms, term);
        tf = ((double) count) / ((double) terms.size());
    }

    /**
     * Computes the Inverse Document Frequency O(n)
     * 
     * @param terms
     * @param term
     */
    public void idf(HashMap<String, ArrayList<String>> terms, String term) {
        int count = 0;
        for (String key : terms.keySet()) {
            if (terms.get(key).contains(term)) {
                count++;
            }
        }
        idf = Math.log10(((double) terms.size()) / ((double) count));
    }

    /**
     * Computes the TFIDF O(1)
     */
    public void tfidf() {
        tfidf = tf * idf;
    }

    /**
     * Counts the times the term appears in Array O(n)
     * 
     * @param List
     * @param term
     * @return count
     */
    public int Count(ArrayList<String> List, String term) {
        int count = 0;
        for (String x : List) {
            if (x.equals(term)) {
                count++;
            }
        }
        return count;
    }
}
