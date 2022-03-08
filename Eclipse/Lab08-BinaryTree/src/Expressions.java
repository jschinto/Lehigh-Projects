/**
 * @author jake
 * @version 2017.05.08
 */
public class Expressions {
    /**
     * main method
     * 
     * @param args
     *            arguments for the main method
     */
    public static void main(String[] args) {
        BinaryTree<String> tree1 = new BinaryTree<String>("a");
        BinaryTree<String> tree2 = new BinaryTree<String>("b");
        BinaryTree<String> tree3 = new BinaryTree<String>("-", tree1, tree2);
        BinaryTree<String> tree4 = new BinaryTree<String>("c");
        BinaryTree<String> tree5 = new BinaryTree<String>("d");
        BinaryTree<String> tree6 = new BinaryTree<String>("+", tree4, tree5);
        BinaryTree<String> tree7 = new BinaryTree<String>("e");
        BinaryTree<String> tree8 = new BinaryTree<String>("/", tree6, tree7);
        BinaryTree<String> tree9 = new BinaryTree<String>("*", tree3, tree8);

        System.out.println(tree9.toInOrderString());
        System.out.println(tree9.toPreOrderString());
        System.out.println(tree9.toPostOrderString());
    }
}
