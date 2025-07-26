package collection.b_queue.queue_array_circle;

/**
 * ClassName: CircleArrayQueueTest
 * Package: collection.b_queue.queue_array_circle
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/7/26 17:22
 * @Version 1.0
 */
public class CircleArrayQueueTest {
    public static void main(String[] args) {

        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(9);
        circleArrayQueue.add(1);
        circleArrayQueue.add(2);
        circleArrayQueue.add(3);
        circleArrayQueue.add(4);
        circleArrayQueue.add(5);
        circleArrayQueue.add(6);
        circleArrayQueue.add(7);
        circleArrayQueue.add(8);
        System.out.println(circleArrayQueue.isFull());
        circleArrayQueue.add(9);
        circleArrayQueue.add(10);
        circleArrayQueue.add(11);
        circleArrayQueue.add(12);

        circleArrayQueue.showQueue();
        System.out.println(circleArrayQueue.getQueue());
        System.out.println(circleArrayQueue.headQueue());
    }
}
