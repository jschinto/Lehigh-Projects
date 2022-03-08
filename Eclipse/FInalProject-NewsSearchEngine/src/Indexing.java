import java.util.ArrayList;
import java.util.HashMap;

/**
 * takes the tagged files and creates a binary search tree of the terms and
 * sorts by alphabetical order of terms
 * 
 * @author Romeo - 33%
 * @author Matt - 33%
 * @author jake - 33%
 * @version 2017.04.30
 * 
 */
public class Indexing {
    HashMap<String, HashMap<String, Double>> input;
    BinarySearchTree<Term> tree;

    /**
     * Constructor takes an input and sets input O(1)
     * 
     * @param inInput
     *            sets input
     */
    public Indexing(HashMap<String, HashMap<String, Double>> inInput) {
        input = inInput;
        tree = new BinarySearchTree<Term>();
    }

    /**
     * populates the binary search tree O(nlogn)
     * 
     * adds files if tfidf is greater than 0.1
     */
    public void createIndex() {
        for (String term : input.keySet()) {
            Term t = new Term(term,
                    new ArrayList<String>(input.get(term).keySet()));
            tree.insert(t);
        }
    }

    /**
     * prints and returns the in order traversal O(n)
     * 
     * @return terms in order traversal
     * 
     */
    public String toInOrderIndex() {
        String result = toInOrderIndex(tree.getRoot());
        System.out.println(result);
        return result;
    }

    private String toInOrderIndex(BinaryNode<Term> root) {
        String result = "";
        if (root == null) {
            return "";
        }
        if (root.getLeft() != null) {
            result += toInOrderIndex(root.getLeft()) + ", ";
        }
        result += root.getElement().toString() + ", ";
        if (root.getRight() != null) {
            result += toInOrderIndex(root.getRight()) + ", ";
        }
        result = result.replaceAll(", $", "");
        return result;
    }

    /**
     * returns the list of files that contains the keyword O(logn)
     * 
     * @param keyword
     *            the term searched for
     * @return the arrayList of files containing the term
     * 
     */
    public ArrayList<String> search(String keyword) {
        Term find = tree.find(new Term(keyword, null));
        if (find == null) {
            return new ArrayList<String>();
        }
        return find.getFiles();
    }

    /**
     * returns the Binary Search Tree mainly for testing O(1)
     * 
     * @return tree
     * 
     */
    public BinarySearchTree<Term> getTree() {
        return tree;
    }
}