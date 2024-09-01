package Stack.ArrayStack;

import Stack.Stack;

import java.util.Iterator;

public class ArrayStack<E> implements Stack<E>, Iterable<E> {
    private E[] array;
    private int top;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean push(E value) {
        if(isFull()){
            return false;
        }
        array[top++] = value;
        return true;
    }

    @Override
    public E pop() {
        if(isEmpty()){
            return null;
        }

        return array[--top];
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        return array[top-1];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public boolean isFull() {
        return top == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = top-1;
            @Override
            public boolean hasNext() {
                return p != -1;
            }

            @Override
            public E next() {
                System.out.println("p="+p);
                return array[p--];
            }
        };
    }
}
