package deque.LinkedListDeque;

import deque.Deque;

import java.util.Iterator;

public class LinkedListDeque<E> implements Deque<E>, Iterable<E> {
    private final Node<E> sentinel = new Node<>(null, null, null);
    private int size;
    private final int capacity;

    private static class Node<E>{
        Node<E> prev;
        E value;
        Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public boolean offerFirst(E val) {
        if(isFull()) return false;
        sentinel.next = new Node<>(sentinel, val, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        ++size;
        return true;
    }

    @Override
    public boolean offerLast(E val) {
        if(isFull()) return false;
        sentinel.prev = new Node<>(sentinel.prev, val, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        ++size;
        return true;
    }

    @Override
    public E pollFirst() {
        if(isEmpty()) return null;
        Node<E> node = sentinel.next;
        sentinel.next = node.next;
        node.next.prev = sentinel;
        --size;
        return node.value;
    }

    @Override
    public E pollLast() {
        if(isEmpty()) return null;
        Node<E> node = sentinel.prev;
        sentinel.prev = node.prev;
        node.prev.next = sentinel;
        --size;
        return null;
    }

    @Override
    public E peekFirst() {
        if(isEmpty()) return null;
        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if(isEmpty()) return null;
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = sentinel.next;
            @Override
            public boolean hasNext() {
                return node != sentinel;
            }

            @Override
            public E next() {
                E val = node.value;
                node = node.next;
                return val;
            }
        };
    }
}
