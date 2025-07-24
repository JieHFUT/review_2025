package thread.threadtongbu_3.threadsafe;

/**
 * ClassName: Window1
 * Package: thread.threadtongbu.threadsafe
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/10/14 22:43
 * @Version 1.0
 */
public class Window1 extends Thread {

    public Window1() {}
    public Window1(String name) {
        super(name);
    }

    static int ticket = 100;
    static Object lock = new Object();
    static boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            show();
        }
    }

    public synchronized void show1() {} // 此时由于方法是非静态的，同步监视器默认是 this，由于创建的实例不一样，仍然是线程不安全的

    public static synchronized void show() { // 此时由于方法是静态的，同步监视器默认是当前类本身: Window1.class
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
