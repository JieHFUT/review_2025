package collection.b_queue.queue_array;

import java.util.Queue;

/**
 * ClassName: ArrayQueue
 * Package: collection.b_queue.queue_array
 * Description:
 * 使用数组来模拟实现一个队列
 * 队列是一个接口，继承了 Collection 接口
 * public interface Queue<E> extends Collection<E> {
 *     boolean add(E e);     入队列
 *     boolean offer(E e);   入队列
 *     E remove();
 *     E poll();             出队列
 *     E element();
 *     E peek();             获取队头元素
 * }
 *
 * @Author jieHFUT
 * @Create 2025/7/26 15:56
 * @Version 1.0
 */
public class ArrayQueue {

    private int maxSize; // 表示数组的最大容量
    private int front;   // 表示队列的头部索引，出
    private int rear;    // 表示队列的尾部索引，入
    private int[] array; // 表示用于存储元素的底层数组


    /**
     * 创建队列的构造器
     * @param maxSize 用户指定的队列大小
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = -1; // 指向队列头部的前一个位置
        this.rear = -1;  // 指向队列尾部的最后一个数据
        this.array = new int[maxSize];
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 判断队列是否已满
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }


    /**
     * 添加数据到队列
     * @param element
     */
    public void addQueue(int element) {
        // 判断数组是否已满
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        array[++rear] = element;
    }


    /**
     * 获取队列的头部数据，然后出队列
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return array[++front];
    }


    /**
     * 展示队列的所有数据
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        for (int i = front + 1; i <= rear; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }


    /**
     * 显示队列的头部数据，不出队列
     * @return
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return array[front + 1];
    }

















}
