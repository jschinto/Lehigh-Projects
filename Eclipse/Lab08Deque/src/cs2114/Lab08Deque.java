package cs2114;

public class Lab08Deque<E> extends DoubleLinkDeque<E> {

    public Lab08Deque() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.setNext(tail);
        tail.setPrevious(head);
        size = 0;
    }

    @Override
    public void enqueueAtFront(E value) {
        Node<E> newNode = new Node<E>(value);
        Node<E> currFirst = head.getNext();
        head.setNext(newNode);
        currFirst.setPrevious(newNode);
        newNode.setPrevious(head);
        newNode.setNext(currFirst);
        size++;
    }

    @Override
    public E dequeueAtFront() {
        if (size == 0) {
            throw new IllegalStateException("the deque is empty");
        }
        E value = head.getNext().getData();
        Node<E> currFirst = head.getNext();
        head.setNext(currFirst.getNext());
        currFirst.getNext().setPrevious(head);
        currFirst.setNext(null);
        currFirst.setPrevious(null);
        size--;
        return value;
    }

    @Override
    public void enqueueAtRear(E value) {
        Node<E> newNode = new Node<E>(value);
        Node<E> currLast = tail.getPrevious();
        currLast.setNext(newNode);
        tail.setPrevious(newNode);
        newNode.setPrevious(currLast);
        newNode.setNext(tail);
        size++;
    }

    @Override
    public E dequeueAtRear() {
        if (size == 0) {
            throw new IllegalStateException("the deque is empty");
        }
        E value = tail.getPrevious().getData();
        Node<E> currLast = tail.getPrevious();
        tail.setPrevious(currLast.getPrevious());
        currLast.getPrevious().setNext(tail);
        currLast.setNext(null);
        currLast.setPrevious(null);
        size--;
        return value;
    }

    @Override
    public E frontItem() {
        if (size == 0) {
            throw new IllegalStateException("the deque is empty");
        }
        return head.getNext().getData();
    }

    @Override
    public E rearItem() {
        if (size == 0) {
            throw new IllegalStateException("the deque is empty");
        }
        return tail.getPrevious().getData();
    }

    @Override
    public void clear() {
        head.setNext(tail);
        tail.setPrevious(head);
        size = 0;
    }

    public String toString() {
        String s = "[";
        while (this.size() > 1) {
            s += (this.dequeueAtFront().toString());
            s += (", ");
        }
        if (size == 1) {
            s += (this.dequeueAtFront().toString());
        }
        s += ("]");
        return s;
    }

}
