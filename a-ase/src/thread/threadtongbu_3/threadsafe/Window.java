package thread.threadtongbu_3.threadsafe;

/**
 * ClassName: Window
 * Package: thread.threadtongbu.threadsafe
 * Description:
 * 此线程是一个买票窗口，通过继承 Thread 的方法来实现线程 同步代码快的方式来实现同步 synchronized(lock){}
 * @Author jieHFUT
 * @Create 2024/10/14 21:59
 * @Version 1.0
 */
public class Window extends Thread {

    public Window(){}

    public Window(String name) {
        super(name);
    }

    // 一共要卖出去 100 张票
    static int ticket = 100;
    static Object lock = new Object();// 使用该 Object 类可以充当锁

    @Override
    public void run() {
        // 开始买票，通过使用同步代码快的方式来实现同步 synchronized(lock){}
        while (true) {
            // 在这个地方同步监视器不能使用 this, 因为该线程创建的对象是不同实例
            // 可以使用 static 修饰的 Object 类，或者是当前类.class
            synchronized (lock) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ": " + ticket);
                    ticket--;
                }else {
                    break;
                }
            }
        }
    }
}
