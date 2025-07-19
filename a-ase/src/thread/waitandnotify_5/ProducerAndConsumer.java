package thread.waitandnotify_5;

/**
 * *       案例2：生产者&消费者
 *  *      生产者 (Productor) 将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，店员一次只能持有
 *  *      固定数量的产品(比如:20），如果生产者试图生产更多的产品，店员会叫生产者停一下，如果店中有空位放产品
 *  *      了再通知生产者继续生产；如果店中没有产品了，店员会告诉消费者等一下，如果店中有产品了再通知消费者来
 *  *      取走产品。
 *  *
 *  *      分析：
 *  *      1. 是否是多线程问题？ 是，生产者、消费者
 *  *      2. 是否有共享数据？有！ 共享数据是：产品
 *  *      3. 是否有线程安全问题？ 有！因为有共享数据
 *  *      4. 是否需要处理线程安全问题？是！ 如何处理？使用同步机制
 *  *      5. 是否存在线程间的通信？ 存在。
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {

        Clerk clerk = new Clerk();

        Producer producer1 = new Producer(clerk);
        Consumer consumer1 = new Consumer(clerk);
//        Consumer consumer2 = new Consumer(clerk);

        producer1.start();
        consumer1.start();
//        consumer2.start();



    }
}
