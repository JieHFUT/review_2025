package thread.lockanddead_4.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 继承 Thread 类来实现买票系统安全
public class SafeByLock extends Thread {
    static int ticket = 100;

    // 1. 创建Lock的实例，需要确保多个线程共用同一个Lock实例!需要考虑将此对象声明为static final
    private static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true) {
            try{
                // 执行lock方法，锁定对共享资源的调用
                lock.lock();
                if(ticket > 0) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + ticket);
                    ticket--;
                } else {
                    break;
                }
            }finally {
                // 3. unlock()的调用，释放对共享数据的锁定
                lock.unlock();
            }
        }
    }
}
