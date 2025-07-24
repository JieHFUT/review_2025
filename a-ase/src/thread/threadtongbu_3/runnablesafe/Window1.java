package thread.threadtongbu_3.runnablesafe;

/**
 * ClassName: Window1
 * Package: thread.threadtongbu.runnablesafe
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/10/14 22:57
 * @Version 1.0
 */


// 使用同步方法来实现同步
public class Window1 implements Runnable {
    int ticket = 100;

    Object lock = new Object();
    boolean flag = true;

    @Override
    public void run() {
        // 该线程调用下面的 show() 方法
        while (flag) {
            show();
        }
    }

    // 此时同步监视器是 this, 由于实现 Runnable 接口的线程创建的线程实例都使用同一个 runnable 实例
    public synchronized void show() {
        if (ticket > 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ": " + ticket);
            ticket--;
        } else {
            flag = false;
        }
    }
}
