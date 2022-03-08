import java.util.ArrayList;

/**
 * Stores the term as well as the array of files that has a tfidf greater than a
 * threshold
 * 
 * @author Romeo - 33%
 * @author Matt - 33%
 * @author jake - 33%
 * @version 2017.04.30
 * 
 */
public class Term implements Comparable<Term> {
    private String term;
    private ArrayList<String> files;

    /**
     * Sets term and arraylist O(1)
     * 
     * @param inTerm
     * @param inFiles
     */
    public Term(String inTerm, ArrayList<String> inFiles) {
        setTerm(inTerm);
        setFiles(inFiles);
    }

    /**
     * returns the term O(1)
     * 
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * sets the term O(1)
     * 
     * @param term
     *            the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * returns the files containing the term O(1)
     * 
     * @return the files
     */
    public ArrayList<String> getFiles() {
        return files;
    }

    /**
     * sets the arraylist containing the files O(1)
     * 
     * @param files
     *            the files to set
     */
    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }

    /**
     * compares the Term objects only on the term string O(1)
     * 
     * @param o
     *            the term to compare to
     * @return the int that relates the terms
     */
    @Override
    public int compareTo(Term o) {
        return this.getTerm().compareTo(o.getTerm());
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Term){
            return this.getTerm().equals(((Term) o).getTerm());
        }
        return false;
    }
    
    @Override
    public String toString(){
        return term;
    }
}
