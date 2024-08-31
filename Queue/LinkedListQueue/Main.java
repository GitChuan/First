package Queue.LinkedListQueue;

public class Main {
    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>(5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        System.out.println(queue.isFull());

    }
}