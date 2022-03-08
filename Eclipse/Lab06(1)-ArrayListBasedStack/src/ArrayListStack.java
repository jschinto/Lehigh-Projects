import java.util.ArrayList;
import java.util.EmptyStackException;

//-------------------------------------------------------------------------
/**
 * An implementation of the stack data type that adapts an ArrayList to store
 * its contents.
 *
 * This is a PARTIAL IMPLEMENTATION that needs completion.
 *
 * @param <T>
 *            the type of elements stored in the stack
 *
 * @author Tony Allevato (authored class skeleton)
 * @author jake
 * @version 2017.03.28
 */
public class ArrayListStack<T> implements SimpleStack<T> {
    // ~ Instance/static variables ............................................

    private ArrayList<T> stack;

    // ~ Constructors .........................................................

    // ----------------------------------------------------------
    /**
     * constructor initializes stack
     */
    public ArrayListStack() {
        stack = new ArrayList<T>();
    }

    // ~ Methods ..............................................................
    /**
     * @param item
     *            item to add
     */
    public void push(T item) {
        stack.add(0, item);
    }

    /**
     * removes the top item
     */
    public void pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        else {
            stack.remove(0);
        }
    }

    /**
     * @return top item
     */
    public T top() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        else {
            return stack.get(0);
        }
    }

    /**
     * @return size of stack
     */
    public int size() {
        return stack.size();
    }

    /**
     * @return if stack empty
     */
    public boolean isEmpty() {
        return (stack.size() == 0);
    }
}
