package PriorityQueue;

public class Entry implements Priority {
    String value;
    int priority;

    public Entry(String value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    @Override
    public int Priority() {
        return priority;
    }


}
