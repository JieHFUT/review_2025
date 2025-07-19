package thread.threadtongbu_3.runnablesafe;

/**
 * ClassName: WindowTest
 * Package: thread.threadtongbu.runnablesafe
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/10/14 22:21
 * @Version 1.0
 */
public class WindowTest {

    public static void main(String[] args) {

        Window runnable = new Window();

        Thread t1 = new Thread(runnable, "窗口一");
        Thread t2 = new Thread(runnable, "窗口二");
        Thread t3 = new Thread(runnable, "窗口三");
        t1.start();
        t2.start();
        t3.start();


    }
}
