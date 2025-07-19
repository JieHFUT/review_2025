package thread.threadtongbu_3.runnablesafe;

/**
 * ClassName: Window1Test
 * Package: thread.threadtongbu.runnablesafe
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/10/14 22:57
 * @Version 1.0
 */
public class Window1Test {
    public static void main(String[] args) {
        Window1 runnable = new Window1();
        Thread thread1 = new Thread(runnable, "窗口一");
        Thread thread2 = new Thread(runnable, "窗口二");
        Thread thread3 = new Thread(runnable, "窗口三");

        thread1.start();
        thread2.start();
        thread3.start();


    }
}
