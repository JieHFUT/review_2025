package thread.threadtongbu_3.runnablesafe;


/**
 * ClassName: Wndow
 * Package: thread.threadtongbu.runnablesafe
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/10/14 22:20
 * @Version 1.0
 */

// 使用同步代码快来实现同步
public class Window implements Runnable {


    int ticket = 100;

    // 下面是可以充当 锁 的类
    Class clazz = Window.class;
    Object object = new Object();

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 下面是使用同步代码快来实现同步
            synchronized(this) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }

    }


}
