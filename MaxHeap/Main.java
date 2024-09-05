package Heap.MaxHeap;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {2, 3, 1, 7, 6, 4, 5};
        MaxHeap heap = new MaxHeap(array);
        while (--heap.size > 0){
            heap.exchange(0, heap.size);
            heap.down(0);
        }
        System.out.println(Arrays.toString(array));
    }
}
