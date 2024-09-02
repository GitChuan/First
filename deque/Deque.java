package deque;

public interface Deque<E> {
    boolean offerFirst(E val);

    boolean offerLast(E val);

    E pollFirst();

    E pollLast();

    E peekFirst();

    E peekLast();

    boolean isEmpty();

    boolean isFull();
}
