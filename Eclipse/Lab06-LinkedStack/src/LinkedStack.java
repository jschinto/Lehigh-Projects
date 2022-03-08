import java.util.EmptyStackException;

//-------------------------------------------------------------------------
/**
 * An implementation of the stack data type that uses a linked chain of
 * {@code Node<E>} objects to store its contents.
 *
 * This is a PARTIAL IMPLEMENTATION that needs completion.
 *
 * @param <E>
 *            the type of elements stored in the stack
 *
 * @author Tony Allevato (authored class skeleton)
 * @author jake
 * @version 2017.04.04
 */
public class LinkedStack<E> implements StackInterface<E> {
    // ~ Fields ...............................................................

    private Node<E> top;
    private int size;

    // ~ Constructors .........................................................

    // ----------------------------------------------------------
    /**
     * Constructor
     */
    public LinkedStack() {
        top = new Node<E>(null);
        size = 0;
    }

    // ~ Methods ..............................................................

    // ----------------------------------------------------------
    /**
     * @param item
     *            node item
     */
    public void push(E item) {
        Node<E> a = top;
        top = new Node<E>(item);
        top.join(a);
        size++;
    }

    // ----------------------------------------------------------
    /**
     * removes top node
     */
    public void pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Node<E> a = top.next();
        top.split();
        top = a;
        size--;
    }

    // ----------------------------------------------------------
    /**
     * @return top node data
     */
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data();
    }

    // ----------------------------------------------------------
    /**
     * @return true if empty
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
