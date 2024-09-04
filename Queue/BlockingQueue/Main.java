package Queue.BlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        Thread thread1 = new Thread(()->{
            try {
                System.out.println("begin: " + System.currentTimeMillis());
                queue.offer("任务1");
                System.out.println(queue);
                queue.offer("任务2");
                System.out.println(queue);
                queue.offer("任务3");
                System.out.println(queue);
                queue.offer("任务4", 5000);
                System.out.println(queue);
                System.out.println("end: " + System.currentTimeMillis());
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        });
        thread1.start();

        Thread.sleep(2000);
        queue.poll();
    }
}
