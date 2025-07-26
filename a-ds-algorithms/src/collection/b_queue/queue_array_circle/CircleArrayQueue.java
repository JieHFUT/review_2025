package collection.b_queue.queue_array_circle;

/**
 * ClassName: CircleArrayQueue
 * Package: collection.b_queue.queue_array_circle
 * Description:
 * 使用数组模拟实现一个环形队列
 *
 * @Author jieHFUT
 * @Create 2025/7/26 16:28
 * @Version 1.0
 */
public class CircleArrayQueue {

    private int maxSize; // 表示数组的最大容量
    private int front;
    private int rear;
    private int[] array;

    /**
     * 环形队列的构造方法
     * @param maxSize
     */
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }


    // 判断队列是否已满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return (rear == front);
    }

    // 添加数据到队列
    public void add(int value) {
        // 判断数组是否已满了
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        array[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    // 读取数据的队列，出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        int ret = array[front];
        front = (front + 1) % maxSize;
        return ret;
    }

    // 显示队列的所有信息
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        int useSize = useSize();
        for (int i = front; i < front + useSize; i++) {
            System.out.print(array[i % maxSize] + " ");
        }
        System.out.println();
    }

    // 求出当前队列中有效数据的个数
    public int useSize() {
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return array[front];
    }

}
