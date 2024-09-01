package Stack.LinkedListStack;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<Integer>(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
