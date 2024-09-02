package deque.ArrayDeque;

import deque.Deque;

import java.util.Iterator;

public class ArrayDeque1<E> implements Deque<E>, Iterable<E> {
    private final E[] array;
    private int head;
    private int tail;

    private static int subtraction(int i, int length) {
        if (i - 1 < 0) {
            return length - i - 1;
        }
        return i - 1;
    }

    @SuppressWarnings("unchecked")
    public ArrayDeque1(int length) {
        array = (E[]) new Object[length+1];
    }

    @Override
    public boolean offerFirst(E val) {
        if (isFull()) return false;
        head = subtraction(head, array.length);
        array[head] = val;
        return true;
    }

    @Override
    public boolean offerLast(E val) {
        if (isFull()) return false;
        array[tail] = val;
        tail = (tail + 1) % array.length;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) return null;
        E val = array[head];
        array[head] = null;
        head = (head + 1) % array.length;
        return val;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) return null;
        tail = subtraction(tail, array.length);
        E val = array[tail];
        array[tail] = null;
        return val;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) return null;
        return array[head];
    }

    @Override
    public E peekLast() {
        if(isEmpty()) return null;
        return array[subtraction(tail, array.length)];
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % array.length == head;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                return value;
            }
        };
    }
}
