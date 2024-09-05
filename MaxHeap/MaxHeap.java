package Heap.MaxHeap;

import java.util.Iterator;

public class MaxHeap implements Iterable<Integer>{
    private final int[] array;
    public int size;

    public MaxHeap(int capacity) {
        array = new int[capacity];
    }

    public int getSize() {
        return size;
    }

    public MaxHeap(int[] array) {
        this.array = array;
        size = array.length;
        heapify();
    }

    public void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    public void exchange(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * parent always have at least one son(left)
     * @param parent
     */
    public void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;
        if(left < size && array[left] > array[max]){
            max = left;
        }
        if(right < size && array[right] > array[max]){
            max = right;
        }
        if(max != parent){
            exchange(max, parent);
            down(max);
        }
    }

    public boolean offer(int value) {
        if(isFull()) return false;
        up(value);
        size++;
        return true;
    }

    private void up(int added){
        int child = size;
        while (child > 0){
            int parent = (child - 1)/2;
            if(array[parent] < added){
                array[child] = array[parent];
            } else{
                break;
            }
            child = parent;

        }
        array[child] = added;
    }

    public int peek(){
        if(isEmpty()) return -1;
        return array[0];
    }

    public int poll() {
        if(isEmpty()) return -1;
        int e = array[0];
        exchange(0, --size);
        down(0);
        return e;
    }

    public int poll(int index){
        if (isEmpty()) return -1;
        int e = array[index];
        exchange(index, --size);
        down(index);
        return e;
    }

    public void replace(int replaced){
        array[0] = replaced;
        down(0);
    }

    public boolean isFull() {
        return size == array.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int p = 0;
            @Override
            public boolean hasNext() {
                return p < size;
            }

            @Override
            public Integer next() {
                return array[p++];
            }
        };
    }
}
