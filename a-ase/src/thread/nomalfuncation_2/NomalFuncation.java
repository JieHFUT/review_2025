package thread.nomalfuncation_2;

/**
 * ClassName: NomalFuncation
 * Package: thread.nomalfuncation
 * Description:
 * Compiled: 编译
 * @Author jieHFUT
 * @Create 2024/10/13 16:47
 * @Version 1.0
 */
public class NomalFuncation {
    public static void main(String[] args) {

        TestThread thread = new TestThread();;
        thread.setName("funcationTest");
        thread.start();


        // 在这里调用 sleep() 方法，main 线程会堵塞五秒钟
        try {
            thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }


    }
}
