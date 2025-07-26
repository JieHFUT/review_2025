package collection.b_queue.queue_array;

/**
 * ClassName: ArrayQueueTest
 * Package: collection.b_queue.queue_array
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/7/26 16:25
 * @Version 1.0
 */
public class ArrayQueueTest {
    public static void main(String[] args) {

        ArrayQueue queue = new ArrayQueue(3);
        queue.addQueue(3);
        queue.addQueue(4);
        queue.addQueue(5);
        queue.addQueue(6);
        queue.addQueue(7);

        System.out.println("输出头部数据：" + queue.getQueue());
        queue.showQueue();
        System.out.println(queue.headQueue());
    }
}
