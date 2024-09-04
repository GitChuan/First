package Queue.BlockingQueue;

import Queue.Queue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<E> implements Queue<E>, Iterable<E> {
    private final E[] array;
    private int size;
    private int head;
    private int tail;
    private Lock lk = new ReentrantLock();
    private Condition headAwait = lk.newCondition();
    private Condition tailAwait = lk.newCondition();

    @SuppressWarnings("unchecked")
    public ArrayBlockingQueue(int capacity) {
        array = (E[]) new Object[capacity];
    }


    @Override
    public boolean offer(E value) throws InterruptedException {
        lk.lockInterruptibly();
        try{
            while (isFull()){
                tailAwait.await();
            }
            array[tail] = value;
            tail = (tail+1)%array.length;
            ++size;
            headAwait.signal();
        } finally {
            lk.unlock();
        }
        return true;
    }



    public boolean offer(E value, long millisecond) throws InterruptedException {
        lk.lockInterruptibly();
        long nanos = TimeUnit.MILLISECONDS.toNanos(millisecond);
        try{
            while (isFull()){
                if (nanos <= 0){
                    return false;
                }
                nanos = tailAwait.awaitNanos(nanos);
            }
            array[tail] = value;
            tail = (tail+1)%array.length;
            ++size;
            headAwait.signal();
        } finally {
            lk.unlock();
        }
        return true;
    }

    @Override
    public E poll() throws InterruptedException {
        lk.lockInterruptibly();
        try{
            while (isEmpty()){
                headAwait.await();
            }
            E e = array[head];
            array[head] = null;
            head = (head+1) % array.length;
            size--;
            tailAwait.signal();
            return e;
        } finally {
            lk.unlock();
        }
    }

    public E poll(long millisecond) throws InterruptedException{
        lk.lockInterruptibly();
        long nanos = TimeUnit.MILLISECONDS.toNanos(millisecond);
        try{
            while (isEmpty()){
                if(nanos <= 0){
                    return null;
                }
                nanos = headAwait.awaitNanos(nanos);
            }
            E e = array[head];
            array[head] = null;
            head = (head+1) % array.length;
            size--;
            tailAwait.signal();
            return e;
        } finally {
            lk.unlock();
        }
    }
    
    @Override
    public E peek() {
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
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
                return array[p++];
            }
        };
    }

    public String toString(){
        return Arrays.toString(array);
    }
}
