package thread.nomalfuncation_2;

/**
 * ClassName: TestThread
 * Package: thread.nomalfuncation
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/10/13 16:47
 * @Version 1.0
 */
public class TestThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 100 ; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
