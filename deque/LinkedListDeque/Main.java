package deque.LinkedListDeque;

public class Main {
    public static void main(String[] args) {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>(100);

        deque.offerFirst(1);
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());
    }
}
